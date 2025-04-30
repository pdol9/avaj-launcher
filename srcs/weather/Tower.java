package weather;

import java.util.ArrayList;
import java.util.List;

import flyable.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        System.out.printf("Tower says: %s registered to weather tower.\n",
                            flyable.getID());

    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        System.out.printf("Tower says: %s unregistered from weather tower.\n",
                            flyable.getID());
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable flyable : copy) {
            flyable.updateConditions();
        }
    }
}