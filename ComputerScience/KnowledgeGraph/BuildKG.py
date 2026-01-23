import re
import csv

# ==============================
# Step 1: 硬编码所有实体（从文本中提取并整理）
# ==============================

# 枪械实体（从目录和正文提取，去重并标准化）
gun_entities = [
    "S&W 465v 左轮手枪", "蟒蛇左轮手枪", "M1911A1军用手枪", "BREN TEN手枪",
    "Cougar 8000手枪", "勃朗宁BDM手枪", "马努汉 左轮手枪", "Taurus左轮手枪系列",
    "M、S系列手枪", "GLOCK系列手枪", "PIERFER ZELISKA左轮手枪", "TUMA MTE-224手枪",
    "SIG SAUER P220系列手枪", "SIG SAUER P226手枪", "WIST-94半自动手枪",
    "PM MAKAROV手枪", "SPP-1水下手枪", "斯捷奇金APS手枪", "PSM手枪",
    "Yarugin PYA 手枪", "MP-448 Skyph手枪", "P×4 Storm风暴手枪", "93R 冲锋手枪",
    "伯莱塔系列手枪", "FIVE-SEVEN 57式手枪", "Forty-nine手枪", "MK23 MOD 0特种作战手枪",
    "HK P7M8手枪", "P11水下手枪", "HK USP手枪", "P99 半自动手枪", "FN-P手枪",
    "勃朗宁大威力HP手枪", "CZ 75系列手枪", "沙漠之鹰手枪", "柯尔特 M1911 手枪",
    "AEK P1PV 冲锋枪", "PP2000冲锋枪", "SR-2冲锋枪", "PP-19 野牛冲锋枪",
    "Goblin折叠冲锋枪", "KRISS Supper V冲锋枪", "B＆T MP9冲锋枪", "卢萨 A2 冲锋枪",
    "PM12S冲锋枪", "幽灵 冲锋枪", "UMP9 冲锋枪", "MP5 冲锋枪", "MP7 冲锋枪",
    "瓦尔特MPK冲锋枪", "MSMC 冲锋枪", "PM98/PM06 冲锋枪", "P90 冲锋枪",
    "美蓓亚M8 冲锋枪", "UZI 微型冲锋枪", "SHIPKA 冲锋枪", "麦德森M/50 冲锋枪",
    "蝎式 冲锋枪", "JACKHAMMER自动散弹枪", "SPAS-15多功能散弹枪", "USAS-12自动散弹枪",
    "FP-6 泵动散弹枪", "Martial紧凑型散弹枪", "S.A.T 8半自动散弹枪",
    "SDASS泵动散弹枪", "M1系列散弹枪", "M3散弹枪", "M4 SUPER90战斗散弹枪",
    "NOVA散弹枪", "SPAS-12多功能散弹枪", "RMB-93泵动散弹枪",
    "SAIGA-20半自动散弹枪", "SAIGA-410半自动散弹枪", "Neostead散弹枪",
    "MAG-7散弹枪", "打击者 散弹枪", "M16突击步枪", "Remington ACR突击步枪",
    "M4卡宾枪", "ADS两栖用步枪", "A-91M突击步枪", "OC-11紫杉树突击步枪",
    "OC-14Groza突击步枪", "APS水下突击步枪", "ASM-DT两栖突击步枪",
    "SR-3VIKHR小型突击步枪", "9A-91小型突击步枪", "AEK-971突击步枪",
    "AK-47/AKS-47突击步枪", "AN94突击步枪", "AKM、AKS突击步枪",
    "CORNER SHOT拐角射击系统", "HEZI TM SM-1卡宾枪", "Tavor突击步枪",
    "AUG突击步枪", "Magal卡宾枪", "Styeler ACR先进突击步枪",
    "CZ 805 BREN突击步枪", "FAMAS突击步枪", "FNC突击步枪", "Scar突击步枪",
    "G3自动步枪", "HK416/HKM4突击步枪", "FG-42伞兵步枪", "G36突击步枪",
    "R×4 Storm突击步枪", "ARX-160突击步枪", "C×4 Storm卡宾枪",
    "SG 55X系列突击步枪", "L85型突击步枪", "Valmet M82突击步枪",
    "Vepr野猪突击步枪", "50HS狙击步枪", "SSG69狙击步枪", "RANGE MASTER狙击步枪",
    "AMSD OM50 Neimesis狙击步枪", "Ashbury ASW338 狙击步枪", "BA 50 狙击步枪",
    "BARRETT M82A1 狙击步枪", "CHEYTAC 狙击步枪", "DTA SRS 狙击步枪",
    "REMINGTON MSR 狙击步枪", "M96 狙击步枪", "TAC 50狙击步枪",
    "M14/M21狙击步枪", "TRUVELO 狙击步枪", "NTW-20 反器材步枪",
    "XM2010 ESR 狙击步枪", "SR-100 狙击步枪", "Blaser LRS2 狙击步枪",
    "DSR 狙击步枪", "WA 2000 狙击步枪", "SV-98 狙击步枪", "SV-99 狙击步枪",
    "SVD 狙击步枪", "SVDS 狙击步枪", "TIKKA T3 狙击步枪", "RT-20 反器材步枪",
    "CZ 750 S1 M1狙击步枪", "FALCON 狙击步枪", "FR-F2 狙击步枪",
    "ULTIMA RATIO 狙击步枪", "PRG-1 狙击步枪", "AR-15 狙击步枪",
    "德拉贡诺夫狙击步枪", "P90 冲锋枪", "HK416 突击步枪",
    "AKM 突击步枪"
]

# 国家
countries = [
    "美国", "前苏联", "俄罗斯", "德国", "意大利", "比利时", "奥地利", "瑞士", "法国", "以色列",
    "捷克", "乌克兰", "南非", "葡萄牙", "日本", "保加利亚", "克罗地亚", "芬兰", "巴西", "英国",
    "丹麦", "西班牙", "挪威", "荷兰", "加拿大", "希腊", "澳大利亚", "新西兰", "埃及", "罗马尼亚",
    "塞尔维亚", "突尼斯", "印度", "匈牙利", "瑞典", "土耳其", "波兰", "中国", "韩国"
]

# 制造商/公司
companies = [
    "史密斯·威森公司", "柯尔特公司", "多诺斯＆迪克逊企业公司", "斯托戈公司", "勃朗宁轻武器公司",
    "马努汉公司", "公牛公司", "斯太尔·曼利夏公司", "格洛克有限公司", "PEIRFER公司",
    "MARTIN TUMA ENGINEERING", "西格公司", "军备部的军事技术协会", "赫克勒-科赫公司",
    "卡尔·瓦尔特公司", "国营赫斯塔尔公司", "伊热夫斯克机械厂", "伊孜玛什公司",
    "雷明顿公司", "KBP仪器设计局", "科若库基础机械设计局", "弗兰齐公司", "伯奈利公司",
    "Armsel公司", "Corner Shot Holdings", "先进战斗系统公司", "萨科-瓦尔梅特公司",
    "PRA国际防护产品公司", "麦克米兰公司", "岩岛兵工厂", "特维洛公司", "埃罗泰柯公司",
    "埃尔玛公司", "布莱瑟尔公司", "AMP技术服务公司", "RH-Alan公司", "zVI公司", "PGM精密武器公司",
    "潘科公司", "吉尔伯特设备有限公司", "费巴尔姆公司", "大宇精密工业公司", "赛茨公司",
    "国家军品工业公司", "恩菲尔德皇家兵工厂", "EDM武器公司", "沙漠战术轻武器公司",
    "中央精密机械研究所", "图拉兵工厂", "奥伯杜瓦特公司"
]

# 口径/弹药
calibers = [
    "9×19mm", ".45 ACP", "5.7×28mm", "7.62×51mm", ".50 BMG", "9×39mm", "5.45×18mm",
    ".338 Lapua", "20×110mm", "12号", ".44 Magnum", ".357 Magnum", "7.62×39mm",
    "5.56×45mm", "7.62×54R", ".308 Win", ".300 Win Mag", "9×21mm", ".40 S&W",
    "12.7×108mm", "5.45×39mm", ".22 LR", "7.62×25mm", ".410 GA", ".460 Steyr",
    "6.8mm", "6.5mm", "7.92×57mm", "7.62×33mm", "5.45×39 PSP", "9×18mm PM",
    "9×18mm PMM", "7N6", "MGTS", "SP-5", "SP-6", "VOG-25", "M193", "SS109", "M855"
]

# 部队/机构
orgs = [
    "GIGN", "SAS", "三角洲突击队", "FBI", "CIA", "海豹突击队", "俄罗斯联邦安全局", "USSOCOM",
    "GSG9", "SEK", "MEK", "苏格兰场", "MIG-5", "GIPN", "RAID", "GEO", "Cobra GEK",
    "美军", "北约", "联合国", "KSK"
]

# 部件/属性
components = [
    "枪管", "弹匣", "瞄准具", "枪托", "膛线", "消声器", "榴弹发射器", "两脚架", "复进簧",
    "击针", "保险装置", "扳机", "套筒", "机匣", "护木", "握把", "照门", "准星", "抛壳口",
    "活塞", "导气箍", "制退器", "消焰器", "缓冲垫", "贴腮板", "拉机柄", "快慢机", "刺刀"
]

# 技术术语
tech_terms = [
    "自由后坐式", "导气式", "滚柱闭锁", "无托结构", "半自动", "全自动", "点射", "浮置式枪管",
    "模块化设计", "包络式枪机", "闭膛待击", "开膛待击", "惯性后坐", "吹气式", "短行程活塞",
    "长行程活塞", "旋转后拉式枪机", "双动击发", "单动击发", "平衡动作原理", "戴维斯原理",
    "勃朗宁闭锁", "枪管偏移式", "枪管短后坐"
]

# 合并所有实体
all_entities = set()
all_entities.update(gun_entities)
all_entities.update(countries)
all_entities.update(companies)
all_entities.update(calibers)
all_entities.update(orgs)
all_entities.update(components)
all_entities.update(tech_terms)

# 清理无效项
all_entities = {e for e in all_entities if len(e) >= 2 and not re.search(r'[0-9]{4}|indd|Empty|Page', e)}

print(f"✅ 总实体数: {len(all_entities)}")

# ==============================
# Step 2: 从文本中抽取三元组（规则匹配）
# ==============================

# 读取文本内容（模拟从文件读取）
text = ""

with open("cctv 经典枪械完全图解手册.txt", "r", encoding="utf-8") as f:
    text = f.read()

# 分页并合并正文
pages = re.split(r"===Page \d+===", text)
content = "\n".join(pages).strip()
lines = [line.strip() for line in content.split("\n") if line.strip()]

# 构建映射集合（用于快速匹配）
country_set = set(countries)
company_set = set(companies)
caliber_set = set(calibers)
gun_set = set(gun_entities)

# 参数关键词
param_keys = {
    "全枪长", "枪管长", "全枪重", "空枪重", "弹匣容量", "口径", "子弹规格",
    "初速", "枪口动能", "理论射速", "有效射程", "膛线", "瞄准基线长", "射速",
    "表尺射程", "枪口初速", "战斗全重", "分解后最大长度", "缠距"
}

# 正则匹配参数
param_pattern = re.compile(r"([\u4e00-\u9fa5a-zA-Z0-9\-·\.\/]+)\s*[:：]\s*([^。\n]+)")

triples = []
current_gun = None

for line in lines:
    # 跳过无效行
    if any(skip in line for skip in ["经典枪械完全图解手册", "军事书.indd", "内容提要", "Empty Page", "枪械.indd", "目录"]):
        continue

    # 检测当前枪械（精确匹配）
    for gun in gun_set:
        if gun in line and len(gun) > 4:
            current_gun = gun
            break

    if not current_gun:
        continue

    # 抽取国家
    for c in country_set:
        if c in line and "公司" not in line:
            triples.append((current_gun, "国家", c))

    # 抽取制造商
    for comp in company_set:
        if comp in line:
            triples.append((current_gun, "制造商", comp))

    # 抽取口径（上下文含“口径”或“子弹规格”）
    for cal in caliber_set:
        if cal in line and ("口径" in line or "子弹规格" in line or "发射" in line):
            triples.append((current_gun, "口径", cal))

    # 抽取参数
    matches = param_pattern.findall(line)
    for key, value in matches:
        key_clean = key.strip().replace(" ", "")
        if key_clean in param_keys:
            value_clean = value.strip().rstrip("。")
            triples.append((current_gun, key_clean, value_clean))

# 去重
triples = list(set(triples))
print(f"✅ 总三元组数: {len(triples)}")

# ==============================
# Step 3: 保存为 CSV
# ==============================

# entities.csv
with open("entities.csv", "w", encoding="utf-8", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["Entity"])
    for e in sorted(all_entities):
        writer.writerow([e])

# triples.csv
with open("triples.csv", "w", encoding="utf-8", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["Subject", "Predicate", "Object"])
    for s, p, o in sorted(triples):
        writer.writerow([s, p, o])

print("✅ 已生成 entities.csv 和 triples.csv")