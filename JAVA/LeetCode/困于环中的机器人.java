package Java.LeetCode;

public class 困于环中的机器人 {
    static boolean isRobotBounded(String instructions) {
        final char[] dirs={'N','E','S','W'};
        int dir=0,x=0,y=0;
        for(char c:instructions.toCharArray()){
            switch(c){
                case 'G'->{
                    switch(dirs[dir]){
                        case 'N'->++y;
                        case 'S'->--y;
                        case 'E'->++x;
                        case 'W'->--x;
                    }
                }case 'R'->dir=(dir+1)%4;
                case 'L'->dir=(dir+3)%4;
            }
        }
        return x==0&&y==0||dir!=0;
    }
    public static void main(String[] args) {
        
    }
}
