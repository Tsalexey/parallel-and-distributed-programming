package lab2;

import java.util.List;

/**
 * Created by Alexey on 11.09.2017.
 */
public class StatUtils {
    long maxLong = (long)(Math.pow(2, 64)-1);

    public static double getMean(List<Double> list){
        double expected = 0;
        for(Double value: list){
            expected += value;
        }
        return expected/list.size();
    }

    public static double getVariance(List<Double> list, double mean){
        double variance = 0;
        for(Double value: list){
            variance += Math.pow(value - mean,2);
        }
        return variance/list.size();
    }

    public static double getTheoreticalMean(double a, double b){
        return (b-a)/2;
    }

    public static double getTheoreticalVariance(double a, double b){
        return Math.pow(b-a, 2)/12;
    }
}
