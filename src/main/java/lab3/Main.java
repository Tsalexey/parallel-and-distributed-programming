package lab3;

import lab2.XorshiftAsterisk;
import lab2.XorshiftPlus;
import lab4.ExponentialGenertor;

import java.util.ArrayList;
import java.util.List;

import static lab2.OutputUtils.printFile;
import static lab2.StatUtils.*;
import static lab4.ThreadUtil.executeThreads;

/**
 * Created by Alexey on 10.09.2017.
 */
public class Main {

    /**
     * Написать параллельный генератор псевдослучайных чисел, распределив работу между параллельными процессами.
     * Воспользоваться одним из генераторов, который вы реализовали при выполнении лабораторной работы №2.
     Для проверки работы генератора сгенерировать вначале небольшое количество псевдослучайных чисел и убедиться,
     что среди них нет периодических повторов.
     Учесть, что для каждого потока необходимо отдельно задать начальное значение генератора иначе они могут
     сгенерировать одинаковую последовательность чисел.
     */
    public static void main(String[] args) throws InterruptedException {
        int threadsCount = 16;
        int numbersToGenerateForThread = 20;

        MutexGenerator uniformGenerator = new MutexGenerator(new XorshiftPlus(false));
        uniformGenerator = executeThreads(uniformGenerator, threadsCount, numbersToGenerateForThread);

        System.out.println("First 15 generated numbers:");
        for (int i = 0; i < uniformGenerator.getGeneratedNumbers().size(); i++) {
            if (i < 15 && i < uniformGenerator.getGeneratedNumbers().size())
                System.out.println("    " + uniformGenerator.getGeneratedNumbers().get(i));
        }

        double xorshiftAEmpiricalMean = getMean(uniformGenerator.getGeneratedNumbers());
        double xorshiftAEmpiricalVariance = getVariance(uniformGenerator.getGeneratedNumbers(), xorshiftAEmpiricalMean);
        System.out.println("\n    theoretical mean = " + getTheoreticalMean(0, 1) +
                "\n    empirical mean = " + xorshiftAEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(0, 1) +
                "\n    empirical variance = " + xorshiftAEmpiricalVariance
        );

        List<List<Double>> result = new ArrayList<>();
        result.add(uniformGenerator.getGeneratedNumbers());
        printFile("src\\main\\java\\lab3\\result.dat", result);

    }
}
