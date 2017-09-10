package lab2;

import com.google.common.primitives.Longs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.SecureRandom;

/**
 * Created by Alexey on 10.09.2017.
 */
public class LCG {
    long a = 25214903917L;
    long c = 11L;
    long m = (long)(Math.pow(2, 63)-1);
    long x;
    Long seed = null;

    public LCG(){
        SecureRandom secureRandom = new SecureRandom();
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        seed = Longs.fromByteArray(bytes);
        this.x = seed;
    }
    public LCG(long seed){
        this.x = seed;
    }

    public double getNumber(){
        x = Long.remainderUnsigned(a * x + c, m);
        return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(m), MathContext.DECIMAL64).doubleValue();
    }
}
