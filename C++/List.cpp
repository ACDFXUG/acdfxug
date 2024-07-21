#include<iostream>
#include<list>
#include<deque>
#include<string>
using namespace std;
int main()
{
    int n;
	cin>>n;
	int id1,id2;
	deque<list <int> > a(20000);
	for(int i=1;i<=n;i++){
        int number;
        string s;
		cin>>s;
		if(s=="new"){
			cin>>id1;
			list<list<int> > a(id1);
		}else if(s=="add"){
			cin>>id1>>number;
			a[id1].push_back(number); 
		}else if(s=="merge"){		
			cin>>id1>>id2;
			a[id1].merge(a[id2]);
		}else if(s=="unique"){		
			cin>>id1;
			a[id1].unique();
		}else if(s=="out"){		
			cin>>id1;
			for(list<int>::const_iterator it = a[id1].begin();it!=a[id1].end();it++){
				cout<<*it<<" ";
			}
			cout<<endl;
	   }
    }		
}