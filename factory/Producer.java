package factory;

import countries.Consumer;

import java.util.List;
import java.util.Random;

public class Producer extends Thread{
    private static final Random RANDOM = new Random();
    private final Factory factory;
    private boolean check = true;
    private long time;

    public Producer(Factory factory, long time) {
        this.factory = factory;
        this.time = time;
    }

    public void run() {
        while (check) {
            synchronized (factory.getStorage()) {
                factory.addToList(Robot.nameByNumber(RANDOM.nextInt(6)));
                try {
                    notify();
                    System.out.println("Detail created");
                    Thread.sleep(time);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public void changeCheck() {
        this.check = false;
    }

    public List<Robot> getStorage() {
        return factory.getStorage();
    }

}
