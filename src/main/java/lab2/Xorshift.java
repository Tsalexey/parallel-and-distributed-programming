package lab2;

/**
 * Created by Alexey on 11.09.2017.
 */

import com.google.common.primitives.Longs;

import java.security.SecureRandom;

/**
 * Created by Alexey on 10.09.2017.
 */
public class Xorshift implements RandomGenerator{
    long x;
    long m = (long)(Math.pow(2, 63)-1);
    Long seed = null;

    public Xorshift(){
        SecureRandom secureRandom = new SecureRandom();
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        seed = Longs.fromByteArray(bytes);
        x = this.seed;
    }

    public Xorshift(byte[] seed){
        this.seed = Longs.fromByteArray(seed);
    }

    public double getNumber() {
        x ^= x << 13;
        x ^= x >>> 17;
        x ^= x << 5;
//        return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(m), MathContext.DECIMAL64).doubleValue();
        return ((double)(x & 0x7fffffffffffffffL)/(double)m);
    }
}

