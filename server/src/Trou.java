import org.bson.types.ObjectId;

public class Trou {

    private ObjectId id;
    private int numero;
    private ObjectId gestionnaireId;
    private ObjectId drapeauId;

    public Trou() {}

    public Trou(int numero, ObjectId gestionnaireId, ObjectId drapeauId) {
        this.numero = numero;
        this.gestionnaireId = gestionnaireId;
        this.drapeauId = drapeauId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ObjectId getGestionnaireId() {
        return gestionnaireId;
    }

    public void setGestionnaireId(ObjectId gestionnaireId) {
        this.gestionnaireId = gestionnaireId;
    }

    public ObjectId getDrapeauId() {
        return drapeauId;
    }

    public void setDrapeauId(ObjectId drapeauId) {
        this.drapeauId = drapeauId;
    }

    public String toString() {
        return id + " " + numero + " " + gestionnaireId + " " + drapeauId;
    }
}
