public class Console {

    public static void main(String[] args) {
        PrimeGenerator pg = new PrimeGenerator();
        int p = pg.p;
        int q = pg.q;
        long m = pg.m;
        int k = pg.k;
        
        //Make ONLY m & k public.
        
        //Call to find what to make public (REMEMBER THESE TO DECRYPT!!):
        publicKeys(m, k);
        
        String message = "I love tribbles! They love me back!";
        long givenM = m;
        int givenK = k;

        //Call to encrypt a message:
        encrypt(message, givenM, givenK);
        
        Encrypt enc = new Encrypt(message, givenM, givenK);
        String encrypted = enc.encrypt();
        
        //Call to decrypt a message:
        decrypt(encrypted, p, q, m, k);
    }
    
    static void publicKeys(long m, int k) {
        System.out.println("Make these public: m = " + m + ", k = " + k);
    }
    
    static void encrypt(String message, long m, int k) {
        Encrypt enc = new Encrypt(message, m, k);
        String encrypted = enc.encrypt();
        
        System.out.println("Encrypted message: " + encrypted);
    }
    
    static void decrypt(String encoded, int p, int q, long m, int k) {
        Decrypt dec = new Decrypt(encoded, p, q, m, k);
        String decrypted = dec.decrypt();
        
        System.out.println("Decrypted message: " + decrypted);
    }

}
