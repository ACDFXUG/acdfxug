package ComputerVision;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static java.lang.Math.*;

public class DDA直线生成 {
    private static class DDALine extends JPanel{
        final int x1,y1,x2,y2;
        DDALine(int x1,int y1,int x2,int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
        /**
         * 使用DDA算法绘制直线
         * DDA（Digital Differential Analyzer）算法是一种用于绘制直线的算法
         * 它通过计算每个像素点的位置，从而在两点之间绘制出一条直线
         * @param g Graphics对象，用于绘制直线
         * @param x1 直线的起始点X坐标
         * @param y1 直线的起始点Y坐标
         * @param x2 直线的结束点X坐标
         * @param y2 直线的结束点Y坐标
         */
        static void drawDDALine(Graphics g,int x1,int y1,int x2,int y2) {
            // 计算x方向和y方向的距离
            int dx=x2-x1;
            int dy=y2-y1;   
            // 选择较大的距离作为步骤数，确保直线覆盖所有像素点
            int steps=max(abs(dx),abs(dy));
            // 计算x和y方向的增量，用于在每个步骤中更新点的位置
            double xIcr=dx*1./steps,x0=x1;
            double yIcr=dy*1./steps,y0=y1;
            // 遍历每个步骤，绘制直线上的每个像素点
            for(int i=0;i<=steps;i++){
                // 绘制当前像素点
                int x=(int)x0,y=(int)y0;
                g.drawLine(x,y,x,y);
                // 更新下一个像素点的位置
                x0+=xIcr;
                y0+=yIcr;
            }
        }
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d=(Graphics2D)g;
            g2d.scale(5,5);
            drawDDALine(g2d,x1,y1,x2,y2);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        JFrame DDA=new JFrame("DDA Line");
        DDA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DDA.setSize(250,250);
        int x1=sc.nextInt(),y1=sc.nextInt(),
            x2=sc.nextInt(),y2=sc.nextInt();
        DDALine ddaLine=new DDALine(x1,y1,x2,y2);
        DDA.add(ddaLine);
        DDA.setVisible(true);
        sc.close();
    }
}
