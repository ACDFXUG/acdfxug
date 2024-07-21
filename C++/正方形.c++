#include <iostream>
#include <algorithm>
using namespace std;

int T, order;
int *arr=new int[order];
int *flag=new int[order];
int tar;

int rec(int now, int cnt,int *arr=new int[order],int *flag=new int[order])
{
    if(now == order || cnt == order)
    {
        if(now == order && cnt == order)
            return 1;
        else
            return 0;
    }
    int add = 0;
    for(int i = now + 1; i <= order; i ++)
    {
        if(flag[i]==0)
        {
            flag[i] = 1;
            add += arr[i];
            if(add == tar)
            {
                cnt ++;
                return rec(i, cnt,arr,flag);
            }
            else if(add > tar)
            {
                flag[i] = 0;
                return rec(i - 1, cnt,arr,flag);
            }
        }
    }
    return 1;
}

int main() {
    cin>>T;
    for(;T>0;T--)
    {           
        cin >> order;
        int sum = 0;
        for(int i = 1; i <= order; i ++)
        {
            cin >> arr[i];
            sum += arr[i];
        }
        if(sum%4)
        {
            cout <<"no"<< endl ;
            continue;
        }
        tar=sum / 4;
        sort(arr + 1, arr + order + 1);
        if(rec(0, 0,arr,flag))
        {
            cout << "yes" << endl ;
            continue;
        }
        else
            cout << "no" << endl ;
    }
}