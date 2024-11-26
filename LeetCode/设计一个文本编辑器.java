package LeetCode;

public class 设计一个文本编辑器 {
    private static class TextEditor{
        final StringBuilder txt;
        int cursor;
        TextEditor(){
            this.txt=new StringBuilder();
            this.cursor=0;
        }
        void addText(String text){
            txt.insert(cursor,text);
            cursor+=text.length();
        }
        int deleteText(int k){
            int ans=Math.min(k,cursor);
            txt.delete(cursor-ans,cursor);
            cursor-=ans;
            return ans;
        }
        String cursorLeft(int k){
            int ans=Math.min(k,cursor);
            cursor-=ans;
            return txt.substring(Math.max(10,cursor)-10,cursor);
        }
        String cursorRight(int k){
            cursor+=Math.min(k,txt.length()-cursor);
            return txt.substring(Math.max(10,cursor)-10,cursor);
        }
    }
    public static void main(String[] args) {
        var te=new TextEditor();
        te.addText("leetcode");
        te.deleteText(4);
        te.addText("practice");
        System.out.println(te.cursorRight(3));
        System.out.println(te.cursorLeft(8));
        te.deleteText(10);
        System.out.println(te.cursorLeft(2));
        System.out.println(te.cursorRight(6));
    }
}
