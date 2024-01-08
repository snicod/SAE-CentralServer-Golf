import org.bson.types.ObjectId;

public class Drapeau {

    private ObjectId id;
    private int latitude;
    private int longitude;

    public Drapeau() {}

    public Drapeau(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return id + " " + latitude + " " + longitude;
    }
}
