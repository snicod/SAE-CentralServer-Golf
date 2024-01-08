import org.bson.types.ObjectId;

public class ConditionMeteo {

    private ObjectId id;
    private ObjectId trouId;
    private String date;
    private int temperature;
    private int humidite;
    private Vent vent;

    public ConditionMeteo() {}

    public ConditionMeteo(ObjectId trouId, String date, int temperature, int humidite, Vent vent) {
        this.trouId = trouId;
        this.date = date;
        this.temperature = temperature;
        this.humidite = humidite;
        this.vent = vent;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getTrouId() {
        return trouId;
    }

    public void setTrouId(ObjectId trouId) {
        this.trouId = trouId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidite() {
        return humidite;
    }

    public void setHumidite(int humidite) {
        this.humidite = humidite;
    }

    public Vent getVent() {
        return vent;
    }

    public void setVent(Vent vent) {
        this.vent = vent;
    }

    public String toString() {
        return id + " " + trouId + " " + date + " " + temperature + " " + humidite + " " + vent;
    }
}

class Vent {
    private int vitesse;
    private String direction;

    public Vent() {}

    public Vent(int vitesse, String direction) {
        this.vitesse = vitesse;
        this.direction = direction;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String toString() {
        return vitesse + " " + direction;
    }
}
