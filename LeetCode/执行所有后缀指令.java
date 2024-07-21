package LeetCode;

public class 执行所有后缀指令 {
    static int[] executeInstructions(int n, int[] startPos, String s) {
        int l=s.length();
        int[] ans=new int[l];
        for(int i=0;i<l;++i){
            int x=startPos[0],y=startPos[1];
            for(int j=i;j<l;++j){
                switch(s.charAt(j)){
                    case'L'->--y;
                    case'R'->++y;
                    case'U'->--x;
                    case'D'->++x;
                }
                if(x>=0&&x<n&&y>=0&&y<n){
                    ++ans[i];
                }else{
                    break;
                }
            }
        }
        return ans;      
    }
    public static void main(String[] args) {
        int[] a=executeInstructions(3,new int[]{0,1},"RRDDLU");
        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
