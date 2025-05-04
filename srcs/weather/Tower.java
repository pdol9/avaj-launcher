package weather;

import java.util.ArrayList;
import java.util.List;

import aviation.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        System.out.printf("Tower says: %s registered to weather tower.\n",
                            p_flyable.getID());

    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        System.out.printf("Tower says: %s unregistered from weather tower.\n",
                            p_flyable.getID());
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable flyable : copy) {
            flyable.updateConditions();
        }
    }
}