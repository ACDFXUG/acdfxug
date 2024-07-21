#include <iostream>
#include <string>
using namespace std;
short isleapyear(int year) {
	if ((year%4==0&&year%100!=0)||year%400==0){
        return 1;
    }else{
        return 0;
    }
}
int pthday[13] = {0,31,29,31,30,31,30,31,31,30,31,30,31};
string abbr[13] = {"0","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
void cpth(int pth)
{
	cout<<abbr[pth]<<" ";
}
void cday(int day)
{
	if(day==1||day==21||day==31)
	{
		cout<<day<<"st"<<" ";
	}
	else if(day==2||day==22) {
		cout<<day<<"nd"<<" ";
	}
	else if(day==3||day==23) {
		cout<<day<<"rd"<<" ";
	}
	else{
		cout<<day<<"th"<<" ";
	}
}
void ctime(int hour, int minute, int second)
{
	if(hour<10){
		cout<<"0"<<hour<<":";
	}else{
        cout<<hour<<":";
    }	
	if(minute<10){
		cout<<"0"<<minute<<":";
	}else{
        cout<<minute<<":";
    }	
	if(second<10){
		cout<<"0"<<second<<" ";
	}else{
        cout<<second<<" ";
    }	
}
void time(int year,int pth,int day,int hour,int minute,int second,long long t)
{
	int second1,minute1,hour1,day1,pth1;
	second+=t;
	if(second>=60){
		second1=second/60;
		second%=60;
	}
	minute+=second1;
	if(minute>=60){
		minute1=minute/60;
		minute%=60;
	}
	hour+=minute1;
	if(hour>=24){
		hour1=hour/24;
		hour%=24;
	}
	for(int i=0;i<hour1;i++){
		if(isleapyear(year)){
            pthday[2]=29;
        }else{
            pthday[2]=28;
        }
		if(day==pthday[pth]){
			pth++;
			day=1;
			if(pth==13){
                pth=1;
				year++;				
			}
		}else{
            day++;
        } 		
	}
	cpth(pth);
	cday(day);
	ctime(hour,minute,second);
	cout<<year<<endl;
}
int main()
{
	int n;
	cin>>n;
	
	for (int i=1;i<=n;i++) {
        int year,pth,day,hour,minute,second;
	    long long t;
		cin>>year>>pth>>day>>hour>>minute>>second>>t;
		time(year,pth,day,hour,minute,second,t);
	}
}
