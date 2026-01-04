package Java.LeetCode;

public class 设计循环队列 {
    @SuppressWarnings("unused")
    private static class MyCircularQueue{
        final int data[],capacity;
        int head,tail,size;
        MyCircularQueue(int k){
            this.capacity=k;
            this.data=new int[k];
            for(int i=0;i<k;data[i++]=-1);
            this.head=this.tail=0;
            this.size=0;
        }
        boolean enQueue(int value) {
            if(size==capacity) return false;
            data[tail]=value;
            tail=(tail+1)%capacity;
            ++size;
            return true;
        }
        boolean deQueue() {
            if(size==0) return false; 
            data[head]=-1;
            head=(head+1)%capacity;
            --size;
            return true;
        }
        int Front() {
            return data[head];
        }
        int Rear(){
            return data[(tail-1+capacity)%capacity];
        }
        boolean isEmpty() {
            return size==0;
        }
        boolean isFull() {
            return size==capacity;
        }
    }
    public static void main(String[] args) {
        MyCircularQueue mcq=new MyCircularQueue(3);
        System.out.println(mcq.enQueue(1));
        System.out.println(mcq.enQueue(2));
        System.out.println(mcq.enQueue(3));
        System.out.println(mcq.enQueue(4));
        System.out.println(mcq.Rear());
        System.out.println(mcq.isFull());
        System.out.println(mcq.deQueue());
        System.out.println(mcq.enQueue(4));
        System.out.println(mcq.Rear());
    }
}
