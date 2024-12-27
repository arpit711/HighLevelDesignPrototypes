public class Consumer implements Runnable {
    private final SharedQueue sharedQueue;

    public Consumer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Simulate consumption delay
                int item = sharedQueue.consume();
                System.out.println("consumed item: " + item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
                break;
            }
        }
    }


}
