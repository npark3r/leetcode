package leetcode.utils;

import leetcode.utils.concurrency.MyBlockingQueue;
import leetcode.utils.concurrency.MyBlockingQueueTwo;

import java.util.*;

public class TestThings {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        MyBlockingQueueTwo<Integer> queue = new MyBlockingQueueTwo<>(5);

        Thread producer1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1333);
                    queue.put(new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer1.start();
        Thread producer2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    queue.put(new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer2.start();

        Thread consumer1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    Integer item = queue.take();
                    System.out.println("Items is: " + item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer1.start();

        long elapsedTime = System.currentTimeMillis() - startTime;
//        System.out.println(elapsedTime);
    }

    public static <E> void mergeLists(List<E> list1, List<E> list2) {
        list1.addAll(list2);
    }
}
