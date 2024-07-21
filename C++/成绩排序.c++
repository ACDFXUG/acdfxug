#include<iostream>
#include<iomanip>
using namespace std;
int main()
{
    double score[5];
    double sum=0;
    for(int i=0;i<5;i++){
        cin>>score[i];
        sum+=score[i];
    }
    int N;
    cin>>N;
    string stu[10000];
    double grade[10000][5];
    double S[10000]={0};
    for(int i=0;i<N;i++){
        cin>>stu[i];
        for(int j=0;j<5;j++){
            cin>>grade[i][j];
        }
    }
    double t,p,h;    
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            if(score[i]>score[j]){
                h=score[i];
                score[i]=score[j];
                score[j]=h;
                for(int s=0;s<N;s++){
                p=grade[s][i];
                grade[s][i]=grade[s][j];
                grade[s][j]=p;
            }
        }
    }
}
    for(int i=0;i<N;i++){
        for(int j=0;j<5;j++){
            S[i]=S[i]+grade[i][j]*(score[j]/sum);
        }
    }
    string l;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(S[i]>S[j]){
                t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;              
            }else if(S[i]==S[j]){
                if(grade[i][0]>grade[j][0]){
                    t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;
                }else if(grade[i][0]==grade[j][0]){
                    if(grade[i][1]>grade[j][1]){
                        t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;
                    }else if(grade[i][1]==grade[j][1]){
                        if(grade[i][2]>grade[j][2]){
                            t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;
                        }else if(grade[i][2]==grade[j][2]){
                            if(grade[i][3]>grade[j][3]){
                                t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;
                            }else if(grade[i][3]==grade[j][3]){
                                if(grade[i][4]>grade[j][4]){
                                    t=S[i];
                S[i]=S[j];
                S[j]=t;
                l=stu[i];
                stu[i]=stu[j];
                stu[j]=l;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    for(int i=0;i<N;i++){
        cout<<stu[i]<<" "<<fixed<<setprecision(2)<<S[i]<<endl;
    }
}