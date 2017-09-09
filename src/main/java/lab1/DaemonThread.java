package lab1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Created by Alexey on 09.09.2017.
 */
public class DaemonThread extends Thread{
    private SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

    public DaemonThread(){
//        this.setDaemon(true);
    }

    @Override
    public void run(){
        PrintWriter writer = null;
        try{
             writer = new PrintWriter("daemon_file.txt", "UTF-8");

            long startTime = System.currentTimeMillis();
            int sleepTime = 1*1000;

            for (int i =0; i < 1520; i++){
                writer.println(time_formatter.format(System.currentTimeMillis()));
                sleep(sleepTime);
                System.out.println("Daemon thread sleeps for 1 sec");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
