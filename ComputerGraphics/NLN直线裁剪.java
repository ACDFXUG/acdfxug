package ComputerGraphics;

import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class NLN直线裁剪 {
    static final byte[] COHEN_SUTHERLAND={
        0b1001,0b1000,0b1010,
        0b0001,0b0000,0b0010,
        0b0101,0b0100,0b0110,
    };
    private static class Point
    implements Comparable<Point>{
        double x,y;
        Point(double x,double y){
            this.x=x;
            this.y=y;
        }
        public int compareTo(Point p){
            return Double.compare(x,p.x);
        }
        public boolean equals(Object dot){
            if(this==dot){
                return true;
            }
            if(dot==null){
                return false;
            }
            return dot instanceof Point p
                &&x==p.x&&y==p.y;
        }
        boolean isInner(Rectangle rec){
            return x>=rec.leftBottom.x&&x<=rec.rightTop.x
                &&y>=rec.leftBottom.y&&y<=rec.rightTop.y;
        }
        byte getCohenSutherlandCode(Rectangle rec){
            return (byte)(
                (x<rec.leftBottom.x?1:0)
                |((x>rec.rightTop.x?1:0)<<1)
                |((y<rec.leftBottom.y?1:0)<<2)
                |((y>rec.rightTop.y?1:0)<<3)
            );
        }
        public String toString(){
            return "("+x+", "+y+")";
        }
    }
    private static class Rectangle{
        Point leftBottom,rightTop;
        Rectangle(Point leftBottom,Point rightTop){
            this.leftBottom=leftBottom;
            this.rightTop=rightTop;
        }
        /**
         * 获取线段与矩形区域的交点列表
         * 如果线段完全在矩形内部或完全在矩形外部，则返回空列表
         * 该方法主要用于图形学中的线段裁剪算法
         * 
         * @param l 表示一条线段，由两个端点定义
         * @return 返回一个Point对象列表，表示线段与矩形区域的交点
         */
        List<Point> getIntersection(Line l){
            // 提取线段的起点和终点坐标
            double x1=l.start.x,y1=l.start.y;
            double x2=l.end.x,y2=l.end.y;
            // 提取矩形区域的最小和最大坐标
            double xMin=leftBottom.x,yMin=leftBottom.y;
            double xMax=rightTop.x,yMax=rightTop.y;
            // 初始化交点列表
            List<Point> interPoints=new ArrayList<>(4);
            // 获取线段两端点的Cohen-Sutherland编码
            byte code1=l.start.getCohenSutherlandCode(this),
                code2=l.end.getCohenSutherlandCode(this);
            
            // 处理线段垂直的情况
            if(x1==x2){
                // 如果起点或终点在矩形内部，则添加相应的交点
                if(code1==0b0000){
                    interPoints.add(y2>=yMax?
                    new Point(x1,yMax):new Point(x2,yMin));
                }else if(code2==0b0000){
                    interPoints.add(y1>=yMax?
                    new Point(x1,yMax):new Point(x2,yMin));
                }else{
                    interPoints.add(new Point(x1,yMax));
                    interPoints.add(new Point(x2,yMin));
                }
            // 处理线段水平的情况
            }else if(y1==y2){
                // 如果起点或终点在矩形内部，则添加相应的交点
                if(code1==0b0000){
                    interPoints.add(x2>=xMax?
                    new Point(xMax,y1):new Point(xMin,y2));
                }else if(code2==0b0000){
                    interPoints.add(x1>=xMax?
                    new Point(xMax,y1):new Point(xMin,y2));
                }else{
                    interPoints.add(new Point(xMax,y1));
                    interPoints.add(new Point(xMin,y2));
                }
            // 处理一般情况，计算线段与矩形四边的交点
            }else{
                Point p1=new Point(((yMax-y1)*(x1-x2)/(y1-y2))+x1,yMax);
                Point p2=new Point(((yMin-y1)*(x1-x2)/(y1-y2))+x1,yMin);
                Point p3=new Point(xMin,((xMin-x1)*(y2-y1)/(x2-x1))+y1);
                Point p4=new Point(xMax,((xMax-x1)*(y2-y1)/(x2-x1))+y1);
                Point[] p={p1,p2,p3,p4};
                // 检查计算出的交点是否在矩形内部，并添加到交点列表中
                OUT:for(int i=0;i<4;i++){
                    if(p[i].isInner(this)){
                        for(int j=0;j<interPoints.size();j++){
                            if(p[i].equals(interPoints.get(j))){
                                continue OUT;
                            }
                        }
                        interPoints.add(p[i]);
                    }
                }
            }
            // 返回交点列表
            return interPoints;
        }
    }
    private static class Line{
        Point start,end;
        Line(Point start,Point end){
            this.start=start;
            this.end=end;
        }
        boolean isGotten(Rectangle rec){
            byte code1=start.getCohenSutherlandCode(rec),
                code2=end.getCohenSutherlandCode(rec);
            return (code1|code2)==0;
        }
        boolean isThrown(Rectangle rec){
            byte code1=start.getCohenSutherlandCode(rec),
                code2=end.getCohenSutherlandCode(rec);
            return (code1&code2)!=0;
        }
    }
    static void drawPoints(Graphics g,List<Point> points){
        for(int i=0,len=points.size();i<len;i++){
            Point s=points.get(i),e=points.get((i+1)%len);
            g.drawLine((int)s.x,(int)s.y,(int)e.x,(int)e.y);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("输入矩阵左下角和右上角坐标");
        double xMIn=sc.nextDouble(),yMin=sc.nextDouble();
        double xMax=sc.nextDouble(),yMax=sc.nextDouble();
        System.out.println("输入直线起点和终点坐标");
        double x1=sc.nextDouble(),y1=sc.nextDouble();
        double x2=sc.nextDouble(),y2=sc.nextDouble();
        Point leftBottom=new Point(xMIn,yMin),
            rightTop=new Point(xMax,yMax),
            rightBottom=new Point(xMax,yMin),
            leftTop=new Point(xMIn,yMax);
        Point start=new Point(x1,y1),end=new Point(x2,y2);
        Rectangle rec=new Rectangle(leftBottom,rightTop);
        Line l=new Line(start,end);
        var ps=List.of(start,end);
        var recs=List.of(leftBottom,rightBottom,rightTop,leftTop);

        JFrame NLNLineClip=new JFrame("NLN直线裁剪"){{
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500,500);
            setVisible(true); 
        }};

        if(l.isThrown(rec)){
            System.out.println("直线完全在矩形外");
            JPanel thrown=new JPanel(){
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    Graphics2D g2d=(Graphics2D)g;
                    g2d.scale(4,4);
                    g.setColor(Color.red);
                    drawPoints(g2d,ps);
                    g.setColor(Color.green);
                    drawPoints(g2d,recs);
                }
            };
            NLNLineClip.add(thrown);
            sc.close();
            return;
        }

        if(l.isGotten(rec)){
            System.out.println("直线完全在矩形内");
            JPanel gotten=new JPanel(){
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    Graphics2D g2d=(Graphics2D)g;
                    g2d.scale(4,4);
                    g2d.setColor(Color.red);
                    drawPoints(g2d,ps);
                    g2d.setColor(Color.green);
                    drawPoints(g2d,recs);
                    g2d.setColor(Color.blue);
                    drawPoints(g2d,ps);
                }
            };
            NLNLineClip.add(gotten);
            sc.close();
            return;
        }
        byte code1=start.getCohenSutherlandCode(rec),
            code2=end.getCohenSutherlandCode(rec);
        var inter=rec.getIntersection(l);
        inter.sort(null);
        if(code1==0b0000){
            var e=inter.get(0);
            System.out.println("直线与矩形相交于"+start+"和"+e);
            JPanel inter1=new JPanel(){
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    Graphics2D g2d=(Graphics2D)g;
                    g2d.scale(4,4);
                    g.setColor(Color.red);
                    drawPoints(g2d,ps);
                    g2d.setColor(Color.green);
                    drawPoints(g2d,recs);
                    g2d.setColor(Color.blue);
                    drawPoints(g2d,List.of(start,e));
                }
            };
            NLNLineClip.add(inter1);
        }else if(code2==0b0000){
            var s=inter.get(0);
            System.out.println("直线与矩形相交于"+s+"和"+end);
            JPanel inter2=new JPanel(){
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    Graphics2D g2d=(Graphics2D)g;
                    g2d.scale(4,4);
                    g.setColor(Color.red);
                    drawPoints(g2d,ps);
                    g2d.setColor(Color.green);
                    drawPoints(g2d,recs);
                    g2d.setColor(Color.blue);
                    drawPoints(g2d,List.of(s,end));
                }
            };
            NLNLineClip.add(inter2);
        }else{
            if(inter.size()<=1){
                System.out.println("只有一个交点");
                JPanel thrown=new JPanel(){
                    protected void paintComponent(Graphics g){
                        super.paintComponent(g);
                        Graphics2D g2d=(Graphics2D)g;
                        g2d.scale(4,4);
                        g.setColor(Color.red);
                        drawPoints(g2d,ps);
                        g2d.setColor(Color.green);
                        drawPoints(g2d,recs);
                    }
                };
                NLNLineClip.add(thrown);
            }else{
                System.out.println("直线与矩形相交于"+inter.get(0)+"和"+inter.get(1));
                JPanel inter3=new JPanel(){
                    protected void paintComponent(Graphics g){
                        super.paintComponent(g);
                        Graphics2D g2d=(Graphics2D)g;
                        g2d.scale(4,4);
                        g.setColor(Color.red);
                        drawPoints(g2d,ps);
                        g2d.setColor(Color.green);
                        drawPoints(g2d,recs);
                        g2d.setColor(Color.blue);
                        drawPoints(g2d,inter);
                    };
                };
                NLNLineClip.add(inter3);
            }
        }
        sc.close();
    }
}
