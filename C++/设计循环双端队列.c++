#include <iostream>
#include <print>

struct MyCircularDeque{
    int *data;
    int front,end;
    const int capacity;
    int size;
    MyCircularDeque(int &&k):capacity(k){
        this->data=new int[k];
        for(int i=0;i<k;data[i++]=-1);
        this->front=this->end=0;
        this->size=0;
    }
    ~MyCircularDeque(){
        delete[] this->data;
    }
    bool insertFront(int value) {
        if (size == capacity) return false;
        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        size++;
        return true;
    }
    bool insertLast(int value) {
        if (size == capacity) return false;
        data[end] = value;
        end = (end + 1) % capacity;
        size++;
        return true;
    }
    bool deleteFront() {
        if (size == 0) return false;
        data[front] = -1;
        front = (front + 1) % capacity;
        size--;
        return true;
    }
    bool deleteLast() {
        if (size == 0) return false;
        end = (end - 1 + capacity) % capacity;
        data[end] = -1;
        size--;
        return true;
    }
    int getFront() {
        return data[front];
    }
    int getRear() {
        return data[(end-1+capacity)%capacity];
    }
    bool isEmpty() {
        return size==0;
    }
    bool isFull() {
        return size==capacity;
    }
};

int main(){
    MyCircularDeque *mcd=new MyCircularDeque(3);
    std::println("{}",mcd->insertLast(1));
    std::println("{}",mcd->insertLast(2));
    std::println("{}",mcd->insertFront(3));
    std::println("{}",mcd->insertFront(4));
    std::println("{}",mcd->getRear());
    std::println("{}",mcd->isFull());
    std::println("{}",mcd->deleteLast());
    std::println("{}",mcd->insertFront(4));
    std::println("{}",mcd->getFront());
}