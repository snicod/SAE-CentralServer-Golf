import org.bson.types.ObjectId;

public class EtatSol {

    private ObjectId id;
    private String date;
    private String densiteHerbe;
    private String qualiteNutriments;
    private int humiditeSol;
    private ObjectId trouId;

    public EtatSol() {}

    public EtatSol(String date, String densiteHerbe, String qualiteNutriments, int humiditeSol, ObjectId trouId) {
        this.date = date;
        this.densiteHerbe = densiteHerbe;
        this.qualiteNutriments = qualiteNutriments;
        this.humiditeSol = humiditeSol;
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

    public String getDensiteHerbe() {
        return densiteHerbe;
    }

    public void setDensiteHerbe(String densiteHerbe) {
        this.densiteHerbe = densiteHerbe;
    }

    public String getQualiteNutriments() {
        return qualiteNutriments;
    }

    public void setQualiteNutriments(String qualiteNutriments) {
        this.qualiteNutriments = qualiteNutriments;
    }

    public int getHumiditeSol() {
        return humiditeSol;
    }

    public void setHumiditeSol(int humiditeSol) {
        this.humiditeSol = humiditeSol;
    }

    public ObjectId getTrouId() {
        return trouId;
    }

    public void setTrouId(ObjectId trouId) {
        this.trouId = trouId;
    }

    public String toString() {
        return id + " " + date + " " + densiteHerbe + " " + qualiteNutriments + " " + humiditeSol + " " + trouId;
    }
}
