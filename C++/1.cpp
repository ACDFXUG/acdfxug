#include <iostream>
#include<cstring>
using namespace std;
int n;
int a[110][110];
int b[110];

int main()
{
    for(;cin>>n;)
    {
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                cin>>a[i][j];

        int Max = -1280000;
        for(int i=0; i<n; i++)
        {
            //数组b表示i~j行，对应列元素的和
            //将二维动态规划问题转化为一维动态规划问题
            memset(b, 0, sizeof(b));
            for(int j=i; j<n; j++)
            {
                //下面是针对数组b求最大子段和的动态规划算法
                int sum=0;
                for(int k=0; k<n; k++)
                {
                    b[k] += a[j][k];
                    sum += b[k];
                    if(sum<0) sum = b[k];
                    else Max=max(Max,sum);
                }
            }
        }
        cout<<Max<<endl;
    }
}
