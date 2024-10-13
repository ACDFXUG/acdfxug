#include <iostream>
#include <vector>
using namespace std;

// 直接插入排序
void insert_sort(vector<int>& L) {
    int len = L.size();
    for (int i = 1; i < len; i++) {
        int tmp = L[i];
        int j = i - 1;
        while (j >= 0 && L[j] > tmp) {
            L[j + 1] = L[j];
            j--;
        }
        L[j + 1] = tmp;
    }
}

// 折半插入排序
void binary_insert_sort(vector<int>& L) {
    int len = L.size();
    for (int i = 1; i < len; i++) {
        int tmp = L[i];
        int left = 0, right = i - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (L[mid] > tmp)
                right = mid - 1;
            else
                left = mid + 1;
        }
        for (int j = i - 1; j >= left; j--)
            L[j + 1] = L[j];
        L[left] = tmp;
    }
}

// 快速排序
void quick_sort(vector<int>& L, int left, int right) {
    if (left >= right) return;
    int i = left, j = right;
    int pivot = L[left];

    while (i < j) {
        while (i < j && L[j] >= pivot) j--;
        L[i] = L[j];
        while (i < j && L[i] <= pivot) i++;
        L[j] = L[i];
    }

    L[i] = pivot;
    cout << "Pivot position: " << i << endl;
    for (int k = 0; k < L.size(); k++)
        cout << L[k] << " ";
    cout << endl;

    quick_sort(L, left, i - 1);
    quick_sort(L, i + 1, right);
}

int main() {
    vector<int> L = {1, 8, 6, 4, 10, 5, 3, 2, 22};

    // 直接插入排序
    insert_sort(L);
    for (int i = 0; i < L.size(); i++)
        cout << L[i] << " ";
    cout << endl;

    // 折半插入排序
    L = {1, 8, 6, 4, 10, 5, 3, 2, 22};
    binary_insert_sort(L);
    for (int i = 0; i < L.size(); i++)
        cout << L[i] << " ";
    cout << endl;

    // 快速排序
    L = {1, 8, 6, 4, 10, 5, 3, 2, 22};
    quick_sort(L, 0, L.size() - 1);

    return 0;
}