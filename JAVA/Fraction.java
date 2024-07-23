package JAVA;

// import java.math.BigDecimal;
// import java.math.BigInteger;
// import java.math.RoundingMode;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.Comparator;
import java.util.*;
import java.math.*;
import java.util.regex.*;

public class Fraction extends Number implements Comparable<Fraction>,Cloneable{
    /**
     * 声明了两个长整型的变量，分别用来存储分子和分母。其中up是分子，low是分母，且分母不为0
     * <p>
     * {@code up 分子} 
     * <p>
     * {@code low 分母}
     */
    private long up,low;
    
    public static final Fraction ONE=new Fraction(1);
    public static final Fraction ZERO=new Fraction(0);
    public static final Fraction HALF=new Fraction(1,2);
    public static final Fraction THIRD=new Fraction(1,3);
    public static final Fraction FOURTH=new Fraction(1,4);
    public static final Fraction TEN=new Fraction(10);
    public static final Fraction TWO=new Fraction(2);
    public static final Fraction THREE=new Fraction(3);
    public static final Fraction FIVE=new Fraction(5);
    public static final Fraction POSITIVE_INFINITY=new Fraction(1, 0);
    public static final Fraction NEGATIVE_INFINITY=new Fraction(-1, 0);
    public static final Fraction NAN=new Fraction(0,0);


    /**
     * 定义一个比较器，用于比较两个分数对象的大小。
     * 这个比较器遵循小于（less）的逻辑，即返回第一个分数小于第二个分数时的负整数，
     * 当两个分数相等时返回0，当第一个分数大于第二个分数时返回正整数。
     * 从小到大排序
     * @param f1 第一个Fraction对象，作为比较的基准。
     * @param f2 第二个Fraction对象，与第一个对象进行比较。
     * @return 返回比较结果，当f1小于f2时返回负数，相等时返回0，大于时返回正数。
     */
    public static final Comparator<Fraction> LESS=(f1,f2)->compare(f1,f2);
    /**
     * 定义一个比较器，用于以相反的顺序比较两个分数对象。
     * 这个比较器使得第一个传入的分数对象被认为是“较小的”。
     * 从大到小排序
     * @param f1 第一个分数对象，作为比较的基准。
     * @param f2 第二个分数对象，与f1进行比较。
     * @return 返回比较结果，如果f2大于f1，则返回正数；如果f2小于f1，则返回负数；如果两者相等，则返回0。
     */
    public static final Comparator<Fraction> GREATER=(f1,f2)->compare(f2,f1);
    /**
     * 计算两个长整型数的最大公约数（GCD）。
     * 使用欧几里得算法的优化版本，通过不断位移和减法来减少计算量。
     * 
     * @param a 第一个长整型数
     * @param b 第二个长整型数
     * @return a 和 b 的最大公约数
     */
    private static final long GCD(long a,long b){
        // 交换变量，确保 a 总是大于等于 b
        long shift=0;
        if(a<b){
            a^=b;
            b^=a;
            a^=b;
        }
        // b 等于 0 时，a 就是最大公约数
        if(b==0){
            return 0;
        }
        // 循环直到找到最大公约数
        for(;a!=b;){
            // 当 a 和 b 都是奇数时，b=(a-b)>>1，a-=b
            if((a&1)==1){
                if((b&1)==1){
                    b=(a-b)>>1;
                    a-=b;
                }else{
                    // 当 a 是奇数，b 是偶数时，只将 b 右移一位
                    b>>=1;
                }
            }else{
                // 当 a 是偶数时
                if((b&1)==1){
                    // 当 b 是奇数时， a 右移一位，如果 a<b，则交换 a 和 b
                    a>>=1;
                    if(a<b){
                        a^=b;
                        b^=a;
                        a^=b;
                    }
                }else{
                    // 当 a 和 b 都是偶数时，a 和 b 都右移一位，fac 记录右移次数
                    a>>=1;
                    b>>=1;
                    shift++;
                }
            }
        }
        // 最大公约数左移 fac 位返回
        return a<<shift;
    }
    private static final long gcd(long a,long b){
        return GCD(Math.abs(a),Math.abs(b));
    }
    private static long power(long a,long b){
        long ans=1;
        for(int i=1;i<=b;i++){
            ans*=a;
        }
        return ans;
    }
    public Fraction(){
        this(0,1);
    }
    public Fraction(long a,long b){
        if(a<0&&b<0){
            a=-a;
            b=-b;
        }
        this.up=a;
        this.low=b;
    }
    public Fraction(long integer){
        this(integer,1);
    }
    public Fraction(String _f){
        this(valueOf(_f).up,valueOf(_f).low);
    }
    public long getUp(){
        return up;
    }
    public static long getUp(Fraction x){
        return x.up;
    }
    public long getLow(){
        return low;
    }
    public static long getLow(Fraction x){
        return x.low;
    }
    public Fraction setUp(long u){
        up=u;
        return this;
    }
    public Fraction setLow(long l){
        low=l;
        return this;
    }
    public Fraction set(long u,long l){
        return setUp(u).setLow(l);
    }
    public long getGcd(){
        return gcd(up,low);
    }
    public static long getGcd(Fraction x){
        return gcd(x.up,x.low);
    }
    public long getInt(){
        return up/low;
    }
    public static long getInt(Fraction x){
        return x.getInt();
    }
    public String getFloat(){
        return subtract(new Fraction(up/low)).toRational();
    }
    public static String getFloat(Fraction x){
        return x.getFloat();
    }
    public void formatPrintln(){
        if(up%low!=0){
            int l=Long.toString(up).length();
            String line="-".repeat(l);
            System.out.printf("%d\n%s\n%d\n",up,line,low);
        }else{
            System.out.println(up/low);
        }
    }
    public int intValue(){
        return (int)(up/low);
    }
    public long longValue(){
        return up/low;
    }
    public float floatValue(){
        return up*1.0F/low;
    }
    public double doubleValue(){
        return up*1.0/low;
    }
    public BigDecimal toBigDecimal(int floatScale,RoundingMode rm){
        return new BigDecimal(up).divide(new BigDecimal(low),floatScale,rm);
    }
    public Fraction allDividedBy(long x){
        return new Fraction(up/x,low/x);
    }
    public Fraction allMultiply(long x){
        return new Fraction(up*x,low*x);
    }
    public Fraction toLowest(){
        return allDividedBy(gcd(up, low));
    }
    public static Fraction toLowest(Fraction x){
        return x.toLowest();
    }
    public Fraction clone(){
        return new Fraction(up,low);
    }
    public Fraction add(Fraction y){
        return toLowest(new Fraction(up*y.low+y.up*low,low*y.low));
    }
    public Fraction addEqual(Fraction y){
        Fraction add=add(y);
        up=add.up;
        low=add.low;
        return this;
    }
    public Fraction subtract(Fraction y){
        return toLowest(new Fraction(up*y.low-y.up*low,low*y.low));
    }
    public Fraction subtractEqual(Fraction y){
        Fraction subtract=subtract(y);
        up=subtract.up;
        low=subtract.low;
        return this;
    }
    public Fraction multiply(Fraction y){
        return toLowest(new Fraction(up*y.up,low*y.low));
    }
    public Fraction multiplyEqual(Fraction y){
        Fraction multiply=multiply(y);
        up=multiply.up;
        low=multiply.low;
        return this;
    }
    public Fraction divide(Fraction y){
        return toLowest(new Fraction(up*y.low,low*y.up));
    }
    public Fraction divideEqual(Fraction y){
        Fraction divide=divide(y);
        up=divide.up;
        low=divide.low;
        return this;
    }
    public Fraction power(int n){
        Fraction ans=ONE;
        for(int i=1;i<=n;i++){
            ans=ans.multiply(this);
        }
        return ans;
    }
    public Fraction powerEqual(int n){
        Fraction t=power(n);
        up=t.up;
        low=t.low;
        return this;
    }
    public static Fraction power(Fraction x,int n){
        return x.power(n);
    }
    private boolean isGreaterThanZero(){
        return up*low>0;
    }
    /**
     * 比较当前分数对象与另一个分数对象的大小。
     * 
     * @param y 另一个分数对象，作为比较的目标。
     * @return 返回一个整数，表示两个分数的大小关系。如果当前分数等于 y，则返回0；如果当前分数大于 y，则返回1；如果当前分数小于 y，则返回-1。
     */
    public int compareTo(Fraction y){
        // 比较两个分数的乘积，用于判断分数的大小关系
        if(up*y.low==low*y.up){
            return 0; // 如果两个分数相等，返回0
        }else if(subtract(y).isGreaterThanZero()){
            return 1; // 如果当前分数减去y后的结果大于0，说明当前分数大于y，返回1
        }else{
            return-1; // 其他情况下，当前分数小于y，返回-1
        }
    }
    public static int compare(Fraction x,Fraction y){
        return x.compareTo(y);
    }
    public boolean equals(Object y){
        if(this==y){
            return true;
        }
        if(y==null||this==null){
            return false;
        }
        return y instanceof Fraction&&
        up*((Fraction)y).low==low*((Fraction)y).up;
    }
    public static boolean equals(Fraction x,Fraction y){
        return x.equals(y);
    }
    public boolean isLess(Fraction y){
        return compareTo(y)==-1;
    }
    public boolean isGreater(Fraction y){
        return compareTo(y)==1;
    }
    public int hashCode(){
        int result=17;
        long gcd=gcd(up,low);
        result=31*result+Long.hashCode(up/gcd);
        result=31*result+Long.hashCode(low/gcd);
        return result;
    }
    /**
     * 将当前对象的高和低数值转换为字符串表示。
     * 如果高（up）除以低（low）的结果不是整数，则以分数形式返回，如"3/2"。
     * 如果结果是整数，则以该整数的字符串形式返回，如"5"。
     * 
     * @return 表示当前对象数值的字符串。如果结果为分数，则形式为"up/low"；如果结果为整数，则为整数的字符串形式。
     */
    public String toString(){
        // 判断高和低数值相除是否为整数
        if(up%low!=0)
        return up+"/"+low; // 以分数形式返回
        else
        return Long.toString(up/low); // 以整数形式返回
    }
    /**
     * 将字符串表示的浮点数转换为分数形式。
     * 此方法支持两种类型的字符串输入：常规浮点数和特殊格式的浮点数。
     * 特殊格式浮点数采用 "整数.小数(循环)" 或 "整数.(循环)" 的形式，其中小数部分会被转换为一个分数来更精确地表示。
     * 
     * @param ratio 待转换的浮点数字符串。可以是常规浮点数字符串，也可以是特殊格式的浮点数字符串。
     *              例如："1.23", "1.(23)", "1.23(45)"。
     * @return 转换后的分数对象。返回一个Fraction对象，代表原本的浮点数转换后的分数形式。
     */
    public static Fraction parseFraction(String ratio) throws NumberFormatException{
        String reg1="([+-]?\\d+)\\.(\\d+)\\((\\d+)\\)";
        String reg2="([+-]?\\d+)\\.\\((\\d+)\\)";
        String reg3="([+-]?\\d+)\\.(\\d+)";
        // 使用正则表达式匹配输入字符串，以确定其格式
        Matcher mat1 = Pattern.compile(reg1).matcher(ratio);
        Matcher mat2 = Pattern.compile(reg2).matcher(ratio);
        Matcher mat3 = Pattern.compile(reg3).matcher(ratio);
        long signum=ratio.startsWith("-")?-1:1;
        if(mat1.matches()){
            // 匹配到了"整数.小数(循环)"格式的浮点数
            // 解析整数部分、小数部分和循环小数部分，并计算其分数表示
            BigInteger a=new BigInteger(mat1.group(1));
            BigInteger b=new BigInteger(mat1.group(2));
            BigInteger c=new BigInteger(mat1.group(3));
            int m=mat1.group(2).length();
            int n=mat1.group(3).length();

            BigInteger UP=BigInteger.ZERO,LOW=BigInteger.ONE;
            UP=UP.add(a);
            UP=UP.multiply(BigInteger.TEN.pow(m)).add(b);
            LOW=BigInteger.TEN.pow(m);
            UP=UP.multiply(BigInteger.TEN.pow(m+n).subtract(BigInteger.TEN.pow(m)))
            .add(c.multiply(LOW));
            LOW=LOW.multiply(BigInteger.TEN.pow(m+n).subtract(BigInteger.TEN.pow(m)));
            BigInteger gcd=UP.gcd(LOW);
            UP=UP.divide(gcd);
            LOW=LOW.divide(gcd);

            return new Fraction(UP.longValue()*signum,LOW.longValue());
        } else if(mat2.matches()){
            // 匹配到了"整数.(循环)"格式的浮点数
            // 解析整数部分和循环小数部分，并计算其分数表示
            BigInteger a=new BigInteger(mat2.group(1));
            BigInteger b=new BigInteger(mat2.group(2));
            int m=mat2.group(2).length();

            BigInteger UP=BigInteger.ZERO,LOW=BigInteger.ONE;
            UP=UP.add(a);
            UP=UP.multiply(BigInteger.TEN.pow(m).subtract(BigInteger.ONE))
            .add(b);
            LOW=BigInteger.TEN.pow(m).subtract(BigInteger.ONE);
            BigInteger gcd=UP.gcd(LOW);
            UP=UP.divide(gcd);
            LOW=LOW.divide(gcd);
            
            return new Fraction(UP.longValue()*signum,LOW.longValue());
        } else if(mat3.matches()){
            // 匹配到了常规的浮点数格式
            // 解析整数部分和小数部分，并计算其分数表示
            BigInteger a=new BigInteger(mat3.group(1));
            BigInteger b=new BigInteger(mat3.group(2));
            int m=mat3.group(2).length();

            BigInteger UP=BigInteger.ZERO,LOW=BigInteger.ONE;
            UP=UP.add(a);
            UP=UP.multiply(BigInteger.TEN.pow(m)).add(b);
            LOW=BigInteger.TEN.pow(m);
            BigInteger gcd=UP.gcd(LOW);
            UP=UP.divide(gcd);
            LOW=LOW.divide(gcd);

            return new Fraction(UP.longValue()*signum,LOW.longValue());
        } else {
            // 对于不符合特殊格式的字符串，尝试直接解析为浮点数并转换为分数形式(即整数)
            return parseFraction(Double.toString(Double.parseDouble(ratio)));
        }
    }
    public static Fraction parseFraction(double x){
        return parseFraction(Double.toString(x));
    }
    public static Fraction valueOf(double x){
        return parseFraction(x);
    }
    
    /**
     * 根据给定的字符串表示法创建一个分数对象。
     * 该方法能够处理两种形式的字符串：一种是整数形式，另一种是分数形式（如"3/4"）。
     * 
     * @param fraction 表示分数的字符串。可以是整数或者形如"整数/整数"的字符串。
     * @return 返回一个对应的Fraction实例。
     * @throws NumberFormatException 如果字符串是分数形式但格式不正确（如包含多个"/"），则抛出此异常。
     */
    public static Fraction valueOf(String fraction) throws NumberFormatException{
        if(fraction.contains("/")){
            // 分割字符串为分子和分母
            String[] parts=fraction.split("/");
            if(parts.length==2){
                // 将字符串转换为长整型并创建Fraction实例
                long _up=Long.parseLong(parts[0]),
                    _low=Long.parseLong(parts[1]);
                return new Fraction(_up,_low);
            }else{
                // 如果字符串分割后不是两个部分，则格式不正确
                throw new NumberFormatException("Invalid rational number format");
            }
        }else if(fraction.contains(" ")){
            String[] parts=fraction.split("\\s+");
            long _up=Long.parseLong(parts[0]),
                _low=Long.parseLong(parts[1]);
            return new Fraction(_up,_low);
        }else{
            return parseFraction(fraction);
        }
    }
    /**
     * 计算并返回当前分数对象的倒数。
     * <p>
     * 该方法不接受任何参数，它会基于当前分数对象的分子和分母计算倒数。
     * 分子和分母会互换位置，从而得到当前分数的倒数。
     *
     * @return 返回一个新的分数对象，该对象是当前分数的倒数。
     */
    public Fraction reciprocal(){
        // 创建一个新的Fraction实例，分子和分母互换位置，即为当前分数的倒数
        return new Fraction(low,up);
    }
    private List<Long> primeFactors(long n) {
        // 输入验证
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be positive Integer");
        }
        List<Long> factors=new ArrayList<>();
        // 对于大数，优化质因数检测过程
        for(;(n&1)==0;n>>=1){
            factors.add(2L);
        }
        for(long i=3;i*i<=n;i+=2L){ // 仅检查奇数作为因数，同时上限优化为sqrt(n)
            for(;n%i==0;n/=i){
                factors.add(i);
            }
        }
        // 如果n大于2，那么它本身就是一个质数
        if(n>2) {
            factors.add(n);
        }
        return factors;
    }
    private boolean containsNotOnly2And5(List<Long> x){
        for(long i:x){
            if(i!=2&&i!=5){
                return true;
            }
        }
        return false;
    }
    /**
     * 高精度得到循环部分的长度
     */
    private int loopLength(long r){
        BigInteger a=BigInteger.TEN;
        for(int i=1;;i++){
            if(a.pow(i).mod(BigInteger.valueOf(r))
            .equals(BigInteger.ONE)){
                return i;
            }
        }
    }
    /**
     * 将分数转换为有理小数。如果可以表示为简单的整数比，则直接返回整数比的字符串形式。
     * 该方法首先尝试将数值转换为最简形式，然后分析分母和分子的质因数分解，根据质因数中2和5的个数来判断是否可以表示为简单的分数形式。
     * 如果质因数中2和5的个数相等，那么这个数值可以表示为一个带小数的形式，且小数部分不会超过指定精度。
     * <p>数学方法来源https://zhuanlan.zhihu.com/p/419792738
     * @return 表示该数值的最简分数或小数形式的字符串。如果可以表示为简单的整数比，则返回整数比的字符串形式；
     *         如果可以表示为带小数的形式，则返回带小数的形式，且移除小数末尾的零；
     *         如果无法表示为简单的整数比或带小数的形式，则返回其小数形式的字符串。
     */
    public String toRational(){
        // 将当前数值转换为最简形式的分子和分母
        long _up_=toLowest().up,_low_=toLowest().low;
        // 获取分母的质因数分解
        List<Long> factors=primeFactors(_low_);
        // 如果分子整除分母，直接返回整数比的字符串形式
        if(_up_%_low_==0){
            return Long.toString(_up_/_low_);
        }
        // 如果分母的质因数中只包含2和5，可以以简单分数形式表示
        if(!containsNotOnly2And5(factors)){
            return Double.toString(_up_*1.0/_low_)
            .replaceAll("0+$",""); // 以小数形式返回，移除末尾的零
        }else{
            // 统计质因数中2和5的个数
            int two=0,five=0;
            for(long i:factors){
                if(i==2L){
                    two++;
                }else if(i==5L){
                    five++;
                }
            }
            // 计算最小需要的位数
            int c=two>five?two:five;
            long r=_low_/(power(2,two)*power(5,five));
            int loopLength=loopLength(r), integerLength=Long.toString(_up_/_low_).length();
            // 计算并格式化最终结果
            String ans=new BigDecimal(_up_).divide(new BigDecimal(_low_),loopLength+c+integerLength,RoundingMode.HALF_UP)
            .toString().substring(0,integerLength+loopLength+c+1);// 1 是小数点的长度
            return new StringBuilder(ans).insert(integerLength+1+c,"(").append(")").toString();
        }
    }
    /**
     * 将给定的Fraction对象转换为字符串形式的有理数。
     * 
     * @param rational 一个Fraction对象，代表待转换的有理数。
     * @return 返回表示该有理数的字符串形式。
     */
    public static String toRational(Fraction rational){
        return rational.toRational();
    }
    public Fraction max(Fraction y){
        return isGreater(y)?this:y;
    }
    public static Fraction max(Fraction x,Fraction y){
        return x.max(y);
    }
    public Fraction min(Fraction y){
        return isLess(y)?this:y;
    }
    public static Fraction min(Fraction x,Fraction y){
        return x.min(y);
    }
    public Fraction abs(){
        return new Fraction(Math.abs(up),Math.abs(low));
    }
    public static Fraction abs(Fraction x){
        return x.abs();
    }
    /**
     * 取负操作。
     * 该方法通过改变分子的符号来获取分数的负值。如果分子和分母的符号相同，则直接改变分子的符号；
     * 如果分子为0，则返回0的副本；如果分子和分母的符号不同，则返回分数的绝对值。
     * 这样处理是因为分数的负值定义为分子取负但分母保持不变。
     *
     * @return 返回一个新的分数对象，表示当前分数的负值。
     */
    public Fraction negate(){
        // 判断分子和分母的符号是否相同
        if(up*low>0){
            // 如果符号相同，直接改变分子的符号
            return new Fraction(-up,low);
        }else if(up==0){
            // 如果分子为0，返回0的副本（避免修改原对象）
            return ZERO.clone();
        }else{
            // 如果符号不同，返回分数的绝对值
            return abs();
        }
    }
    public long round(){
        return Math.round(up*1.0/low);
    }
    public int signum(){
        return up*low>0?1:up*low<0?-1:0;
    }
    public boolean isPositiveInfinity(){
        return up==1&&low==0;
    }
    public static boolean isPositiveInfinity(Fraction f){
        return f.isPositiveInfinity();
    }
    public boolean isNegativeInfinity(){
        return up==-1&&low==0;
    }
    public static boolean isNegativeInfinity(Fraction f){
        return f.isNegativeInfinity();
    }
    public boolean isNaN(){
        return up==0&&low==0;
    }
    public static boolean isNaN(Fraction f){
        return f.isNaN();
    }
}
