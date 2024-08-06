package JAVA;

import java.util.regex.*;

public class Complex{
    private double real,imag;

    public Complex(double real,double imaginary){
        this.real=real; 
        this.imag=imaginary;
    }
    public Complex(String z){
        this(valueOf(z).real,valueOf(z).imag);
    }
    public Complex(){
        this(0,0);
    }

    public static final Complex ZERO=new Complex(0,0);
    public static final Complex ONE=new Complex(1,0);
    public static final Complex I=new Complex(0,1);

    public String toString(){
        String r,i;
        if(Math.abs(real)<1e-10){
            r="0";
        }else{
            r=real==Math.round(real)?
            String.valueOf((int)real):String.valueOf(real);
        }
        if(Math.abs(imag)<1e-10){
            i="0";
        }else{
            i=imag==Math.round(imag)?
            String.valueOf((int)imag):String.valueOf(imag);
        }
        if(r.equals("0")&&!i.equals("0")){
            if(i.equals("1"))return "i";
            else if(i.equals("-1"))return "-i";
            else return i+"i";
        }else if(i.equals("0")){
            return r;
        }else{
            if(i.equals("1"))return r+"+i";
            else if(i.equals("-1"))return r+"-i";
            else return i.startsWith("-")?
            r+"-"+i.substring(1)+"i":r+"+"+i+"i";
        }
    }
    private boolean equals(Complex c){
        if(c==null||this==null){
            return false;
        }
        return real==c.real&&imag==c.imag;
    }
    public boolean equals(Object z){
        if(z==this){
            return true;
        }
        return z instanceof Complex&&equals((Complex)z);
    }
    public Complex clone(){
        return new Complex(real,imag);
    }
    public int hashCode(){
        int hash=17;
        hash=31*hash+Double.hashCode(real);
        hash=31*hash+Double.hashCode(imag);
        return hash;
    }
    public Complex add(Complex c){
        return new Complex(real+c.real,imag+c.imag);
    }
    public Complex subtract(Complex c){
        return new Complex(real-c.real,imag-c.imag);
    }
    public Complex multiply(Complex c){
        return new Complex(real*c.real-imag*c.imag,
        real*c.imag+imag*c.real);
    }
    public Complex multiply(double k){
        return new Complex(real*k,imag*k);
    }
    public Complex divide(Complex c){
        if(c.equals(ZERO)){
            throw new IllegalArgumentException("Division by zero");
        }
        double denominator=c.real*c.real+c.imag*c.imag;
        return new Complex((real*c.real+imag*c.imag)/denominator,
        (imag*c.real-real*c.imag)/denominator);
    }
    public Complex divide(double k){
        return new Complex(real/k,imag/k);
    }
    public Complex power(int n){
        Complex result=ONE;
        for(int i=1;i<=n;i++){
            result=result.multiply(this);
        }
        return result;
    }
    /**
     * 计算复数的n次根。
     * 
     * @param N 指定的次数，必须大于0。
     * @return 一个复数数组，包含n个复数根。
     * @throws IllegalArgumentException 如果n等于0，则抛出此异常。
     */
    public Complex[] nthrt(int N){
        // 检查n是否大于0，如果不是，则抛出异常
        if(N == 0){
            throw new IllegalArgumentException("N must be greater than 0");
        }
        // 计算当前复数的模长和辐角
        double r = magnitude(), theta = arg();
        // 计算每个根的模长
        double omegaL = Math.pow(r, 1.0 / N);
        // 初始化一个长度为n的复数数组，用于存放所有根
        Complex[] omega = new Complex[N];
        // 遍历n次，计算每个根的值
        for(int k = 0; k < N; k++){
            // 计算每个根的辐角
            double theta1 = (theta + 2 * Math.PI * k) / N;
            // 根据模长和辐角创建每个根的复数，并乘以相应的模长
            omega[k] = new Complex(Math.cos(theta1), Math.sin(theta1)).multiply(omegaL);
        }
        // 返回包含所有根的复数数组
        return omega;
    }
    public double Re(){
        return real;
    }
    public static double Re(Complex c){
        return c.real;
    }
    public double Im(){
        return imag;
    }
    public static double Im(Complex c){
        return c.imag;
    }
    /**
     * 根据字符串表示法创建一个复数。
     * 支持三种格式的字符串表示：实数（如"1"）、虚数（如"2i"）和复数（如"1+2i"）。
     * 
     * @param z 复数的字符串表示。
     * @return 对应于字符串表示的复数对象。
     * @throws IllegalArgumentException 如果字符串不符合任何支持的格式。
     */
    public static Complex valueOf(String z){
        /* 正则表达式匹配实数形式的字符串 */
        String onlyReal="[-+]?\\d*\\.?\\d+";
        /* 正则表达式匹配虚数形式的字符串 */
        String onlyImaginary="[-+]?\\d*\\.?\\d+i";
        /* 正则表达式匹配复数形式的字符串 */
        String realAndImaginary="([-+]?\\d*\\.?\\d+)?([-+]\\d*\\.?\\d+i)";
        
        /* 根据字符串的匹配模式返回相应的复数实例 */
        if(Pattern.matches(onlyReal,z)){
            /* 实数形式：创建一个实部为字符串解析出的值，虚部为0的复数 */
            return new Complex(Double.parseDouble(z),0);
        }else if(Pattern.matches(onlyImaginary,z)){
            /* 虚数形式：创建一个实部为0，虚部为字符串解析出的值（除去'i'）的复数 */
            return new Complex(0,Double.parseDouble(z.substring(0,z.length()-1)));
        }else if(Pattern.matches(realAndImaginary,z)){
            /* 复数形式：解析出实部和虚部，创建相应的复数 */
            // return new Complex(Double.parseDouble(z.substring(0,z.indexOf("+"))),
            // Double.parseDouble(z.substring(z.indexOf("+")+1,z.length()-1)));
            if(z.contains("+")){
                return new Complex(Double.parseDouble(z.substring(0,z.indexOf("+"))),
                Double.parseDouble(z.substring(z.indexOf("+")+1,z.length()-1)));
            }else{
                return new Complex(Double.parseDouble(z.substring(0,z.indexOf("-"))),
                -Double.parseDouble(z.substring(z.indexOf("-")+1,z.length()-1)));
            }
        }
        /* 如果字符串不符合任何支持的格式，抛出异常 */
        throw new IllegalArgumentException("Invalid Complex Number");
    }
    public double[] toArray(){
        return new double[]{real,imag};
    }
    public static double[] toArray(Complex c){
        return c.toArray();
    }
    /**
     * 计算复数的模长（也称为 magnitude 或绝对值）。
     * 模长定义为复数的欧几里得距离，即 real 和 imaginary 分量在二维空间中的长度。
     * 使用 Math.hypot 方法来计算这个距离，该方法能更安全地处理接近无穷大的数值。
     *
     * @return 复数的模长，即 real 和 imaginary 分量构成的向量的长度。
     * 即|this|
     */
    public double magnitude(){
        return Math.hypot(real, imag);
    }
    /**
     * 计算复数的共轭复数。
     * 共轭复数是将原复数的虚部符号取反得到的新复数。对于实数而言，其共轭复数为其本身。
     * 该方法用于扩展复数类的功能，提供对共轭复数的计算支持。
     *
     * @return 返回一个新的复数对象，该对象为当前复数的共轭复数。
     */
    public Complex conjugate(){
        // 创建一个新的复数对象，其中实部与当前对象相同，虚部与当前对象相反。
        return new Complex(real,-imag);
    }
    public double arg(){
        return Math.atan2(imag,real);
    }
    public Complex reciprocal(){
        return ONE.divide(this);
    }
    public Complex normalize(){
        return divide(magnitude());
    }
    public static Complex exp(Complex z){
        return new Complex(Math.cos(z.imag),Math.
        sin(z.imag)).multiply(Math.exp(z.real));
    }
    public double[] toExp(){
        return new double[]{magnitude(),arg()};
    }
    public static double[] toExp(Complex z){
        return z.toExp();
    }
    public Complex negate(){
        return new Complex(-real,-imag);
    }
    public static Complex sin(Complex z){
        return (exp(z).subtract(exp(z.negate()))).divide(new Complex(0, 2));
    }
    public static Complex cos(Complex z){
        return (exp(z.negate()).add(exp(z))).divide(2);
    }
    public static Complex tan(Complex z){
        return sin(z).divide(cos(z));
    }
    public static Complex ln(Complex z){
        return new Complex(Math.log(z.magnitude()),z.arg());
    }
    public static Complex sqrt(Complex z){
        double r = z.magnitude();
        double theta = z.arg();
        return new Complex(Math.sqrt(r)*Math.cos(theta/2),Math.sqrt(r)*Math.sin(theta/2));
    }
    public static Complex sinh(Complex z){
        return (exp(z).subtract(exp(z.negate()))).divide(2);
    }
    public static Complex cosh(Complex z){
        return (exp(z).add(exp(z.negate()))).divide(2);
    }
    public static Complex tanh(Complex z){
        return sinh(z).divide(cosh(z));
    }
    public static Complex polar(double r,double theta){
        return new Complex(r*Math.cos(theta),r*Math.sin(theta));
    }

    public static Complex polar(double theta){
        return new Complex(Math.cos(theta),Math.sin(theta));
    }
}
