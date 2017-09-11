package lab4;

import lab2.RandomGenerator;

/**
 * Created by Alexey on 12.09.2017.
 */
public class WeibullGenerator implements RandomGenerator {
    RandomGenerator uniformGenerator;
    double lambda;
    double k;

    public WeibullGenerator(double lambda, double k, RandomGenerator uniformGenerator){
        this.uniformGenerator = uniformGenerator;
        this.lambda = lambda;
        this.k = k;
    }

    @Override
    public double getNumber() {
        return lambda * Math.pow(-Math.log(uniformGenerator.getNumber()), 1/k);
    }
}
