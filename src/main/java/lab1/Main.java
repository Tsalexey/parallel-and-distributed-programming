package lab1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexey on 08.09.2017.
 */
public class Main {
    /**
     * 1. Программа порождает некоторое число потоков, каждый из которых бездействует определенное время,
     *    а затем выводит некоторое сообщение , после чего завершает работу. Число потоков предавать программе
     *    в виде аргумента командной строки при запуске.
     * 2. Программа порождает поток-демон, который с определенной периодичностью записывает в специальный файл
     *    текущее время. Основная программа ждет некоторое время а затем завершает работу. Как отображается
     *    демон в списке процессов? Что с ним случается после завершения программы.
     * 3. Программа порождает определенное число потоков, которые работают с общим ресурсом. В качестве общего
     *    ресурса использовать двухстороннюю очередь, защитив её мьютексом, обернув их в класс. Потоки должны
     *    как записывать данные в очередь, так и извлекать их из нее.
     */
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

    public static void main(String[] args){
        System.out.println("Lab #1, start of work");
        System.out.println("Choose mode: 1, 2, 3");

        Scanner in = new Scanner(System.in);
        try {
            int mode = in.nextInt();
            switch (mode){
                case 1:
                    one(args);
                    break;
                case 2:
                    break;
                case 3:
                    three(args);
                    break;
                default:
                    System.out.print("Wrong mode. You must input '1', '2', or '3'");
                    break;
            }
        } catch (Exception e){
            System.out.println("Wrong input!");
        }
        System.out.println("Lab #1, end of work");
    }

    public static void one(String[] args) throws InterruptedException {
        if (args.length != 1){
            System.out.println("You must pass one command line parameter!");
        } else {
            int sleepTime = 3; // sec



            List<MyThread> threads = new ArrayList<MyThread>();
            for (int i = 0; i < Integer.parseInt(args[0]); i++){
                MyThread t = new MyThread(i + 1, sleepTime);
                threads.add(t);
            }
            for (MyThread t: threads){
                t.start();
            }
            System.out.println();
            for (MyThread t: threads){
                t.join();
            }
            System.out.println();
            System.out.println("Current time: " + timeFormatter.format(System.currentTimeMillis()));
        }
    }

    public static void two(){

    }

    public static void three(String[] args) throws InterruptedException {
        if (args.length != 1){
            System.out.println("You must pass one command line parameter!");
        } else {
            MutexDeque deque = new MutexDeque();

            List<DequeThread> threads = new ArrayList<DequeThread>();
            for (int i = 0; i < Integer.parseInt(args[0]); i++){
                DequeThread t = new DequeThread(deque);
                t.setName("#" + i);
                threads.add(t);
            }
            for (DequeThread t: threads){
                t.start();
            }

            for (DequeThread t: threads){
                t.join();
            }
        }

    }


}
