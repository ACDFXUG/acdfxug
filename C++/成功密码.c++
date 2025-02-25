#include <iostream>
#include <pthread.h>
#include <cmath>
#define EULER_GAMMA 0.577215664901532860606512090082l
using ll=long long;
using ld=long double;

#pragma GCC optimize(2)

const int thread_count=20;
ll n=10;
ld sum=0,x=0.5;
pthread_mutex_t mutex=PTHREAD_MUTEX_INITIALIZER;

ld item(ld X,ll i){
    ld mul=1.l;
    for(;i>0;i>>=1){
        if(i&1) mul*=X;
        X*=X;
    }
    return mul;
}

void *thread_sum(void *rank){
    int my_rank=*(int *)rank;
    ll i,my_n=n/thread_count,
        my_first_i=my_n*my_rank,
        my_last_i=my_first_i+my_n;
    ld my_sum=0.l;
    for(i=my_first_i;i<my_last_i;++i){
        if(i!=0)
            my_sum+=item(x,i)/i;
    }
    pthread_mutex_lock(&mutex);
    sum+=my_sum;
    pthread_mutex_unlock(&mutex);
    return nullptr;
}

int main(){
    std::cin>>x>>n;
    if(x==1.l&&n>100000z){
        printf("%.4Lf",logl(n)+EULER_GAMMA);
        return 0;
    }
    if(n>100000z) n=100000z;
    pthread_t thread[thread_count];
    int value[thread_count];
    for(int i=0;i<thread_count;i++){
        value[i]=i;
        pthread_create(thread+i,nullptr,thread_sum,value+i);
    }
    for(int i=0;i<thread_count;i++){
        pthread_join(thread[i],nullptr);
    }
    printf("%.4Lf",sum);
}