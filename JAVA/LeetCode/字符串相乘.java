package Java.LeetCode;

public class 字符串相乘 {
    static String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        int[] res=new int[num1.length()+num2.length()];
        for(int i=num1.length()-1;i>=0;i--){
            for(int j=num2.length()-1;j>=0;j--){
                res[i+j+1]+=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                res[i+j]+=res[i+j+1]/10;
                res[i+j+1]%=10;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<res.length;i++){
            if(i==0&&res[i]==0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(multiply("555","666"));
    }
}
