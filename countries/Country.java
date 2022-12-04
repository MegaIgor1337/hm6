package countries;

import factory.Factory;
import factory.Robot;

import java.util.HashSet;
import java.util.Set;

public class Country extends Thread {
    private static final String FORMAT_MESSAGE = "Country %s - winner!!!\n";
    private int countOfRobots;
    private final int limit;
    private final Factory factory;
    private final Set<Robot> details = new HashSet<>();

    public Country(factory.Factory factory, int limit) {
        this.limit = limit;
        this.factory = factory;
        this.countOfRobots = 0;
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
                        }
                    }
                }
                if (!factory.isCheck()) {
                    break;
                }
            }
            if (!factory.isCheck()) {
                break;
            }
            details.removeAll(details);
            countOfRobots++;
        }
        synchronized ((Object) factory.isCheck()) {
            if (factory.isCheck()) {
                printCountryWinner(Thread.currentThread().getName());
                factory.changeCheckOnFalse();
            }
        }
    }

    private void printCountryWinner(String nameThread) {
        System.out.printf(FORMAT_MESSAGE, nameThread.substring(7));
    }
}
