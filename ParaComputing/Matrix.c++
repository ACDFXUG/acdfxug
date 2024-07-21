#include <iostream>
#include <vector>
#include <pthread.h>
#define Array std::vector<long long>
#define Matrix std::vector<Array > 

int order=1000,thread_count=8;

Matrix A(order,Array(order,0)),
B(order,Array(order,0)),C(order,Array(order,0));

pthread_mutex_t mutex=PTHREAD_MUTEX_INITIALIZER;

void printM(Matrix x){
    for(int i=0;i<order;i++){
        for(int j=0;j<order;j++){
            printf(j==order-1?"%d\n":"%d ",x[i][j]);
        }
    }
}

void *multiply(void *arg){
    int thread_id=*(int *)arg,
    block_size=order/thread_count,
    firstRow=thread_id*block_size,
    lastRow=firstRow+block_size,i,j,k;

    for(i=firstRow;i<lastRow;i++){
        for(j=0;j<order;j++){
            long long sum=0;
            for(k=0;k<order;k++){
                sum+=A[i][k]*B[k][j];
            }
            pthread_mutex_lock(&mutex);
            C[i][j]=sum;
            pthread_mutex_unlock(&mutex);
        }
    }

    return NULL;
}

int main(){
    int N;
    printf("input order,expon and thread\n");
    std::cin>>order>>N>>thread_count;
    for(int i=0;i<order;i++){
        for(int j=0;j<order;j++){
            A[i][j]=rand()%10;
        }
    }
    B=A;
    for(int s=1;s<N;s++){
        pthread_t *threadID=new pthread_t[thread_count];
        int *value=new int[thread_count];
        for(int i=0;i<thread_count;i++){
            value[i]=i;
        }
        for(int i=0;i<thread_count;i++){
            pthread_create(threadID+i,NULL,multiply,value+i);
        }
        for(int i=0;i<thread_count;i++){
            pthread_join(threadID[i],NULL);
        }
        A=C;
        delete[] threadID,value;
    }
    printM(A);
}
