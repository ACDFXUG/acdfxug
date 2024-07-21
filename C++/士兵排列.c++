#include<iostream>
#include <list>
using namespace std;

int main()
{
    int n,t;
	cin>>t;
	for (int j=0;j<t;j++)
    {
        int k=2;
        list<int> l1;
        list<int>::iterator it;
		cin>>n;
		for(int i=1;i<=n;i++){        
            l1.push_back(i);
        }
		while(l1.size()>3)
        {
			int l=1;
			it=l1.begin();
			while(it!=l1.end()){           
				if(l%k==0) {
                    it=l1.erase(it);
                }else {
                    it++;
                }				
				l++;
			}
			if(k==2){            
                k=3;
            }
			else{
                k=2;
            }
		}
		it=l1.begin();
		while(it!=l1.end()) {                   
            cout<<*it<<" ";
            it++;
        }
        cout<<endl;
	}
}
