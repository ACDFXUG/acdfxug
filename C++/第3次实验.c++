#include <iostream>
#include <vector>
#include <mpi.h>

using namespace std;

// 矩阵乘法
void matrix_multiply(const vector<vector<double>>& A, const vector<vector<double>>& B, vector<vector<double>>& C) {
    int m = A.size();
    int n = A[0].size();
    int p = B[0].size();

    C.resize(m, vector<double>(p, 0));

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < p; ++j) {
            for (int k = 0; k < n; ++k) {
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }
}

// 快速幂算法
void matrix_power(const vector<vector<double>>& A, int n, vector<vector<double>>& result) {
    int m = A.size();
    result.resize(m, vector<double>(m, 0));

    // 初始化结果为单位矩阵
    for (int i = 0; i < m; ++i) {
        result[i][i] = 1;
    }

    vector<vector<double>> temp = A;

    while (n > 0) {
        if (n % 2 == 1) {
            matrix_multiply(result, temp, result);
        }
        matrix_multiply(temp, temp, temp);
        n /= 2;
    }
}

int main(int argc, char** argv) {
    MPI_Init(&argc, &argv);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    int m = 3; // 矩阵大小
    int n = 2; // 幂的指数

    vector<vector<double>> A(m, vector<double>(m, 0));
    vector<vector<double>> result(m, vector<double>(m, 0));

    // 初始化矩阵A
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < m; ++j) {
            A[i][j] = i + j;
        }
    }

    // 如果只有一个进程，直接计算矩阵幂
    if (world_size == 1) {
        matrix_power(A, n, result);
        cout << "Matrix power result:" << endl;
        for (const auto& row : result) {
            for (double val : row) {
                cout << val << " ";
            }
            cout << endl;
        }
    } else {
        // 在多个进程上并行计算矩阵幂（此处为示例，实际上需要更复杂的逻辑来分配任务）
        if (world_rank == 0) {
            matrix_power(A, n, result);
            cout << "Matrix power result:" << endl;
            for (const auto& row : result) {
                for (double val : row) {
                    cout << val << " ";
                }
                cout << endl;
            }
        }
    }

    MPI_Finalize();
    return 0;
}
