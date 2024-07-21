#include<iostream>
#include<iomanip>
#include<algorithm>
using namespace std;
class Mark{
    public:
    string names;
    double mark[5];
    double sum;
};

int main()
{
    Mark S[10000];
    double grade[5];
    double p=0;
    for(int i=0;i<5;i++){
        cin>>grade[i];
        p+=grade[i];
    }
    int N;
    cin>>N;
    double a[10000]={0};
    for(int i=0;i<N;i++){
        cin>>S[i].names>>S[i].mark[0]>>S[i].mark[1]>>S[i].mark[2]>>S[i].mark[3]>>S[i].mark[4];
        for(int j=0;j<5;j++){
            a[i]+=S[i].mark[j]*grade[j];
        }
        S[i].sum=a[i]/p;
    }
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            if(grade[i]<grade[j]){
                swap(grade[i],grade[j]);
            }
        }
    }
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(S[i].sum<S[j].sum){
                swap(S[i],S[j]);
            }else if(S[i].sum==S[j].sum){
                if(S[i].mark[0]<S[j].mark[0]){
                    swap(S[i],S[j]);
                }else if(S[i].mark[0]==S[j].mark[0]){
                    if(S[i].mark[1]<S[j].mark[1]){
                        swap(S[i],S[j]);
                    }else if(S[i].mark[1]==S[j].mark[1]){
                        if(S[i].mark[2]<S[j].mark[2]){
                            swap(S[i],S[j]);
                        }else if(S[i].mark[2]==S[j].mark[2]){
                            if(S[i].mark[3]<S[j].mark[3]){
                                swap(S[i],S[j]);
                            }else if(S[i].mark[3]==S[j].mark[3]){
                                if(S[i].mark[4]<S[j].mark[4]){
                                    swap(S[i],S[j]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    for(int i=N-1;i>=0;i--){
        cout<<S[i].names<<" "<<fixed<<setprecision(2)<<S[i].sum<<endl;
    }
}