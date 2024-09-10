package Luogu;

import java.util.*;

public class 木材仓库 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Set<Integer> wood=new HashSet<>();
        int n=sc.nextInt();
        while(n-->0){
            int act=sc.nextInt(),
            length=sc.nextInt();
            switch(act){
                case 1->{
                    if(wood.contains(length)){
                        System.out.println("Already Exist");
                    }else{
                        wood.add(length);
                    }
                }case 2->{
                    if(wood.contains(length)){
                        System.out.println(length);
                        wood.remove(length);
                    }else{
                        if(wood.isEmpty()){
                            System.out.println("Empty");
                            continue;
                        }
                        for(int k=1;k<length;k++){
                            int l1=length-k,l2=length+k;
                            if(wood.contains(l1)&&wood.contains(l2)){
                                System.out.println(l1);
                                wood.remove(l1);
                                break;
                            }else if(wood.contains(l1)&&!wood.contains(l2)){
                                System.out.println(l1);
                                wood.remove(l1);
                                break;
                            }else if((!wood.contains(l1))&&wood.contains(l2)){
                                System.out.println(l2);
                                wood.remove(l2);
                                break;
                            }else{
                                continue;
                            }
                        }
                    }
                }
            }
        }
        sc.close();
    }
}
