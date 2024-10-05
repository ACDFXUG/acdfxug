#include <iostream>
#include <unordered_map>

std::unordered_map<char,std::pair<int,int>> chr_pos={
    {'a',{0,0}},{'b',{0,1}},{'c',{0,2}},{'d',{0,3}},{'e',{0,4}},
    {'f',{1,0}},{'g',{1,1}},{'h',{1,2}},{'i',{1,3}},{'j',{1,4}},
    {'k',{2,0}},{'l',{2,1}},{'m',{2,2}},{'n',{2,3}},{'o',{2,4}},
    {'p',{3,0}},{'q',{3,1}},{'r',{3,2}},{'s',{3,3}},{'t',{3,4}},
    {'u',{4,0}},{'v',{4,1}},{'w',{4,2}},{'x',{4,3}},{'y',{4,4}},
    {'z',{5,0}}
};

const auto z_pos=chr_pos['z'];

std::string alphabetBoardPath(std::string target) {
    std::string ans("");
    std::pair pos={0,0};
    for(char &ch:target){
        auto next_pos=chr_pos[ch];
        int dltY=next_pos.first-pos.first,
            dltX=next_pos.second-pos.second;
        if(pos!=z_pos&&ch=='z'){
            if(dltX>0){
                ans+=std::string(dltX,'R');
            }else if(dltX<0){
                ans+=std::string(-dltX,'L');
            }    
            if(dltY>0){
                ans+=std::string(dltY,'D');
            }else if(dltY<0){
                ans+=std::string(-dltY,'U');
            }
        }else{
            if(dltY>0){
                ans+=std::string(dltY,'D');
            }else if(dltY<0){
                ans+=std::string(-dltY,'U');
            }
            if(dltX>0){
                ans+=std::string(dltX,'R');
            }else if(dltX<0){
                ans+=std::string(-dltX,'L');
            }
        }
        ans+="!";
        pos=next_pos;
    }
    return ans;
}

int main(){
    std::cout<<alphabetBoardPath("zbz");
}