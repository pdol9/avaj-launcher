package aviation;

import nav.Coordinates;

class Balloon extends Aircraft {
    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }
    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);

        switch (weather) {
            case "SUN":
                coordinates.changeLongitude(2);
                coordinates.changeHeight(4);
                System.out.println(this.getID() + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates.changeHeight(-5);
                System.out.println(this.getID() + ": Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                coordinates.changeHeight(-3);
                System.out.println(this.getID() + ": Fog is so thick...");
                break;
            case "SNOW":
                coordinates.changeHeight(-15);
                System.out.println(this.getID() + ": It's snowing. We're gonna crash.");
                break;
        }
    }

    @Override
    public String getType() {
        return "Balloon";
    }
}