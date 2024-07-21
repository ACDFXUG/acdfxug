#include<stdio.h>
#include<math.h>
int main(){
int year;
printf("���:%d",year);
scanf("%d",&year);
printf("闰年�?:") ;
for(year;year<=2022;year++){
	if(year%4==0&&year%100!=0||year%400==0){
		printf("%d",year);}
		else{
		printf("__");
		}
	}
	return 0;
}
