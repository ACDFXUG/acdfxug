#include <iostream>
#include <string>
using namespace std;
void check(char *input, double &x, double &y, int &op, char * &err);
double chage(char *ch);
int main()
{
double x,y;
int op;
char input[40],*err;
double result;
gets(input);
check(input,x,y,op,err);
if(op<0)
{cout<<err<<endl; return 0;}
switch(op)
{
case 1: result=x+y; break;
case 2: result=x-y; break;
case 3: result=x*y; break;
case 4: result=x/y; break;
}
cout<<input<<"="<<result<<endl;
return 0;
}
void check(char * input, double &x, double &y, int &op, char * &err)
{
int i=0,j=0,k;
int point=0;
char leftx[20],righty[20];
while(input[i]==' ') i++;
j=i;
if(input[j]=='0'&&(input[j+1]>='0'&&input[j+1]<='9'))
{err="左操作数错误!"; op=-1; return;}
while((input[j]>='0'&&input[j]<='9')||input[j]=='.')
{
if(input[j]=='.') point++;
if(point>1) {err="左操作数错误!"; op=-1; return;}
j++;
}
k=0;
while(i<j) leftx[k++]=input[i++];
leftx[k]='\0';
x=chage(leftx);
while(input[i]==' ') i++;
switch(input[i])
{
case '+': op=1; i++; break;
case '-': op=2; i++; break;
case '*': op=3; i++; break;
case '/': op=4; i++; break;
default: op=-3;
err="运算符错误!";
return;
}
while(input[i]==' ') i++;
j=i;
point=0;
if(input[j]=='0'&&(input[j+1]>='0'&&input[j+1]<='9'))
{err="右操作数错误!"; op=-2; return;}
while((input[j]>='0'&&input[j]<='9')||input[j]=='.')
{
if(input[j]=='.') point++;
if(point>1) {err="右操作数错误!"; op=-2; return;}
j++;
}
k=0;
while(i<j) righty[k++]=input[i++];
righty[k]='\0';
y=chage(righty);
if(op==4&&y==0)
{err="除数不能为0"; op=-2; return;}
}
double chage(char *ch)
{
int i=0,j,f=-1;
double result=0,wre;
while(ch[i])
{
if(ch[i]=='.') break;
i++;
}
f=i;
for(i=f-1;i>=0;i--)
{
wre=1;
j=0;
while(j<f-i-1) {wre*=10; j++;}
result+=(ch[i]-'0')*wre;
}
for(i=f+1;ch[f]&&ch[i];i++)
{
wre=1;
j=0;
while(j<i-f) {wre*=0.1; j++;}
result+=(ch[i]-'0')*wre;
}
return result;
}