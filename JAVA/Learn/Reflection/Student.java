package Java.Learn.Reflection;

public class Student {
    private String name;
    private int age;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }
}
