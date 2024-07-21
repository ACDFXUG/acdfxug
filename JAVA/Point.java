package JAVA;

public class Point{
    private double x,y,z;
    public Point(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Point(String point){
        this.x=Double.parseDouble(point.substring(1,point.indexOf(",")));
        this.y=Double.parseDouble(point.substring(point.indexOf(",")+1,point.lastIndexOf(",")));
        this.z=Double.parseDouble(point.substring(point.lastIndexOf(",")+1,point.length()-1));
    }
    public Point(java.util.Scanner sc){
        this.x=sc.nextDouble();
        this.y=sc.nextDouble();
        this.z=sc.nextDouble();
    }

    public static final Point O=new Point(0,0,0);
    public static final Point I=new Point(1,0,0);
    public static final Point J=new Point(0,1,0);
    public static final Point K=new Point(0,0,1);

    public String toString(){
        String X=x==Math.round(x)?String.format("%.0f", x):String.valueOf(x);
        String Y=y==Math.round(y)?String.format("%.0f", y):String.valueOf(y);
        String Z=z==Math.round(z)?String.format("%.0f", z):String.valueOf(z);
        return String.format("(%s, %s, %s)",X,Y,Z);
    }
    public Point clone(){
        return new Point(x,y,z);
    }
    public int hashCode(){
        int hash=17;
        hash=31*hash+Double.hashCode(x);
        hash=31*hash+Double.hashCode(y);
        hash=31*hash+Double.hashCode(z);
        return hash;
    }
    public boolean equals(Point p){
        if(this==p){
            return true;
        }
        if(p==null||this==null){
            return false;
        }
        return x==p.x&&y==p.y&&z==p.z;
    }
    public boolean isLess(Point p){
        return x<p.x&&y<p.y&&z<p.z;
    }
    public boolean isLessEqual(Point p){
        return x<=p.x&&y<=p.y&&z<=p.z;
    }
    public boolean isGreater(Point p){
        return x>p.x&&y>p.y&&z>p.z;
    }
    public boolean isGreaterEqual(Point p){
        return x>=p.x&&y>=p.y&&z>=p.z;
    }
    public Point add(Point p){
        return new Point(x+p.x,y+p.y,z+p.z);
    }
    public Point subtract(Point p){
        return new Point(x-p.x,y-p.y,z-p.z);
    }
    public Point multiply(double k){
        return new Point(x*k,y*k,z*k);
    }
    public Point divide(double k){
        return new Point(x/k,y/k,z/k);
    }
    public Point negate(){
        return new Point(-x,-y,-z);
    }
    public Point negateX(){
        return new Point(-x,y,z);
    }
    public Point negateY(){
        return new Point(x,-y,z);
    }
    public Point negateZ(){
        return new Point(x,y,-z);
    }
    public double dot(Point p){
        return x*p.x+y*p.y+z*p.z;
    }
    public Point cross(Point p){
        return new Point(y*p.z-z*p.y,z*p.x-x*p.z,x*p.y-y*p.x);
    }
    public double distance(Point p){
        return subtract(p).distance();
    }
    public Point normalize(){
        return divide(distance());
    }
    /**
     * 计算点的球坐标。
     * 将点的笛卡尔坐标 (x, y, z) 转换为其球坐标 (r, φ, θ)。
     * 球坐标定义如下：
     * r - 原点到点的距离
     * φ - 极角，正z轴与径向方向之间的角度，取值范围为 [0, π]
     * θ - 方位角，正x轴与径向方向在xy平面上的投影之间的角度，取值范围为 [0, 2π)
     *
     * @return 包含该点球坐标 (r, φ, θ) 的数组
     * @throws ArithmeticException 当距离 r 为0时抛出，表示点位于原点
     */
    public double[] toSpherical() {
        // 计算原点到点的距离
        double r = distance();
        // 若距离为0，则抛出异常，表示点位于原点
        if (r == 0) {
            throw new ArithmeticException("距离为零");
        }
        // 计算极角 φ
        double φ = Math.acos(z / r);
        // 计算方位角 θ
        double θ = Math.atan2(y, x);
        // 返回球坐标的数组形式
        return  new double[]{r, φ, θ};
    }
    public double distance(){
        return Math.sqrt(x*x+y*y+z*z);
    }
    public double[] toArray(){
        return new double[]{x,y,z};
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
    public void setZ(double z){
        this.z=z;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public double[] directionCosine(){
        double r=distance();
        if(r==0){
            throw new Error("infinite directions");
        }
        return new double[]{x/r,y/r,z/r};
    }
    public double[] directionTheta(){
        double[] cos=directionCosine();
        return new double[]{
            Math.acos(cos[0]),
            Math.acos(cos[1]),
            Math.acos(cos[2]),
        };
    }
}
