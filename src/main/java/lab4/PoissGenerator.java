package lab4;

import lab2.RandomGenerator;

/**
 * Created by Alexey on 12.09.2017.
 */
public class PoissGenerator implements RandomGenerator {
    RandomGenerator uniformGenerator;
    double lambda;

    public PoissGenerator(double lambda, RandomGenerator uniformGenerator){
        this.uniformGenerator = uniformGenerator;
        this.lambda = lambda;
    }

    @Override
    public double getNumber() {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= uniformGenerator.getNumber();
        } while (p > L);

        return k - 1;
    }
}
