import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * https://leetcode-cn.com/problems/print-zero-even-odd/
 *
 * Semaphore 解决
 */
public class ZeroEvenOddCondition {
    public static void main(String[] args) {
        ZeroEvenOddCondition zeroEvenOddSemaphore = new ZeroEvenOddCondition(4);
        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.zero(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.even(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.odd(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }

    private int n;
    // 记录打印数字
    private volatile Integer incre = 1;
    private volatile int flag = 1;
    private ReentrantLock lock;
    private Condition conditionA;
    private Condition conditionB;
    private Condition conditionC;

    public ZeroEvenOddCondition(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.conditionA = this.lock.newCondition();
        this.conditionB = this.lock.newCondition();
        this.conditionC = this.lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while (flag != 1) {
                conditionA.await();
            }
            printNumber.accept(0);
            if (incre % 2 == 0) {
                flag = 2;
                conditionB.signal();
            }else {
                flag = 3;
                conditionC.signal();
            }
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while ((n % 2 == 0 && incre <= n) || (n % 2 == 1 && incre <= n - 1)) {
            lock.lock();
            while (flag != 2) {
                conditionB.await();
            }
            printNumber.accept(incre++);
            flag = 1;
            conditionA.signal();
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while ((n % 2 == 0 && incre <= n - 1 ) || (n % 2 == 1 && incre <= n)) {
            lock.lock();
            while (flag != 3) {
                conditionC.await();
            }
            printNumber.accept(incre++);
            flag = 1;
            conditionA.signal();
            lock.unlock();
        }
    }
}