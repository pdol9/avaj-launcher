package simulator;

public class Coordinates {
	private int longitude, latitude, height;

	Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
	}

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void changeLongitude(int delta) {
        longitude += delta;
    }

    public void changeLatitude(int delta) {
        latitude += delta;
    }

    public void changeHeight(int delta) {
        height += delta;
    }
}