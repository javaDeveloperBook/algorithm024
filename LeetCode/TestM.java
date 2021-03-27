import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JackWu
 * @version 1.0
 */
public class TestM {

    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        AtomicInteger num = new AtomicInteger(0);

        new Thread(() -> {
            try {
                while (true) {
                    semaphoreA.acquire();
                    System.out.println(Thread.currentThread().getName() + ":" + num.getAndAdd(1));
                    semaphoreB.release();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"A").start();

        new Thread(() -> {
            try {
                while (true) {
                    semaphoreB.acquire();
                    System.out.println(Thread.currentThread().getName() + ":" + num.getAndAdd(1));
                    semaphoreA.release();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"B").start();

    }
}
