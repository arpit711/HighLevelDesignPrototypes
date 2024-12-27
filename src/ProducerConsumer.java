import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    public static void main(String[] args) {
        // Shared queue with a capacity of 5
        SharedQueue sharedQueue = new SharedQueue(5);

        // Create producer and consumer threads
        Thread producer = new Thread(new Producer(sharedQueue), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue), "Consumer");

        // Start the threads
        producer.start();
        consumer.start();
    }
}





