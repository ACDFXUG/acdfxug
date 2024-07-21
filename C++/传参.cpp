#include <iostream>
using namespace std;
void my_abs(){
    int num;
    if(num>=0){
        cout<<num;
    }else{
        cout<<-num;
    }
}
int main()
{
    int num;
    cin >> num;
    my_abs();
    cout << num << endl;
    return 0;
}