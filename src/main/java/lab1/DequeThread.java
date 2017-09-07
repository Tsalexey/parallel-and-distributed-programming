package lab1;

import java.util.Random;

/**
 * Created by Alexey on 08.09.2017.
 */
public class DequeThread extends Thread {
    private MutexDeque deque;
    private int min = 1;
    private int max = 4;
    private int repeats = 5;

    public DequeThread(MutexDeque deque){
        this.deque = deque;
    }
    @Override
    public void run() {
        for (int i = 0; i < repeats; i ++) {
            int rand = new Random().nextInt((max - min) + 1) + min;
            switch (rand) {
                case 1:
                    System.out.println("Thread " + this.getName() + " added to HEAD");
                    deque.addFirst(this.getName());
                    break;
                case 2:
                    System.out.println("Thread " + this.getName() + " added to TAIL");
                    deque.addLast(this.getName());
                    break;
                case 3:
                    System.out.println("Thread " + this.getName() + " removed from HEAD");
                    deque.removeFirst();
                    System.out.println(this.getName());
                    break;
                case 4:
                    System.out.println("Thread " + this.getName() + " removed from TAIL");
                    deque.removeLast();
                    System.out.println(this.getName());
                    break;
            }
        }
    }
}
