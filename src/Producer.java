//Producer class
public class Producer implements Runnable {
    private final SharedQueue sharedQueue;

    Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        int item = 0;
        while (true) {
            try {
                Thread.sleep(500);
                sharedQueue.produce(item++);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
                break;
            }
        }
    }
}
