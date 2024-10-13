#include <iostream>
#include <stdlib.h>
#include <pthread.h>
#include <cmath>
#define ll long long
#define Thread pthread_t

int thread_count=8;
ll n=100000;
double sum=0,x=10;
pthread_mutex_t mutex=PTHREAD_MUTEX_INITIALIZER;

double neg(ll n){
    return (n&1)?-1.0:1.0;
}

double taylor(double x,ll i){
    double ans=1,x2=x*x;
    for(ll s=2;s<=2*i;s+=2){
        ans*=(x2/(s*(s+1)));
    }
    return ans*x;
    /* 
    for(int s=1;s<=2*i+1;s++){
        ans*=(x/s);
    }
    return ans;
    */
}

void *Thread_Sum(void *rank){
    int my_rank=*(int*)rank;
    ll i,my_n=n/thread_count,
    my_first_i=my_n*my_rank,
    my_last_i=my_first_i+my_n;
    double my_sum=0;

    for(i=my_first_i;i<my_last_i;i++){
        my_sum+=neg(i)*taylor(x,i);
    }
    
    pthread_mutex_lock(&mutex);
    sum+=my_sum;
    pthread_mutex_unlock(&mutex);

    return NULL;
}

int main(){
    printf("input radius x,scale n,threads:\n");
    std::cin>>x>>n>>thread_count;
    Thread *threadID=new Thread[thread_count];
    int *value=new int[thread_count];
    for(int i=0;i<thread_count;i++){
        value[i]=i;
    }
    for(int i=0;i<thread_count;i++){
        pthread_create(threadID+i,NULL,Thread_Sum,value+i);
    }
    for(int i=0;i<thread_count;i++){
        pthread_join(threadID[i],NULL);
    }

    printf("sin(%lf)=%.20lf\n",x,sum);
    delete[] threadID,value;
}


