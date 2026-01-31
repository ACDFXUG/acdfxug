#include <iostream>
#include <unordered_map>
#include <stack>
#include <string>

std::unordered_map<char,const int8_t> op_priority{
    {'+', 1},
    {'-', 1},
    {'*', 2},
    {'/', 2},
    {'(', 0},  // 左括号优先级最低
    {')', 99}  // 右括号优先级最高
};

int cal_expr(std::string_view expr) {
    std::stack<int> nums;
    std::stack<char> ops;
    
    for (size_t i = 0; i < expr.length(); ) {
        char c = expr[i];
        
        if (isdigit(c)) {
            // 处理多位数字
            int num = 0;
            while (i < expr.length() && isdigit(expr[i])) {
                num = num * 10 + (expr[i] - '0');
                i++;
            }
            nums.push(num);
        } else if (c == '(') {
            ops.push(c);
            i++;
        } else if (c == ')') {
            // 处理到左括号为止的所有操作
            while (!ops.empty() && ops.top() != '(') {
                if (nums.size() < 2 || ops.empty()) {
                    throw std::runtime_error("Invalid expression");
                }
                
                int b = nums.top(); nums.pop();
                int a = nums.top(); nums.pop();
                char op = ops.top(); ops.pop();
                
                int result = 0;
                switch (op) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/': 
                        if (b == 0) throw std::runtime_error("Division by zero");
                        result = a / b; 
                        break;
                }
                nums.push(result);
            }
            if (!ops.empty()) {
                ops.pop(); // 弹出左括号
            }
            i++;
        } else {
            // 处理操作符
            while (!ops.empty() && 
                   ops.top() != '(' && 
                   op_priority[ops.top()]>=op_priority[c]) {
                
                if (nums.size() < 2) {
                    throw std::runtime_error("Invalid expression");
                }
                
                int b = nums.top(); nums.pop();
                int a = nums.top(); nums.pop();
                char op = ops.top(); ops.pop();
                
                int result = 0;
                switch (op) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/': 
                        if (b == 0) throw std::runtime_error("Division by zero");
                        result = a / b; 
                        break;
                }
                nums.push(result);
            }
            ops.push(c);
            i++;
        }
    }
    
    // 处理剩余的操作符
    while (!ops.empty()) {
        if (nums.size() < 2) {
            throw std::runtime_error("Invalid expression");
        }
        
        int b = nums.top(); nums.pop();
        int a = nums.top(); nums.pop();
        char op = ops.top(); ops.pop();
        
        int result = 0;
        switch (op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/': 
                if (b == 0) throw std::runtime_error("Division by zero");
                result = a / b; 
                break;
        }
        nums.push(result);
    }
    
    return nums.top();
}

int main() {
    std::string expr;
    std::getline(std::cin,expr);
    std::string expression="";
    for(auto &i:expr){
        if(i==' ') continue;
        expression+=i;
    }
    try{
        std::cout << cal_expr(expression) << std::endl;
    }catch(std::runtime_error &e){
        std::cout << e.what() << std::endl;
    }
}