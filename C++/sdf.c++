int min,flag,ans=0,task[];
for(int i=0;i<n;i++){
    min=0x7fffffff,flag=-1;
    for(int j=0;j<n;j++){
        if(task[j]<min){
            min=task[j];
            flag=j;
        }
    }
    task[flag]=0x7fffffff;
    ans+=min*(n-i);
}