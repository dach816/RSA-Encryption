import java.math.BigInteger;
import java.util.Random;

/**
 * Makes random prime numbers, gets values for p, q, m and k for encryption.
 */
public class PrimeGenerator {
    /**
     * Random prime different than q.
     */
    int p;
    /**
     * Random prime different than p.
     */
    int q;
    /**
     * m = p * q
     */
    long m;
    /**
     * 1 < k < φ(m) and gcd(k, φ(m)) = 1
     */
    int k;
    /**
     * Random number generator.
     */
    static Random rand = new Random();
    static final int min = 10000;
    static final int max = 45000;
    
    public PrimeGenerator() {
        this.p = makeP();
        this.q = makeQ(p);
        this.m = p * q;
        this.k = makeK(p, q);
    }

    /**
     * Determines if a number is prime. (Taken from Wikipedia).
     * @param n An integer
     * @return Whether or not {@code n} is prime
     */
    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        } else {
            double sqrtN = Math.floor(Math.sqrt(n));
            for (int i = 5; i <= sqrtN; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        }
    }
    
    /**
     * Makes a random number between 2 ints.
     * @param low The lower bound
     * @param high The upper bound
     * @return A random number
     */
    public static int makeRand(int low, int high) {
        return rand.nextInt(high - low) + low;
    }
    
    /**
     * Finds the gcd between 2 ints.
     * @param a First int.
     * @param b Second int.
     * @return The gcd
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    /**
     * Makes the prime number p for encryption.
     * @return A prime number.
     */
    public int makeP() {
        int p = makeRand(min, max);
        
        while (!isPrime(p)) {
            p = makeRand(min, max);
        }
        
        return p;
    }
    
    /**
     * Makes the prime number q for encryption that's coprime to p.
     * @return A prime number.
     */
    public int makeQ(int p) {
        int q = makeRand(min, max);
        
        while (!isPrime(q) || gcd(p, q) != 1) {
            q = makeRand(min, max);
        }
        
        return q;
    }
    
    /**
     * Finds an int between 1 and φ(m) and is also coprime to it.
     * @param p One component of m
     * @param q The other component of m
     * @return An int
     */
    public int makeK(int p, int q) {
        int phi = ((p - 1) * (q - 1));
        int k = 0;
        int d = 0;
        
        while (gcd(phi, k) != 1 && gcd(phi, d) != 1) {
            k = makeRand(3, min);
            
            BigInteger biK = BigInteger.valueOf(k);
            BigInteger biPhi = BigInteger.valueOf((p-1) * (q-1));
            try {
                BigInteger inv = biK.modInverse(biPhi);
                d = inv.intValue();
            } catch (java.lang.ArithmeticException e) {
                d = 0;
            }
        }
        
        return k;
    }
}
