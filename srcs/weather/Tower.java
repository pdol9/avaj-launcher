package weather;

import java.util.ArrayList;
import java.util.List;

import aviation.Aircraft;
import aviation.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        handleDuplicateAircraft(p_flyable, ((Aircraft)p_flyable).getName());
        // simulation output
        if (((Aircraft) p_flyable).isDuplicate() == false || ((Aircraft) p_flyable).getAirStatus() == false) {
            System.out.printf("Tower says: %s registered to weather tower.\n",
            p_flyable.getID());
        }
        // p_flyable.setAirStatus(true);
    }

    public void unregister(Flyable p_flyable) {
        System.out.printf("Tower says: %s unregistered from weather tower.\n",
        p_flyable.getID());
        p_flyable.setAirStatus(false);
    }

    protected void conditionChanged() {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable flyable : copy) {
            try {
                flyable.updateConditions();
                this.status_check(flyable);
            } catch (Exception e) {
                System.err.println("Error: Failed to update conditions for aircraft: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    public void status_check(Flyable p_flyable) {
        if (p_flyable.getCoordinates().getHeight() <= 0) {
            System.out.println("Attention! " + p_flyable.getID() + " landed.");
            this.unregister(p_flyable);
        }
    }

    private void handleDuplicateAircraft(Flyable newAircraft, String name) {

        for (Flyable existingAircraft : this.observers) {
            if (existingAircraft != newAircraft && ((Aircraft) existingAircraft).getName().equals(name)) {

                newAircraft.setIdNum(existingAircraft.getIdNum());
                newAircraft.markDuplicate(true);
                newAircraft.linkAirborneStatus((Aircraft) existingAircraft);
                return;
            }
        }
        newAircraft.setAirborneReference();
    }
}