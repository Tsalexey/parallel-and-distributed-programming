package lab3;

import lab2.XorshiftAsterisk;
import lab2.XorshiftPlus;

import java.util.ArrayList;
import java.util.List;

import static lab2.OutputUtils.printFile;
import static lab2.StatUtils.*;

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
        int threadsCount = 4;
        int numbersToGenerateForThread = 4;
        MutexGenerator generator = new MutexGenerator(new XorshiftPlus());

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++){
            Thread t = new Thread(new RandomGeneratorThread(generator, numbersToGenerateForThread));
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t: threads){
            t.join();
        }

        System.out.println("Generated numbers:");
        for (Double d: generator.getGeneratedNumbers()){
            System.out.println("    " + d);
        }

        double xorshiftAEmpiricalMean = getMean(generator.getGeneratedNumbers());
        double xorshiftAEmpiricalVariance = getVariance(generator.getGeneratedNumbers(), xorshiftAEmpiricalMean);
        System.out.println("\n    theoretical mean = " + getTheoreticalMean(0, 1) +
                "\n    empirical mean = " + xorshiftAEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(0, 1) +
                "\n    empirical variance = " + xorshiftAEmpiricalVariance
        );

        List<List<Double>> result = new ArrayList<>();
        result.add(generator.getGeneratedNumbers());
        printFile("src\\main\\java\\lab3\\result.dat", result);

    }
}
