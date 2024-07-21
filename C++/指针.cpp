#include <iostream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
using namespace std;
class A{
  public:
  int a;
  protected:
  int b;
  private:
  int c;
};

class B: protected A{
  public:
  void print(){
    cout<<a;
    cout<<b;
    cout<<c;
  }
};
int main()
{
  B b;
  cout<<b.a;
}