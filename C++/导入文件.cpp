#include<iostream>
#include<fstream>
using namespace std;
int main()
{
    ifstream file1;
    file1.open("C:\\Users\\yaoyu\\Desktop\\scp.txt");
    string s;
    getline(file1,s);
    cout<<s;
    file1.close();
    fstream file2;
    file2.open("C:\\Users\\yaoyu\\Desktop\\scp.txt",ios::out);
    file2<<"shit";
    file2.close();
}