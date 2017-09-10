package lab3;

import lab2.RandomGenerator;

import java.util.Random;

/**
 * Created by Alexey on 11.09.2017.
 */
public class RandomGeneratorThread implements Runnable {
    private MutexGenerator generator;
    private int numbersToGenerate;

    public RandomGeneratorThread(MutexGenerator generator, int numbersToGenerate){
        this.generator = generator;
        this.numbersToGenerate = numbersToGenerate;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numbersToGenerate; i++) {
                generator.acquire();
                generator.generate();
                generator.release();
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
