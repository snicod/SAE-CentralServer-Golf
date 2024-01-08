import java.util.List;

public interface DataDriver {

    // initialize the driver (if needed)
    public boolean init();
    // store a measure in the DB
    public String saveMeasure(String type, String date, String value, String moduleKey);
    // store a measure in the DB but coming from the analysis server -> no module key
    public String saveAnalysis(String type, String date, String value);
    // register a module from its own request
    public String autoRegisterModule(String uc, List<String> chipsets);

    String saveConditionMeteo(String trouId, String date, int temperature, int humidite, int vitesseVent, String directionVent);

    String saveStatistiqueCoup(String golfeurId, int vitesse, int trajectoire, String conseils);

    String saveEtatSol(String trouId, String date, String densiteHerbe, String qualiteNutriments, int humiditeSol);

    String saveTrou(int numero, String gestionnaireId, String drapeauId);

    String saveLocalisationBalle(String golfeurId, int latitude, int longitude);

    String saveCameraSurveillance(String trouId, String date, String videoUrl);

    String saveGestionnaireTrous(String nom, String prenom, String email, String motDePasse);

    String saveDrapeau(int latitude, int longitude);

    String saveGolfeur(String nom, String prenom, String email, String motDePasse);

    String saveImageDrapeau(int distance);
}
