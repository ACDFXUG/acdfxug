#include <iostream>
#define constRef(type,name) const type &name

double IoU2Dice(constRef(double,iou)){
    return 2*iou/(1+iou);
}

double Dice2IoU(constRef(double,dice)){
    return dice/(2-dice);
}

int main(){
    int T;
    std::cin>>T;
    for(std::string type;T-->0;){
        std::cin>>type;
        if(type=="iou"){
            double iou;
            std::cin>>iou;
            printf("%.2f\n",IoU2Dice(iou));
        }else if(type=="dice"){
            double dice;
            std::cin>>dice;
            printf("%.2f\n",Dice2IoU(dice));
        }
    }
}