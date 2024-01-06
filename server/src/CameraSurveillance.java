import org.bson.types.ObjectId;
public class CameraSurveillance {

    private ObjectId id;
    private String date;
    private String videoUrl;
    private ObjectId trouId;

    public CameraSurveillance() {
    }

    public CameraSurveillance(String date, String videoUrl, ObjectId trouId) {
        this.date = date;
        this.videoUrl = videoUrl;
        this.trouId = trouId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ObjectId getTrouId() {
        return trouId;
    }

    public void setTrouId(ObjectId trouId) {
        this.trouId = trouId;
    }

    @Override
    public String toString() {
        return id + " " + date + " " + videoUrl + " " + trouId;
    }
}
