package lab5;

import lab2.XorshiftPlus;

/**
 * Created by Alexey on 12.09.2017.
 */
public class Producer implements Runnable{
    private Buffer buffer;
    private XorshiftPlus uniformGenerator;
    private String name;
    private int maxWatingTime = 2;

    public Producer(String name, Buffer buffer){
        this.name = name;
        this.buffer = buffer;
        uniformGenerator = new XorshiftPlus(false);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep((int) (Math.random() * maxWatingTime)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (buffer) {
                if (buffer.isFull()) {
                    System.out.println("    Producer " + name + " waiting because of buffer is full");
                } else {
                    double number = uniformGenerator.getNumber();
                    buffer.put(number);
                    System.out.println("    Producer " + name + " generated number = " + number);
                }
            }
        }
    }
}
