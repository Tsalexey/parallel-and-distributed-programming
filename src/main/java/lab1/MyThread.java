package lab1;

import java.text.SimpleDateFormat;

/**
 * Created by Alexey on 08.09.2017.
 */
class MyThread extends Thread {
    private int threadNumber;
    private int sleepTime;
    private SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

    public MyThread(int threadNumber, int sleepTime){
        this.threadNumber = threadNumber;
        this.sleepTime = sleepTime*1000;
        System.out.println("Thread #" + threadNumber + " created at " + time_formatter.format(System.currentTimeMillis()));
    }

    @Override
    public void run()
    {
        try {
            System.out.println("Thread #" + threadNumber + " started working at " +  time_formatter.format(System.currentTimeMillis()));
            System.out.println("Thread #" + threadNumber + " sleep at " +  time_formatter.format(System.currentTimeMillis()));
            sleep(sleepTime);
            System.out.println("Thread #" + threadNumber + " waked up at " +  time_formatter.format(System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}