import java.math.BigInteger;


public class Encrypt {
    String message;
    static long m;
    int k;
    
    public Encrypt(String message, long m, int k) {
        this.message = message;
        Encrypt.m = m;
        this.k = k;
    }
    
    public String stringToInt() {
        char[] chars = message.toCharArray();
        int[] ints = new int[chars.length];
        String result = "";
        
        for (int i = 0; i < chars.length; ++i) {
            char ch = chars[i];
            ints[i] = Chars.getIndex(ch);
        }
        
        for (int i : ints) {
            result += i;
        }
        
        return result;
    }
    
    public String encrypt() {
        int mLength = String.valueOf(m).length();
        String[] sMes = separate(stringToInt(), mLength - 1);
        String encodedNum = "";
        
        for (String mes : sMes) {
            if (null == mes || mes.equals("")) {
            }
            else {
                BigInteger biMes = BigInteger.valueOf(Long.parseLong(mes));
                BigInteger biM = BigInteger.valueOf(m);
                BigInteger biK = BigInteger.valueOf(k);

                //encodedInt = mes^k (mod m)
                BigInteger mToK = biMes.modPow(biK, biM);

                encodedNum += mToK.toString();
            }
        }
        
        return encodedNum;
    }
    
    public static String[] separate(String st, int len) {
        int length = st.length() / len;
        String[] sArray = new String[length];
        String s = st;
        
        for (int i = 0; i < length; ++i) {
            if (s.length() <= len + 1) {
                if (s.equals("") || Long.parseLong(s) < m) {
                    sArray[i] = s;
                    s = "";
                }
                else {
                    sArray[i] = s.substring(0, len);
                    s = s.substring(len);
                }
            }
            else if (Long.parseLong(s.substring(0, len + 1)) < m) {
                sArray[i] = s.substring(0, len + 1);
                s = s.substring(len + 1);
            }
            else {
                sArray[i] = s.substring(0, len);
                s = s.substring(len);
            }
        }
        
        return sArray;
    }
}
