#include<iostream>
using namespace std;
void post(int root,int start,int end,string pre,string in)
{
	if(start>end) {
        return; 
    }
	int i=start;
	for(;i<end&&in[i]!=pre[root];) i++;
	post(root+1,start,i-1,pre,in);
	post(root+1-start+i,i+1,end,pre,in);
	cout<<pre[root];
};

int main()
{
    string pre,in;  
	while(cin>>pre>>in)
	{  
	   post(0,0,pre.length()-1,pre,in);
	   cout<<endl;	
	}
}