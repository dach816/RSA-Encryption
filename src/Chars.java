public class Chars {
    public static final char[] allChars = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            + " .,'\"!?$%-+=^*/()1234567890").toCharArray();
    
    public static int getIndex(char ch) {
        for (int i = 0; i < allChars.length; ++i) {
            char c = allChars[i];
            if (c == ch) {
                return i + 12;
            }
        }
        
        return -1;
    }
    
    public static char getChar(int index) {
        if (index < 12) {
            return '@';
        }
        else {
            return allChars[index - 12];
        }
    }
}
