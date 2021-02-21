import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author JackWu
 * @version 1.0
 */
public class FizzBuzzSemaphore {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        new Thread(() -> {
            try {
                fizzBuzz.fizz(new Print("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fizz").start();

        new Thread(() -> {
            try {
                fizzBuzz.buzz(new Print("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "buzz").start();


        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(new Print("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fizzbuzz").start();


        new Thread(() -> {
            try {
                fizzBuzz.number(new PrintX());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "number").start();
    }
}


class FizzBuzz {
    private int n;

    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);
    private Semaphore number = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n ; i ++) {
            if (i % 3 == 0 && i % 5 !=0) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n ; i ++){
            if (i % 5 == 0 && i % 3 != 0) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i ++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i ++) {
            number.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.release();
            }else if (i % 3 == 0) {
                fizz.release();
            }else if (i % 5 == 0) {
                buzz.release();
            }else {
                printNumber.accept(i);
                number.release();
            }
        }
    }
}

class Print implements Runnable {

    public Print(String str) {
        this.str = str;
    }

    private String str = "";

    @Override
    public void run() {
        System.out.println(str);
    }
}

class PrintX implements IntConsumer {

    @Override
    public void accept(int value) {
        System.out.println(value);
    }
}