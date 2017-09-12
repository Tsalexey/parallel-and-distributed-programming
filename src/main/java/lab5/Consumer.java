package lab5;

/**
 * Created by Alexey on 12.09.2017.
 */
public class Consumer implements Runnable {
    private Buffer buffer;
    private String name;
    private int maxWatingTime = 2;
    public Consumer(String name, Buffer buffer){
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * maxWatingTime)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    System.out.println("Consumer " + name + " waiting because of buffer is empty");
                } else {
                    double number = buffer.get();
                    System.out.println("Consumer " + name + " get from buffer number = " + number);
                }
            }
        }
    }
}
