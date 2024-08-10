@echo off
chcp 65001

setlocal EnableDelayedExpansion

:: 遍历当前目录下的所有 .vtt 文件
for %%f in (*.vtt) do (
    echo Converting: %%f

    :: 获取不含扩展名的文件基本名，并移除可能的后缀，这部分可自行进行增添
    set "base_name=%%~nf"
    set "base_name=!base_name:.mp3=!"
    set "base_name=!base_name:.vtt=!"
    set "base_name=!base_name:.wav=!"

    :: 创建对应的 .lrc 文件名
    set "lrc_file=!base_name!.lrc"
    echo. > "!lrc_file!"
    set "counter=0"

    :: 读取并转换每一行
    for /f "tokens=*" %%i in (%%f) do (
        set /a counter+=1
        if !counter! geq 3 (
            if !counter!==3 (
                set "ttt=%%i"
            )
            if !counter!==4 (
                echo [!ttt:~3,8!]%%i>>"!lrc_file!"
                set "counter=1"
            )
        )
    )
)

echo All .vtt files have been converted to .lrc format.
pause
endlocal