#include <iostream>
#include <string>
using namespace std;
int main(){
    int N,M;
    cin>>N>>M;
    string s[100];
    for(int i=0;i<M;i++){
        cin>>s[i];
    }
    int *num=new int[N+1];
    for(int i=1;i<=N;i++) {
	int j;
	for(j=0;j<M;j++) {
		if(to_string(i).find(s[j])!=-1) {
			break;
		}
	}
	if(j==M) {
		num[i]=1;
	}else{
        num[i]=0;
    }
}
int sum=0;
for(int i=1;i<=N;i++){
    if(num[i]==1){
        sum++;
    }
}
cout<<sum<<endl;
}