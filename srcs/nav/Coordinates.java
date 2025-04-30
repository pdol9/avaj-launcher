package nav;

public class Coordinates {

	private int longitude, latitude, height;
	public Coordinates(int longitude, int latitude, int height) {
        this.longitude = Math.max(0, longitude);
        this.latitude = Math.max(0, latitude);
        this.height = Math.max(0, Math.min(height, 100));
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
        longitude = Math.max(0, longitude + delta);
    }

    public void changeLatitude(int delta) {
        latitude = Math.max(0, latitude + delta);
    }

    public void changeHeight(int delta) {
        height = Math.max(0, Math.min(height + delta, 100));
    }
}
