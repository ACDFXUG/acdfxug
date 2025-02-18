#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

template<typename K,typename V>
class hash_map:public unordered_map<K,V>{
    using unordered_map<K,V>::unordered_map;
public:
    const V &operator [](this const hash_map self,const K &key){
        return self.at(key);
    }
};

const hash_map<char,string> PHONE{
    {'2',"abc"},{'3',"def"},{'4',"ghi"},
    {'5',"jkl"},{'6',"mno"},{'7',"pqrs"},
    {'8',"tuv"},{'9',"wxyz"}
};
[[nodiscard]]
vector<string> letterCombinations(string digits) {
    if(digits.empty())[[unlikely]] return {};
    vector<string> ans;
    if(digits.size()==1)[[unlikely]]{
        for(int i=0;i<PHONE[digits[0]].size();i++){
            ans.push_back({PHONE[digits[0]][i]});
        }
    }else[[likely]]{ 
        for(int i=0;i<PHONE[digits[0]].size();i++){
            vector<string> temp=letterCombinations(digits.substr(1));
            for(int j=0;j<temp.size();j++){
                ans.push_back(PHONE[digits[0]][i]+temp[j]);
            }
        }
    }
    return ans;
}

int main(){
    string digits="234";
    vector<string> ans=letterCombinations(digits);
    for(int i=0;i<ans.size();i++){
        cout<<ans[i]<<endl;
    }
}