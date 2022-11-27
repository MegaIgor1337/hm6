import countries.Consumer;
import factory.Factory;
import factory.Producer;

import static java.lang.Thread.sleep;

public class Main {
    private static final int LIMIT = 5;
    private static final long TIME = 200;
    private static final Factory FACTORY = new Factory();

    public static void main(String[] args) {
        Consumer consumer = new Consumer(FACTORY, LIMIT);
        Producer producer = new Producer(FACTORY, TIME);
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
