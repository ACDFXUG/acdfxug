package Java.LeetCode;

public class Z字形变换 {
    static String convert(String s, int numRows) {
        StringBuilder ans=new StringBuilder();
        int len=s.length();
        int col;
        if(len<=numRows||numRows==1){
            return s;
        }else{
            int cnt=(len/((numRows<<1)-2))*(numRows-1);
            int rem=len%((numRows<<1)-2);
            if(rem<=numRows){
                col=cnt+1;
            }else{
                col=cnt+1+rem-numRows;
            }
        }
        char[][] chMatrix=new char[numRows][col];
        int i=0,j=0;
        for(char c:s.toCharArray()){
            chMatrix[i][j]=c;
            if(j%(numRows-1)==0){
                if(i==numRows-1){
                    j++;
                    i--;
                }else{
                    i++;
                }
            }else{
                i--;
                j++;
            }
        }
        for(i=0;i<numRows;i++){
            for(j=0;j<col;j++){
                if(chMatrix[i][j]!='\0') ans.append(chMatrix[i][j]);
            }
        }
        return ans.toString();
    }
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING",3));
    }
}
