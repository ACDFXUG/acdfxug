#include <iostream>
#include <vector>
using namespace std;

int main() {
    int T, n;
    cin>>T;
    for (;T-->0;) {       
        cin>>n;
        vector<int> v(n);
        for (int i=0; i<n;i++) {
            v[i]=i+1;   //第几个同学就赋值为几
        }
        int s=0;
        for (;v.size()>=7;){
            s=(s+6)%v.size();   // 报数到7的倍数，因为下标从0开始所以是6
            v.erase(v.begin()+s);   // 移出队伍
        }
        for (int i=0;i<v.size();i++) {
            cout<<v[i]; // 输出剩余同学的原始编号
            if(i<v.size()-1){
                cout<<" ";
            }
        }
        cout<<endl;
    }
}