import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * https://leetcode-cn.com/problems/print-zero-even-odd/
 *
 * Semaphore 解决
 */
public class ZeroEvenOddSemaphore {
    public static void main(String[] args) {
        ZeroEvenOddSemaphore zeroEvenOddSemaphore = new ZeroEvenOddSemaphore(1);
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
    //
    Semaphore semaphoreA = new Semaphore(1);
    Semaphore semaphoreB = new Semaphore(0);
    Semaphore semaphoreC = new Semaphore(0);

    public ZeroEvenOddSemaphore(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphoreA.acquire();
            printNumber.accept(0);
            if (incre % 2 == 0) {
                semaphoreB.release();
            }else {
                semaphoreC.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while ((n % 2 == 0 && incre <= n) || (n % 2 == 1 && incre <= n - 1)) {
            semaphoreB.acquire();
            printNumber.accept(incre++);
            semaphoreA.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while ((n % 2 == 0 && incre <= n - 1 ) || (n % 2 == 1 && incre <= n)) {
            semaphoreC.acquire();
            printNumber.accept(incre++);
            semaphoreA.release();
        }
    }
}