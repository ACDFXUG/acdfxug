#include <iostream>
#include <cstdlib>
#include <stdio.h>
#include <windows.h>
using namespace std;
void COLOR_PRINT(const char* s, int color)
{
 HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
 SetConsoleTextAttribute(handle, FOREGROUND_INTENSITY | color);
 printf(s);
 SetConsoleTextAttribute(handle, FOREGROUND_INTENSITY | 7);
}
int main(){
    cout<<"请输入你想要通关的游戏："<<endl;
    string s;
    cin>>s;
    COLOR_PRINT("恭喜你",1);
    cout<<s;
    COLOR_PRINT("已通关！",6);
    cout<<endl;
    system("pause");
    return 0;
}