package main

// 这个文件包含各种错误，用于测试错误检测功能

func testUndefinedVariable() {
    x = 10  // 错误: 变量未声明
}

func testTypeMismatch() {
    var x int
    var y string
    x = y  // 错误: 类型不匹配（简化版可能不检测）
}

func testUndefinedFunction() {
    result := undefinedFunc()  // 错误: 函数未定义
}

func testWrongArguments() {
    add(1)  // 错误: 参数数量不匹配
}

func add(a int, b int) int {
    return a + b
}

func main() {
    testUndefinedVariable()
    testTypeMismatch()
    testUndefinedFunction()
    testWrongArguments()
}

