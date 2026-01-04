package Java.LeetCode;

public class 游戏中弱角色的数量 {
    private static record Character(int atk,int def)
    implements Comparable<Character>{
        public int compareTo(Character ch){
            if(atk<ch.atk&&def<ch.def) return -1;
            else if(atk>ch.atk&&def>ch.def) return 1;
            else return 0;
        }
        public String toString(){
            return "Ch:["+atk+", "+def+"]";
        }
    }
    static int numberOfWeakCharacters(int[][] properties) {
        Character[] chs=new Character[properties.length];
        for(int i=0;i<properties.length;++i){
            chs[i]=new Character(properties[i][0],properties[i][1]);
        }
        java.util.Arrays.sort(chs);
        int ans=0;
        for(int i=0;i<chs.length-1;++i){
            if(chs[i].compareTo(chs[i+1])==-1) ++ans;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] properties={{5,5},{6,3},{3,6}};
        System.out.println(numberOfWeakCharacters(properties));
    }
}
