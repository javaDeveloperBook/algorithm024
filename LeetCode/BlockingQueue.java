import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {

    public static void main(String[] args) {
        BoundedBlockingQueue blockingQueue = new BoundedBlockingQueue(4);

        new Thread(() -> {
            while (true) {
                try {
                    blockingQueue.enqueue((int) Math.random() * 10 / 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "put").start();


        new Thread(() -> {
            try {
               while (true) {
                   System.out.println(blockingQueue.dequeue());
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "take").start();
    }

}

class BoundedBlockingQueue {
    private Integer[] items;
    private volatile int putIndex = 0;
    private volatile int takeIndex = 0;
    private volatile int count = 0;

    private final ReentrantLock reentrantLock;

    // 等待 dequeue
    private Condition notEmpty;

    // 可以 enqueue
    private Condition notFull;


    public BoundedBlockingQueue(int capacity) {
        this.items = new Integer[capacity];
        this.reentrantLock = new ReentrantLock();
        this.notEmpty = this.reentrantLock.newCondition();
        this.notFull = this.reentrantLock.newCondition();

    }

    public void enqueue(int element) throws InterruptedException {
        this.reentrantLock.lock();
        try{
            while (count == items.length) {
                notFull.await();
            }
            items[putIndex] = element;
            count ++;
            if (++ putIndex == items.length) {
                putIndex = 0;
            }
            notEmpty.signal();
        }finally {
            this.reentrantLock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        this.reentrantLock.lock();
        try{
            while (count == 0) {
                notEmpty.await();
            }
            final int item = items[takeIndex];
            items[takeIndex] = null;
            if (++ takeIndex == items.length) {
                takeIndex = 0;
            }
            count --;
            notFull.signal();
            return item;
        }finally {
            this.reentrantLock.unlock();
        }
    }

    public int size() {
        return count;
    }
}