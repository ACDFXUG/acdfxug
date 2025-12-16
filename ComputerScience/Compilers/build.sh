#!/bin/bash

# GO语言编译器构建脚本

echo "======================================"
echo "Building Go Language Compiler"
echo "======================================"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 检查Java
echo -e "\n${YELLOW}[1/5]${NC} Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo -e "${RED}Error: Java is not installed${NC}"
    exit 1
fi

if ! command -v javac &> /dev/null; then
    echo -e "${RED}Error: javac is not installed${NC}"
    exit 1
fi

java -version
echo -e "${GREEN}✓ Java found${NC}"

# 检查ANTLR
echo -e "\n${YELLOW}[2/5]${NC} Checking ANTLR..."
ANTLR_JAR="lib/antlr-4.13.1-complete.jar"

if [ ! -f "$ANTLR_JAR" ]; then
    echo "ANTLR jar not found. Downloading..."
    mkdir -p lib
    wget -O "$ANTLR_JAR" https://www.antlr.org/download/antlr-4.13.1-complete.jar
    
    if [ $? -ne 0 ]; then
        echo -e "${RED}Error: Failed to download ANTLR${NC}"
        exit 1
    fi
fi

echo -e "${GREEN}✓ ANTLR found${NC}"

# 生成解析器
echo -e "\n${YELLOW}[3/5]${NC} Generating parser from grammar..."
mkdir -p src/parser

java -jar "$ANTLR_JAR" -o src/parser -package parser \
    -visitor -listener grammars/GoLang.g4

if [ $? -ne 0 ]; then
    echo -e "${RED}Error: Failed to generate parser${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Parser generated${NC}"

# 编译Java源文件
echo -e "\n${YELLOW}[4/5]${NC} Compiling Java sources..."
mkdir -p bin

# 查找所有Java文件
JAVA_FILES=$(find src -name "*.java")

javac -d bin -cp "$ANTLR_JAR:src" $JAVA_FILES

if [ $? -ne 0 ]; then
    echo -e "${RED}Error: Compilation failed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Compilation successful${NC}"

# 创建运行脚本
echo -e "\n${YELLOW}[5/5]${NC} Creating run script..."

cat > gocompiler.sh << 'EOF'
#!/bin/bash
java -cp "bin:lib/antlr-4.13.1-complete.jar" GoCompiler "$@"
EOF

chmod +x gocompiler.sh

echo -e "${GREEN}✓ Run script created${NC}"

# 完成
echo -e "\n${GREEN}======================================"
echo "Build completed successfully!"
echo "======================================${NC}"
echo ""
echo "Usage: ./gocompiler.sh [options] <source-file>"
echo "Example: ./gocompiler.sh examples/test.go"
echo ""

