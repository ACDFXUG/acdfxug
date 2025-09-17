package JAVA.LeetCode;

import java.util.*;

public class 统计匹配检索规则的物品数量 {
    static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        return items.stream().filter(L->
            ruleKey.equals("type")&&L.get(0).equals(ruleValue)
            ||ruleKey.equals("color")&&L.get(1).equals(ruleValue)
            ||ruleKey.equals("name")&&L.get(2).equals(ruleValue)
        ).mapToInt(_->1).sum();
    }
}
