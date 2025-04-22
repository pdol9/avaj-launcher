package weather;

import java.util.ArrayList;
import java.util.List;

import flyable.Aircraft;
import flyable.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();
	
    public void register(Flyable p_flyable) {
		observers.add(p_flyable);
	}
	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}
	protected void conditionChanged() {
        for (Flyable flyable : observers) {
            if (flyable instanceof Aircraft aircraft) {
                aircraft.incrementCoordinates(10);
            }
            // Notify the Flyable object of the change
            flyable.updateConditions();
        }
    }
}
