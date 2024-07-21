package JAVA;

public class FractionDebug {
    public static void main(String[] args) {
        Fraction a=new Fraction(-1,3);
        System.out.print(a.toLowest().equals(a));
    }
}
