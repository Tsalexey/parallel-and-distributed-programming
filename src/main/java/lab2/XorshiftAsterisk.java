package lab2;

import com.google.common.primitives.Longs;

import java.security.SecureRandom;

/**
 * Created by Alexey on 10.09.2017.
 */
public class XorshiftAsterisk implements RandomGenerator {
    long x;
    long y;
    long m = (long)(Math.pow(2, 63)-1);
    Long seed = null;

    public XorshiftAsterisk(){
        SecureRandom secureRandom = new SecureRandom();
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        seed = Longs.fromByteArray(bytes);
        x = this.seed;
        y = this.seed;
    }

    public XorshiftAsterisk(byte[] seed){
        this.seed = Longs.fromByteArray(seed);
        x = this.seed;
        y = this.seed;
    }

    public double getNumber() {
        x ^= x >>> 12;
        x ^= x << 25;
        x ^= x >>> 27;
        y = x*2685821657736338717L;

        return ((double)((x + 2685821657736338717L) & 0x7fffffffffffffffL))/(double)m;
//        return (BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(2685821657736338717L))).divide(BigDecimal.valueOf(m), MathContext.DECIMAL64).doubleValue();
    }
}
