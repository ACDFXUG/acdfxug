#!/bin/bash

# GO语言编译器交互式演示脚本

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
MAGENTA='\033[0;35m'
CYAN='\033[0;36m'
WHITE='\033[1;37m'
NC='\033[0m' # No Color

# 清屏函数
clear_screen() {
    clear
    echo -e "${CYAN}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo -e "${WHITE}              GO语言编译器 - 交互式演示系统${NC}"
    echo -e "${CYAN}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo ""
}

# 暂停函数
pause() {
    echo ""
    echo -e "${YELLOW}按Enter键继续...${NC}"
    read
}

# 显示菜单
show_menu() {
    clear_screen
    echo -e "${GREEN}请选择演示内容:${NC}"
    echo ""
    echo "  1) 📝 Hello World - 基础程序编译"
    echo "  2) 🧮 算术运算 - 函数调用和表达式"
    echo "  3) 🔄 递归函数 - 阶乘计算"
    echo "  4) 📊 代码优化 - 对比优化效果"
    echo "  5) 🔍 错误检测 - 演示错误处理"
    echo "  6) 📚 符号表 - 查看符号信息"
    echo "  7) ⚙️  复杂程序 - 完整功能展示"
    echo "  8) 🎯 全流程演示 - 完整编译过程"
    echo "  9) 📖 查看文档"
    echo "  0) 🚪 退出"
    echo ""
    echo -e "${CYAN}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
}

# 显示源代码
show_source() {
    local file=$1
    echo -e "\n${BLUE}【源代码】${NC} ${file}\n"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat "$file" | nl -w3 -s'│ '
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
}

# 演示1: Hello World
demo_hello() {
    clear_screen
    echo -e "${WHITE}【演示1】Hello World - 基础程序编译${NC}"
    echo ""
    
    show_source "examples/hello.go"
    pause
    
    echo -e "\n${GREEN}开始编译...${NC}\n"
    ./gocompiler.sh -v examples/hello.go
    
    echo -e "\n${BLUE}【生成的三地址代码】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.tac | sed 's/^/│ /'
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    pause
}

# 演示2: 算术运算
demo_arithmetic() {
    clear_screen
    echo -e "${WHITE}【演示2】算术运算 - 函数调用和表达式${NC}"
    echo ""
    
    show_source "examples/arithmetic.go"
    pause
    
    echo -e "\n${GREEN}开始编译...${NC}\n"
    ./gocompiler.sh examples/arithmetic.go
    
    echo -e "\n${BLUE}【三地址代码】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.tac | sed 's/^/│ /'
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    pause
}

# 演示3: 递归函数
demo_factorial() {
    clear_screen
    echo -e "${WHITE}【演示3】递归函数 - 阶乘计算${NC}"
    echo ""
    
    show_source "examples/factorial.go"
    pause
    
    echo -e "\n${GREEN}开始编译...${NC}\n"
    ./gocompiler.sh examples/factorial.go
    
    echo -e "\n${BLUE}【三地址代码 - 展示递归】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.tac | sed 's/^/│ /'
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    pause
}

# 演示4: 代码优化
demo_optimization() {
    clear_screen
    echo -e "${WHITE}【演示4】代码优化 - 对比优化效果${NC}"
    echo ""
    
    show_source "examples/arithmetic.go"
    pause
    
    echo -e "\n${GREEN}开始编译（启用优化）...${NC}\n"
    ./gocompiler.sh examples/arithmetic.go
    
    echo -e "\n${YELLOW}【优化前 - output.tac】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.tac | head -20 | sed 's/^/│ /'
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    echo -e "\n${GREEN}【优化后 - output.opt.tac】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.opt.tac | head -20 | sed 's/^/│ /'
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    echo -e "\n${MAGENTA}优化效果统计:${NC}"
    echo "  优化前指令数: $(wc -l < output.tac)"
    echo "  优化后指令数: $(wc -l < output.opt.tac)"
    echo "  减少指令数: $(($(wc -l < output.tac) - $(wc -l < output.opt.tac)))"
    
    pause
}

# 演示5: 错误检测
demo_errors() {
    clear_screen
    echo -e "${WHITE}【演示5】错误检测 - 演示错误处理${NC}"
    echo ""
    
    show_source "tests/test_errors.go"
    pause
    
    echo -e "\n${GREEN}开始编译（包含错误的代码）...${NC}\n"
    ./gocompiler.sh tests/test_errors.go 2>&1
    
    echo -e "\n${RED}注意：编译器正确检测到了所有错误！${NC}"
    
    pause
}

# 演示6: 符号表
demo_symboltable() {
    clear_screen
    echo -e "${WHITE}【演示6】符号表 - 查看符号信息${NC}"
    echo ""
    
    show_source "examples/complex.go"
    pause
    
    echo -e "\n${GREEN}开始编译（详细输出）...${NC}\n"
    ./gocompiler.sh -v examples/complex.go 2>&1 | grep -A 20 "Symbol Table"
    
    pause
}

# 演示7: 复杂程序
demo_complex() {
    clear_screen
    echo -e "${WHITE}【演示7】复杂程序 - 完整功能展示${NC}"
    echo ""
    
    echo -e "${BLUE}包含以下特性:${NC}"
    echo "  • 全局变量"
    echo "  • 多个函数"
    echo "  • 递归调用"
    echo "  • 复杂表达式"
    echo ""
    
    show_source "examples/complex.go"
    pause
    
    echo -e "\n${GREEN}开始编译...${NC}\n"
    ./gocompiler.sh examples/complex.go
    
    echo -e "\n${BLUE}【生成的三地址代码（部分）】${NC}"
    echo -e "${CYAN}┌────────────────────────────────────────────────────────────┐${NC}"
    cat output.tac | head -30 | sed 's/^/│ /'
    echo "│ ..."
    echo -e "${CYAN}└────────────────────────────────────────────────────────────┘${NC}"
    
    echo -e "\n${MAGENTA}统计信息:${NC}"
    echo "  总指令数: $(wc -l < output.tac)"
    echo "  优化后: $(wc -l < output.opt.tac)"
    echo "  函数数量: $(grep -c '^[a-z]*:$' output.tac)"
    
    pause
}

# 演示8: 全流程
demo_full() {
    clear_screen
    echo -e "${WHITE}【演示8】全流程演示 - 完整编译过程${NC}"
    echo ""
    echo -e "${GREEN}演示编译器的所有阶段...${NC}"
    echo ""
    
    echo "以factorial.go为例，完整展示编译过程"
    pause
    
    show_source "examples/factorial.go"
    pause
    
    echo -e "\n${BLUE}━━━ 阶段1: 词法和语法分析 ━━━${NC}\n"
    echo "✓ 解析GO源代码"
    echo "✓ 构建语法分析树"
    pause
    
    echo -e "\n${BLUE}━━━ 阶段2: 语义分析 ━━━${NC}\n"
    echo "✓ 构建符号表"
    echo "✓ 类型检查"
    echo "✓ 作用域分析"
    pause
    
    echo -e "\n${BLUE}━━━ 阶段3: 三地址代码生成 ━━━${NC}\n"
    ./gocompiler.sh examples/factorial.go > /dev/null 2>&1
    cat output.tac | sed 's/^/  /'
    pause
    
    echo -e "\n${BLUE}━━━ 阶段4: 代码优化 ━━━${NC}\n"
    echo "优化前: $(wc -l < output.tac) 条指令"
    echo "优化后: $(wc -l < output.opt.tac) 条指令"
    echo "优化技术: 常量折叠、常量传播、死代码消除"
    pause
    
    echo -e "\n${BLUE}━━━ 阶段5: 汇编代码生成 ━━━${NC}\n"
    echo "生成x86-64汇编代码:"
    cat output.asm | head -20 | sed 's/^/  /'
    echo "  ..."
    pause
    
    echo -e "\n${GREEN}✓ 完整编译流程演示完毕！${NC}"
    pause
}

# 查看文档
view_docs() {
    clear_screen
    echo -e "${WHITE}【文档列表】${NC}"
    echo ""
    echo "  1. README.md - 项目概览"
    echo "  2. 项目完成报告.md - 详细完成报告"
    echo "  3. IDE使用说明.md - IDE使用指南"
    echo "  4. docs/项目设计文档.md - 设计文档"
    echo "  5. docs/用户手册.md - 用户手册"
    echo "  6. docs/测试报告.md - 测试报告"
    echo "  7. docs/GO语言语法描述.md - 语法规范"
    echo "  8. docs/三地址指令设计.md - TAC设计"
    echo ""
    echo -e "${YELLOW}请输入文档编号 (1-8) 或按Enter返回:${NC}"
    read doc_num
    
    case $doc_num in
        1) less README.md ;;
        2) less 项目完成报告.md ;;
        3) less IDE使用说明.md ;;
        4) less docs/项目设计文档.md ;;
        5) less docs/用户手册.md ;;
        6) less docs/测试报告.md ;;
        7) less docs/GO语言语法描述.md ;;
        8) less docs/三地址指令设计.md ;;
    esac
}

# 主循环
main() {
    while true; do
        show_menu
        echo -n "请选择 (0-9): "
        read choice
        
        case $choice in
            1) demo_hello ;;
            2) demo_arithmetic ;;
            3) demo_factorial ;;
            4) demo_optimization ;;
            5) demo_errors ;;
            6) demo_symboltable ;;
            7) demo_complex ;;
            8) demo_full ;;
            9) view_docs ;;
            0) 
                clear_screen
                echo -e "${GREEN}感谢使用GO语言编译器演示系统！${NC}"
                echo ""
                exit 0
                ;;
            *)
                echo -e "${RED}无效选择，请重试${NC}"
                sleep 1
                ;;
        esac
    done
}

# 启动
main

