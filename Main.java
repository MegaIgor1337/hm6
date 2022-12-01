import check.CheckClass;
import countries.Consumer;
import factory.Factory;
import factory.Producer;

public class Main {
    private static final int LIMIT = 20;
    private static final long TIME = 200;
    private static final Factory FACTORY = new Factory();

    public static void main(String[] args) {
        CheckClass check = new CheckClass();
        Producer producer = new Producer(FACTORY, TIME, check);
        Consumer consumer1 = new Consumer(FACTORY, LIMIT, check);
        Consumer consumer2 = new Consumer(FACTORY, LIMIT, check);
        Consumer consumer3 = new Consumer(FACTORY, LIMIT, check);
        Consumer consumer4 = new Consumer(FACTORY, LIMIT, check);
        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        try {
            producer.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
            consumer4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
