package lab2;

import com.google.common.primitives.Longs;

import java.security.SecureRandom;

/**
 * Created by Alexey on 10.09.2017.
 */
public class XorshiftPlus implements RandomGenerator{
    Long seed1 = null;
    Long seed2 = null;
    long m = (long)(Math.pow(2, 64)-1);
    boolean withNegative;

    public XorshiftPlus(boolean withNegative){
        SecureRandom secureRandom = new SecureRandom();
        byte bytes1[] = new byte[20];
        byte bytes2[] = new byte[20];
        secureRandom.nextBytes(bytes1);
        secureRandom.nextBytes(bytes2);
        seed1 = Longs.fromByteArray(bytes1);
        seed2 = Longs.fromByteArray(bytes2);
        this.withNegative = withNegative;
    }

    public XorshiftPlus(byte[] seed1, byte[] seed2){
        this.seed1 = Longs.fromByteArray(seed1);
        this.seed2 = Longs.fromByteArray(seed2);
        this.withNegative = false;
    }

    public double getNumber() {
        long x = seed1;
        long y = seed2;

        seed1 = y;
        x^= x<<23;
        seed2 = x^y^(x>>>17)^(y>>>26);
        return withNegative ? (double)((seed2+y))/(double)m : ((double)((seed2+y) & 0x7fffffffffffffffL))/(double)m;
    }
}
