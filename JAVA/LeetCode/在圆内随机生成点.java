package Java.LeetCode;

import static java.lang.Math.*;

public class 在圆内随机生成点 {
    @SuppressWarnings("unused")
    private static class Circle{
        final double r,x,y;
        Circle(double radius, double x_center, double y_center){
            this.r=radius;
            this.x=x_center;
            this.y=y_center;
        }
        double[] randPoint() {
            double randR=r*random();
            double theta=random()*2*PI;
            return new double[]{
                x+randR*cos(theta),y+randR*sin(theta)
            };
        }
    }
   public static void main(String[] args) {
    
   }
}
