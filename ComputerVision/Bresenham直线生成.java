package ComputerVision;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static java.lang.Math.*;

public class Bresenham直线生成 {
    private static class Bresenham extends JPanel{
        final int x1,y1,x2,y2;
        Bresenham(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
        /**
         * 使用Bresenham算法绘制直线
         * 该方法通过逐点决策误差，确定直线的下一个像素点位置，从而在两点间绘制出一条直线
         * 
         * @param g Graphics对象，用于绘制直线
         * @param x1 直线的起始点X坐标
         * @param y1 直线的起始点Y坐标
         * @param x2 直线的结束点X坐标
         * @param y2 直线的结束点Y坐标
         */
        static void drawBresenhamLine(Graphics g,int x1,int y1,int x2,int y2) {
            // 计算直线的水平和垂直距离
            int dx=x2-x1,dy=y2-y1;
            // 确定直线的步进方向，sx和sy表示直线在X轴和Y轴上的步进方向
            int sx=dx>0?1:-1,sy=dy>0?1:-1;
            // 初始化直线的起点和误差
            int x0=x1,y0=y1,err=0;
            // 获取直线在X轴和Y轴上的实际距离
            dx=abs(dx);
            dy=abs(dy);
            // 当直线的水平距离大于垂直距离时，按照水平方向进行步进
            if(dx>dy){
                for(;x0!=x2;x0+=sx){
                    // 在当前点绘制一个像素
                    g.drawLine(x0,y0,x0,y0);
                    // 更新误差
                    err+=dy;
                    // 当误差大于等于直线的水平距离的一半时，垂直方向上步进一个单位，并重置误差
                    if(err<<1>=dx){
                        y0+=sy;
                        err-=dx;
                    }
                }
            // 当直线的垂直距离大于等于水平距离时，按照垂直方向进行步进
            }else{
                for(;y0!=y2;y0+=sy){
                    // 在当前点绘制一个像素
                    g.drawLine(x0,y0,x0,y0);
                    // 更新误差
                    err+=dx;
                    // 当误差大于等于直线的垂直距离的一半时，水平方向上步进一个单位，并重置误差
                    if(err<<1>=dy){
                        x0+=sx;
                        err-=dy;
                    }
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
        Bresenham bre=new Bresenham(x1,y1,x2,y2);
        bresenham.add(bre);
        bresenham.setVisible(true);
        sc.close();
    }
}
