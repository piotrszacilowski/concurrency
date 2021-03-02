import ReadersWritersProblem.ReadersWritersProblem;
import org.junit.Test;

public class ReadersWritersProblemTest {
    static int a = 10;

    @Test
    public void testRead() throws InterruptedException {
        ReadersWritersProblem readWriteLock = new ReadersWritersProblem();
        Thread thread1 = new Thread(() -> {
            try {
                readWriteLock.readLock();
                System.out.println(a + " - reader");
                Thread.sleep(3000);
                readWriteLock.readUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                readWriteLock.readLock();
                System.out.println(a + "- reader");
                Thread.sleep(2000);
                readWriteLock.readUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                readWriteLock.writeLock();
                a = 100;
                System.out.println(a + " - writer");
                readWriteLock.writeUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread3.join();
    }

    @Test
    public void testWrite() throws InterruptedException {
        ReadersWritersProblem readWriteLock = new ReadersWritersProblem();
        Thread thread1 = new Thread(() -> {
            try {
                readWriteLock.readLock();
                System.out.println(a + " - reader");
                readWriteLock.readUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                readWriteLock.readLock();
                System.out.println(a + " - reader");
                readWriteLock.readUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                readWriteLock.writeLock();
                a = 100;
                System.out.println(a + " - writer");
                Thread.sleep(2000);
                readWriteLock.writeUnlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        thread3.start();
        thread1.start();
        thread2.start();
        thread2.join();
    }
}
