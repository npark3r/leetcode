package leetcode.utils.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueueTwo<E> {

    private final Queue<E> queue;
    private final int max;

    public MyBlockingQueueTwo(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    public synchronized void put(E element) throws InterruptedException {
        while (queue.size() == max) {
            this.wait();
        }
        queue.add(element);
        System.out.println("Added to queue. Notifying...");
        this.notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Waiting...");
            this.wait();
        }
        E item = queue.remove();
        this.notifyAll();
        return item;
    }


}
