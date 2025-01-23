import requests,time,random,json,csv,re

HEADERS={
    'User-Agent':"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 Edg/132.0.0.0",
    'Cookie':'buvid3=2F22E2C4-7198-D8CD-F416-5E361F7578F322874infoc; b_nut=1729090922; _uuid=10F1023A7D-BC83-DC3F-3D710-895891BC4F9122688infoc; enable_web_push=DISABLE; buvid_fp=fbccc450219004fd7923507977bd1359; buvid4=4EE7864B-FF2D-0A9B-48D5-8DA7F03081C223909-024101615-cD5dWhOut%2F2y%2F6eALYQOqg%3D%3D; header_theme_version=CLOSE; rpdid=0zbf9caoLN|gQM2fGD|4FtD|3w1T6JRf; CURRENT_QUALITY=80; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mzc1MjM0NzgsImlhdCI6MTczNzI2NDIxOCwicGx0IjotMX0.ZQgiAHfyUW8qkamjG0z8oIqD0aAYxYHfLMYp-f8FKH0; bili_ticket_expires=1737523418; home_feed_column=4; bp_t_offset_402399083=1024419643638415360; browser_resolution=1117-618; CURRENT_FNVAL=4048; b_lsid=5101449AE_19482AA90AC; SESSDATA=1a3ecab3%2C1752912627%2C7b0ae%2A12CjDXmBPKQTzC1WBXwrE3q1fMvQKTUJYkDFgzqlYDZA5VyFT3KnAbLkShj3ZDWKzq4EsSVlI0SzdvNVI4TG02Ni1DRUFRaWwxMXViamFtQTE1cjBYdzNyUTF6bW5ka0V0UkZ4aUxuTC1NbDhEVld5bjMtd1JWVFpNUnY0bU1CV2Y5NlBjdDAwVmhRIIEC; bili_jct=916fe678aa49ee7d9df3b6eefe2cbb20; DedeUserID=402399083; DedeUserID__ckMd5=72707c0c198a767e; sid=67fhizof',
}

def fetch_sub_comments(video_id, root_id):
    url=f'https://api.bilibili.com/x/v2/reply/reply?oid={video_id}&root={root_id}&type=1&sort=1&ps=30'
    sub_comments=[]
    response=requests.get(url,headers=HEADERS,timeout=5)
    if response.status_code==200:
        reply=response.json()
        return reply.get('data',{}).get('replies',[])
    else:
        return []

def fetch_comments(video_id, max_pages=500):#最大页面数量可调整
    comments = []
    last_count = 0
    for page in range(1, max_pages+1):
        url=f'https://api.bilibili.com/x/v2/reply?oid={video_id}&pn={page}&type=1&sort=1'
        try:
            # 添加超时设置
            response = requests.get(url, headers=HEADERS, timeout=10)
            if response.status_code == 200:
                data = response.json()
                print(data)
                if data['data']['replies'] is None:
                    break
                if data and 'replies' in data['data']:
                    for comment in data['data']['replies']:
                        comment_info = {
                            '用户昵称': comment['member']['uname'],
                            '评论内容': comment['content']['message'],
                            '性别': comment['member']['sex'],
                            '用户当前等级': comment['member']['level_info']['current_level'],
                            '点赞数量': comment['like'],
                            '子评论':[]
                        }
                        if int(comment['count'])>0 :
                            root_id=comment['rpid']
                            sub_comments = fetch_sub_comments(video_id, root_id)
                            for sub_comment in sub_comments:
                                sub_comment_info = {
                                    '用户昵称': sub_comment['member']['uname'],
                                    '评论内容': sub_comment['content']['message'],
                                }
                                comment_info['子评论'].append(sub_comment_info)
                        comments.append(comment_info)
                if last_count == len(comments):
                    break
                last_count = len(comments)
            else:
                break
        except requests.RequestException as e:
            print(f"请求出错: {e}")
            break
        # 控制请求频率
        time.sleep(random.uniform(1,3))
    return comments
 
def save_comments_to_json(comments,filename='output'):
    with open(f'{filename}.json','w',encoding='utf-8') as f:
        json.dump(comments,f,ensure_ascii=False,indent=4)


if __name__=="__main__":
    videoBV="BV173qfYcEkF"
    comments = fetch_comments(videoBV)
    save_comments_to_json(comments,f'俄乌战争{videoBV}')
