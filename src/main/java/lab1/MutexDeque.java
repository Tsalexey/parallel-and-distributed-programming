package lab1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Alexey on 08.09.2017.
 */
public class MutexDeque {
    private Semaphore mutex = new Semaphore(1);
    private Deque<String> deque;

    public MutexDeque() {
        deque = new LinkedList();
    }

    public void acquire() throws InterruptedException {
        mutex.acquire();
    }

    public void release(){
        mutex.release();
    }

    public void addFirst(String element){
        deque.addFirst(element);
        print();
    }

    public void addLast(String element){
        deque.addLast(element);
        print();
    }

    public void removeFirst(){
        if (deque.size() > 0) {
            deque.removeFirst();
            print();
        }
    }

    public void removeLast(){
        if (deque.size() > 0) {
            deque.removeLast();
            print();
        }
    }

    public void print(){
        System.out.print("  Deque: ");
        for (String s: deque){
            System.out.print(s + ", ");
        }
        System.out.println();
    }

}
