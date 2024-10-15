package ComputerVision;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static java.lang.Math.*;

public class Bresenham直线生成 {
    private static class BresenhamLine extends JPanel{
        final int x1,y1,x2,y2;
        BresenhamLine(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
        /**
         * 使用Bresenham算法绘制直线
         * 该方法通过逐点决策误差，确定直线的下一个像素点位置，从而在两点间绘制出一条直线。
         * Bresenham算法是一种高效的整数算法，用于确定在两个端点之间哪些像素应该被选中以形成最接近真实直线的路径。
         * 
         * @param g Graphics对象，用于绘制直线
         * @param x1 直线的起始点X坐标
         * @param y1 直线的起始点Y坐标
         * @param x2 直线的结束点X坐标
         * @param y2 直线的结束点Y坐标
         */
        static void drawBresenhamLine(Graphics g,int x1,int y1,int x2,int y2) {
            // 计算直线的水平和垂直距离
            int dx=abs(x2-x1);
            int dy=abs(y2-y1);
            // 确定直线的步进方向，sx和sy表示直线在X轴和Y轴上的步进方向
            int sx=x1<x2?1:-1;
            int sy=y1<y2?1:-1;
            // 根据直线的倾斜程度选择不同的绘制策略
            if(dx>dy){
                // 当直线的水平距离大于垂直距离时，按照水平方向进行步进,即|斜率| < 1
                int p=2*dy-dx; //决策误差
                int y0=y1;
                // 遍历X轴，更新像素点并绘制
                for(int x0=x1;x0!=x2;x0+=sx) {
                    g.drawLine(x0,y0,x0,y0);
                    if(p>0){
                        y0+=sy;
                        p-=2*dx;
                    }
                    p+=2*dy;
                }
            }else{
                // 当直线的垂直距离大于等于水平距离时，按照垂直方向进行步进,即|斜率| >= 1
                int p=2*dx-dy;
                int x0=x1;
                // 遍历Y轴，更新像素点并绘制
                for (int y0=y1;y0!=y2;y0+=sy) {
                    g.drawLine(x0,y0,x0,y0);
                    if(p>0){
                        x0+=sx;
                        p-=2*dy;
                    }
                    p+=2*dx;
                }
            }
        }
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d=(Graphics2D)g;
            g2d.scale(5,5);
            drawBresenhamLine(g2d,x1,y1,x2,y2);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        JFrame bresenham=new JFrame("Bresenham Line");
        bresenham.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bresenham.setSize(250,250);
        int x1=sc.nextInt(),y1=sc.nextInt(),
            x2=sc.nextInt(),y2=sc.nextInt();
        BresenhamLine bre=new BresenhamLine(x1,y1,x2,y2);
        bresenham.add(bre);
        bresenham.setVisible(true);
        sc.close();
    }
}
