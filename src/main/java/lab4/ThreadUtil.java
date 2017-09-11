package lab4;

import lab3.MutexGenerator;
import lab3.RandomGeneratorThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 12.09.2017.
 */
public class ThreadUtil {

    public static MutexGenerator executeThreads(MutexGenerator gnrtr,
                                                int threadsCount,
                                                int numbersToGenerateForThread) throws InterruptedException {
        MutexGenerator generator = gnrtr;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++){
            Thread t = new Thread(new RandomGeneratorThread(generator, numbersToGenerateForThread));
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t: threads){
            t.join();
        }
        return generator;
    }
}
