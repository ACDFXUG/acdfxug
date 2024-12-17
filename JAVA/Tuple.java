package JAVA;

import java.util.*;
import java.util.stream.*;
import java.lang.reflect.*;

public class Tuple implements Iterable<Object>,Cloneable,Comparable<Tuple> {
    private int length;
    private List<Object> tuple;
    public Tuple(){
        this.length=0;
        this.tuple=new ArrayList<>();
    }
    public Tuple(int capacity){
        this.length=0;
        this.tuple=new ArrayList<>(capacity);
    }
    public Tuple(Object... args){
        this.length=args.length;
        this.tuple=new ArrayList<>();
        for(Object arg:args){
            tuple.add(arg);
        }
    }
    public Tuple(Collection<Object> args){
        this(args.toArray());
    }
    public Tuple(List<Object> args){
        this(args.toArray());
    }
    public int size(){
        return length;
    }
    public boolean isEmpty(){
        return length==0;
    }
    public boolean contains(Object arg){
        return tuple.contains(arg);
    }
    public boolean containsClass(String classSimpleName){
        for(String clazz:getClassSimpleNameArray()){
            if(clazz.equals(classSimpleName)){
                return true;
            }
        }
        return false;
    }
    public Object[] toArray(){
        return tuple.toArray();
    }
    public String toString(){
        StringJoiner str=new StringJoiner(", ","[","]");
        for(Object obj:this){
            String className=obj.getClass().getSimpleName();
            str.add(className+":"+obj);
        }
        return str.toString();
    }
    public void clear(){
        length=0;
        tuple.clear();
    }
    public boolean equals(Object tp){
        if(this==tp){
            return true;
        }
        if(tp==null||!(tp instanceof Tuple)){
            return false;
        }
        if(length!=((Tuple)tp).length){
            return false;
        }
        return tuple.equals(((Tuple)tp).tuple);
    }
    public int hashCode(){
        return tuple.hashCode();
    }
    public int compareTo(Tuple tp){
        if(length!=tp.length){
            throw new Error("LengthNotEqualException: "+length+"!="+tp.length);
        }
        Class<?>[] ty1=getClassArray(),ty2=tp.getClassArray();
        for(int i=0;i<length;i++){
            if(ty1[i]!=ty2[i]){
                throw new Error("ClassNotEqualException: "+ty1[i].
                getSimpleName()+"!="+ty2[i].getSimpleName());
            }
        }
        for(int i=0;i<length;i++){
            Class<?> clazz=getClassOf(i);
            if(!Comparable.class.isAssignableFrom(clazz)){
                throw new Error("ClassNotComparableException");
            }else{
                try{
                    Method compareTo=clazz.getMethod("compareTo",clazz);
                    try{
                        int com=(int)compareTo.invoke(tuple.get(i),tp.tuple.get(i));
                        if(com!=0){
                            return com>0?1:-1;
                        }else{
                            continue;
                        }
                    }catch(IllegalAccessException | InvocationTargetException i_e){
                        i_e.printStackTrace();
                    }
                }catch(NoSuchMethodException | SecurityException nsme_se){
                    nsme_se.printStackTrace();
                }
            }
        }
        return 0;
    }
    public int indexOf(Object arg){
        return tuple.indexOf(arg);
    }
    public int indexOfRange(Object arg,int fromIndex,int toIndex){
        for(int i=fromIndex;i<toIndex;i++){
            if(tuple.get(i).equals(arg)){
                return i;
            }
        }
        return -1;
    }
    public int lastIndexOf(Object arg){
        return tuple.lastIndexOf(arg);
    }
    public int lastIndexOfRange(Object arg,int fromIndex,int toIndex){
        for(int i=toIndex-1;i>=fromIndex;i--){
            if(tuple.get(i).equals(arg)){
                return i;
            }
        }
        return -1;
    }
    public Tuple clone(){
        return new Tuple(tuple);
    }
    public Iterator<Object> iterator(){
        return tuple.iterator();
    }
    public Class<?> getClassOf(int index){
        return tuple.get(index).getClass();
    }
    public String getClassNameOf(int index){
        return getClassOf(index).getName();
    }
    public String getClassSimpleNameOf(int index){
        return getClassOf(index).getSimpleName();
    }
    public Class<?>[] getClassArray(){
        Class<?>[] type=new Class[length];
        for(int i=0;i<length;i++){
            type[i]=getClassOf(i);
        }
        return type;
    }
    public String[] getClassNameArray(){
        String[] type=new String[length];
        for(int i=0;i<length;i++){
            type[i]=getClassOf(i).getName();
        }
        return type;
    }
    public String[] getClassSimpleNameArray(){
        String[] type=new String[length];
        for(int i=0;i<length;i++){
            type[i]=getClassOf(i).getSimpleName();
        }
        return type;
    }
    public Object get(int index){
        return tuple.get(index);
    }
    public<T> Tuple add(T arg){
        tuple.add(arg);
        ++length;
        return this;
    }
    // public Tuple add(Object... args){
    //     for(Object arg:args){
    //         add(arg);
    //     }
    //     return this;
    // }
    
    public<T> Tuple add(int index,T arg){
        if(index<0||index>length){
            throw new IndexOutOfBoundsException();
        }
        if(index==length){
            add(arg);
        }else{
            tuple.add(index,arg);
            ++length;
        }
        return this;
    }
    public Tuple addAll(Collection<Object> args){
        return new Tuple(tuple.addAll(args));
    }
    public Tuple remove(Object arg){
        tuple.remove(arg);
        --length;
        return this;
    }
    // public Tuple remove(Object... args){
    //     for(Object arg:args){
    //         remove(arg);
    //     }
    //     return this;
    // }

    public Tuple remove(int index){
        if(index<0||index>=length){
            throw new IndexOutOfBoundsException();
        }
        tuple.remove(index);
        --length;
        return this;
    }
    public Tuple removeAll(Collection<Object> args){
        return new Tuple(tuple.removeAll(args));
    }
    public Tuple retainAll(Collection<Object> args){
        return new Tuple(tuple.retainAll(args));
    }

    public Tuple set(int index,Object arg){
        if(index<0||index>=length){
            throw new IndexOutOfBoundsException();
        }
        tuple.set(index,arg);
        return this;
    }
    public Tuple subTuple(int fromIndex,int toIndex){
        return new Tuple(tuple.subList(fromIndex,toIndex));
    }
    static public Tuple copyOf(Collection<Object> args){
        return new Tuple(args);
    }
    static public Tuple of(Object... args){
        return new Tuple(args);
    }
    public Stream<Object> stream(){
        return tuple.stream();
    }
}
