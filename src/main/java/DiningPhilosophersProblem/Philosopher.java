package DiningPhilosophersProblem;

public class Philosopher implements Runnable {
    public Object leftFork;
    public Object rightFork;

    Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void doSomething(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doSomething(System.nanoTime() + ": myśli");
                synchronized (leftFork) {
                    doSomething(System.nanoTime() + ": L widelec - podnosi");
                    synchronized (rightFork) {
                        doSomething(System.nanoTime() + ": R widelec - je");
                        doSomething(System.nanoTime() + ": R widelec - zostawia");
                    }
                    doSomething(System.nanoTime() + ": L widelec - zostawia");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
