import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public SharedQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue is full. Producer is waiting...");
            wait(); // Wait until there is space in the queue
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll(); // Notify waiting threads
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Consumer is waiting...");
            wait(); // Wait until there are items in the queue
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll(); // Notify waiting threads
        return item;
    }
}
