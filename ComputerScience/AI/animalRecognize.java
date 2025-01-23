package ComputerScience.AI;

import java.util.Scanner;

public class animalRecognize {
    // 定义了一个静态常量数组rule，包含了描述不同生物特征的字符串
    static final String[] rule={  
        "有毛发", "有奶", "有羽毛", "会飞", "会下蛋", "吃肉",
        "有犬齿", "有爪", "眼盯前方", "有蹄", "嚼反刍", "黄褐色",
        "身上有暗斑点", "身上有黑色条纹", "有长脖子", "有长腿",
        "不会飞", "会游泳", "有黑白二色", "善飞", "哺乳动物", "鸟",
        "食肉动物", "蹄类动物"
    };
    /**
     * 检查字符串s是否存在于字符串数组r中。
     * 
     * @param s 要检查的字符串
     * @param r 字符串数组，作为查找的范围
     * @return 如果s存在于数组r中，则返回true；否则返回false。
     */
    static boolean contains(String s,String[] r){
        // 遍历字符串数组r
        for(String p:r){
            // 如果当前遍历到的字符串与s相等，则返回true，表示找到了匹配的字符串
            if(s.equals(p)){
                return true;
            }
        }
        // 如果遍历完成后仍未找到匹配的字符串，则返回false
        return false;
    }
    /**
     * 判断给定的动物特征是否表明它是哺乳动物。
     * 
     * @param r 一个字符串数组，包含待检查的动物特征。
     * @return 如果数组中存在至少一个特征表明动物是有毛发或有奶的，则返回true；否则返回false。
     * 
     * 方法通过遍历数组中的每个特征，并使用switch-case结构来检查特定的哺乳动物特征。
     * 特征包括“有毛发”和“有奶”，如果发现其中任何一个特征，则立即确定该动物是哺乳动物并返回true。
     * 如果遍历完所有特征都没有找到哺乳动物的特征，则返回false。
     */
    static boolean isMammal(String[] r){
        for(String t:r){
            switch(t){
                case "有毛发","有奶"->{return true;}
            }
        }
        return false;
    }
    /**
     * 判断是否为鸟的特征。
     * 
     * 本函数通过检查一系列特征来判断某个生物是否符合“鸟”的定义。
     * 特征列表中的任何一个特征如果存在，就足以判定该生物为鸟。
     * 
     * @param r 特征数组，包含一系列描述生物特征的字符串。
     * @return 如果生物具备鸟的特征，则返回true；否则返回false。
     */
    static boolean isBird(String[] r){
        // 遍历特征数组
        for(String t:r){
            // 使用switch语句检查当前特征
            switch(t){
                // 如果特征是“有羽毛”或“会飞”，则认为该生物是鸟
                case "有羽毛","会飞"->{return true;}
            }
        }
        // 如果所有特征都不符合，则认为该生物不是鸟
        return false;
    }
    /**
     * 判断一种动物是否为肉食动物。
     * 通过检查动物的特征来判断，如果动物具备“吃肉”这一特征，则直接判断为肉食动物。
     * 如果动物没有“吃肉”这一特征，但同时具备“有犬齿”、“有爪”和“眼盯前方”这三个特征，则也判断为肉食动物。
     *
     * @param r 动物的特征描述数组，每个元素代表一个特征。
     * @return 如果动物是肉食动物，则返回true；否则返回false。
     */
    static boolean isCarnivore(String[] r){
        // 遍历动物的特征描述数组
        for(String t:r){
            // 判断动物是否具有“吃肉”这一特征
            switch(t){
                case "吃肉"->{return true;}
            }
        }
        // 检查动物是否同时具备“有犬齿”、“有爪”和“眼盯前方”这三个特征
        if(contains("有犬齿", r)&&contains("有爪", r)&&contains("眼盯前方", r)){
            return true;
        }
        // 如果动物既没有“吃肉”的特征，也不同时具备三个肉食特征，则判断为非肉食动物
        return false;
    }
    /**
     * 判断传入的动物特征是否属于有蹄类或反刍类哺乳动物。
     * 有蹄类和反刍类动物都是哺乳动物，这个函数首先检查动物是否是哺乳动物，
     * 然后检查是否具有有蹄或反刍的特征。
     *
     * @param r 字符串数组，包含动物的特征描述。
     * @return 如果动物是哺乳动物且具有有蹄或反刍的特征，则返回true；否则返回false。
     */
    static boolean isUngulates(String[] r){
        // 检查动物是否是哺乳动物且具有有蹄特征
        if(isMammal(r)&&contains("有蹄", r)){
            return true;
        }
        // 检查动物是否是哺乳动物且具有反刍特征
        if(isMammal(r)&&contains("嚼反刍", r)){
            return true;
        }
        // 如果动物不是哺乳动物，或不具有有蹄、反刍特征，则返回false
        return false;
    }
    /**
     * 判断是否为豹子。
     * 该方法通过检查一系列特征来判断一个生物是否符合“豹子”的特征描述。
     * 这些特征包括：哺乳动物、食肉动物、黄褐色、身上有暗斑点。
     * 
     * @param r 一个字符串数组，包含关于生物特征的描述。
     * @return 如果生物符合所有豹子的特征，则返回true；否则返回false。
     */
    static boolean isLeopard(String[] r){
        // 检查是否同时满足哺乳动物、食肉动物、黄褐色、身上有暗斑点这四个特征
        if((isMammal(r)||contains("哺乳动物", r))&&contains("食肉动物", r)&&contains("黄褐色", r)
            &&contains("身上有暗斑点", r)){
                // 如果满足，则输出判断过程和结果
                System.out.println("由毛或奶或哺乳动物得到是哺乳动物");
                System.out.println("由吃肉且有犬齿且有爪且眼盯前方得到是食肉动物");
                System.out.print("由是黄褐色且身上有暗斑点得到");
                return true;
        }
        // 如果不满足，则返回false
        return false;
    }
    /**
     * 判断传入的描述是否符合“老虎”的特征。
     * 老虎的特征包括：哺乳动物、食肉动物、黄褐色、身上有黑色条纹。
     * 
     * @param r 一个字符串数组，包含对某个生物的特征描述。
     * @return 如果描述符合老虎的特征，则返回true；否则返回false。
     */
    static boolean isTiger(String[] r){
        // 判断传入的描述是否同时满足哺乳动物、食肉动物、黄褐色、身上有黑色条纹这四个特征
        if((isMammal(r)||contains("哺乳动物", r))&&contains("食肉动物", r)&&contains("黄褐色", r)
            &&contains("身上有黑色条纹", r)){
                // 如果满足，打印确认信息并返回true
                System.out.println("由毛或奶或哺乳动物得到是哺乳动物");
                System.out.println("由吃肉且有犬齿且有爪且眼盯前方得到是食肉动物");
                System.out.print("由是黄褐色且身上有黑色条纹得到");
                return true;
            }
        // 如果不满足，则返回false
        return false;
    }
    /**
     * 判断给定描述是否符合 giraffe 的特征。
     * 
     * @param r 描述数组，包含关于动物特征的字符串。
     * @return 如果描述符合 giraffe 的特征，则返回 true；否则返回 false。
     */
    static boolean isGiraffe(String[] r){
        // 检查是否同时满足蹄类动物、有长脖子、有长腿和身上有暗斑点的特征
        if((isUngulates(r)||contains("蹄类动物", r))&&contains("有长脖子", r)&&contains("有长腿", r)
        &&contains("身上有暗斑点", r)){
            // 如果满足，打印确认信息并返回 true
            System.out.println("由有蹄或反刍得到是蹄类动物");
            System.out.print("由有长脖子有长腿且有暗斑点得到");
            return true;
        }
        // 如果不满足所有特征，则返回 false
        return false;
    }
    /**
     * 判断是否为斑马。
     * <p>
     * 本函数通过检查给定字符串数组中是否包含特定的描述来判断一个动物是否为斑马。
     * 它首先检查数组是否包含“蹄类动物”或“反刍动物”的描述，然后再检查是否包含“身上有黑色条纹”的描述。
     * 如果两个条件都满足，则判断该动物为斑马。
     * 
     * @param r 字符串数组，包含对动物的描述。
     * @return 如果动物被判断为斑马，则返回true；否则返回false。
     */
    static boolean isZebra(String[] r) {
        // 检查是否包含“蹄类动物”或“反刍动物”描述，并且包含“身上有黑色条纹”的描述
        if ((isUngulates(r) || contains("蹄类动物", r)) && contains("身上有黑色条纹", r)) {
            // 输出判断结果
            System.out.println("由有蹄或反刍得到是蹄类动物");
            System.out.print("由有黑色条纹得到");
            return true;
        }
        return false;
    }
    /**
     * 判断给定的描述是否符合 ostrich（鸵鸟）的特征。
     * 鸵鸟的特点包括：是有羽毛的鸟类、有长脖子、有长腿、不会飞、有黑白二色。
     * 
     * @param r 一个字符串数组，包含对某种动物特征的描述。
     * @return 如果描述符合鸵鸟的特征，则返回 true；否则返回 false。
     */
    static boolean isOstrich(String[] r){
        // 判断描述中是否包含鸵鸟的特点：是有羽毛的鸟类、有长脖子、有长腿、不会飞、有黑白二色。
        // 如果满足所有特征，则认定描述的是鸵鸟。
        if((isBird(r)||contains("鸟", r))&&contains("有长脖子", r)&&contains("有长腿", r)&&
        contains("不会飞", r)&&contains("有黑白二色", r)){
                // 输出判断过程中的逻辑推理
                System.out.println("由有羽毛或会飞且下蛋是鸟");
                System.out.print("由有长脖子有长腿且不会飞且有黑白色得到");
            return true;
        }
        return false;
    }
    /**
     * 判断是否为企鹅。
     * 通过检查一系列特征来判断一个生物是否符合企鹅的描述。这些特征包括：是否是鸟、是否会游泳、是否有黑白二色、是否会飞。
     * 如果所有特征都符合，则认定该生物为企鹅。
     *
     * @param r 一个字符串数组，包含关于生物特征的描述。
     * @return 如果生物符合企鹅的特征，则返回true；否则返回false。
     */
    static boolean isPenguin(String[] r){
        // 检查是否满足企鹅的全部特征：是鸟、会游泳、有黑白二色、不会飞
        if((isBird(r)||contains("鸟", r))&&contains("会游泳", r)&&contains("有黑白二色", r)
        &&contains("不会飞", r)){
            // 如果满足所有特征，则输出判断过程并返回true
            System.out.println("由有羽毛或会飞且下蛋是鸟");
            System.out.print("由会游泳且不会飞且有黑白色得到");
            return true;
        }
        // 如果不满足所有特征，则返回false
        return false;
    }
    /**
     * 判断是否为信天翁。
     * 
     * 信天翁是一类特定的鸟类，此方法通过检查输入数组中是否包含特定的描述来判断一个对象是否符合“信天翁”的定义。
     * 判断条件包括：是鸟类、会飞行、善于飞行。
     * 
     * @param r 一个字符串数组，包含对某个对象的描述。
     * @return 如果描述满足信天翁的条件，则返回true；否则返回false。
     */
    static boolean isAlbatross(String[] r){
        // 检查数组中是否包含“鸟类”或“鸟”字样的描述，并且包含“善飞”的描述
        if((isBird(r)||contains("鸟", r))&&contains("善飞", r)){
            // 如果满足条件，打印确认信息并返回true
            System.out.println("由有羽毛或会飞且下蛋是鸟");
            System.out.print("由善飞得到");
            return true;
        }
        // 如果不满足条件，返回false
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 打印出所有24个时间点的动物规则
        for (int i = 0; i < 24; i++) {
            System.out.printf((i + 1) % 6 != 0 ? "%d:%s " : "%d:%s\n", i + 1, rule[i]);
        }
        
        // 提示用户选择动物特征
        System.out.println("请选择特征");
        String[] index = sc.nextLine().split(" ");
        String[] r = new String[index.length];
        
        // 根据用户输入的特征索引，获取对应的动物规则
        for (int i = 0; i < index.length; i++) {
            r[i] = rule[Integer.parseInt(index[i]) - 1];
        }
        
        // 根据用户选择的特征判断是哪种动物，并打印结果
        if (isLeopard(r)) {
            System.out.println("是金钱豹");
        } else if (isTiger(r)) {
            System.out.println("是老虎");
        } else if (isGiraffe(r)) {
            System.out.println("是长颈鹿");
        } else if (isZebra(r)) {
            System.out.println("是斑马");
        } else if (isOstrich(r)) {
            System.out.println("是鸵鸟");
        } else if (isPenguin(r)) {
            System.out.println("是企鹅");
        } else if (isAlbatross(r)) {
            System.out.println("是信天翁");
        } else {
            System.out.println("不是这几种动物");
        }
        
        // 关闭Scanner资源
        sc.close();
    }
}
