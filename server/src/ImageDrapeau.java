import org.bson.types.ObjectId;

public class ImageDrapeau {

    private ObjectId id;
    private double distanceEstimee;

    public ImageDrapeau() {}

    public ImageDrapeau(double distanceEstimee) {
        this.distanceEstimee = distanceEstimee;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getDistanceEstimee() {
        return distanceEstimee;
    }

    public void setDistanceEstimee(double distanceEstimee) {
        this.distanceEstimee = distanceEstimee;
    }

    @Override
    public String toString() {
        return id + " " + distanceEstimee;
    }
}
