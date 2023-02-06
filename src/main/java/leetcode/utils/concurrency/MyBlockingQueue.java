package leetcode.utils.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

    private final Queue<E> queue;
    private final int max;

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    public void put(E element) {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(element);
            System.out.println("Added to queue. Notifying...");
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty. Waiting...");
                notEmpty.await();
            }
            E item = queue.remove();
            notFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


}
