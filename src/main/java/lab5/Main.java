package lab5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 10.09.2017.
 */
public class Main {

    /**
     * Реализовать используя потоки и шаблон производитель-потребитель (producer-consumer).
     * Схема работы программы следующая.
     *  1) Призапуске программы создаются минимум два потока.
     *  2) Один из потоков (producer) производит некоторую информацию (например, генерирует случайные числа)
     *     и отправляет их в некоторый буфер (например, очередь).
     *  3) Буфер имеет конечную длину и при полном заполнении producer или прекращает посылать
     *     данные или данные попросту теряются.
     *  4) Второй из процессов (consumer) обращается к буферу, извлекает имеющуюся там информацию и
     *     использует ее в своей работе. Если буфер пустой, то он ждет появление информации, проверяя
     *     буфер с некоторой периодичностью.
     *  5) Буфер должен обеспечивать корректную работу с множеством потоков и не допускать гонки данных.
     */
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 20;
        int producersCount = 1;
        int consumersCount = 2;

        Buffer buffer = new Buffer(bufferSize);
        for (int i = 0; i < producersCount; i++) {
            Thread producer = new Thread(new Producer("#"+i, buffer));
            producer.setDaemon(true);
            producer.start();
        }
        for (int i = 0; i < consumersCount; i++) {
            Thread consumer = new Thread(new Consumer("#"+i, buffer));
            consumer.setDaemon(true);
            consumer.start();
        }

        Thread.sleep(10*1000);
    }
}
