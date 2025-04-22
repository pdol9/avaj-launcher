package nav;

public class Coordinates {

	private int longitude, latitude, height;
	public Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	public int getLongitude() { return longitude; }
	public int getLatitude() { return latitude; }
	public int getHeight() { return height; }

	public void incrementCoordinates(int delta) {
        this.longitude += delta;
        this.latitude += delta;
		// Prevent height from going negative
        this.height = Math.max(0, this.height + delta);
    }

}
