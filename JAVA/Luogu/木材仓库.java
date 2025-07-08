package JAVA.Luogu;

import java.util.*;

public class 木材仓库 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        TreeSet<Integer> woods=new TreeSet<>();
        for(int act,len;m-->0;){
            act=sc.nextInt();
            len=sc.nextInt();
            switch(act){
                case 1->{
                    if(woods.contains(len)){
                        System.out.println("Already Exist");
                    }else{
                        woods.add(len);
                    }
                }case 2->{
                    if(woods.contains(len)){
                        System.out.println(len);
                        woods.remove(len);
                    }else{
                        if(woods.isEmpty()){
                            System.out.println("Empty");
                        }else{
                            var shorter=woods.floor(len);
                            var longer=woods.ceiling(len);
                            if(shorter==null&&longer!=null){
                                System.out.println(longer);
                                woods.remove(longer);
                            }else if(shorter!=null&&longer==null){
                                System.out.println(shorter);
                                woods.remove(shorter);
                            }else{
                                if(longer-len<len-shorter){
                                    System.out.println(longer);
                                    woods.remove(longer);
                                }else{
                                    System.out.println(shorter);
                                    woods.remove(shorter);
                                }
                            }
                        }
                    }
                }
            }
        }
        sc.close();
    }
}
