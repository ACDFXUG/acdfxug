package Java.Self;

import java.util.*;
import static java.lang.Math.*;


public class GenericMatrix<T extends Number> implements Cloneable {
    private int rows,cols;
    private T[][] data;
    // private int negative(int n){
    //     return (n&1)==0?1:-1;
    // }
    @SuppressWarnings("unchecked")
    public GenericMatrix(int row,int col){
        this.rows=row;
        this.cols=col;
        this.data=(T[][])new Number[row][col];
    }
    public GenericMatrix(int order){
        this(order,order);
    }
    public GenericMatrix(T[][] mat){
        this.rows=mat.length;
        this.cols=mat[0].length;
        this.data=mat.clone();
    }
    @SuppressWarnings("unchecked")
    public GenericMatrix(T[] array){
        this.rows=1;
        this.cols=array.length;
        this.data=(T[][])new Number[1][cols];
    }
    public GenericMatrix(GenericMatrix<T> mat){
        this.rows=mat.rows;
        this.cols=mat.cols;
        this.data=mat.data.clone();
    }
    public T[][] toArray(){
        return data.clone();
    }
    public int getRow(){
        return rows;
    }
    public int getCol(){
        return cols;
    }
    public void init(T x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]=x;
            }
        }
    }
    public void init(){
        init(null);
    }
    public void inputAt(int x,int y,T z){
        data[x][y]=z;
    }
    public T valueAt(int x,int y){
        return data[x][y];
    }
    public boolean equals(Object mat){
        if(this==mat) return true;
        if(mat==null||!(mat instanceof GenericMatrix<?>)) return false;
        if(this.rows!=((GenericMatrix<?>)mat).rows||this.cols!=((GenericMatrix<?>)mat).cols) return false;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(!this.data[i][j].equals(((GenericMatrix<?>)mat).data[i][j])) return false;
            }
        }
        return true;
    }
    public int hashCode(){
        int hash=17;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                hash=31*hash+data[i][j].hashCode();
            }
        }
        return hash;
    }
    public GenericMatrix<T> clone(){
        return new GenericMatrix<T>(data);
    }
    public static<T extends Number> GenericMatrix<T> copyOf(GenericMatrix<T> mat,int row,int col){
        GenericMatrix<T> res=new GenericMatrix<>(row,col);
        for(int i=0;i<min(row,mat.rows);i++){
            for(int j=0;j<min(col,mat.cols);j++){
                res.data[i][j]=mat.data[i][j];
            }
        }
        return res;
    }
    //[x1-x2)-[y1-y2)
    public GenericMatrix<T> subMatrix(int x1,int y1,int x2,int y2){
        GenericMatrix<T> res=new GenericMatrix<>(x2-x1,y2-y1);
        for(int i=x1;i<x2;i++){
            for(int j=y1;j<y2;j++){
                res.data[i-x1][j-y1]=data[i][j];
            }
        }
        return res;
    }
    public String toString(){
        StringBuilder res=new StringBuilder().append("[");
        for(int i=0;i<rows;i++){
            res.append(Arrays.toString(data[i])
            +(i==rows-1?"]\n":",\n "));
        }
        return res.toString();
    }
    public GenericMatrix<T> spin(){
        GenericMatrix<T> res=new GenericMatrix<>(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                res.data[i][j]=data[rows-i-1][cols-j-1];
            }
        }
        return res;
    }
    @SuppressWarnings("unchecked")
    public GenericMatrix<T> add(GenericMatrix<T> mat){
        GenericMatrix<T> res=new GenericMatrix<>(rows,cols);
        Class<?> clazz=mat.data[0][0].getClass();
        if(Byte.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Byte)(byte)
                    (data[i][j].byteValue()+mat.data[i][j].byteValue());
                }
            }
        }else if(Short.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Short)(short)
                    (data[i][j].shortValue()+mat.data[i][j].shortValue());
                }
            }
        }else if(Integer.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Integer)
                    (data[i][j].intValue()+mat.data[i][j].intValue());
                }
            }
        }else if(Long.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Long)
                    (data[i][j].longValue()+mat.data[i][j].longValue());
                }
            }
        }else if(Float.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Float)
                    (data[i][j].floatValue()+mat.data[i][j].floatValue());
                }
            }
        }else{
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Double)
                    (data[i][j].doubleValue()+mat.data[i][j].doubleValue());
                }
            }
        }
        return res;
    }
    public GenericMatrix<T> addEqual(GenericMatrix<T> mat){
        data=add(mat).data;
        return this;
    }
    @SuppressWarnings("unchecked")
    public GenericMatrix<T> subtract(GenericMatrix<T> mat){
        GenericMatrix<T> res=new GenericMatrix<>(rows,cols);
        Class<?> clazz=mat.data[0][0].getClass();
        if(Byte.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Byte)(byte)
                    (data[i][j].byteValue()-mat.data[i][j].byteValue());
                }
            }
        }else if(Short.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Short)(short)
                    (data[i][j].shortValue()-mat.data[i][j].shortValue());
                }
            }
        }else if(Integer.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Integer)
                    (data[i][j].intValue()-mat.data[i][j].intValue());
                }
            }
        }else if(Long.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Long)
                    (data[i][j].longValue()-mat.data[i][j].longValue());
                }
            }
        }else if(Float.class==clazz){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Float)
                    (data[i][j].floatValue()-mat.data[i][j].floatValue());
                }
            }
        }else{
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(T)(Double)
                    (data[i][j].doubleValue()-mat.data[i][j].doubleValue());
                }
            }
        }
        return res;
    }
    public GenericMatrix<T> subtractEqual(GenericMatrix<T> mat){
        data=subtract(mat).data;
        return this;
    }
    @SuppressWarnings("unchecked")
    public GenericMatrix<T> multiply(GenericMatrix<T> mat){
        Class<?> clazz=mat.data[0][0].getClass();
        if(Byte.class==clazz){
            GenericMatrix<Byte> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    byte temp=0;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].byteValue()*mat.data[k][j].byteValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Short.class==clazz){
            GenericMatrix<Short> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    short temp=0;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].shortValue()*mat.data[k][j].shortValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Integer.class==clazz){
            GenericMatrix<Integer> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    int temp=0;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].intValue()*mat.data[k][j].intValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Long.class==clazz){
            GenericMatrix<Long> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    long temp=0L;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].longValue()*mat.data[k][j].longValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Float.class==clazz){
            GenericMatrix<Float> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    float temp=.0f;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].floatValue()*mat.data[k][j].floatValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }else{
            GenericMatrix<Double> res=new GenericMatrix<>(rows,mat.cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<mat.cols;j++){
                    double temp=.0;
                    for(int k=0;k<cols;k++){
                        temp+=data[i][k].doubleValue()*mat.data[k][j].doubleValue(); 
                    }
                    res.data[i][j]=temp;
                }
            }
            return (GenericMatrix<T>)res;
        }
    }
    //useless
    public GenericMatrix<T> multiplyEqual(GenericMatrix<T> mat){
        data=multiply(mat).data;
        return this;
    }
    @SuppressWarnings("unchecked")
    public GenericMatrix<T> multiply(T k){
        Class<?> clazz=data[0][0].getClass();
        if(Byte.class==clazz){
            GenericMatrix<Byte> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Byte)(byte)(data[i][j].byteValue()*k.byteValue());
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Short.class==clazz){
            GenericMatrix<Short> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Short)(short)(data[i][j].shortValue()*k.shortValue());
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Integer.class==clazz){
            GenericMatrix<Integer> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Integer)(data[i][j].intValue()*k.intValue());
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Long.class==clazz){
            GenericMatrix<Long> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Long)(data[i][j].longValue()*k.longValue());
                }
            }
            return (GenericMatrix<T>)res;
        }else if(Float.class==clazz){
            GenericMatrix<Float> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Float)(data[i][j].floatValue()*k.floatValue());
                }
            }
            return (GenericMatrix<T>)res;
        }else{
            GenericMatrix<Double> res=new GenericMatrix<>(rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    res.data[i][j]=(Double)(data[i][j].doubleValue()*k.doubleValue());
                }
            }
            return (GenericMatrix<T>)res;
        }
    }
    public GenericMatrix<T> multiplyEqual(T k){
        data=multiply(k).data;
        return this;
    }
}
