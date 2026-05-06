"""
## Multi-Stage Progressive Image Restoration
## Syed Waqas Zamir, Aditya Arora, Salman Khan, Munawar Hayat, Fahad Shahbaz Khan, Ming-Hsuan Yang, and Ling Shao
## https://arxiv.org/abs/2102.02808
"""

import torch
import torch.nn as nn
import torch.nn.functional as F
from pdb import set_trace as stx

##########################################################################
def conv(in_channels, out_channels, kernel_size, bias=False, stride = 1):
    return nn.Conv2d(
        in_channels, out_channels, kernel_size,
        padding=(kernel_size//2), bias=bias, stride = stride)


##########################################################################
## Channel Attention Layer
class CALayer(nn.Module):
    def __init__(self, channel, reduction=16, bias=False):
        super(CALayer, self).__init__()
        # global average pooling: feature --> point
        self.avg_pool = nn.AdaptiveAvgPool2d(1)
        # feature channel downscale and upscale --> channel weight
        self.conv_du = nn.Sequential(
                nn.Conv2d(channel, channel // reduction, 1, padding=0, bias=bias),
                nn.ReLU(inplace=True),
                nn.Conv2d(channel // reduction, channel, 1, padding=0, bias=bias),
                nn.Sigmoid()
        )

    def forward(self, x):
        y = self.avg_pool(x)
        y = self.conv_du(y)
        return x * y


##########################################################################
## Channel Attention Block (CAB)
class CAB(nn.Module):
    def __init__(self, n_feat, kernel_size, reduction, bias, act):
        super(CAB, self).__init__()
        modules_body = []
        modules_body.append(conv(n_feat, n_feat, kernel_size, bias=bias))
        modules_body.append(act)
        modules_body.append(conv(n_feat, n_feat, kernel_size, bias=bias))

        self.CA = CALayer(n_feat, reduction, bias=bias)
        self.body = nn.Sequential(*modules_body)

    def forward(self, x):
        res = self.body(x)
        res = self.CA(res)
        res += x
        return res

##########################################################################
## Supervised Attention Module
class SAM(nn.Module):
    def __init__(self, n_feat, kernel_size, bias):
        super(SAM, self).__init__()
        self.conv1 = conv(n_feat, n_feat, kernel_size, bias=bias)
        self.conv2 = conv(n_feat, 3, kernel_size, bias=bias)
        self.conv3 = conv(3, n_feat, kernel_size, bias=bias)

    def forward(self, x, x_img):
        x1 = self.conv1(x)
        img = self.conv2(x) + x_img
        x2 = torch.sigmoid(self.conv3(img))
        x1 = x1*x2
        x1 = x1+x
        return x1, img

##########################################################################
## U-Net

class Encoder(nn.Module):
    def __init__(self, n_feat, kernel_size, reduction, act, bias, scale_unetfeats, csff):
        super(Encoder, self).__init__()

        self.encoder_level1 = [CAB(n_feat,                     kernel_size, reduction, bias=bias, act=act) for _ in range(2)]
        self.encoder_level2 = [CAB(n_feat+scale_unetfeats,     kernel_size, reduction, bias=bias, act=act) for _ in range(2)]
        self.encoder_level3 = [CAB(n_feat+(scale_unetfeats*2), kernel_size, reduction, bias=bias, act=act) for _ in range(2)]

        self.encoder_level1 = nn.Sequential(*self.encoder_level1)
        self.encoder_level2 = nn.Sequential(*self.encoder_level2)
        self.encoder_level3 = nn.Sequential(*self.encoder_level3)

        self.down12  = DownSample(n_feat, scale_unetfeats)
        self.down23  = DownSample(n_feat+scale_unetfeats, scale_unetfeats)

        # Cross Stage Feature Fusion (CSFF)
        if csff:
            self.csff_enc1 = nn.Conv2d(n_feat,                     n_feat,                     kernel_size=1, bias=bias)
            self.csff_enc2 = nn.Conv2d(n_feat+scale_unetfeats,     n_feat+scale_unetfeats,     kernel_size=1, bias=bias)
            self.csff_enc3 = nn.Conv2d(n_feat+(scale_unetfeats*2), n_feat+(scale_unetfeats*2), kernel_size=1, bias=bias)

            self.csff_dec1 = nn.Conv2d(n_feat,                     n_feat,                     kernel_size=1, bias=bias)
            self.csff_dec2 = nn.Conv2d(n_feat+scale_unetfeats,     n_feat+scale_unetfeats,     kernel_size=1, bias=bias)
            self.csff_dec3 = nn.Conv2d(n_feat+(scale_unetfeats*2), n_feat+(scale_unetfeats*2), kernel_size=1, bias=bias)

    def forward(self, x, encoder_outs=None, decoder_outs=None):
        enc1 = self.encoder_level1(x)
        if (encoder_outs is not None) and (decoder_outs is not None):
            # 确保尺寸匹配
            enc1_csff = self.csff_enc1(encoder_outs[0])
            dec1_csff = self.csff_dec1(decoder_outs[0])
            if enc1_csff.size() != enc1.size():
                enc1_csff = F.interpolate(enc1_csff, size=enc1.shape[2:], mode='bilinear', align_corners=False)
            if dec1_csff.size() != enc1.size():
                dec1_csff = F.interpolate(dec1_csff, size=enc1.shape[2:], mode='bilinear', align_corners=False)
            enc1 = enc1 + enc1_csff + dec1_csff

        x = self.down12(enc1)

        enc2 = self.encoder_level2(x)
        if (encoder_outs is not None) and (decoder_outs is not None):
            # 确保尺寸匹配
            enc2_csff = self.csff_enc2(encoder_outs[1])
            dec2_csff = self.csff_dec2(decoder_outs[1])
            if enc2_csff.size() != enc2.size():
                enc2_csff = F.interpolate(enc2_csff, size=enc2.shape[2:], mode='bilinear', align_corners=False)
            if dec2_csff.size() != enc2.size():
                dec2_csff = F.interpolate(dec2_csff, size=enc2.shape[2:], mode='bilinear', align_corners=False)
            enc2 = enc2 + enc2_csff + dec2_csff

        x = self.down23(enc2)

        enc3 = self.encoder_level3(x)
        if (encoder_outs is not None) and (decoder_outs is not None):
            # 确保尺寸匹配
            enc3_csff = self.csff_enc3(encoder_outs[2])
            dec3_csff = self.csff_dec3(decoder_outs[2])
            if enc3_csff.size() != enc3.size():
                enc3_csff = F.interpolate(enc3_csff, size=enc3.shape[2:], mode='bilinear', align_corners=False)
            if dec3_csff.size() != enc3.size():
                dec3_csff = F.interpolate(dec3_csff, size=enc3.shape[2:], mode='bilinear', align_corners=False)
            enc3 = enc3 + enc3_csff + dec3_csff
        
        return [enc1, enc2, enc3]

class Decoder(nn.Module):
    def __init__(self, n_feat, kernel_size, reduction, act, bias, scale_unetfeats):
        super(Decoder, self).__init__()

        self.decoder_level1 = [CAB(n_feat,                     kernel_size, reduction, bias=bias, act=act) for _ in range(2)]
        self.decoder_level2 = [CAB(n_feat+scale_unetfeats,     kernel_size, reduction, bias=bias, act=act) for _ in range(2)]
        self.decoder_level3 = [CAB(n_feat+(scale_unetfeats*2), kernel_size, reduction, bias=bias, act=act) for _ in range(2)]

        self.decoder_level1 = nn.Sequential(*self.decoder_level1)
        self.decoder_level2 = nn.Sequential(*self.decoder_level2)
        self.decoder_level3 = nn.Sequential(*self.decoder_level3)

        self.skip_attn1 = CAB(n_feat,                 kernel_size, reduction, bias=bias, act=act)
        self.skip_attn2 = CAB(n_feat+scale_unetfeats, kernel_size, reduction, bias=bias, act=act)

        self.up21  = SkipUpSample(n_feat, scale_unetfeats)
        self.up32  = SkipUpSample(n_feat+scale_unetfeats, scale_unetfeats)

    def forward(self, outs):
        enc1, enc2, enc3 = outs
        dec3 = self.decoder_level3(enc3)

        # 确保尺寸匹配后再进行跳跃连接
        skip_attn2_output = self.skip_attn2(enc2)
        if dec3.size()[2:] != skip_attn2_output.size()[2:]:
            dec3 = F.interpolate(dec3, size=skip_attn2_output.shape[2:], mode='bilinear', align_corners=False)
        x = self.up32(dec3, skip_attn2_output)
        dec2 = self.decoder_level2(x)

        # 同样处理第二层
        skip_attn1_output = self.skip_attn1(enc1)
        if dec2.size()[2:] != skip_attn1_output.size()[2:]:
            dec2 = F.interpolate(dec2, size=skip_attn1_output.shape[2:], mode='bilinear', align_corners=False)
        x = self.up21(dec2, skip_attn1_output)
        dec1 = self.decoder_level1(x)

        return [dec1,dec2,dec3]

##########################################################################
##---------- Resizing Modules ----------    
class DownSample(nn.Module):
    def __init__(self, in_channels,s_factor):
        super(DownSample, self).__init__()
        self.down = nn.Sequential(nn.Upsample(scale_factor=0.5, mode='bilinear', align_corners=False),
                                  nn.Conv2d(in_channels, in_channels+s_factor, 1, stride=1, padding=0, bias=False))

    def forward(self, x):
        x = self.down(x)
        return x

class UpSample(nn.Module):
    def __init__(self, in_channels,s_factor):
        super(UpSample, self).__init__()
        self.up = nn.Sequential(nn.Upsample(scale_factor=2, mode='bilinear', align_corners=False),
                                nn.Conv2d(in_channels+s_factor, in_channels, 1, stride=1, padding=0, bias=False))

    def forward(self, x):
        x = self.up(x)
        return x

class SkipUpSample(nn.Module):
    def __init__(self, in_channels,s_factor):
        super(SkipUpSample, self).__init__()
        self.up = nn.Sequential(nn.Upsample(scale_factor=2, mode='bilinear', align_corners=False),
                                nn.Conv2d(in_channels+s_factor, in_channels, 1, stride=1, padding=0, bias=False))

    def forward(self, x, y):
        x = self.up(x)
        # 确保x和y的尺寸一致，如果不一致则调整x的尺寸到与y相同
        if x.size() != y.size():
            x = F.interpolate(x, size=y.shape[2:], mode='bilinear', align_corners=False)
        x = x + y
        return x

##########################################################################
## Original Resolution Block (ORB)
class ORB(nn.Module):
    def __init__(self, n_feat, kernel_size, reduction, act, bias, num_cab):
        super(ORB, self).__init__()
        modules_body = []
        modules_body = [CAB(n_feat, kernel_size, reduction, bias=bias, act=act) for _ in range(num_cab)]
        modules_body.append(conv(n_feat, n_feat, kernel_size))
        self.body = nn.Sequential(*modules_body)

    def forward(self, x):
        res = self.body(x)
        res += x
        return res

##########################################################################
class ORSNet(nn.Module):
    def __init__(self, n_feat, scale_orsnetfeats, kernel_size, reduction, act, bias, scale_unetfeats, num_cab):
        super(ORSNet, self).__init__()

        self.orb1 = ORB(n_feat+scale_orsnetfeats, kernel_size, reduction, act, bias, num_cab)
        self.orb2 = ORB(n_feat+scale_orsnetfeats, kernel_size, reduction, act, bias, num_cab)
        self.orb3 = ORB(n_feat+scale_orsnetfeats, kernel_size, reduction, act, bias, num_cab)

        self.up_enc1 = UpSample(n_feat, scale_unetfeats)
        self.up_dec1 = UpSample(n_feat, scale_unetfeats)

        self.up_enc2 = nn.Sequential(UpSample(n_feat+scale_unetfeats, scale_unetfeats), UpSample(n_feat, scale_unetfeats))
        self.up_dec2 = nn.Sequential(UpSample(n_feat+scale_unetfeats, scale_unetfeats), UpSample(n_feat, scale_unetfeats))

        self.conv_enc1 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)
        self.conv_enc2 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)
        self.conv_enc3 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)

        self.conv_dec1 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)
        self.conv_dec2 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)
        self.conv_dec3 = nn.Conv2d(n_feat, n_feat+scale_orsnetfeats, kernel_size=1, bias=bias)

    def forward(self, x, encoder_outs, decoder_outs):
        x = self.orb1(x)
        
        # 确保尺寸匹配
        conv_enc1_out = self.conv_enc1(encoder_outs[0])
        conv_dec1_out = self.conv_dec1(decoder_outs[0])
        
        # 调整所有特征图到相同的尺寸
        target_size = x.shape[2:]
        if x.size() != conv_enc1_out.size():
            x = F.interpolate(x, size=target_size, mode='bilinear', align_corners=False)
        if conv_enc1_out.size()[2:] != target_size:
            conv_enc1_out = F.interpolate(conv_enc1_out, size=target_size, mode='bilinear', align_corners=False)
        if conv_dec1_out.size()[2:] != target_size:
            conv_dec1_out = F.interpolate(conv_dec1_out, size=target_size, mode='bilinear', align_corners=False)
            
        x = x + conv_enc1_out + conv_dec1_out

        x = self.orb2(x)
        
        # 确保尺寸匹配
        up_enc1_out = self.up_enc1(encoder_outs[1])
        up_dec1_out = self.up_dec1(decoder_outs[1])
        conv_enc2_out = self.conv_enc2(up_enc1_out)
        conv_dec2_out = self.conv_dec2(up_dec1_out)
        
        # 调整所有特征图到相同的尺寸
        target_size = x.shape[2:]
        if x.size() != conv_enc2_out.size():
            x = F.interpolate(x, size=target_size, mode='bilinear', align_corners=False)
        if conv_enc2_out.size()[2:] != target_size:
            conv_enc2_out = F.interpolate(conv_enc2_out, size=target_size, mode='bilinear', align_corners=False)
        if conv_dec2_out.size()[2:] != target_size:
            conv_dec2_out = F.interpolate(conv_dec2_out, size=target_size, mode='bilinear', align_corners=False)
            
        x = x + conv_enc2_out + conv_dec2_out

        x = self.orb3(x)
        
        # 确保尺寸匹配
        up_enc2_out = self.up_enc2(encoder_outs[2])
        up_dec2_out = self.up_dec2(decoder_outs[2])
        conv_enc3_out = self.conv_enc3(up_enc2_out)
        conv_dec3_out = self.conv_dec3(up_dec2_out)
        
        # 调整所有特征图到相同的尺寸
        target_size = x.shape[2:]
        if x.size() != conv_enc3_out.size():
            x = F.interpolate(x, size=target_size, mode='bilinear', align_corners=False)
        if conv_enc3_out.size()[2:] != target_size:
            conv_enc3_out = F.interpolate(conv_enc3_out, size=target_size, mode='bilinear', align_corners=False)
        if conv_dec3_out.size()[2:] != target_size:
            conv_dec3_out = F.interpolate(conv_dec3_out, size=target_size, mode='bilinear', align_corners=False)
            
        x = x + conv_enc3_out + conv_dec3_out

        return x


##########################################################################
class MPRNet(nn.Module):
    def __init__(self, in_c=1, out_c=1, n_feat=40, scale_unetfeats=20, scale_orsnetfeats=16, num_cab=8, kernel_size=3, reduction=4, bias=False):
        super(MPRNet, self).__init__()

        act=nn.PReLU()
        self.shallow_feat1 = nn.Sequential(conv(in_c, n_feat, kernel_size, bias=bias), CAB(n_feat,kernel_size, reduction, bias=bias, act=act))
        self.shallow_feat2 = nn.Sequential(conv(in_c, n_feat, kernel_size, bias=bias), CAB(n_feat,kernel_size, reduction, bias=bias, act=act))
        self.shallow_feat3 = nn.Sequential(conv(in_c, n_feat, kernel_size, bias=bias), CAB(n_feat,kernel_size, reduction, bias=bias, act=act))

        # Cross Stage Feature Fusion (CSFF)
        self.stage1_encoder = Encoder(n_feat, kernel_size, reduction, act, bias, scale_unetfeats, csff=False)
        self.stage1_decoder = Decoder(n_feat, kernel_size, reduction, act, bias, scale_unetfeats)

        self.stage2_encoder = Encoder(n_feat, kernel_size, reduction, act, bias, scale_unetfeats, csff=True)
        self.stage2_decoder = Decoder(n_feat, kernel_size, reduction, act, bias, scale_unetfeats)

        self.stage3_orsnet = ORSNet(n_feat, scale_orsnetfeats, kernel_size, reduction, act, bias, scale_unetfeats, num_cab)

        self.sam12 = SAM(n_feat, kernel_size=1, bias=bias)
        self.sam23 = SAM(n_feat, kernel_size=1, bias=bias)
        
        self.concat12  = conv(n_feat*2, n_feat, kernel_size, bias=bias)
        self.concat23  = conv(n_feat*2, n_feat+scale_orsnetfeats, kernel_size, bias=bias)
        self.tail     = conv(n_feat+scale_orsnetfeats, out_c, kernel_size, bias=bias)

    def forward(self, x3_img):
        # Original-resolution Image for Stage 3
        H = x3_img.size(2)
        W = x3_img.size(3)

        # Multi-Patch Hierarchy: Split Image into four non-overlapping patches

        # Two Patches for Stage 2
        x2top_img  = x3_img[:,:,0:int(H/2),:]
        x2bot_img  = x3_img[:,:,int(H/2):H,:]

        # Four Patches for Stage 1
        x1ltop_img = x2top_img[:,:,:,0:int(W/2)]
        x1rtop_img = x2top_img[:,:,:,int(W/2):W]
        x1lbot_img = x2bot_img[:,:,:,0:int(W/2)]
        x1rbot_img = x2bot_img[:,:,:,int(W/2):W]

        ##-------------------------------------------
        ##-------------- Stage 1---------------------
        ##-------------------------------------------
        ## Compute Shallow Features
        x1ltop = self.shallow_feat1(x1ltop_img)
        x1rtop = self.shallow_feat1(x1rtop_img)
        x1lbot = self.shallow_feat1(x1lbot_img)
        x1rbot = self.shallow_feat1(x1rbot_img)
        
        ## Process features of all 4 patches with Encoder of Stage 1
        feat1_ltop = self.stage1_encoder(x1ltop)
        feat1_rtop = self.stage1_encoder(x1rtop)
        feat1_lbot = self.stage1_encoder(x1lbot)
        feat1_rbot = self.stage1_encoder(x1rbot)
        
        ## Concat deep features
        # 处理尺寸不匹配的问题
        feat1_top = []
        for k, v in zip(feat1_ltop, feat1_rtop):
            # 确保两个特征图的宽度维度一致
            if k.size()[3] != v.size()[3]:
                max_width = max(k.size()[3], v.size()[3])
                k = F.interpolate(k, size=(k.size()[2], max_width), mode='bilinear', align_corners=False)
                v = F.interpolate(v, size=(v.size()[2], max_width), mode='bilinear', align_corners=False)
            feat1_top.append(torch.cat([k, v], 3))
        
        feat1_bot = []
        for k, v in zip(feat1_lbot, feat1_rbot):
            # 确保两个特征图的宽度维度一致
            if k.size()[3] != v.size()[3]:
                max_width = max(k.size()[3], v.size()[3])
                k = F.interpolate(k, size=(k.size()[2], max_width), mode='bilinear', align_corners=False)
                v = F.interpolate(v, size=(v.size()[2], max_width), mode='bilinear', align_corners=False)
            feat1_bot.append(torch.cat([k, v], 3))
        
        ## Pass features through Decoder of Stage 1
        res1_top = self.stage1_decoder(feat1_top)
        res1_bot = self.stage1_decoder(feat1_bot)

        ## Apply Supervised Attention Module (SAM)
        x2top_samfeats, stage1_img_top = self.sam12(res1_top[0], x2top_img)
        x2bot_samfeats, stage1_img_bot = self.sam12(res1_bot[0], x2bot_img)

        ## Output image at Stage 1
        # 确保stage1_img_top和stage1_img_bot高度一致后拼接
        if stage1_img_top.size()[2] != stage1_img_bot.size()[2]:
            # 调整stage1_img_bot的尺寸以匹配stage1_img_top
            stage1_img_bot = F.interpolate(stage1_img_bot, size=(stage1_img_top.size()[2], stage1_img_bot.size()[3]), 
                                          mode='bilinear', align_corners=False)
        stage1_img = torch.cat([stage1_img_top, stage1_img_bot], 2) 
        ##-------------------------------------------
        ##-------------- Stage 2---------------------
        ##-------------------------------------------
        ## Compute Shallow Features
        x2top  = self.shallow_feat2(x2top_img)
        x2bot  = self.shallow_feat2(x2bot_img)

        ## Concatenate SAM features of Stage 1 with shallow features of Stage 2
        x2top_cat = self.concat12(torch.cat([x2top, x2top_samfeats], 1))
        x2bot_cat = self.concat12(torch.cat([x2bot, x2bot_samfeats], 1))

        ## Process features of both patches with Encoder of Stage 2
        feat2_top = self.stage2_encoder(x2top_cat, feat1_top, res1_top)
        feat2_bot = self.stage2_encoder(x2bot_cat, feat1_bot, res1_bot)

        ## Concat deep features
        feat2 = []
        for k, v in zip(feat2_top, feat2_bot):
            # 确保两个特征图的宽度维度一致
            if k.size()[3] != v.size()[3]:
                max_width = max(k.size()[3], v.size()[3])
                k = F.interpolate(k, size=(k.size()[2], max_width), mode='bilinear', align_corners=False)
                v = F.interpolate(v, size=(v.size()[2], max_width), mode='bilinear', align_corners=False)
            feat2.append(torch.cat([k, v], 2))

        ## Pass features through Decoder of Stage 2
        res2 = self.stage2_decoder(feat2)

        ## Apply SAM
        x3_samfeats, stage2_img = self.sam23(res2[0], x3_img)


        ##-------------------------------------------
        ##-------------- Stage 3---------------------
        ##-------------------------------------------
        ## Compute Shallow Features
        x3     = self.shallow_feat3(x3_img)

        ## Concatenate SAM features of Stage 2 with shallow features of Stage 3
        # 确保x3和x3_samfeats尺寸一致
        if x3.size() != x3_samfeats.size():
            x3_samfeats = F.interpolate(x3_samfeats, size=x3.shape[2:], mode='bilinear', align_corners=False)
        x3_cat = self.concat23(torch.cat([x3, x3_samfeats], 1))
        
        x3_cat = self.stage3_orsnet(x3_cat, feat2, res2)

        stage3_img = self.tail(x3_cat)

        # 确保stage3_img和原始输入x3_img尺寸一致后再相加
        if stage3_img.size() != x3_img.size():
            stage3_img = F.interpolate(stage3_img, size=x3_img.shape[2:], mode='bilinear', align_corners=False)
        
        return stage3_img+x3_img
