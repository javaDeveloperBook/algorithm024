import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
 * 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推
 *
 * 使用 Lock 搭配 Condition 实现
 */
public class AbcLockCondition {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        new Thread(()-> {
            while (true) {
                lock.lock();
                System.out.print(Thread.currentThread().getName());
                b.signal();
                try {
                    a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "A").start();

        new Thread(()-> {
            while (true) {
                lock.lock();
                System.out.print(Thread.currentThread().getName());
                c.signal();
                try {
                    b.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "B").start();

        new Thread(()-> {
            while (true) {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                a.signal();
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "C").start();


    }
}
