package LeetCode;

public class 长按键入 {
    static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
               return false;  
            }
        }
        return i == name.length();
    }
    public static void main(String[] args) {
        System.out.println(isLongPressedName("saeed","ssaaedd"));   
    }
}
