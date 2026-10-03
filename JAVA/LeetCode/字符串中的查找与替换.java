package Java.LeetCode;

public class 字符串中的查找与替换 {
    static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        String[] rep=new String[s.length()],split=s.chars()
            .mapToObj(c->String.valueOf((char)c))
            .toArray(String[]::new);
        for(int i=0;i<indices.length;++i){
            int indic=indices[i];
            String source=sources[i];
            String target=targets[i];
            if(s.indexOf(source,indic)==indic){
                split[indic]=target;
                rep[indic]=source;
            }
        }
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<split.length;){
            ans.append(split[i]);
            i+=rep[i]==null?1:rep[i].length();  
        }
        return ans.toString();
    }
    public static void main(String[] args) {
        String s="abcd";
        int[] indices={0,2};
        String[] sources={"a","cd"},targets={"eee","ffff"};
        System.out.println(findReplaceString(s, indices, sources, targets));
    }
}
