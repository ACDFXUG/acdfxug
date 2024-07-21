#include <bits/stdc++.h>
using namespace std;
int zuobiao[11000][11000];

int ask(int maxx,int minx,int miny,int maxy)
{
	if(maxx<minx){
        swap(maxx,minx);
    }
	if(maxy<miny){
        swap(maxy,miny);
    }
	return zuobiao[maxx][maxy]-zuobiao[minx-1][maxy]-zuobiao[maxx][miny-1]+zuobiao[minx-1][miny-1];
}
int main()
{
    int cord[11000][2];
    int cordtemp[11000][2];
    int pict[11000];
	int n,m,out,k1,k2;
	scanf("%d %d",&n,&m);	
	for(int i=1;i<=n;i++){
		scanf("%d %d",&cord[i][0],&cord[i][1]);
		pict[out++]=cord[i][0];
		pict[out++]=cord[i][1];
	} 
	sort(pict+1,pict+1+out);
	int lens=unique(pict+1,pict+1+out)-pict-1;
	for(int i=1;i<=n;i++){
		cordtemp[i][0]=lower_bound(pict+1,pict+1+lens,cord[i][0])-pict;
		cordtemp[i][1]=lower_bound(pict+1,pict+1+lens,cord[i][1])-pict;
	}
	for(int i=1;i<=n;i++){
		zuobiao[cordtemp[i][0]][cordtemp[i][1]]++;
	}
	for(int i=1;i<=lens;i++){
		for(int j=1;j<=lens;j++){
			zuobiao[i][j]+=zuobiao[i-1][j]+zuobiao[i][j-1]-zuobiao[i-1][j-1];
		} 
	}
	for(int i=0;i<m;i++){
		scanf("%d %d",&k1,&k2);
		printf("%d\n",ask(cordtemp[k1+1][0],cordtemp[k2+1][0],cordtemp[k1+1][1],cordtemp[k2+1][1]));
	}
 }
