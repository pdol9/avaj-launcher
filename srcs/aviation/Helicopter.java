package aviation;

import nav.Coordinates;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        switch (weather) {
            case "SUN":
                coordinates.changeLongitude(10);
                coordinates.changeHeight(2);
                System.out.println(this.getID() + ": This is hot.");
                break;
            case "RAIN":
                coordinates.changeLongitude(5);
                System.out.println(this.getID() + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates.changeLongitude(1);
                System.out.println(this.getID() + ": Can't see anything in this fog.");
                break;
            case "SNOW":
                coordinates.changeHeight(-12);
                System.out.println(this.getID() + ": My rotor is going to freeze!");
                break;
            }
        this.status_check();
    }

    @Override
    public String getType() {
        return "Helicopter";
    }
}