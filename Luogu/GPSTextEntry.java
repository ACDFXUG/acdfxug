package Luogu;

import java.util.*;
import static java.lang.Math.abs;

public class GPSTextEntry {
    static final char[][] keyboard={
        {'A','B','C','D','E','F'},
        {'G','H','I','J','K','L'},
        {'M','N','O','P','Q','R'},
        {'S','T','U','V','W','X'},
        {'Y','Z',' ','-','.','$'},
    };
    static final Map<Character,int[]> keyboards=
    new HashMap<Character,int[]>(){{
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                put(keyboard[i][j],new int[]{i,j});
            }
        }
    }};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String type=sc.nextLine();
        int x=0,y=0,ans=0;
        for(int i=0,l=type.length();i<l;i++){
            int[] pos=keyboards.get(type.charAt(i));
            ans+=abs(pos[0]-x)+abs(pos[1]-y);
            x=pos[0];
            y=pos[1];
        }
        ans+=abs(x-4)+abs(y-5);
        System.out.println(ans);
        sc.close();
    }
}
