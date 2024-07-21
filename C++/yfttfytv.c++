#include <iostream>
using namespace std; 
int main()
{
    long long int a[100001];
    long long int sum[500000];
    long int count = 0;
    int m=0;
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
 
    for (int i = 0; i <n; i++)
    {
        for (int j = 1; j< n-i; j++)
        {
            sum[count] =a[i] + a[i + j];
            count++;
        }
    }
 
    for (int i = 0; i< n;i++) 
    {
        for (int j = 0; j < count ; j++)
        {
            if (sum[j] == a[i]) 
            {
                m++;
                a[i] = 0;
            }
        }
    }
    cout << m<<endl;
}
