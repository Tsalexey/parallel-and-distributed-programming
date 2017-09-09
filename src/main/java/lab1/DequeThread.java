package lab1;

import java.util.Random;

/**
 * Created by Alexey on 08.09.2017.
 */
public class DequeThread implements Runnable {
    private MutexDeque deque;
    private int min = 1;
    private int max = 4;
    private String name;
    private int repeats;

    public DequeThread(MutexDeque deque, String name, int repeats){
        this.deque = deque;
        this.name = name;
        this.repeats = repeats;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < repeats; i++){
                deque.acquire();
                int rand = new Random().nextInt((max - min) + 1) + min;
                switch (rand) {
                    case 1:
                        System.out.println("Thread " + name + " added to HEAD");
                        deque.addFirst(name);
                        break;
                    case 2:
                        System.out.println("Thread " + name + " added to TAIL");
                        deque.addLast(name);
                        break;
                    case 3:
                        System.out.println("Thread " + name + " removed from HEAD");
                        deque.removeFirst();
                        break;
                    case 4:
                        System.out.println("Thread " + name + " removed from TAIL");
                        deque.removeLast();
                        break;
                }
                deque.release();
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
