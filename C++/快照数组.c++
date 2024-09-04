#include <iostream>
#include <unordered_map>
using namespace std;

int *deep_copy(int *arr,int len){
    int *new_arr=new int[len];
    for(int i=0;i<len;i++)
        new_arr[i]=arr[i];
    return new_arr;
}

class SnapshotArray{
private:
    const int length;
    int *snap_arr,snap_id;
    unordered_map<int,int*> snapshot;
    
public:
    SnapshotArray(int length):
        length(length),
        snap_arr(new int[length]()),
        snap_id(0),snapshot(){}
    void set(int index,int val){
        snap_arr[index]=val;
    }
    int snap(){
        snapshot[snap_id]=deep_copy(snap_arr,length);
        return snap_id++;
    }
    int get(int index,int snap_id){
        return snapshot[snap_id][index];
    }
    ~SnapshotArray(){
        delete[] snap_arr;
    }
};

int main(){
    SnapshotArray *sa=new SnapshotArray(3);
    sa->set(0,5);
    printf("%d\n",sa->snap());
    sa->set(0,6);
    printf("%d\n",sa->get(0,0));
}