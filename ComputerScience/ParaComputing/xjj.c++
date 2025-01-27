#include <iostream>
#include <vector>
#include <pthread.h>
#include <cstdlib>
#include <ctime>

#define Array std::vector<long long>
#define Matrix std::vector<Array>

int order = 1000, thread_count = 8;
Matrix A(order, Array(order, 0)), B(order, Array(order, 0)), C(order, Array(order, 0));

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void printM(Matrix x){
    for(int i=0;i<order;i++){
        for(int j=0;j<order;j++){
            printf(j==order-1?"%d\n":"%d ",x[i][j]);
        }
    }
}

void *multiply(void *arg) {
    int *task = (int *)arg;
    int i = *task;

    for (int j = 0; j < order; j++) {
        long long sum = 0;
        for (int k = 0; k < order; k++) {
            sum += A[i][k] * B[k][j];
        }
        pthread_mutex_lock(&mutex);
        C[i][j] = sum;
        pthread_mutex_unlock(&mutex);
    }

    return NULL;
}

void *thread_pool(void *arg) {
    int thread_id = *(int *)arg;
    while (true) {
        pthread_mutex_lock(&mutex);
        int *task = (int *)arg;
        int current_task = *task;
        *task += 1;
        pthread_mutex_unlock(&mutex);

        if (current_task >= order) break;

        multiply(&current_task);
    }

    return NULL;
}

int main() {
    int N;
    std::cout << "input order, expon and thread\n";
    std::cin >> order >> N >> thread_count;

    srand(time(NULL));
    for (int i = 0; i < order; i++) {
        for (int j = 0; j < order; j++) {
            B[i][j] = A[i][j] = rand() % 10;
        }
    }

    for (int s = 1; s < N; s++) {
        pthread_t *threadID = new pthread_t[thread_count];
        int *task = new int(0);

        for (int i = 0; i < thread_count; i++) {
            pthread_create(&threadID[i], NULL, thread_pool, task);
        }

        for (int i = 0; i < thread_count; i++) {
            pthread_join(threadID[i], NULL);
        }

        A = C;
        delete[] threadID;
        delete task;
    }
    printM(A);
}