package ComputerGraphics;

import java.util.List;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class SutherHodg多边形裁剪 {
    private static class Point{
        double x,y;
        Point(double x,double y) {
            double X=Math.round(x);
            double Y=Math.round(y);
            this.x=Math.abs(X-x)<=1e-6?X:x;
            this.y=Math.abs(Y-y)<=1e-6?Y:y;
        }
        public String toString(){
            return "("+x+", "+y+")";
        }
        boolean isInside(Line wndEdge){
            double x1=wndEdge.start.x,y1=wndEdge.start.y;
            double x2=wndEdge.end.x,y2=wndEdge.end.y;
            return (y2-y1)*x+(x1-x2)*y+(x2*y1-x1*y2)<0;
        }
        public boolean equals(Object point){
            if(point==this) return true;
            if(point==null) return false;
            return point instanceof Point p
                &&x==p.x&&y==p.y;
        }
        public int hashCode(){
            return Objects.hash(x,y);
        }
    }
    private static class Line{
        Point start,end;
        Line(Point start,Point end){
            this.start=start;
            this.end=end;
        }
        public String toString(){
            return start+"-->"+end;
        }
        Point intersection(Line other){
            double x1=start.x,y1=start.y;
            double x2=end.x,y2=end.y;
            double x3=other.start.x,y3=other.start.y;
            double x4=other.end.x,y4=other.end.y;

            Point dc=new Point(x1-x2,y1-y2);
            Point dp=new Point(x3-x4,y3-y4);

            double n1=x1*y2-y1*x2,n2=x3*y4-y3*x4;
            double n3=1/(dc.x*dp.y-dc.y*dp.x);

            return new Point(
                (n1*dp.x-n2*dc.x)*n3,
                (n1*dp.y-n2*dc.y)*n3
            );
        }
    }
    /**
     * 使用Sutherland-Hodgman算法进行多边形裁剪
     * 该算法通过一个裁剪窗口对一个给定的多边形进行裁剪，返回裁剪后的多边形顶点列表
     * 
     * @param polyGon  待裁剪的多边形顶点列表
     * @param clipWnd  裁剪窗口的顶点列表，必须是凸多边形
     * @return 裁剪后的多边形顶点列表
     */
    static List<Point> SutherlandHodgman(List<Point> polyGon,List<Point> clipWnd){
        // 初始化输出多边形为输入多边形
        var output=new ArrayList<>(polyGon);
        // 遍历裁剪窗口的每一条边
        for(int edge=0,cLen=clipWnd.size();edge<cLen;edge++){
            // 获取当前裁剪边的两个顶点
            Point e1=clipWnd.get(edge);
            Point e2=clipWnd.get((edge+1)%cLen);
            // 创建当前裁剪边
            Line wndEdge=new Line(e1,e2);
            // 用于存储当前裁剪边裁剪结果的临时输入列表
            var input=new ArrayList<>(output);
            // 清空输出列表，用于存储下一轮裁剪的结果
            output.clear();
            // 遍历输入多边形的每一条边
            for(int i=0,iLen=input.size();i<iLen;i++){
                // 获取当前顶点和前一个顶点
                Point curr=input.get(i);
                Point prev=input.get((i+iLen-1)%iLen);
                // 创建当前多边形边
                Line polyEdge=new Line(prev,curr);
                // 根据当前顶点与裁剪边的位置关系，决定是否保留顶点或计算交点
                if(curr.isInside(wndEdge)){
                    if(!prev.isInside(wndEdge)){
                        output.add(wndEdge.intersection(polyEdge));
                    }
                    output.add(curr);
                }else if(prev.isInside(wndEdge)){
                    output.add(wndEdge.intersection(polyEdge));
                }
            }
        }
        // 返回去重后的裁剪结果
        return output.stream().distinct().toList();
    }
    static void drawPolygon(Graphics g,List<Point> polyGon,Color c){
        int len=polyGon.size();
        g.setColor(c);
        for(int i=0;i<len;i++){
            Point curr=polyGon.get(i);
            Point next=polyGon.get((i+1)%len);
            g.drawLine((int)curr.x,(int)curr.y,(int)next.x,(int)next.y);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // 输入多边形顶点
        //System.out.println("输入裁剪多边形(红色)顶点数");
        //int n=sc.nextInt();
        
        double[][] polygonPoints={  // 裁剪多边形顶点
            {0,10},{20,30},
            {40,10},{40,40},
            {0,40}
        };
        System.out.println("裁剪多边形的顶点数为"+polygonPoints.length);
        List<Point> polygon=new ArrayList<>(polygonPoints.length);

        //System.out.println("\n逆时针输入多边形顶点坐标");
        System.out.println("裁剪多边形(红色)顶点坐标为");
        for(int i=0;i<polygonPoints.length;i++){
            //polygon.add(new Point(sc.nextDouble(),sc.nextDouble()));
            polygon.add(new Point(polygonPoints[i][0]+10,polygonPoints[i][1]+5));
            System.out.println(polygon.get(i));
        }

        // 输入裁剪窗口
        //System.out.println("\n输入裁剪窗口(绿色)顶点数");
        //int m=sc.nextInt();

        double[][] clipWndPoints={  // 裁剪窗口顶点
            {0,0},{40,0},
            {40,20},{0,20}
        };
        System.out.println("\n裁剪窗口顶点数为"+clipWndPoints.length);
        List<Point> clipWnd=new ArrayList<>(clipWndPoints.length);

        //System.out.println("\n逆时针输入裁剪窗口顶点坐标");
        System.out.println("裁剪窗口(绿色)顶点坐标为");
        for(int i=0;i<clipWndPoints.length;i++){
            //clipWnd.add(new Point(sc.nextDouble(),sc.nextDouble()));
            clipWnd.add(new Point(clipWndPoints[i][0]+10,clipWndPoints[i][1]+5));
            System.out.println(clipWnd.get(i));
        }

        System.out.println("\n裁剪后多边形(蓝色)顶点为:");
        List<Point> clipedPolygon=SutherlandHodgman(polygon,clipWnd);
        clipedPolygon.forEach(System.out::println);
        sc.close();

        // 绘制图形
        JPanel clip=new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d=(Graphics2D)g;
                g2d.scale(4,4);
                drawPolygon(g2d,polygon,Color.RED);
                drawPolygon(g2d,clipWnd,Color.GREEN);
                drawPolygon(g2d,clipedPolygon,Color.BLUE);
            }
        };
        new JFrame("Sutherland-Hodgman多边形裁剪"){{
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400,300);
            add(clip);
            setVisible(true); 
        }};
    }
}
