import countries.Country;
import factory.Factory;

public class Main {

    public static void main(String[] args) {
        final int limit = 5;
        final long time = 100;
        Factory factory = new factory.Factory(time);
        Country country1 = new Country(factory, limit);
        Country country2 = new Country(factory, limit);
        Country country3 = new Country(factory, limit);
        Country country4 = new Country(factory, limit);
        factory.start();
        country1.start();
        country2.start();
        country3.start();
        country4.start();
        try {
            factory.join();
            country1.join();
            country2.join();
            country3.join();
            country4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
