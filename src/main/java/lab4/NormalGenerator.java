package lab4;

import lab2.RandomGenerator;


/**
 * Created by Alexey on 12.09.2017.
 */
public class NormalGenerator implements RandomGenerator {
    RandomGenerator uniforGenerator;
    double mu;
    double sigma;

    public NormalGenerator(double mu, double sigma, RandomGenerator uniformGenerator){
        this.uniforGenerator = uniformGenerator;
        this.mu = mu;
        this.sigma = sigma;
    }

    @Override
    public double getNumber() {
        double u1;
        double u2;
        double s;
        while (true) {

            u1 = uniforGenerator.getNumber();
            u2 = uniforGenerator.getNumber();
            s = Math.pow(u1, 2) + Math.pow(u2, 2);
            if (s>0 && s <=1){
                break;
            }
        }
        double x = u1*Math.sqrt(-(2*Math.log(s)/s)); // standart normal view
        double y = u2*Math.sqrt(-(2*Math.log(s)/s));// standart normal view

        double z = sigma * x + mu; // normal common view
        return z;
    }
}
