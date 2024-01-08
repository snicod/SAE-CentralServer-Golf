import org.bson.types.ObjectId;

public class StatistiqueCoup {

    private ObjectId id;
    private ObjectId golfeurId;
    private int vitesse;
    private int trajectoire;
    private String conseils;

    public StatistiqueCoup() {}

    public StatistiqueCoup(ObjectId golfeurId, int vitesse, int trajectoire, String conseils) {
        this.golfeurId = golfeurId;
        this.vitesse = vitesse;
        this.trajectoire = trajectoire;
        this.conseils = conseils;
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

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getTrajectoire() {
        return trajectoire;
    }

    public void setTrajectoire(int trajectoire) {
        this.trajectoire = trajectoire;
    }

    public String getConseils() {
        return conseils;
    }

    public void setConseils(String conseils) {
        this.conseils = conseils;
    }

    public String toString() {
        return id + " " + golfeurId + " " + vitesse + " " + trajectoire + " " + conseils;
    }
}
