#include <iostream>
using namespace std;

int SEQ(int a[],int n,int key) {
    int i;
    a[0]=key;
    for (i=n; a[i]!=key;i--);
    return i;
}

int BIN(int a[],int n,int key) {
    int low=1,high=n,mid;
    int count=0;
    for(;low<=high;) {
        count++;
        mid=(low+high)/2;
        if (a[mid]==key) {
            cout<<"search times: "<<count<<endl;
            return mid;
        }else if(a[mid]>key){
            high=mid-1;
        } else {
            low=mid+1;
        }
    }
    cout << "search times: " << count << endl;
    return 0;
}

int main() {
    int a[] = {0, 5, 13, 19, 21, 37, 56, 64, 75, 80, 88, 92};
    cout << "sequential search of 21: " << SEQ(a, 11, 21) << endl;
    cout << "sequential search of 85: " << SEQ(a, 11, 85) << endl;
    cout <<  BIN(a, 11, 21) <<" is binary search of 21"<< endl;
    cout  << BIN(a, 11, 85)<< " is binary search of 85" << endl;
}
