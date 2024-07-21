#include<iostream>
#include<string>
using namespace std;
int main(){
    int dice[6],len,B[6];
    for(int i=0;i<6;i++){
        cin>>dice[i];
    }
    string order;
    cin>>order;
    len=order.length();
    for(int j=0;j<len;j++){
        if(order[j]=='W') {
		B[0]=dice[2];
		B[1]=dice[1];
		B[2]=dice[5];
		B[3]=dice[0];
		B[4]=dice[4];
		B[5]=dice[3];
		for(int j=0;j<6;j++) {
			dice[j]=B[j];
		}
	}else if(order[j]=='E') {
		B[0]=dice[3];
		B[1]=dice[1];
		B[2]=dice[0];
		B[3]=dice[5];
		B[4]=dice[4];
		B[5]=dice[2];
		for(int j=0;j<6;j++) {
			dice[j]=B[j];
		}
	}else if(order[j]=='S') {
		B[0]=dice[4];
		B[1]=dice[0];
		B[2]=dice[2];
		B[3]=dice[3];
		B[4]=dice[5];
		B[5]=dice[1];
		for(int j=0;j<6;j++) {
			dice[j]=B[j];
		}
	}else if(order[j]=='N') {
		B[0]=dice[1];
		B[1]=dice[5];
		B[2]=dice[2];
		B[3]=dice[3];
		B[4]=dice[0];
		B[5]=dice[4];
		for(int j=0;j<6;j++) {
			dice[j]=B[j];
		}
	}
	}
    cout<<dice[0]<<endl;
}