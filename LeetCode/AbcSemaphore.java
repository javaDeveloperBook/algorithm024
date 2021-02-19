import java.util.concurrent.Semaphore;

/**
 * 编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
 * 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推
 *
 * 使用 Semaphore 实现
 */
public class AbcSemaphore {
    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);

        new Thread(()->{
            try {
                while (true) {
                    semaphoreA.acquire();
                    System.out.print(Thread.currentThread().getName());
                    semaphoreB.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                while (true) {
                    semaphoreB.acquire();
                    System.out.print(Thread.currentThread().getName());
                    semaphoreC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                while (true) {
                    semaphoreC.acquire();
                    System.out.println(Thread.currentThread().getName());
                    semaphoreA.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
