package Java.Learn.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetClassContent {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception{
        Student s=new Student("张三",18);
        var stu=s.getClass();
        String name=stu.getSimpleName();
        var cons=stu.getConstructors();
        var con=stu.getDeclaredConstructor(String.class,int.class);
        Field[] fields=stu.getDeclaredFields();
        Field field=stu.getDeclaredField("name");

        Method[] methods=stu.getDeclaredMethods();
        Method method=stu.getDeclaredMethod("getName");
        System.out.println(method.invoke(s));
    }
}
