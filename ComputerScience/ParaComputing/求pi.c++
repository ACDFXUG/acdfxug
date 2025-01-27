#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include <iomanip>
#include <time.h>
#include <pthread.h>

using namespace std;

long long n;
int thread_count;
double sum = 0.0;
pthread_mutex_t mutex, lock;

void* Thread_sum(void* rank)
{
    int my_rank = *(int *)rank;
    double factor, my_sum = 0.0;
    long long i;
    long long my_n = n/thread_count;
    long long my_first_i = my_n * my_rank;
    long long my_last_i = my_first_i + my_n;

    if(my_first_i % 2==0) /* my_first_i is even */
        factor = 1.0;
    else    /* my_first_i is odd */
        factor = -1.0;
    
    for(i = my_first_i; i < my_last_i; i ++, factor = -factor)
        my_sum += factor/(2*i+1);
    
    pthread_mutex_lock(&mutex);
    sum += my_sum;
    pthread_mutex_unlock(&mutex);
    
    return NULL;
    /* Thread_sum */
}

int main(void)
{
    struct timeval time1, time2;
    cout << "输入n值:" << endl;
    cin >> n ;
    cout << "输入线程数：" << endl;
    cin >> thread_count ;
    
    gettimeofday(&time1, NULL);
    
    pthread_t thread_ID[thread_count];
    
    int value[thread_count];
    for(int i = 0; i < thread_count; i ++)
        value[i] = i;
    
    //Create the thread, passing &value for the argument.
    for(int i = 0; i < thread_count; i ++)
        pthread_create(&thread_ID[i], NULL, Thread_sum, &value[i]);
    
    //Wait for the thread to terminate.
    for(int i = 0; i < thread_count; i ++)
        pthread_join(thread_ID[i], NULL);
    
    sum *= 4.0;
    printf("%.20lf\n", sum);
    gettimeofday(&time2, NULL);
    printf("s: %ld, ms: %ld\n", time2.tv_sec-time1.tv_sec, (time2.tv_sec*1000 + time2.tv_sec/1000)-(time1.tv_sec*1000 + time1.tv_sec/1000));
    
    return 0;
}