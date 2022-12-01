package factory;

import check.CheckClass;

import java.util.List;
import java.util.Random;

public class Producer extends Thread{
    private static final Random RANDOM = new Random();
    private final Factory factory;
    private long time;
    private volatile CheckClass check;

    public Producer(Factory factory, long time, CheckClass check) {
        this.factory = factory;
        this.time = time;
        this.check = check;
    }


    public void run() {
        while (check.isCheck()) {
            synchronized (factory.getStorage()) {
                factory.addToList(Robot.valueOf(Robot.nameByNumber(RANDOM.nextInt(6) + 1)));
                try {
                    factory.getStorage().notify();
                    Thread.sleep(time);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    public List<Robot> getStorage() {
        return factory.getStorage();
    }

}
