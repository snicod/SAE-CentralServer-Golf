import org.bson.types.ObjectId;

public class ImageDrapeau {

    private ObjectId id;
    private int distanceEstimee;

    public ImageDrapeau() {}

    public ImageDrapeau(int distanceEstimee) {
        this.distanceEstimee = distanceEstimee;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getDistanceEstimee() {
        return distanceEstimee;
    }

    public void setDistanceEstimee(int distanceEstimee) {
        this.distanceEstimee = distanceEstimee;
    }

    @Override
    public String toString() {
        return id + " " + distanceEstimee;
    }
}
