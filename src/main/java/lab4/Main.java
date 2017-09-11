package lab4;

import lab2.RandomGenerator;
import lab2.XorshiftPlus;
import lab3.MutexGenerator;
import lab3.RandomGeneratorThread;

import java.util.ArrayList;
import java.util.List;

import static lab2.OutputUtils.printFile;
import static lab2.StatUtils.getMean;
import static lab2.StatUtils.getVariance;
import static lab2.StatUtils.printStatInfo;
import static lab4.ThreadUtil.executeThreads;

/**
 * Created by Alexey on 10.09.2017.
 */
public class Main {

    /**
     * На основе генераторов из предыдущей лабораторной, реализовать генерацию распределений,
     * отличных от равномерного: нормальное распределение, распределение 3. Пуассона,
     * Вейбула и экспоненциальное распределение.
     * Распределить работу между несколькими нитями.
     * Протестировать программу, нарисовав гистограмму распределений, а также вычислив эмпирическое
     * математическое ожидание и эмпирическую дисперсию, сравнив их с теоретическими значениями
     * для соответствующих распределений.
     **/
    public static void main(String[] args) throws InterruptedException {
        int threadsCount = 16;
        int numbersToGenerateForThread = 20;

        double lambda = 1; // Poiss, Weibull, Exponential parameters
        double k = 5; // Weibull parameter
        double mu = 0; // Normal parameter
        double sigma = 0.2; // Normal parameter

        MutexGenerator exponetialGenerator = new MutexGenerator(new ExponentialGenertor(lambda, new XorshiftPlus(false)));
        exponetialGenerator = executeThreads(exponetialGenerator, threadsCount, numbersToGenerateForThread);

        MutexGenerator poissGenerator = new MutexGenerator(new PoissGenerator(lambda, new XorshiftPlus(false)));
        poissGenerator = executeThreads(poissGenerator, threadsCount, numbersToGenerateForThread);

        MutexGenerator normalGenerator = new MutexGenerator(new NormalGenerator(mu, sigma, new XorshiftPlus(true)));
        normalGenerator = executeThreads(normalGenerator, threadsCount, numbersToGenerateForThread);

        MutexGenerator weibullGenerator = new MutexGenerator(new WeibullGenerator(lambda, k, new XorshiftPlus(false)));
        weibullGenerator = executeThreads(weibullGenerator, threadsCount, numbersToGenerateForThread);

        List<List<Double>> result = new ArrayList<>();
        result.add(exponetialGenerator.getGeneratedNumbers());
        result.add(poissGenerator.getGeneratedNumbers());
        result.add(normalGenerator.getGeneratedNumbers());
        result.add(weibullGenerator.getGeneratedNumbers());
        printFile("src\\main\\java\\lab4\\result.dat", result);

        // statistical test
        double exponentialMean = getMean(exponetialGenerator.getGeneratedNumbers());
        double exponentialVariance = getVariance(exponetialGenerator.getGeneratedNumbers(), exponentialMean);

        double poissMean = getMean(poissGenerator.getGeneratedNumbers());
        double poissVariance = getVariance(poissGenerator.getGeneratedNumbers(), poissMean);

        double normalMean = getMean(normalGenerator.getGeneratedNumbers());
        double normalVariance = getVariance(normalGenerator.getGeneratedNumbers(), normalMean);

        double weibullMean = getMean(weibullGenerator.getGeneratedNumbers());
        double weibullVariance = getVariance(weibullGenerator.getGeneratedNumbers(), weibullMean);

        printStatInfo("Exponential distribution", exponentialMean, exponentialVariance, Math.pow(lambda, -1), Math.pow(lambda, -2));
        printStatInfo("Poisson distribution", poissMean, poissVariance, lambda, lambda);
        printStatInfo("Normal distribution", normalMean, normalVariance, mu, Math.pow(sigma, 2));
        printStatInfo("Weibull distribution", weibullMean, weibullVariance, Double.NaN, Double.NaN);
    }
}
