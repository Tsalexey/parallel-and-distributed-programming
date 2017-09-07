package lab1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Alexey on 08.09.2017.
 */
public class MutexDeque {
    Semaphore mutex = new Semaphore(1);
    private Deque<String> deque;

    public MutexDeque() {
        deque = new LinkedList();
    }

    public void addFirst(String element){
        try {
            mutex.acquire();
            try {
                deque.addFirst(element);
            } finally {
                mutex.release();
                print();
            }
        } catch(InterruptedException ie) {
            System.out.println("Exception raised at getting mutex for add element to head");
        }
    }

    public void addLast(String element){
        try {
            mutex.acquire();
            try {
                deque.addLast(element);
            } finally {
                mutex.release();
                print();
            }
        } catch(InterruptedException ie) {
            System.out.println("Exception raised at getting mutex for add element to tail");
        }
    }

    public void removeFirst(){
        try {
            mutex.acquire();
            try {
                if (deque.size() > 0){
                    deque.removeFirst();
                }
            } finally {
                mutex.release();
                print();
            }
        } catch(InterruptedException ie) {
            System.out.println("Exception raised at getting mutex for remove element from head");
        }
    }

    public void removeLast(){
        try {
            mutex.acquire();
            try {
                if (deque.size() > 0) {
                    deque.removeLast();
                }
            } finally {
                mutex.release();
                print();
            }
        } catch(InterruptedException ie) {
            System.out.println("Exception raised at getting mutex for remove element from tail");
        }
    }

    public void print(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < deque.size(); i++)
        for(String element: deque){
            sb.append(element);

            if (i == deque.size() -1){
                sb.append(";");
            } else {
                sb.append(",");

            }
        }
        System.out.println("Deque: " + sb);
    }

}
