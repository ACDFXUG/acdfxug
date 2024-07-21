#include <pthread.h>
#include <iostream>
#include <vector>

// Define matrix dimensions
const int order = 2; // Assuming square matrices

// Define number of threads
const int num_threads = 8; // Assuming 4 threads for example

// Define matrices
int A[order][order];
int B[order][order];
int C[order][order];

// Define mutex for synchronization
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

// Function to perform matrix multiplication for a specific block
void* multiply(void* arg) {
    int thread_id = *((int*)arg);
    int block_size = order / num_threads;
    int start_row = thread_id * block_size;
    int end_row = (thread_id + 1) * block_size;

    // Matrix multiplication
    for (int i = start_row; i < end_row; ++i) {
        for (int j = 0; j < order; ++j) {
            int sum = 0;
            for (int k = 0; k < order; ++k) {
                sum += A[i][k] * B[k][j];
            }
            pthread_mutex_lock(&mutex);
            C[i][j] = sum;
            pthread_mutex_unlock(&mutex);
        }
    }

    return NULL;
}

int main() {
    // Initialize matrices A and B (omitted for brevity)
    for(int i=0;i<order;i++){
        for(int j=0;j<order;j++){
            B[i][j]=A[i][j]=rand()%10;
        }
    }
    // Create thread identifiers
    pthread_t threads[num_threads];
    int thread_ids[num_threads];

    // Create threads
    for (int i = 0; i < num_threads; ++i) {
        thread_ids[i] = i;
        pthread_create(&threads[i], NULL, multiply,&thread_ids[i]);
    }

    // Join threads
    for (int i = 0; i < num_threads; ++i) {
        pthread_join(threads[i], NULL);
    }

    // Matrix C now contains the result of matrix multiplication (C = A * B)
    for(int i=0;i<order;i++){
        for(int j=0;j<order;j++){
            printf(j==order-1?"%d\n":"%d ",C[i][j]);
        }
    }
    return 0;
}