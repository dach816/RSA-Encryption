import java.math.BigInteger;


public class Decrypt {
    String encodedNum;
    int p;
    int q;
    long m;
    int k;
    
    public Decrypt(String encodedNum, int p, int q, long m, int k) {
        this.encodedNum = encodedNum;
        this.p = p;
        this.q = q;
        this.m = m;
        this.k = k;
    }
    
    public String decrypt() {
        String decoded = this.decodeNums();
        String result = "";
        
        for (int i = 0; i < decoded.length() / 2; ++i) {
            String s = decoded.substring(i * 2, i * 2 + 2);
            int num = Integer.parseInt(s);
            char ch = Chars.getChar(num);
            result += ch;
        }
        
        return result;
    }
    
    public String decodeNums() {
        int mLength = String.valueOf(m).length();
        String[] nums = Encrypt.separate(encodedNum, mLength - 1);
        String decodedNum = "";
        
        for (String num : nums) {
            if (num.equals("")) {
            }
            else {
                BigInteger biNum = BigInteger.valueOf(Long.parseLong(num));
                BigInteger biM = BigInteger.valueOf(m);
                BigInteger biK = BigInteger.valueOf(k);
                BigInteger biPhi = BigInteger.valueOf((p-1) * (q-1));
                BigInteger inv = biK.modInverse(biPhi);

                BigInteger numToInv = biNum.modPow(inv, biM);

                decodedNum += numToInv.toString();
            }
        }
        
        return decodedNum;
    }
}
