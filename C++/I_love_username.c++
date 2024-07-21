#include <iostream>
#include <queue>
#define PqueueI std::priority_queue<int>

int indexOf(PqueueI a,int i){
    PqueueI temp=a;
    for(int s=0;s<i;s++){
        temp.pop();
    }
    return temp.top();
}

int main(){
    int n,t=0;
    std::cin>>n;
    int *score=new int[n];
    for(int i=0;i<n;scanf("%d",score+i++));
    PqueueI coder;
    coder.push(score[0]);
    for(int i=1;i<n;i++){
        int l=coder.size();
        int max=coder.top(),min=indexOf(coder,l-1);
        if(score[i]>max||score[i]<min){
            t++;
        }
        coder.push(score[i]);
    }
    printf("%d\n",t);
    delete[] score;
}
