package factory;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private volatile List<Robot> storage = new ArrayList<>();

    public synchronized void addToList(Robot detail) {
        storage.add(detail);
    }

    public List<Robot> getStorage() {
        return storage;
    }
}
