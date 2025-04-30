package flyable;

import nav.Coordinates;
import weather.WeatherTower;

class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
        System.out.printf("%s just took off. Let's enjoy the flight.\n",
                            this.getID());

    }

    @Override
    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        switch (weather) {
            case "SUN":
                coordinates.changeLatitude(10);
                coordinates.changeHeight(2);
                System.out.println(this.getID() + ": It's sunny, cruising faster!");
                break;
            case "RAIN":
                coordinates.changeLatitude(5);
                System.out.println(this.getID() + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates.changeLatitude(1);
                System.out.println(this.getID() + ": Visibility is low, proceeding with caution.");
                break;
            case "SNOW":
                coordinates.changeHeight(-7);
                System.out.println(this.getID() + ": OMG! Winter is coming!");
                break;
        }

        if (coordinates.getHeight() <= 0) {
            landed = true;
            System.out.println(this.getID() + " landing.");
        }
    }

    @Override
    public String getType() {
        return "JetPlane";
    }
}