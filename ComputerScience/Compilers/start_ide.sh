#!/bin/bash

# GO语言编译器IDE启动脚本

echo "=========================================="
echo "  GO语言编译器 - 可视化演示IDE"
echo "=========================================="
echo ""

# 检查是否已构建
if [ ! -d "bin" ]; then
    echo "项目尚未构建，正在构建..."
    ./build.sh
    if [ $? -ne 0 ]; then
        echo "构建失败！请先运行 ./build.sh"
        exit 1
    fi
fi

# 启动IDE
echo "正在启动IDE..."
echo ""

# 设置UTF-8编码和中文字体支持
java -Dfile.encoding=UTF-8 \
     -Dsun.jnu.encoding=UTF-8 \
     -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel \
     -cp "bin:lib/antlr-4.13.1-complete.jar" \
     ide.GoCompilerIDE

echo ""
echo "IDE已关闭"

