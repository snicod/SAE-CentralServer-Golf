import org.bson.types.ObjectId;

import java.util.Date;

public class StatistiqueCoup {

    private ObjectId id;
    private ObjectId golfeurId;
    private ObjectId trouId;
    private String date;
    private int vitesse;
    private int trajectoire;
    private String conseils;
    private double latitudeDepart;
    private double longitudeDepart;
    private double latitudeArrivee;
    private double longitudeArrivee;

    private double accelerationX;

    private double accelerationY;

    public StatistiqueCoup() {}

    public StatistiqueCoup(ObjectId golfeurId, ObjectId trouId, int vitesse, int trajectoire, String conseils, double latitudeDepart, double longitudeDepart, double latitudeArrivee, double longitudeArrivee, double accelerationX, double accelerationY) {
        this.golfeurId = golfeurId;
        this.trouId = trouId;
        this.date = new Date().toString();
        this.vitesse = vitesse;
        this.trajectoire = trajectoire;
        this.conseils = conseils;
        this.latitudeDepart = latitudeDepart;
        this.longitudeDepart = longitudeDepart;
        this.latitudeArrivee = latitudeArrivee;
        this.longitudeArrivee = longitudeArrivee;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
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

    public double getLatitudeDepart() {
        return latitudeDepart;
    }

    public void setLatitudeDepart(double latitudeDepart) {
        this.latitudeDepart = latitudeDepart;
    }

    public double getLongitudeDepart() {
        return longitudeDepart;
    }

    public void setLongitudeDepart(double longitudeDepart) {
        this.longitudeDepart = longitudeDepart;
    }

    public double getLatitudeArrivee() {
        return latitudeArrivee;
    }

    public void setLatitudeArrivee(double latitudeArrivee) {
        this.latitudeArrivee = latitudeArrivee;
    }

    public double getLongitudeArrivee() {
        return longitudeArrivee;
    }

    public void setLongitudeArrivee(double longitudeArrivee) {
        this.longitudeArrivee = longitudeArrivee;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }

    public String toString() {
        return id + " " + golfeurId + " " + trouId + " " + date + " " + vitesse + " " + trajectoire + " " + conseils + " " + latitudeDepart + " " + longitudeDepart + " " + latitudeArrivee + " " + longitudeArrivee + " " + accelerationX + " " + accelerationY;
    }
}
