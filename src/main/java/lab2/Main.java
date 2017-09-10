package lab2;

import com.google.common.primitives.Longs;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static lab2.OutputUtils.printFile;
import static lab2.StatUtils.*;

/**
 * Created by Alexey on 10.09.2017.
 */
public class Main {

    /**
     * Реализовать по крайней мере два алгоритма генерации псевдослучайных равномерно-распределенных
     * чисел (при этом один из них обязательно должен использовать побитовые операции).
     * В качестве инициализирующих значений для генератора использовать биты из псевдоустройства Unix /dev/random.
     * Протестировать генераторы с помощью графического теста, нарисовав гистограмму для разного количества
     * сгенерированных чисел.
     * Протестировать генераторы вычислив для разного количества сгенериорванных чисел эмпирическое математическое
     * ожидание и эмпирическую дисперсию. Сравнить их с теоретическими значениями для равномерного распределения.
     *
     **/
    public static void main(String[] args){
        // get seed
        SecureRandom secureRandom = new SecureRandom();
        byte bytes1[] = new byte[20];
        byte bytes2[] = new byte[20];
        secureRandom.nextBytes(bytes1);
        secureRandom.nextBytes(bytes2);
        // init generators
        LCG lcg = new LCG(Longs.fromByteArray(bytes1));
        Xorshift xorshift = new Xorshift(bytes1);
        XorshiftAsterisk xorshiftAsterisk = new XorshiftAsterisk(bytes1);
        XorshiftPlus xorshiftPlus = new XorshiftPlus(bytes1, bytes2);

        List<Double> lcgList = new ArrayList<>();
        List<Double> xorshiftList = new ArrayList<>();
        List<Double> xorshiftAList = new ArrayList<>();
        List<Double> xorshiftPList = new ArrayList<>();

        for (int i  = 0; i < 10000 ; i++){
            lcgList.add(lcg.getNumber());
            xorshiftAList.add(xorshiftAsterisk.getNumber());
            xorshiftList.add(xorshift.getNumber());
            xorshiftPList.add(xorshiftPlus.getNumber());
        }

        double a = 0;
        double b = 1;

        double lcgEmpiricalMean = getMean(lcgList);
        double lcgEmpiricalVariance = getVariance(lcgList, lcgEmpiricalMean);

        System.out.println("LCG: " +
                "\n    theoretical mean = " + getTheoreticalMean(a, b) +
                "\n    empirical mean = " + lcgEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(a, b) +
                "\n    empirical variance = " + lcgEmpiricalVariance
        );

        double xorshiftEmpiricalMean = getMean(xorshiftList);
        double xorshiftEmpiricalVariance = getVariance(xorshiftList, xorshiftEmpiricalMean);
        System.out.println("Xorshift: " +
                "\n    theoretical mean = " + getTheoreticalMean(a, b) +
                "\n    empirical mean = " + xorshiftEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(a, b) +
                "\n    empirical variance = " + xorshiftEmpiricalVariance
        );

        double xorshiftAEmpiricalMean = getMean(xorshiftAList);
        double xorshiftAEmpiricalVariance = getVariance(xorshiftAList, xorshiftAEmpiricalMean);
        System.out.println("Xorshift+: " +
                "\n    theoretical mean = " + getTheoreticalMean(a, b) +
                "\n    empirical mean = " + xorshiftAEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(a, b) +
                "\n    empirical variance = " + xorshiftAEmpiricalVariance
        );

        double xorshiftPEmpiricalMean = getMean(xorshiftPList);
        double xorshiftPEmpiricalVariance = getVariance(xorshiftPList, xorshiftPEmpiricalMean);
        System.out.println("Xorshift*: "+
                "\n    theoretical mean = " + getTheoreticalMean(a, b) +
                "\n    empirical mean = " + xorshiftPEmpiricalMean +
                "\n    theoretical variance = " + getTheoreticalVariance(a, b) +
                "\n    empirical variance = " + xorshiftPEmpiricalVariance
        );

        List<List<Double>> data = new ArrayList<>();
        data.add(lcgList);
        data.add(xorshiftList);
        data.add(xorshiftAList);
        data.add(xorshiftPList);

        printFile("src\\main\\java\\lab2\\result.dat", data);
    }

}
