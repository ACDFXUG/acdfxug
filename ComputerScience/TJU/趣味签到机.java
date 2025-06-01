package ComputerScience.TJU;

import java.util.Scanner;

public class 趣味签到机 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String name=sc.nextLine();
        byte kind=sc.nextByte();
        System.out.println(
            switch(kind){
                case 1->name+", you need to work hard today";
                case 2->name+", you are really a cute little girl";
                case 3->name+", if you don't hurry up, you'll be late";
                default->"no result";
            }
        );
        sc.close();
    }
}
