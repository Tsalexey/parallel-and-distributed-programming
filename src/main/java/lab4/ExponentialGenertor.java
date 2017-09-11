package lab4;

import lab2.RandomGenerator;

/**
 * Created by Alexey on 12.09.2017.
 */
public class ExponentialGenertor implements RandomGenerator{
    RandomGenerator uniformGenerator;
    double lambda;

    public ExponentialGenertor(double lambda, RandomGenerator uniformGenerator){
        this.uniformGenerator = uniformGenerator;
        this.lambda = lambda;
    }

    @Override
    public double getNumber() {
        double x = uniformGenerator.getNumber();
        while(x==1){
            x = uniformGenerator.getNumber();
        }
        return -1/lambda*Math.log(1-x);
    }
}
