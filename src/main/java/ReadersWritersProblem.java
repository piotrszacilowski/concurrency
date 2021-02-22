import java.util.concurrent.Semaphore;

public class ReadersWritersProblem {
    private Semaphore readSemaphore = new Semaphore(1);
    private Semaphore writeSemaphore = new Semaphore(1);

    private int count = 0;

    public void readLock() throws InterruptedException {
        readSemaphore.acquire();
        if(count == 0){
            writeSemaphore.acquire();
        }
        count++;
        readSemaphore.release();
    }

    public void readUnLock() throws InterruptedException {
        readSemaphore.acquire();
        count--;
        if(count == 0){
            writeSemaphore.release();
        }
        readSemaphore.release();
    }

    public void writeLock() throws InterruptedException {
        writeSemaphore.acquire();
    }

    public void writeUnlock(){
        writeSemaphore.release();
    }
}
