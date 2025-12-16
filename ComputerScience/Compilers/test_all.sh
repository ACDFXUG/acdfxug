#!/bin/bash

# GO语言编译器测试脚本

echo "=========================================="
echo "GO Language Compiler - Test Suite"
echo "=========================================="

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

PASSED=0
FAILED=0

# 测试单个文件
test_file() {
    local file=$1
    local name=$(basename "$file" .go)
    
    echo -e "\n${BLUE}Testing: $name${NC}"
    echo "----------------------------------------"
    
    # 运行编译器
    ./gocompiler.sh -S "$file" -o "test_output/$name" > /dev/null 2>&1
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ $name: PASSED${NC}"
        ((PASSED++))
    else
        echo -e "${RED}✗ $name: FAILED${NC}"
        ((FAILED++))
    fi
}

# 创建输出目录
mkdir -p test_output

echo -e "\n${YELLOW}Phase 1: Building Compiler${NC}"
echo "=========================================="
./build.sh

if [ $? -ne 0 ]; then
    echo -e "${RED}Build failed!${NC}"
    exit 1
fi

echo -e "\n${YELLOW}Phase 2: Running Tests${NC}"
echo "=========================================="

# 测试所有示例程序
for file in examples/*.go; do
    test_file "$file"
done

# 测试错误检测（应该编译失败）
echo -e "\n${BLUE}Testing: error detection${NC}"
echo "----------------------------------------"
./gocompiler.sh -S tests/test_errors.go -o test_output/errors > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo -e "${GREEN}✓ error detection: PASSED (correctly detected errors)${NC}"
    ((PASSED++))
else
    echo -e "${RED}✗ error detection: FAILED (should have detected errors)${NC}"
    ((FAILED++))
fi

# 总结
echo ""
echo "=========================================="
echo "Test Summary"
echo "=========================================="
echo -e "Total tests: $((PASSED + FAILED))"
echo -e "${GREEN}Passed: $PASSED${NC}"
echo -e "${RED}Failed: $FAILED${NC}"

if [ $FAILED -eq 0 ]; then
    echo -e "\n${GREEN}All tests passed! ✓${NC}"
    exit 0
else
    echo -e "\n${RED}Some tests failed! ✗${NC}"
    exit 1
fi

