package Java.Learn.Reflection;

public class GetClass {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        var s1=Student.class;
        try {
            var s2=Class.forName("Java.Learn.Reflection.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        var s3=new Student("张三",18);
        var s4=s3.getClass();
    }
}
