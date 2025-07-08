package JAVA;

import java.util.*;

/**
 * 一个底层为二维double数组的矩阵
 */
public class Matrix {
    private int rows,cols;
    private double[][] data;
    private int negative(int n){
        return (n&1)==0?1:-1;
    } 

    /**
     * 构造一个row行col列的矩阵，初始化为0
     * @param row
     * @param col
     */
    public Matrix(int row,int col){
        this.rows=row;
        this.cols=col;
        this.data=new double[row][col];
    }
    /**
     * 构造一个order阶方阵并初始化为0 
     * @param order
     */
    public Matrix(int order){
        this(order,order);
    }
    /**
     * 将double二维数组构造为矩阵
     * @param mat
     */
    public Matrix(double[][] mat){
        this(mat.length,mat[0].length);
        for(int i=0;i<rows;data[i]=mat[i++].clone());
    }
    public Matrix(double[] x){
        this(1, x.length);
        data[0]=x.clone();
    }
    /**
     * 将二维数组赋值给矩阵
     * @param x
     */
    public Matrix assign(double[][] x){
        for(int i=0;i<rows;i++){
            data[i]=x[i].clone();
        }
        return this;
    }
    /**
     * 把矩阵转换为二维矩阵
     * @return (double[][])this
     */
    public double[][] toArray(){
        double[][] ans=new double[rows][cols];
        for(int i=0;i<rows;i++){
            ans[i]=data[i].clone();
        }
        return ans;
    }
    /**
     * 得到矩阵的行数
     * @return 行数rows
     */
    public int getRows(){
        int p=rows;
        return p;
    }
    /**
     * 得到矩阵的列数
     * @return 列数cols
     */
    public int getCols(){
        int p=cols;
        return p;
    }
    /**
     * 将矩阵初始化为0
     */
    public void init(){
        data=new double[rows][cols];
    }
    public static void init(Matrix a){
        a.init();
    }
    /**
     * 将矩阵初始化值为x
     * @param x
     */
    public Matrix init(double x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]=x;
            }
        }
        return this;
    }
    public static void init(Matrix x,double y){
        x.init(y);
    }
    /**
     * 在矩阵( i , j )处输入x
     * @param x
     * @param i
     * @param j
     */
    public void inputAt(double x,int i,int j){
        data[i][j]=x;
    }
    /**
     * 输入矩阵
     * @param sc
     */
    public void nextMatrix(Scanner sc){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]=sc.nextDouble();
            }
        }
    }
    /**
     * 得到矩阵在( i , j )处的值
     * @param i
     * @param j
     * @return 矩阵在( i , j )处的值
     */
    public double valueAt(int i,int j){
        return data[i][j];
    }
    public boolean equals(Object b){
        if(this==b){
            return true;
        }
        if(this==null||b==null){
            return false;
        }
        if(((Matrix)b).rows!=rows||((Matrix)b).cols!=cols){
            return false;
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]!=((Matrix)b).data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean equals(Matrix a,Matrix b){
        return a.equals(b);
    }
    public int hashCode(){
        int result=17;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result=31*result+Double.hashCode(data[i][j]);
            }
        }
        return result;
    }
    public Matrix clone(){
        Matrix temp=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                temp.data[i][j]=data[i][j];
            }
        }
        return temp;
    }
    /**
     * 复制原矩阵并指定复制后的大小
     * @param a
     * @param row
     * @param col
     * @return 复制后的矩阵
     */
    public static Matrix copyOf(Matrix a,int row,int col){
        Matrix re=new Matrix(row, col);
        for(int i=0;i<Math.min(row,a.rows);i++){
            for(int j=0;j<Math.min(col,a.cols);j++){
                re.data[i][j]=a.data[i][j];
            }
        }
        return re;
    }
    /**
     * 得到矩阵的子矩阵，其中左上角坐标为(x1,y1)，右下角坐标为(x2,y2)
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return (x1,y1)->(x2,y2)之间的子矩阵
     */
    public Matrix subMatrix(int x1,int y1,int x2,int y2){
        double[][] sub=new double[y2-y1+1][x2-x1+1];
        for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                sub[i-y1][j-x1]=data[i][j];
            }
        }
        return new Matrix(sub);
    }
    /**
     * 输出矩阵，每行输出完换行
     */
    public void printM(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.printf(Math.round(data[i][j])==
                data[i][j]?"%.0f ":"%.4f ",data[i][j]+0);
            }
            System.out.println();
        }
    }
    public static void printM(Matrix a){
        a.printM();
    }
    public void printRow(int i){
        for(int s=0;s<cols;s++){
            System.out.print(data[i][s]+" ");
        }
        System.out.println();
    }
    public void printCol(int i){
        for(int s=0;s<rows;s++){
            System.out.print(data[s][i]+" ");
        }
        System.out.println();
    }
    /**
     * 将矩阵旋转180度
     * @return 旋转后的矩阵
     */
    public Matrix spin(){
        Matrix spin=new Matrix(rows, cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                spin.data[i][j]=data[rows-1-i][cols-1-j];
            }
        }
        return spin;
    }
    /**
     * 得到矩阵相加的和矩阵
     * @param b
     * @return this + b
     */
    public Matrix add(Matrix b){
        Matrix c=new Matrix(rows, cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=data[i][j]+b.data[i][j];
            }
        }
        return c;
    }
    /**
     * 得到矩阵相加的和矩阵并赋值给原矩阵
     * @param b
     * @return this += b
     */
    public Matrix addEqual(Matrix b){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]+=b.data[i][j];
            }
        } 
        return this;
    }
    /**
     * 得到矩阵相减的差矩阵
     * @param b
     * @return this - b
     */
    public Matrix subtract(Matrix b){
        Matrix c=new Matrix(rows, cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=data[i][j]-b.data[i][j];
            }
        }
        return c;
    }
    /**
     * 得到矩阵相减的差矩阵并赋值给原矩阵
     * @param b
     * @return this -= b
     */
    public Matrix subtractEqual(Matrix b){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]-=b.data[i][j];
            }
        }
        return this;
    }
    /**
     * 得到矩阵相乘的乘积矩阵
     * @param b
     * @return this * b
     */
    public Matrix multiply(Matrix b){
        Matrix c=new Matrix(rows,b.cols);
        for(int i=0;i<rows;i++){
            for(int k=0;k<b.cols;k++){
                for(int j=0;j<cols;j++){
                    c.data[i][k]+=data[i][j]*b.data[j][k];
                }
            }
        }
        return c;
    }
    /**
     * 得到矩阵相乘的乘积矩阵并赋值给原矩阵（仅适用于方阵）
     * @param b
     * @return this *= b
     */
    public Matrix multiplyEqual(Matrix b){
        Matrix c=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int k=0;k<cols;k++){
                for(int j=0;j<cols;j++){
                    c.data[i][k]+=data[i][j]*b.data[j][k];
                }
            }
        }
        data=c.data;
        return this;
    }
    /**
     * 得到矩阵的数乘矩阵
     * @param k
     * @return this * (k)
     */
    public Matrix multiply(double k){
        Matrix c=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=data[i][j]*k;
            }
        }
        return c;
    }
    /**
     * 将矩阵的每个元素乘以一个常数，原地修改矩阵。
     * 这种方法采用了链式编程的风格，允许连续调用。
     *
     * @param k 要乘以的常数。
     * @return this *= (k)
     */
    public Matrix multiplyEqual(double k){
        // 遍历矩阵的每一行
        for(int i=0;i<rows;i++){
            // 遍历每一行的每一列
            for(int j=0;j<cols;j++){
                data[i][j]*=k; // 将当前元素乘以常数k
            }
        }
        return this; // 返回修改后的矩阵，支持链式调用
    }
    /**
     * 得到矩阵的哈达玛积
     * @param b
     * @return this ** b
     */
    public Matrix hadamard(Matrix b){
        Matrix p=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                p.data[i][j]=data[i][j]*b.data[i][j];
            }
        }
        return p;
    }
    
    /**
     * 得到矩阵的幂次方
     * @param n
     * @return this ^ n
     */
    public Matrix power(int n){
        Matrix c=identity(rows),t=this;
        for(;n>0;n>>=1){
            if((n&1)==1) c.multiplyEqual(t);
            t.multiplyEqual(t);
        }
        return c;
    }
    /**
     * 矩阵的幂次方，原地修改矩阵
     * @param n
     * @return this ^= n
     */
    public Matrix powerEqual(int n){
        data=power(n).data;
        return this;
    }
    /**
     * 得到矩阵的转置矩阵
     * @return this^T
     */
    public Matrix transpose(){
        Matrix c=new Matrix(cols,rows);
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                c.data[i][j]=data[j][i];
            }
        }
        return c;
    }

    private double det(double[][] a){
        int n=a.length;
        if(n==1)return a[0][0];
        double det=0,minor[][]=new double[n-1][n-1];
        for(int j=0;j<n;j++){
            for (int i=1;i<n;i++) {
                int col=0;
                for(int k=0;k<n;k++){
                    if(k!=j){
                        minor[i-1][col++]=a[i][k];
                    }
                }
            }
            det+=negative(j)*a[0][j]*det(minor);
        }
        return det;
    }
    /**
     * 得到矩阵的行列式
     * @return |this|
     */
    public double determine(){
        return det(data);
    }
    public static double determine(Matrix a){
        return a.determine();
    }
    /**
     * 得到矩阵关于（ i , j )的余子式
     * @param i
     * @param j
     * @return M_i j
     */
    public Matrix minor(int i,int j){
        Matrix p=new Matrix(rows-1,cols-1);
        for(int r=0;r<rows;r++){
            if(r!=i){
                for(int c=0;c<cols;c++){
                    if(c!=j){
                        p.data[r-(r<i?0:1)][c-(c<j?0:1)]=data[r][c];
                    }
                }
            }
	}
        return p;
    }
    /**
     * 得到矩阵关于( i , j )的代数余子式
     * @param i
     * @param j
     * @return A_i j=(-1)^(i+j)*M_i j
     */
    public Matrix cofactor(int i,int j){
        return minor(i,j).multiply(negative(i+j));
    }
    /**
     * 得到矩阵的伴随矩阵 
     * @return this* 
     */
    public Matrix adjoint(){
        Matrix c=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[j][i]=negative(i+j)*minor(i,j).determine();
            }
        }
        return c;
    }
    /**
     * 得到矩阵的逆矩阵
     * @return this(-1)
     */
    public Matrix inverse(){
        return adjoint().multiply(1/determine());
    }
    /**
     * 得到矩阵的迹
     * @return trace(this)
     */
    public double trace(){
        double trace=0;
        for(int i=0;i<rows;trace+=data[i][i++]);
        return trace;
    }
    public static double trace(Matrix a){
        return a.trace();
    }
    public static Matrix valueOf(double[][] mat){
        return new Matrix(mat);
    }
    public static Matrix zero(int order){
        return new Matrix(order);
    }
    /**
     * 创建一个order阶的单位矩阵
     * @param order
     * @return order阶的单位矩阵
     */
    public static Matrix identity(int order){
        double[][] p=new double[order][order];
        for(int i=0;i<order;p[i][i++]=1);
        return new Matrix(p);
    }
    /**
     * 重新将矩阵大小更改为row行col列并赋值
     * @param row
     * @param col
     * @return this(row,col)
     */
    public Matrix resize(int row,int col){
        Matrix re=new Matrix(row, col);
        for(int i=0;i<Math.min(row,rows);i++){
            for(int j=0;j<Math.min(col,cols);j++){
                re.data[i][j]=data[i][j];
            }
        }
        rows=re.rows;
        cols=re.cols;
        data=re.data;
        return this;
    }
    /**
     * 将矩阵的值取负
     * @param a
     * @return - this
     */
    public static Matrix negative(Matrix a){
        Matrix p=a.clone();
        for(int i=0;i<a.rows;i++){
            for(int j=0;j<a.cols;j++){
                p.data[i][j]=-a.data[i][j];
            }
        }
        return p;
    }
    /**
     * 在矩阵后增加一行0
     * @return this ++ {0}
     */
    public Matrix addRow(){
        double[][] add=new double[rows+1][cols];
        for(int i=0;i<rows;i++){
            add[i]=data[i].clone();
        }
        return new Matrix(add);
    }
    /**
     * 在矩阵的第i行处插入矩阵a
     * @param i
     * @param a
     * @return this insert a
     */
    public Matrix addRow(int i,Matrix a){
        double[][] add=new double[rows+a.rows][cols];
        for(int s=0;s<i;s++){
            add[s]=data[s].clone();
        }
        for(int s=i;s<i+a.rows;s++){
            add[s]=a.data[s-i].clone();
        }
        for(int s=i+a.rows;s<rows+a.rows;s++){
            add[s]=data[s-a.rows].clone();
        }
        return new Matrix(add);
    }
    /**
     * 在矩阵的最后一行后增加矩阵a
     * @param a
     * @return this ++ a
     */
    public Matrix addRow(Matrix a){
        return addRow(rows, a);
    }
    /**
     * 给矩阵添加一列0
     * @return this ++ {0}
     */
    public Matrix addCol(){
        double[][] add=new double[rows][cols+1];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                add[i][j]=data[i][j];
            }
        }
        return new Matrix(add);
    }
    /**
     * 在矩阵的第i列处插入矩阵a
     * @param i
     * @param a
     * @return this insert a
     */
    public Matrix addCol(int i,Matrix a){
        double[][] add=new double[rows][cols+a.cols];
        for(int r=0;r<rows;r++){
            for(int c=0;c<i;c++){
                add[r][c]=data[r][c];
            }
        }
        for(int r=0;r<rows;r++){
            for(int c=i;c<i+a.cols;c++){
                add[r][c]=a.data[r][c-i];
            }
        }
        for(int r=0;r<rows;r++){
            for(int c=i+a.cols;c<cols+a.cols;c++){
                add[r][c]=data[r][c-a.cols];
            }
        }
        return new Matrix(add);
    }
    /**
     * 在矩阵的最后一列后增加矩阵a
     * @param a
     * @return this ++ a 
     */
    public Matrix addCol(Matrix a){
        return addCol(cols, a);
    }
    /**
     * 判断是否为正交矩阵
     * @return 是否为正交矩阵
     */
    public boolean isOrthogonal(){
        return multiply(transpose()).equals(identity(rows));
    }
    
    /**
     * 交换矩阵的i,j两行
     * @param i
     * @param j
     */
    public void swapRow(int i,int j){
        for(int c=0;c<cols;c++){
            double t=data[i][c];
            data[i][c]=data[j][c];
            data[j][c]=t;
            
        }
    }
    /**
     * 交换矩阵的i,j两列
     * @param i
     * @param j
     */
    public void swapCol(int i,int j){
        for(int r=0;r<rows;r++){
            double t=data[r][j];
            data[r][j]=data[r][i];
            data[r][i]=t;
        }
    }
    /**
     * 得到矩阵的增广矩阵
     * @param col1
     * @return this ++ a
     */
    public Matrix augmented(Matrix col1){
        return addCol(col1);
    }
    /**
     * 判断是否为对角矩阵
     * @return 是否为对角矩阵
     */
    public boolean isDiagonal(){
        if(rows!=cols){
            return false;
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]!=0&&i!=j){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isDiagonal(Matrix a){
        return a.isDiagonal();
    }
    /**
     * 判断是否为方阵
     * @return 是否为方阵
     */
    public boolean isSquare(){
        return rows==cols;
    }

    public static boolean isSquare(Matrix a){
        return a.isSquare();
    }
    /**
     * 判断this是否比b大
     * @param b
     * @return this > b
     */
    public boolean isGreater(Matrix b){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]<=b.data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 判断this是否比b小
     * @param b
     * @return this < b
     */
    public boolean isLess(Matrix b){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]>=b.data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 比较两矩阵的大小
     * @param b
     * @return 1(this>b)||0(this==b)||-1(this<b)
     * @throws new Error("cannot compare");
     */
    public int compareTo(Matrix b){
        if(isGreater(b)){
            return 1;
        }else if(isLess(b)){
            return -1;
        }else if(equals(b)){
            return 0;
        }else{
            throw new Error("no result");
        }
    }

    public static int compare(Matrix a,Matrix b){
        return a.compareTo(b);
    }
    
    /**
     * 逻辑和
     * @param b
     * @return this | b
     */
    public Matrix logicAdd(Matrix b){
        Matrix c=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=(int)data[i][j]|(int)b.data[i][j];
            }
        }
        return c;
    }
    /**
     * 逻辑积
     * @param b
     * @return 逻辑相乘后的矩阵
     */
    public Matrix logicMultiply(Matrix b){
        Matrix c=new Matrix(rows);
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                for(int k=0;k<rows;k++){
                    c.data[i][j]=(int)c.data[i][j]|((int)data[i][j]&(int)b.data[i][j]);
                }
            }
        }
        return c;
    }
    /**
     * 将矩阵的第i行替换为x
     * @param i
     * @param x
     * @return 替换后的矩阵
     */
    public Matrix replaceRow(int i,double[] x){
        Matrix t=new Matrix(data);
        t.data[i]=x.clone();
        return t;
    }
    /**
     * 将矩阵的第i列替换为x
     * @param i
     * @param x
     * @return 替换后的矩阵
     */
    public Matrix replaceCol(int i,double[] x){
        Matrix t=new Matrix(data);
        for(int s=0;s<rows;t.data[s][i]=x[s++]);
        return t;
    }
    /**
     * 克莱默法则求方程组的解
     * @param coeff
     * @param x
     * @return 解数组
     */
    public static double[] solution(Matrix coeff,double[] x){
        double[] solution=new double[coeff.rows];
        double det=determine(coeff);
        if(det!=0){
            for(int i=0;i<coeff.rows;i++){
                solution[i]=determine(coeff.replaceCol(i,x))/det;
            }
            return solution;
        }else{
            return null;
        }
    }
    /**
     * 删除矩阵的第i行
     * @param i
     * @return 删除后的矩阵
     */
    public Matrix removeRow(int i){
        double[][] temp=new double[rows-1][cols];
        for(int s=0;s<rows;s++){
            if(s!=i){
                temp[s-(s<i?0:1)]=data[s].clone();
            }
        }
        return new Matrix(temp);
    }
    /**
     * 删除矩阵的第i列
     * @param i
     * @return 删除后的矩阵
     */
    public Matrix removeCol(int i){
        double[][] temp=new double[rows][cols-1];
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(c!=i){
                    temp[r][c-(c<i?0:1)]=data[r][c];
                }
            }
        }
        return new Matrix(temp);
    }
    /**
     * 得到矩阵的第i行
     * @param i
     * @return 第i行的数组
     */
    public double[] rowAt(int i){
        return data[i].clone();
    }
    /**
     * 得到矩阵的第i列
     * @param j
     * @return 第i列的数组
     */
    public double[] colAt(int j){
        double[] t=new double[rows];
        for(int s=0;s<rows;t[s]=data[s++][j]);
        return t;
    }
    /**
     * 减化矩阵至行简行阶梯形式。
     * 该方法通过高斯-约旦消元法，将输入的二维数组（矩阵）转化为行简行阶梯形式。
     * 这一过程用于线性代数中解线性方程组或求矩阵的逆矩阵。
     * 
     * @param matrix 一个二维数组，代表待处理的矩阵。矩阵的行数由rows变量定义，列数由cols变量定义。
     */
    private void Reduce(double[][] matrix) {
        /* 用于标识当前操作的列 */
        int pivot = 0;
        /* 遍历每一行 */
        for (int r = 0; r < rows; r++, pivot++) {
            /* 如果当前列超过矩阵的列数，结束操作 */
            if (cols <= pivot) return;
            /* 寻找当前列中第一个非零元素所在的行 */
            int i = r;
            for (; matrix[i][pivot] == 0; ) {
                i++;
                /* 如果到达最后一行但仍没有找到非零元素，则向下一列移动 */
                if (i == rows) {
                    i = r;
                    pivot++;
                    /* 如果下一列超过矩阵的列数，结束操作 */
                    if (cols == pivot) return;
                }
            }
            /* 交换当前行（r）和找到的非零元素所在行（i）的当前列元素 */
            double temp = matrix[r][pivot];
            matrix[r][pivot] = matrix[i][pivot];
            matrix[i][pivot] = temp;
            /* 如果当前行的当前列元素非零，则对该行的其他元素进行标准化 */
            if (matrix[r][pivot] != 0) {
                double scalar = matrix[r][pivot];
                /* 将当前行的每个元素除以当前列元素，使其变为1 */
                for (int j = 0; j < cols; matrix[r][j++]/=scalar);
            }
            /* 对除当前行外的其他行进行消元操作 */
            for (int k = 0; k < rows; k++) {
                if (k != r) {
                    double scalar = matrix[k][pivot];
                    /* 将其他行的当前列元素乘以当前行的当前列元素，并从其他行的每个元素中减去 */
                    /* 以此消去其他行在当前列上的元素，实现高斯-约旦消元 */
                    for (int j = 0; j < cols; matrix[k][j] -= scalar * matrix[r][j++]);
                }
            }
        }
    }
    /**
     * 将矩阵转化为行最简矩阵
     * @return 转化后的矩阵
     */
    public Matrix toReducedEchelon(){
        double[][] ech=clone().data;
        Reduce(ech);
        return new Matrix(ech);
    }
    private static boolean isAllZero(double[][] p,int i){
        for(int j=0;j<p[0].length;j++){
            if(p[i][j]!=0){
                return false;
            }
        }
        return true;
    }
    /**
     * 求方阵的秩
     * @return rank(this)
     */
    public int rank(){
        int rank=0;
        double[][] t=toReducedEchelon().data;
        for(int i=0;i<t.length;i++){
            if(!isAllZero(t,i)){
                rank++;
            }
        }
        return rank;
        /*Matrix p=clone();
        int rank=0;
        for(int i=0;i<cols;i++){
            int r=0,c=0;
        out:for(c=i;c<cols;c++){
                for(r=i;r<rows;r++){
                    if(Math.abs(p.data[r][c])!=0){
                        break out;
                    }
                }
            }
            if(r<rows&&c<cols){
                for(int j=c;j<cols;j++){
                    double t=p.data[r][j];
                    p.data[r][j]=p.data[i][j];
                    p.data[i][j]=t;
                }
                for(int j=i+1;j<rows;j++){
                    double a=p.data[j][c]/p.data[i][c];
                    for(int k=c;k<cols;k++){
                        p.data[j][k]-=a*p.data[i][k];
                    }
                }
                rank++;
            }else{
                break;
            }
        }
        return rank;*/
    }
    public static int rank(Matrix a){
        return a.rank();
    }
    /**
     * 判断矩阵是否为行满秩
     * @return this是否为行满秩
     */
    public boolean isFullRank(){
        return rank()==rows;
    }
    public static boolean isFullRank(Matrix a){
        return a.isFullRank();
    }
    /**
     * 通过高斯消元法求解方程组的解,系数矩阵为coeff,等式右边为b
     * @param coeff 系数矩阵，代表线性方程组的系数部分。
     * @param b 右侧常数矩阵，代表线性方程组的右侧常数项。
     * @return 得到的解数组，解的个数与方程组的未知数一致。
     * @throws Error 如果方程组无解或有无穷多解，则抛出错误。
     */
    public static double[] gaussSolution(Matrix coeff,double[] b){
        int m=coeff.rows,n=coeff.cols;
        Matrix expand=coeff.addCol(new Matrix(b).transpose());
        if(n>m){
            throw new Error("NO SOLUTION");
        }else{
            int r1=rank(coeff),r2=rank(expand);
            if(r1<r2){
                throw new Error("NO SOLUTION");
            }else{
                if(r1<n){
                    throw new Error("INFINITE SOLUTIONS");
                }else{
                    Matrix re=expand.toReducedEchelon();
                    Matrix sub=copyOf(re,n,n);
                    double[] B=new double[n];
                    for(int i=0;i<n;b[i]=re.data[i++][n]);
                    return solution(sub,B);
                }
            }
        }
    }
    /**
     * 标准化矩阵的第一行向量。
     * 该函数对矩阵a的第一行向量进行标准化处理，使其模长（欧几里得距离）为1。
     * 注意：该函数仅适用于矩阵a的第一行非零元素。
     *
     * @param a 待处理的矩阵，假设为二维数组表示。
     */
    private static void normalize(Matrix a){
        double n=0;
        // 计算第一行向量的模长的平方
        for(double x:a.data[0]){
            n+=x*x;
        }
        // 求模长的平方根，即求模长
        n=Math.sqrt(n);
        // 标准化处理，使第一行向量的模长为1
        for(int i=0;i<a.data[0].length;i++){
            a.data[0][i]/=n;
        }
    }
    /**
     * 计算给定矩阵的特征值。
     * 
     * @param a 待计算特征值的矩阵。
     * @param iteration 迭代次数，用于迭代计算特征值。
     * @return 计算得到的特征值。
     */
    public static double eigenValue(Matrix a,int iteration){
        // 初始化一个与矩阵a列数相等的1行向量v
        Matrix v=new Matrix(1,a.cols);
        // 迭代计算特征值
        for(int i=0;i<iteration;i++){
            // 计算p=v*a
            Matrix p=v.multiply(a);
            // 对p进行标准化处理
            normalize(p);
            // 更新v为p的拷贝
            v=p.clone();
        }
        // 返回最终计算得到的特征值
        return v.multiply(a).rowAt(0)[0];
    }
    public String toString(){
        String ans="[";
        for(int i=0;i<rows;i++){
            ans+=Arrays.toString(data[i])+(i==rows-1?"]\n":",\n ");
        }
        return ans;
    }
}