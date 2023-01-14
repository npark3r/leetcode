package leetcode.utils.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 1114. Print in Order
 *
 * The same instance of Foo will be passed to three different threads. Thread A will call first(),
 * thread B will call second(), and thread C will call third(). Design a mechanism and modify the program
 * to ensure that second() is executed after first(), and third() is executed after second().
 *
 * Result: Accepted
 * Runtime beats: 96.54%
 * Memory beats: 66.26%
 *
 * Notes:
 * Also used semaphores and AtomicBoolean.
 *
 */
public class Foo {

    private Object lock;

    CountDownLatch secondPrintConditionLatch = new CountDownLatch(1);
    CountDownLatch thirdPrintConditionLatch = new CountDownLatch(1);

    public Foo() {
        lock = new Object();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {

        }
        printFirst.run();
        secondPrintConditionLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondPrintConditionLatch.await();
        printSecond.run();
        thirdPrintConditionLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdPrintConditionLatch.await();
        printThird.run();
    }
}
