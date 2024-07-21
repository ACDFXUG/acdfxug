#include<stdio.h>
#include<string>
#include<iostream>
using namespace std;
int NEXT[100];
void getNEXT(string s){
	NEXT[0]=0;
	for(int i =1;i<s.length();i++){
		if(i==1){
			if (s[1]==s[0]) NEXT[1]=1;
			else NEXT[1]=0;
		}
		if(s[i]==s[NEXT[i-1]]) NEXT[i]=NEXT[i-1]+1;
		else if (s[i]==s[0]) {
			NEXT[i]=1;
		}
		else{
			NEXT[i]=0;
		} 
	}
	for(int i =s.length()-1;i>0;i--){
		NEXT[i]=NEXT[i-1]+1;
	}
}
int main(){
	string s;
	cin>>s;
	getNEXT(s);
	for(int i =0;i<s.length();i++)
	printf("%d ",NEXT[i]);
	
}