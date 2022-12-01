package countries;

import check.CheckClass;
import factory.Factory;
import factory.Robot;

import java.util.HashSet;
import java.util.Set;

public class Consumer extends Thread {
    private static final String FORMAT_MESSAGE = "Country %s - winner!!!";
    private int countOfRobots;
    private final int limit;
    private volatile Factory factory;
    private volatile Set<Robot> details = new HashSet<>();
    private CheckClass check;
    public Consumer(Factory factory, int limit, CheckClass check) {
        this.limit = limit;
        this.factory = factory;
        this.countOfRobots = 0;
        this.check = check;
    }


    public void run() {
        while (countOfRobots != limit) {
            while (details.size() != 6) {
                synchronized (factory.getStorage()) {
                    while (factory.getStorage().isEmpty()) {
                        try {
                            factory.getStorage().wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    Robot detailToDeleteFromStorage = null;
                    for (Robot r : factory.getStorage()) {
                        if (details.add(r)) {
                            detailToDeleteFromStorage = r;
                            break;
                        }
                    }
                    if (detailToDeleteFromStorage != null) {
                        factory.getStorage().remove(detailToDeleteFromStorage);
                    } else {
                        try {
                            factory.getStorage().wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (!check.isCheck()) {
                    break;
                }
            }
            if (!check.isCheck()) {
                break;
            }
            details.removeAll(details);
            countOfRobots++;
        }
        synchronized (check) {
            if (check.isCheck()) {
                System.out.printf((FORMAT_MESSAGE) + "%n", Thread.currentThread().getName().substring(7));
            }
        }
        check.changeCheckOnFalse();
    }
}
