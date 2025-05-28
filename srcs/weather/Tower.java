package weather;

import java.util.ArrayList;
import java.util.List;

import aircrafts.Aircraft;
import aircrafts.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        handleDuplicateAircraft(p_flyable, ((Aircraft) p_flyable).getName());
        if (((Aircraft) p_flyable).isDuplicate() == false || ((Aircraft) p_flyable).getAirStatus() == false) {
            print_tower_msg(p_flyable);
        }
    }

    public void unregister(Flyable p_flyable) {
        System.out.printf("Tower says: %s unregistered from weather tower.\n",
                p_flyable.getID());
        p_flyable.setAirStatus(false);
    }

    protected void conditionChanged() {
        for (Flyable flyable : this.observers) {
            try {
                if (((Aircraft) flyable).getAirStatus() == false) {
                    print_tower_msg(flyable);
                }
                flyable.updateConditions();
                this.status_check(flyable);
            } catch (Exception e) {
                System.err.println("Error: Failed to update conditions for aircraft: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private void print_tower_msg(Flyable p_flyable) {
        System.out.printf("Tower says: %s registered to weather tower.\n",
                p_flyable.getID());
    }

    private void status_check(Flyable p_flyable) {
        if (p_flyable.getCoordinates().getHeight() <= 0) {
            System.out.println("Attention! " + p_flyable.getID() + " landed.");
            this.unregister(p_flyable);
        } else {
            p_flyable.setAirStatus(true);
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
        // if not duplicate, initialise SharedState
        newAircraft.setAirborneReference();
        newAircraft.setAirStatus(true);
    }
}