package factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory extends Thread {
    private static final Random RANDOM = new Random();
    private final long time;
    private boolean check;

    private final List<Robot> storage = new ArrayList<>();

    public Factory(long time) {
        this.time = time;
        this.check = true;
    }

    public void run() {
        while (check) {
            synchronized (storage) {
                storage.add(Robot.nameByNumber(RANDOM.nextInt(6)));
                try {
                    storage.notify();
                    Thread.sleep(time);
                } catch (InterruptedException ignored) {}
            }
        }
    }
    public List<Robot> getStorage() {
        return storage;
    }

    public boolean isCheck() {
        return check;
    }

    public void changeCheckOnFalse() {
        this.check = false;
    }

}
