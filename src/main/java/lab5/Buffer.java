package lab5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alexey on 12.09.2017.
 */
public class Buffer {
    private int BUFFER_SIZE;
    private Queue<Double> queue;

    public Buffer(int bufferSize){
        this.BUFFER_SIZE = bufferSize;
        queue = new LinkedList<>();
    }

    public Double get(){
        return queue.remove();
    }

    public void put(Double number){
        queue.add(number);
    }

    public int size(){
        return queue.size();
    }

    public boolean isFull(){
        return queue.size() == BUFFER_SIZE ? true : false;
    }

    public boolean isEmpty(){
        return queue.size() == 0 ? true : false;
    }
}
