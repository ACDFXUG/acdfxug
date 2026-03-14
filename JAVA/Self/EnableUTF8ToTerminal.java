package Java.Self;

import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;

import java.io.IOException;

public class EnableUTF8ToTerminal {
    static final String VSCODE_DIR="C:/Users/yaoyu/AppData/Local/Programs/Microsoft VS Code";
    static final String PS_FILE="shellIntegration.ps1";

    static final String OUTPUT="[Console]::OutputEncoding=[System.Text.Encoding]::UTF8;";
    static final String INPUT="[Console]::InputEncoding=[System.Text.Encoding]::UTF8;";
    static Path getPSDir() throws IOException{
        Path vs=Path.of(VSCODE_DIR);
        var notBin=Files.list(vs).filter(
            file->Files.isDirectory(file)&&!file.getFileName().toString().equals("bin")
        ).findFirst().get().toString();
        return Path.of(notBin,"resources/app/out/vs/workbench/contrib/terminal/common/scripts");
    }
    public static void main(String[] args) throws Exception{
        Path psDir=getPSDir();
        Path ps=Path.of(psDir.toString(),PS_FILE);
        Path tmp=Files.createTempFile(psDir,null,".ps1");
        var lines=Files.readAllLines(ps);
        int insertLine=lines.indexOf("\t$env:VSCODE_ENV_APPEND = $null")+2;
        lines.add(insertLine,OUTPUT);
        lines.add(insertLine,INPUT);
        Files.write(tmp,lines);
        Files.move(tmp,ps,REPLACE_EXISTING);
    }
}
