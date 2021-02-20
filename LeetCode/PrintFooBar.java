import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 */
public class PrintFooBar {

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(4);

        new Thread(() -> {
            try {
                fooBar.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"FOO").start();

        new Thread(() -> {
            try {
                fooBar.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Bar").start();
    }

}

class FooBar {
    private int n;

    // 方式一：lock condition
    private ReentrantLock lock;
    private Condition conditionFoo;
    private Condition conditionBar;
    private volatile boolean flag;

    // 方式二：semaphore
//    private Semaphore semaphoreFoo = new Semaphore(1);
//    private Semaphore semaphoreBar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.conditionFoo = this.lock.newCondition();
        this.conditionBar = this.lock.newCondition();
        this.flag = false;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (flag)
                conditionFoo.await();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            flag = true;
            conditionBar.signal();
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();

            while (!flag)
                conditionBar.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            flag = false;
            conditionFoo.signal();
            lock.unlock();
        }
    }
}

class PrintFoo implements Runnable {

    @Override
    public void run() {
        System.out.print("Foo");
    }
}

class PrintBar implements Runnable {

    @Override
    public void run() {
        System.out.println("Bar");
    }
}