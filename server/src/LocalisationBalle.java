import org.bson.types.ObjectId;

public class LocalisationBalle {

    private ObjectId id;
    private ObjectId golfeurId;
    private int latitude;
    private int longitude;

    public LocalisationBalle() {}

    public LocalisationBalle(ObjectId golfeurId, int latitude, int longitude) {
        this.golfeurId = golfeurId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getGolfeurId() {
        return golfeurId;
    }

    public void setGolfeurId(ObjectId golfeurId) {
        this.golfeurId = golfeurId;
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
        return id + " " + golfeurId + " " + latitude + " " + longitude;
    }
}
