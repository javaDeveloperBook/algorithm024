import java.util.concurrent.locks.LockSupport;

/**
 * 编写一个程序，开启三个线程，这三个线程的 ID 分别是 A、B 和 C，每个线程把自己的 ID 在屏幕上打印 10 遍，
 * 要求输出结果必须按 ABC 的顺序显示，如 ABCABCABC... 依次递推。
 *
 */
public class AbcLockSupport {

    static Thread threadA,threadB,threadC;

    public static void main(String[] args) {
        threadA = new Thread(() -> {
            while (true) {
                System.out.print(Thread.currentThread().getName());
                // 唤醒B
                LockSupport.unpark(threadB);
                // 阻塞A
                LockSupport.park();
            }
        } ,"A");

        threadB = new Thread(() -> {
            while (true) {
                // 阻塞B
                LockSupport.park();
                System.out.print(Thread.currentThread().getName());
                // 唤醒C
                LockSupport.unpark(threadC);
            }
        } ,"B");

        threadC = new Thread(() -> {
            while (true) {
                // 阻塞C
                LockSupport.park();
                System.out.println(Thread.currentThread().getName());
                // 唤醒A
                LockSupport.unpark(threadA);
            }
        } ,"C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
