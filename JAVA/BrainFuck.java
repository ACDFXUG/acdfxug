package JAVA;

import java.util.*;
import java.io.*;

public class BrainFuck {
    private final int memSize;
    private final byte[] memArr;
    private int pointer,instrPointer;
    private HashMap<Integer,Integer> loops;
    public BrainFuck(int memSize){
        this.memSize=memSize;
        this.memArr=new byte[memSize];
        this.pointer=0;
        this.instrPointer=0;
        this.loops=new HashMap<>();
    }
    public BrainFuck(){
        this(30000);
    }
    private void processLoop(String command) throws IllegalArgumentException{
        Deque<Integer> stack=new ArrayDeque<>();
        for(int i=0;i<command.length();++i){
            switch(command.charAt(i)){
                case '['->{
                    stack.push(i);
                }case ']'->{
                    if(stack.isEmpty()) throw new IllegalArgumentException("Unmatched ] at index "+i);
                    int idx=stack.pop();
                    loops.put(idx,i);
                    loops.put(i,idx);
                }
            }
        }
        if(!stack.isEmpty()) throw new IllegalArgumentException("Unmatched [ at index "+stack.pop());
    }
    public void run(String command) throws IOException{
        try{
            processLoop(command);
        }catch(IllegalArgumentException iae){
            iae.printStackTrace();
            return;
        }
        for(;instrPointer<command.length();++instrPointer){
            switch(command.charAt(instrPointer)){
                case '<'->{
                    pointer=(pointer-1+memSize)%memSize;
                }case '>'->{
                    pointer=(pointer+1)%memSize;
                }case '+'->{
                    ++memArr[pointer];
                }case '-'->{
                    --memArr[pointer];
                }case '.'->{
                    System.out.print((char)memArr[pointer]);
                }case ','->{
                    memArr[pointer]=(byte)System.in.read();
                }case '['->{
                    if(memArr[pointer]==0) instrPointer=loops.get(instrPointer);
                }case ']'->{
                    if(memArr[pointer]!=0) instrPointer=loops.get(instrPointer);
                }
            }
        }
    }
    public static void main(String[] args) {
        BrainFuck bf=new BrainFuck();
        String command="++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        try{
            bf.run(command);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
