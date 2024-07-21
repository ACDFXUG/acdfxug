#include <iostream>
#include <stdlib.h>
#include <vector>
#include <cmath>
#include <pthread.h>

int thread_count=8;

struct Matrix{
    int rows,cols;
    std::vector<std::vector<double> > data;
};

struct Thread{
    Matrix* result;
    const Matrix* matrix;
    int power,threadID,numThreads;
};

Matrix multiply(const Matrix& A,const Matrix& B){
    Matrix result;
    result.rows = A.rows;
    result.cols = B.cols;
    result.data.resize(A.rows, std::vector<double>(B.cols, 0));
    for(int i=0;i<A.rows;i++){
        for(int j=0;j<B.cols;j++){
            for(int k=0;k<A.cols;k++){
                result.data[i][j]+=A.data[i][k]*B.data[k][j];
            }
        }
    }
    return result;
}

Matrix matrixPower(const Matrix& matrix, int power, int numThreads) {
    if(power==1){
        return matrix;
    }

    Matrix result=matrixPower(matrix,power>>1,numThreads);
    result=multiply(result,result);

    if ((power&1)==1) {
        result=multiply(result,matrix);
    }

    return result;
}

void *calMatPow(void *args){
    Thread* ARGS=(Thread *)args;
    int firstRow=(ARGS->threadID*ARGS->result->rows)/ARGS->numThreads,
    lastRow=((ARGS->threadID+1)*ARGS->result->rows)/ARGS->numThreads;
    Matrix Res=matrixPower(*(ARGS->matrix),ARGS->power,ARGS->numThreads);
    for(int i=firstRow;i<lastRow;i++){
        ARGS->result->data[i]=Res.data[i];
    }

    return NULL;
}

void printM(Matrix result){
    for(int i=0;i<result.rows;i++){
        for(int j=0;j<result.cols;j++){
            printf(j==(result.cols-1)?"%.2f\n":"%.2f ",result.data[i][j]);
        }
    }
}

int main(){
    Matrix input,result;
    int M,n;
    printf("Enter the matrix order,the power and the thread_count\n");
    std::cin>>M>>n>>thread_count;
    result.rows=result.cols=input.rows=input.cols=M;
    input.data.resize(M,std::vector<double>(M,0));
    result.data.resize(M,std::vector<double>(M,0));
    for(int i=0;i<M;i++){
        for(int j=0;j<M;j++){
            input.data[i][j]=rand()%10;
        }
    }
    pthread_t *threads=new pthread_t[thread_count];
    Thread *ARGS=new Thread[thread_count];
    for(int i=0;i<thread_count;i++){
        ARGS[i].result=&result;
        ARGS[i].matrix=&input;
        ARGS[i].power=n;
        ARGS[i].threadID=i;
        ARGS[i].numThreads=thread_count;
    }
    for(int i=0;i<thread_count;i++){
        pthread_create(threads+i,NULL,calMatPow,(void *)(ARGS+i));
    }
    for(int i=0;i<thread_count;i++){
        pthread_join(threads[i],NULL);
    }
    printM(result);
    delete[] threads,ARGS;
}
