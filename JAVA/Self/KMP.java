package Java.Self;

public class KMP {
    private final char[] str,pat;
    public KMP(String str,String pat){
        this.str=str.toCharArray();
        this.pat=pat.toCharArray();
    }
    int[] nextArray(){
        int[] nxt=new int[pat.length];
        int i=1,j=0;
        nxt[0]=0;
        while(i<pat.length-1){
            if(j==0||pat[i]==pat[j]){
                ++i;++j;
                nxt[i]=j;
            }else{
                j=nxt[j];
            }
        }
        return nxt;
    }

    int KMPIndex(){
        int i=0,j=0;
        int[] nxt=nextArray();
        while(i<str.length&&j<pat.length){
            if(j==0||str[i]==pat[j]){
                ++i;++j;
            }else{
                j=nxt[j];
            }
        }
        if(j==pat.length){
            return i-j;
        }else{
            return -1;
        }
    }
    public static void main(String[] args) {
        KMP kmp=new KMP("ababababab","baba");
        System.out.println(kmp.KMPIndex());
    }
}
