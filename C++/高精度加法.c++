#include <iostream>
#include <cstring>
#include <vector>
using namespace std;
int p,n;
vector<vector<int>,int > add(vector<vector<int>,int > a, vector<vector<int>,int > b)
{
	vector<vector<int>,int> c;
	int t=0;	
	for(int i=0;i<a[p].size()||i<b[p].size();i++)
	{
		if(i<a[p].size()) {
            t+=a[p][i];
        }
		if(i<b[p].size()) {
            t+=b[p][i];
        }	
		c[p].push_back(t%10);
		t/=10;
	}
	if(t) {
        c[p].push_back(1);
    }	
	return c[p];
}
int main()
{
    vector< vector<int> , int > a;
    cin>>p;
    a[p]=add(a[p-1], add(a[p-2],a[p-3]));
    for(int i=a[p].size()-1;i>=0;i--){
        cout<<a[i][p];
    }

}