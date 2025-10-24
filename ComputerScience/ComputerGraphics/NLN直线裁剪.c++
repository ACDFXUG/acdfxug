#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

constexpr int cohen_sutherland[]{
    0b1001,0b1000,0b1010,
    0b0001,0b0000,0b0010,
    0b0101,0b0100,0b0110
};

struct Point{
    double x,y;
    Point(const double &x,const double &y):x(x),y(y){}
    
    bool operator ==(const Point &p) const{
        if(this==&p){
            return true;
        }
        return x==p.x&&y==p.y;
    }
};

struct Rectangle{
    const Point left_bottom,right_top;
    Rectangle(const Point &left_bottom,const Point &right_top):
    left_bottom(left_bottom),right_top(right_top){}
    bool is_inner(const Point &p) const{
        return (p.x>=left_bottom.x&&p.x<=right_top.x)
            &&(p.y>=left_bottom.y&&p.y<=right_top.y);
    }
    int get_position(const Point &p) const{
        double x_min=left_bottom.x,x_max=right_top.x,
            y_min=left_bottom.y,y_max=right_top.y;
        double x=p.x,y=p.y;
        if(x<x_min&&y>y_max) return 0;
        else if(x>x_min&&x<x_max&&y>y_max) return 1;
        else if(x>x_max&&y>y_max) return 2;
        else if(x<x_min&&y>y_min&&y<y_max) return 3;
        else if(x>x_min&&x<x_max&&y>y_min&&y<y_max) return 4;
        else if(x>x_max&&y>y_min&&y<y_max) return 5;
        else if(x<x_min&&y<y_min) return 6;
        else if(x>x_min&&x<x_max&&y<y_min) return 7;
        else if(x>x_max&&y<y_min) return 8;
        else return 4;
    }
    int get_cohen_sutherland_code(const Point &p) const{
        double x_min=left_bottom.x,x_max=right_top.x,
            y_min=left_bottom.y,y_max=right_top.y;
        return cohen_sutherland[get_position(p)];
    }
};



struct Line{
    const Point start,end;  // 起点和终点
    Line(const Point &start,const Point &end):
    start(start),end(end){}
    bool is_thrown(const Rectangle &rec) const{
        int code1=rec.get_cohen_sutherland_code(start),
            code2=rec.get_cohen_sutherland_code(end);
        return code1&code2;
    }
    bool is_get(const Rectangle &rec) const{
        int code1=rec.get_cohen_sutherland_code(start),
            code2=rec.get_cohen_sutherland_code(end);
        return !(code1|code2);
    }
    std::vector<Point> get_intersection(const Rectangle &rec) const{
        double x_min=rec.left_bottom.x,x_max=rec.right_top.x,
            y_min=rec.left_bottom.y,y_max=rec.right_top.y;
        double x1=start.x,y1=start.y,x2=end.x,y2=end.y;
        std::vector<Point> intersection;
        int code1=rec.get_cohen_sutherland_code(start),
            code2=rec.get_cohen_sutherland_code(end);
        if(x1==x2){
            if(code1==0b0000){
                if(y2>=y_max){
                    intersection.push_back({x1,y_max});
                }else{
                    intersection.push_back({x2,y_min});
                }
            }else if(code2==0b0000){
                if(y1>=y_max){
                    intersection.push_back({x1,y_max});
                }else{
                    intersection.push_back({x1,y_min});
                }
            }else{
                intersection.push_back({x1,y_max});
                intersection.push_back({x1,y_min});
            }  
        }else if(y1==y2){
            if(code1==0b0000){
                if(x2>=x_max){
                    intersection.push_back({x_max,y1});
                }else{
                    intersection.push_back({x_min,y2});
                }
            }else if(code2==0b0000){
                if(x1>=x_max){
                    intersection.push_back({x_max,y1});
                }else{
                    intersection.push_back({x_min,y2});
                }
            }else{
                intersection.push_back({x_min,y1});
                intersection.push_back({x_max,y1});
            }
        }else{
            Point p1={((y_max-y1)*(x1-x2)/(y1-y2))+x1,y_max};
            Point p2={((y_min-y1)*(x1-x2)/(y1-y2))+x1,y_min};
            Point p3={x_min,((x_min-x1)*(y2-y1)/(x2-x1))+y1};
            Point p4={x_max,((x_max-x1)*(y2-y1)/(x2-x1))+y1};
            Point p[]{p1,p2,p3,p4};
            for(int i=0;i<4;){
                if(rec.is_inner(p[i])){
                    for(int j=0;j<intersection.size();j++){
                        if(p[i]==intersection[j]){
                            goto next;
                        }
                    }
                    intersection.push_back(p[i]);
                }
                next:i++;
            }
        }
        return intersection;
    }
};

int main(){
    double x_min,y_min,x_max,y_max;
    printf("输入矩形的左下角和右上角坐标:\n");
    std::cin>>x_min>>y_min>>x_max>>y_max;
    double x1,y1,x2,y2;
    printf("输入直线的起点和终点坐标:\n");
    std::cin>>x1>>y1>>x2>>y2;
    Point left_bottom(x_min,y_min),
        right_top(x_max,y_max);
    Point start(x1,y1),end(x2,y2);
    Line line(start,end);
    Rectangle rec(left_bottom,right_top);
    if(line.is_thrown(rec)){
        printf("简弃\n");
        return 0;
    }
    if(line.is_get(rec)){
        std::cout<<"起点为: ("<<start.x<<","<<start.y<<")\n终点为: ("<<end.x<<","<<end.y<<")\n";
        return 0;
    }
    int code1=rec.get_cohen_sutherland_code(start),
        code2=rec.get_cohen_sutherland_code(end);
    auto inter=line.get_intersection(rec);
    std::sort(inter.begin(),inter.end(),[](auto &a,auto &b){
        return a.x<=b.x;
    });
    if(code1==0b0000){
        auto p=inter[0];
        std::cout<<"裁剪后直线段的起点为: ("<<start.x<<", "<<start.y<<"),";
        std::cout<<"终点为: ("<<p.x<<", "<<p.y<<")\n";
    }else if(code2==0b0000){
        auto p=inter[0];
        std::cout<<"裁剪后直线段的起点为: ("<<p.x<<", "<<p.y<<"),";
        std::cout<<"终点为: ("<<end.x<<", "<<end.y<<")\n";
    }else{
        if(inter.size()<=1){
            printf("简弃\n");
            return 0;
        }else{
            std::cout<<"裁剪后直线段的起点为: ("<<inter[0].x<<", "<<inter[0].y<<"),";
            std::cout<<"终点为: ("<<inter[1].x<<", "<<inter[1].y<<")\n";
        }
    }
}