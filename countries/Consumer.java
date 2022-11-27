package countries;

import factory.Factory;
import factory.Robot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Consumer extends Thread {
    private int countOfRobots;
    private final int limit;
    private volatile Factory factory;
    private volatile Set<Robot> details = new HashSet<>();

    public Consumer(Factory factory, int limit) {
        this.limit = limit;
        this.factory = factory;
        this.countOfRobots = 0;
    }

    private synchronized void getDetail() {
        while (factory.getStorage().isEmpty()) {
            try {
                System.out.println("заснул");
                wait();
            } catch (InterruptedException e) {}
        }
        Iterator<Robot> iterator = factory.getStorage().iterator();
        while (iterator.hasNext()) {
            Robot r = iterator.next();
            boolean check = true;
            for (Robot r1 : details) {
                if (r.equals(r1)) {
                    check = false;
                }
            }
            if (check) {
                details.add(r);
                System.out.println("added detail");
                factory.getStorage().remove(r);
            }
        }
    }


    public void run() {
        while (countOfRobots != limit) {
           while (details.size() != 6) {
               getDetail();
           }
           Iterator<Robot> iterator = details.iterator();
           while (iterator.hasNext()) {
               details.remove(iterator.next());
           }
            System.out.println("robot created");
           countOfRobots++;
        }
        System.out.println(Thread.currentThread().getName() + " - win!");

    }
}
