package lab3;

import lab2.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Alexey on 11.09.2017.
 */
public class MutexGenerator {
    private Semaphore mutex = new Semaphore(1);
    private RandomGenerator generator;
    private List<Double> generatedNumbers;

    public MutexGenerator(RandomGenerator generator) {
        this.generator = generator;
        generatedNumbers = new ArrayList<>();
    }

    public void acquire() throws InterruptedException {
        mutex.acquire();
    }

    public void release(){
        mutex.release();
    }

    public double generate(){
        double number = generator.getNumber();
        getGeneratedNumbers().add(number);
        return number;
    }

    public List<Double> getGeneratedNumbers() {
        return generatedNumbers;
    }
}
