interface helloworld{
    public void println(String s);
}

abstract class print implements helloworld{
    static helloworld output=s->{
        System.out.println(s);
    };
}

public class activate {
    static helloworld op=new helloworld() {
        public void println(String s){
            System.out.println(s);
        }
    };
    static void swap(int[] num,int i,int j){
        int temp=num[i];
        num[i]=num[j];
        num[j]=temp;
    }
    public static void main(String[] args){
        Integer a=1,b=0;
        System.out.println(a==b);
    }
}
