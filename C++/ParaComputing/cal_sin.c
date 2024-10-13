#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

double res;

typedef struct Args {
    float radian;
	long max_n;
	long begin;
	long step;
} Args;

void *cal(void *arg);

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Parameters error: radian N threads!\n");
        exit(1);
    }

	long i;
    double radian = atof(argv[1]);
	long max_n = atol(argv[2]);
	int threads = atoi(argv[3]);
	Args *arg;
	double* val;

	pthread_t *pid;
	pid = (pthread_t *)malloc(threads * sizeof(pthread_t));
	
	res = 0.0;
	for (i = 0; i < threads; i++) {
		arg = (Args *)malloc(sizeof(Args));
        arg->radian = radian;
		arg->max_n = max_n;
		arg->begin = i;
		arg->step = threads;
		pthread_create(&pid[i], NULL, cal, (void *)arg);
	}
	for (i = 0; i < threads; i++) {
		pthread_join(pid[i], NULL);
	}
	printf("%.20lf\n", res);
	free(pid);
	return 0;
}

void *cal(void *_arg) {
	long i, j;
	double val, tmp;
	Args *arg = (Args *)_arg;
	
	val = 0.0;
	for (i = arg->begin; i <= arg->max_n; i += arg->step) {
        tmp = 1.0;
		for (j = 1; j <= 2*i+1; j++) {
            tmp *= arg->radian / j;
        }
        if (i % 2 > 0) {
            val -= tmp;
        } else {
            val += tmp;
        }
	}
	res += val;
	return NULL;
}

