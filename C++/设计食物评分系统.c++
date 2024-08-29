#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
#define PRINTLN(x) cout<<(x)<<endl;
using namespace std;

class Food{
public:
    string name,cuisine;
    int rating;
    Food():name(""),cuisine(""),rating(0){}
    Food(string name,string cuisine,int rating):
    name(name),cuisine(cuisine),rating(rating){}
    void setRating(int newRating){
        this->rating=newRating;
    }
    bool operator <(const Food &food) const{
        return rating==food.rating?
        name<food.name:rating>food.rating;
    }
    Food &operator =(const Food &food){
        name=food.name;
        cuisine=food.cuisine;
        rating=food.rating;
        return *this;
    }
};

class FoodRatings{
private:
    std::unordered_map<string,Food> FOOD;
public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        int n=foods.size();
        for(int i=0;i<n;i++){
            FOOD[foods[i]]=Food(foods[i],cuisines[i],ratings[i]);
        }
    }
    void changeRating(string food, int newRating) {
        FOOD[food].setRating(newRating);
    }
    string highestRated(string cuisine) {
        vector<Food> fd;
        for(auto &[_,food]:FOOD){
            if(food.cuisine==cuisine){
                fd.push_back(food);
            }
        }
        sort(fd.begin(),fd.end());
        return fd[0].name;
    }
};

int main(){
    vector<string> foods={"kimchi","miso","sushi","moussaka","ramen","bulgogi"};
    vector<string> cuisines={"korean","japanese","japanese","greek","japanese","korean"};
    vector<int> ratings={9,12,8,15,14,7};
    FoodRatings fr={foods,cuisines,ratings};
    PRINTLN(fr.highestRated("korean"));
    PRINTLN(fr.highestRated("japanese"));
    fr.changeRating("sushi",16);
    PRINTLN(fr.highestRated("japanese"));
    fr.changeRating("ramen",16);
    PRINTLN(fr.highestRated("japanese"));
}