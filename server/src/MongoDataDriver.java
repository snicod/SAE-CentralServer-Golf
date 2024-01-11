import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import static com.mongodb.client.model.Filters.eq;

public class MongoDataDriver implements DataDriver {

    private String mongoURL;
    private CodecProvider pojoCodecProvider;
    private CodecRegistry pojoCodecRegistry;
    private MongoClient mongoClient;
    private MongoDatabase database;
    MongoCollection<Measure> measures;
    MongoCollection<Module> modules;
    MongoCollection<Chipset> chipsets;

    MongoCollection<Trou> trous;

    MongoCollection<ConditionMeteo> conditions;

    MongoCollection<StatistiqueCoup> statistiqueCoup;

    MongoCollection<Golfeur> golfeurs;

    MongoCollection<Drapeau> drapeaux;

    MongoCollection<CameraSurveillance> cameraSurveillances;

    MongoCollection<EtatSol> etatSols;

    MongoCollection<GestionnaireTrous> gestionnaireTrous;

    MongoCollection<LocalisationBalle> localisationBalles;

    MongoCollection<ImageDrapeau> imageDrapeaux;

    public MongoDataDriver(String mongoURL) {
        this.mongoURL = mongoURL;
        pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }

    public boolean init()  {
        mongoClient = MongoClients.create(mongoURL);
        try {
            database = mongoClient.getDatabase("weatherapi").withCodecRegistry(pojoCodecRegistry);
            measures = database.getCollection("measures", Measure.class);
            modules = database.getCollection("modules", Module.class);
            chipsets = database.getCollection("chipsets", Chipset.class);
            conditions = database.getCollection("conditionsmeteos", ConditionMeteo.class);
            statistiqueCoup = database.getCollection("statistiquecoup", StatistiqueCoup.class);
            golfeurs = database.getCollection("golfeurs", Golfeur.class);
            trous = database.getCollection("trous", Trou.class);
            drapeaux = database.getCollection("drapeauxes", Drapeau.class);
            cameraSurveillances = database.getCollection("camerasurveillances", CameraSurveillance.class);
            etatSols = database.getCollection("etatsols", EtatSol.class);
            gestionnaireTrous = database.getCollection("gestionnairetrous", GestionnaireTrous.class);
            localisationBalles = database.getCollection("localisationballes", LocalisationBalle.class);
            imageDrapeaux = database.getCollection("imagedrapeauxes", ImageDrapeau.class);
        }
        catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private ObjectId getModuleId(String moduleKey) {
        Module module = modules.find(eq("key",moduleKey)).first();
        if (module != null) {
            System.out.println(module.getKey()+ " -> "+module.getId());

            return module.getId();
        }
        return null;
    }

    private ObjectId getChipsetId(String chipsetName) {
        Chipset chipset = chipsets.find(eq("name",chipsetName)).first();
        if (chipset != null) {
            System.out.println(chipset.getName()+ " -> "+chipset.getId());

            return chipset.getId();
        }
        return null;
    }

    private ObjectId getTrouId(String trouId) {
        Trou trou = trous.find(eq("trouId",trouId)).first();
        if (trou != null) {
            System.out.println(trou.getNumero()+ " -> "+trou.getId());

            return trou.getId();
        }
        return null;
    }

    private ObjectId getGolfeurId(String golfeurId) {
        Golfeur golfeur = golfeurs.find(eq("golfeurId",golfeurId)).first();
        if (golfeur != null) {
            System.out.println(golfeur.getNom()+ " -> "+golfeur.getId());

            return golfeur.getId();
        }
        return null;
    }

    private ObjectId getDrapeauId(String drapeauId) {
        Drapeau drapeau = drapeaux.find(eq("drapeauId",drapeauId)).first();
        if (drapeau != null) {
            System.out.println(drapeau.getLatitude()+ " -> "+drapeau.getId());

            return drapeau.getId();
        }
        return null;
    }

    private ObjectId getGestionnaireTrousId(String gestionnaireTrousId) {
        GestionnaireTrous gestionnaireTrou = gestionnaireTrous.find(eq("gestionnaireTrousId",gestionnaireTrousId)).first();
        if (gestionnaireTrou != null) {
            System.out.println(gestionnaireTrou.getNom()+ " -> "+gestionnaireTrou.getId());

            return gestionnaireTrou.getId();
        }
        return null;
    }

    public synchronized  String autoRegisterModule(String uc, List<String> chipsets) {
        List<ObjectId> lst = new ArrayList<>();
        for(String chipset : chipsets) {
            ObjectId id = getChipsetId(chipset);
            if (id != null) {
                lst.add(id);
            }
        }
        // must generate an unique key
        UUID key = UUID.randomUUID();
        boolean stop = false;
        while(!stop) {
            ObjectId id = getModuleId(key.toString());
            if (id == null) {
                stop = true;
            }
            else {
                key = UUID.randomUUID();
            }
        }
        long nb = modules.estimatedDocumentCount()+1;
        String name = "module "+nb;
        String shortName = "mod"+nb;
        Module m = new Module(name, shortName, key.toString(), uc, lst);
        modules.insertOne(m);
        return "OK "+m.getName()+","+m.getShortName()+","+m.getKey();
    }

    public synchronized String saveMeasure(String type, String date, String value, String moduleKey) {

        ObjectId idModule = getModuleId(moduleKey);
        if (idModule == null) {
            return "ERR invalid module key";
        }
        Measure m = new Measure(type, LocalDateTime.parse(date), value, idModule);
        measures.insertOne(m);
        return "OK";
    }

    public synchronized String saveAnalysis(String type, String date, String value) {
        Measure m = new Measure(type, LocalDateTime.parse(date), value, null);
        measures.insertOne(m);
        return "OK";
    }

    public synchronized String saveConditionMeteo(String trouId, String date, int temperature, int humidite, int vitesseVent, String directionVent) {
        ConditionMeteo c = new ConditionMeteo(getTrouId(trouId), date, temperature, humidite, new Vent(vitesseVent, directionVent));
        conditions.insertOne(c);
        return "OK";
    }
    public synchronized String saveStatistiqueCoup(String golfeurId, String trouId, int vitesse, int trajectoire, String conseils, int latitudeDepart, int longitudeDepart, int latitudeArrivee, int longitudeArrivee, int accelerationX, int accelerationY) {
        StatistiqueCoup s = new StatistiqueCoup(getGolfeurId(golfeurId), getTrouId(trouId), vitesse, trajectoire, conseils, latitudeDepart, longitudeDepart, latitudeArrivee, longitudeArrivee, accelerationX, accelerationY);
        statistiqueCoup.insertOne(s);
        return "OK";
    }
    public synchronized String saveEtatSol(String trouId, String date, String densiteHerbe, String qualiteNutriments, int humiditeSol) {
        EtatSol e = new EtatSol(date, densiteHerbe, qualiteNutriments, humiditeSol, getTrouId(trouId));
        etatSols.insertOne(e);
        return "OK";
    }

    public synchronized String saveTrou(int numero, String gestionnaireId, String drapeauId) {
        Trou t = new Trou(numero, getGestionnaireTrousId(gestionnaireId), getDrapeauId(drapeauId));
        trous.insertOne(t);
        return "OK";
    }

    public synchronized String saveLocalisationBalle(String golfeurId, int latitude, int longitude) {
        LocalisationBalle l = new LocalisationBalle(getGolfeurId(golfeurId), latitude, longitude);
        localisationBalles.insertOne(l);
        return "OK";
    }

    public synchronized String saveCameraSurveillance(String trouId, String date, String videoUrl) {
        CameraSurveillance c = new CameraSurveillance(date, videoUrl, getTrouId(trouId));
        cameraSurveillances.insertOne(c);
        return "OK";
    }

    public synchronized String saveGestionnaireTrous(String nom, String prenom, String email, String motDePasse) {
        GestionnaireTrous g = new GestionnaireTrous(nom, prenom, email, motDePasse);
        gestionnaireTrous.insertOne(g);
        return "OK";
    }

    public synchronized String saveDrapeau(int latitude, int longitude) {
        Drapeau d = new Drapeau(latitude, longitude);
        drapeaux.insertOne(d);
        return "OK";
    }

    public synchronized String saveGolfeur(String nom, String prenom, String email, String motDePasse) {
        Golfeur g = new Golfeur(nom, prenom, email, motDePasse);
        golfeurs.insertOne(g);
        return "OK";
    }

    public synchronized String saveImageDrapeau(double distance) {
        ImageDrapeau i = new ImageDrapeau(distance);
        imageDrapeaux.insertOne(i);
        return "OK";
    }
}
