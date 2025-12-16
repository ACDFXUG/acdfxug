# GO语言编译器项目

一个用Java和ANTLR4实现的GO语言子集编译器，支持完整的编译流程：词法分析→语法分析→语义分析→中间代码生成→优化→汇编代码生成→可执行文件生成。

## 项目概述

本项目是一个教学型编译器，实现了GO语言的核心特性，包括：
- ✅ 基本数据类型（int, float, string, bool）
- ✅ 变量声明和赋值
- ✅ 函数定义和调用
- ✅ 控制流语句（if-else, for循环）
- ✅ 表达式运算（算术、关系、逻辑）
- ✅ 数组基本操作
- ✅ 递归函数支持

## 项目结构

```
go_compiler/
├── grammars/               # ANTLR语法文件
│   └── GoLang.g4          # GO语言语法定义
├── src/                   # 源代码
│   ├── GoCompiler.java    # 编译器主类
│   ├── semantic/          # 语义分析
│   │   ├── Symbol.java
│   │   ├── VariableSymbol.java
│   │   ├── FunctionSymbol.java
│   │   ├── Scope.java
│   │   ├── BaseScope.java
│   │   ├── GlobalScope.java
│   │   └── LocalScope.java
│   │   └── SemanticAnalyzer.java
│   ├── codegen/           # 代码生成
│   │   ├── Quadruple.java
│   │   ├── TACGenerator.java
│   │   ├── CodeGenerator.java
│   │   └── AssemblyGenerator.java
│   ├── optimizer/         # 代码优化
│   │   └── TACOptimizer.java
│   └── error/             # 错误处理
│       ├── ErrorHandler.java
│       └── CompileError.java
├── examples/              # 示例程序
├── tests/                 # 测试用例
├── docs/                  # 文档
├── lib/                   # 依赖库
├── build.sh              # 构建脚本
└── run.sh                # 运行脚本
```

## 编译器架构

### 1. 词法分析和语法分析
- 使用ANTLR4自动生成词法分析器和语法分析器
- 支持GO语言核心语法
- 生成语法分析树（Parse Tree）

### 2. 语义分析
- 符号表管理（支持嵌套作用域）
- 类型检查
- 变量定义检查
- 函数调用验证

### 3. 中间代码生成
- 生成三地址指令（Three-Address Code）
- 支持所有GO语言表达式和语句
- 临时变量管理
- 标号管理

### 4. 代码优化
- 常量折叠
- 常量传播
- 复制传播
- 死代码消除
- 代数简化

### 5. 汇编代码生成
- 生成x86-64汇编代码
- 寄存器分配
- 栈帧管理
- 函数调用约定

### 6. 可执行文件生成
- 使用nasm汇编器
- 使用ld链接器
- 生成可执行ELF文件

## 安装和构建

### 前置要求

1. **Java Development Kit (JDK) 8或更高版本**
   ```bash
   java -version
   javac -version
   ```

2. **ANTLR 4.13.1**（自动下载）

3. **NASM汇编器**（可选，用于生成可执行文件）
   ```bash
   sudo apt-get install nasm
   ```

4. **GNU链接器**（可选，用于生成可执行文件）
   ```bash
   sudo apt-get install binutils
   ```

### 构建步骤

1. 克隆或下载项目：
   ```bash
   cd /root/go_compiler
   ```

2. 运行构建脚本：
   ```bash
   chmod +x build.sh
   ./build.sh
   ```

3. 构建成功后，会生成`gocompiler.sh`运行脚本

## 🎨 演示系统

本项目提供了**两种演示方式**，方便展示和演示编译器功能：

### 方式1：图形界面IDE（推荐用于演示）
```bash
./start_ide.sh
```
- 可视化代码编辑器
- 8个内置示例代码
- 多标签页显示结果
- 实时优化统计

### 方式2：终端交互式演示
```bash
./demo.sh
```
- 交互式菜单系统
- 8种演示场景
- 彩色输出，美观易读
- 适合SSH远程演示

详见：[演示指南.md](演示指南.md) 和 [IDE使用说明.md](IDE使用说明.md)

---

## 使用方法

### 基本用法

```bash
./gocompiler.sh <source-file.go>
```

### 命令行选项

```bash
./gocompiler.sh [选项] <源文件>

选项:
  -v, --verbose    详细输出模式
  -O0              禁用优化
  -S               只生成三地址代码（不生成汇编）
  -o <目录>        指定输出目录（默认：当前目录）
  -h, --help       显示帮助信息
```

### 示例

1. **编译简单程序**：
   ```bash
   ./gocompiler.sh examples/hello.go
   ```

2. **启用详细输出**：
   ```bash
   ./gocompiler.sh -v examples/arithmetic.go
   ```

3. **只生成三地址代码**：
   ```bash
   ./gocompiler.sh -S examples/factorial.go
   ```

4. **指定输出目录**：
   ```bash
   ./gocompiler.sh -o output examples/fibonacci.go
   ```

## 输出文件

编译器会生成以下文件：

1. **`output.tac`** - 三地址指令代码
2. **`output.opt.tac`** - 优化后的三地址指令代码
3. **`output.asm`** - x86-64汇编代码
4. **`output.o`** - 目标文件
5. **`output`** - 可执行文件

## 示例程序

### 1. Hello World
```go
package main

func main() {
    println("Hello, World!")
}
```

### 2. 阶乘函数
```go
package main

func factorial(n int) int {
    if n <= 1 {
        return 1
    } else {
        return n * factorial(n - 1)
    }
}

func main() {
    result := factorial(5)
}
```

### 3. 斐波那契数列
```go
package main

func fib(n int) int {
    if n <= 1 {
        return n
    }
    return fib(n - 1) + fib(n - 2)
}

func main() {
    result := fib(10)
}
```

更多示例请查看 `examples/` 目录。

## 编译过程示例

```
Compiling: examples/factorial.go
============================================================

[Phase 1] Lexical and Syntax Analysis...
✓ Parsing completed successfully

[Phase 2] Semantic Analysis...
✓ Semantic analysis completed successfully

[Phase 3] Generating Three-Address Code...
✓ Generated 24 TAC instructions
✓ TAC code saved to: ./output.tac

[Phase 4] Optimizing...
Instructions before: 24
Instructions after:  18
Instructions removed: 6
Reduction: 25.00%
✓ Optimized TAC code saved to: ./output.opt.tac

[Phase 5] Generating Assembly Code...
✓ Assembly code saved to: ./output.asm

[Phase 6] Assembling and Linking...
✓ Executable created: ./output

============================================================
Compilation completed successfully!
```

## 三地址指令示例

**源代码：**
```go
func add(a int, b int) int {
    return a + b
}
```

**三地址指令：**
```
add:
    t1 = a + b
    return t1
```

## 优化示例

**优化前：**
```
t1 = 2 + 3
t2 = t1 * 4
x = t2
```

**优化后（常量折叠）：**
```
t1 = 5
t2 = 20
x = t2
```

## 错误检测

编译器支持以下错误检测：
- ✅ 语法错误
- ✅ 未定义的变量
- ✅ 未定义的函数
- ✅ 类型不匹配
- ✅ 函数参数数量不匹配
- ✅ 变量重复定义

**错误报告示例：**
```
Error at line 3, column 4: Variable 'x' is not defined
Error at line 5: Function 'foo' expects 2 arguments but got 1
```

## 技术特点

### 1. 符号表管理
- 支持嵌套作用域
- 全局作用域、函数作用域、局部作用域
- 符号解析链

### 2. 类型系统
- 静态类型检查
- 类型推导（短变量声明）
- 函数签名验证

### 3. 代码优化
- 常量折叠：编译时计算常量表达式
- 常量传播：将常量值传播到使用处
- 复制传播：消除不必要的复制
- 死代码消除：删除未使用的代码
- 代数简化：简化数学表达式

### 4. 目标代码生成
- x86-64指令集
- 系统V调用约定
- 栈帧管理
- 寄存器分配

## 测试

运行所有示例测试：
```bash
cd examples
for file in *.go; do
    echo "Testing $file..."
    ../gocompiler.sh "$file"
done
```

测试错误检测：
```bash
./gocompiler.sh tests/test_errors.go
```

## 局限性

本编译器是教学项目，有以下局限：
- 仅支持GO语言的核心子集
- 不支持结构体、接口、通道等高级特性
- 不支持包管理
- 优化较为简单
- 目标代码效率一般

## 参考文献

1. Alfred V. Aho et al., "Compilers: Principles, Techniques, and Tools", 2nd Edition
2. The Go Programming Language Specification (https://go.dev/ref/spec)
3. ANTLR 4权威指南
4. x86-64 Assembly Language Reference
5. 编译原理课程讲义（31-63页：三地址指令代码）

## 开发团队

本项目为编译原理课程设计项目。

## 许可证

MIT License

## 联系方式

如有问题或建议，请联系项目维护者。

