import org.bson.Document;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import java.io.*;
import java.util.List;


public class HttpDataDriver implements DataDriver {

    private HttpClient client;
    private String apiURL;

    public HttpDataDriver(String apiURL) {
        this.apiURL = apiURL;
        client = HttpClient.newHttpClient();
    }

    public boolean init() {
        return true;
    }

    private String checkError(Document answer) {
        int error = answer.getInteger("error");
        if (error != 0) {
            return answer.getString("data");
        }
        return null;
    }

    private Document postRequest(String route, String payload) {
        Document doc = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL+route))
                .header("Content-Type", "application/json")
                .method("POST",HttpRequest.BodyPublishers.ofString(payload))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.body());
            // parse received JSON
            doc = Document.parse(response.body());
        }
        catch(InterruptedException e) {
            return null;
        }
        catch(IOException e) {
            return null;
        }
        return doc;
    }

    private Document patchRequest(String route, String payload) {
        Document doc = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL+route))
                .header("Content-Type", "application/json")
                .method("PATCH",HttpRequest.BodyPublishers.ofString(payload))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.body());
            // parse received JSON
            doc = Document.parse(response.body());
        }
        catch(InterruptedException e) {
            return null;
        }
        catch(IOException e) {
            return null;
        }
        return doc;
    }


    public synchronized String autoRegisterModule(String uc, List<String> chipsets) {
        String payload = "{\"uc\": \""+uc+"\", \"chipsets\": [";
        String name = "";
        String shortName = "";
        String key = "";
        int i = 0;
        for(i=0;i<chipsets.size()-1;i++) {
            payload += "\""+chipsets.get(i)+"\",";
        }
        payload += "\""+chipsets.get(i)+"\"]}";

        Document doc = postRequest("/module/register", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        // if error
        String err = checkError(doc);
        if (err != null) return err;
        // if not, get desired field in data
        Document data = (Document)doc.get("data");
        name = data.getString("name");
        shortName = data.getString("shortName");
        key = data.getString("key");
        return "OK "+name+","+shortName+","+key;
    }

    public synchronized String saveConditionMeteo(String trouId, String date, int temperature, int humidite, int vitesseVent, String directionVent) {
        String payload = "{\"trou_id\": \"" + trouId + "\", \"date\": \"" + date + "\", \"temperature\": " + temperature + ", \"humidite\": " + humidite + ", \"vent\": {\"vitesse\": " + vitesseVent + ", \"direction\": \"" + directionVent + "\"}}";
        return sendConditionMeteo(payload);
    }
    public synchronized String saveStatistiqueCoup(String golfeurId, String trouId, int vitesse, int trajectoire, String conseils, int latitudeDepart, int longitudeDepart, int latitudeArrivee, int longitudeArrivee) {
        String payload = "{\"golfeur_id\": \"" + golfeurId + "\", \"trou_id\": \"" + trouId + "\", \"vitesse\": " + vitesse + ", \"trajectoire\": " + trajectoire + ", \"conseils\": \"" + conseils + "\", \"latitude_depart\": " + latitudeDepart + ", \"longitude_depart\": " + longitudeDepart + ", \"latitude_arrivee\": " + latitudeArrivee + ", \"longitude_arrivee\": " + longitudeArrivee + ", \"date\": \"\"}";
        return sendStatistiqueCoup(payload);
    }

    public synchronized String saveEtatSol(String trouId, String date, String densiteHerbe, String qualiteNutriments, int humiditeSol) {
        String payload = "{\"trou_id\": \"" + trouId + "\", \"date\": \"" + date + "\", \"densite_herbe\": \"" + densiteHerbe + "\", \"qualite_nutriments\": \"" + qualiteNutriments + "\", \"humidite_sol\": " + humiditeSol + "}";
        return sendEtatSol(payload);
    }
    public synchronized String saveTrou(int numero, String gestionnaireId, String drapeauId) {
        String payload = "{\"numero\": " + numero + ", \"gestionnaire_id\": \"" + gestionnaireId + "\", \"drapeau_id\": \"" + drapeauId + "\"}";
        return sendTrou(payload);
    }
    public synchronized String saveLocalisationBalle(String golfeurId, int latitude, int longitude) {
        String payload = "{\"golfeur_id\": \"" + golfeurId + "\", \"latitude\": " + latitude + ", \"longitude\": " + longitude + "}";
        return sendLocalisationBalle(payload);
    }

    public synchronized String saveCameraSurveillance(String trouId, String date, String videoUrl) {
        String payload = "{\"trou_id\": " + trouId + ", \"date\": \"" + date + "\", \"video_url\": \"" + videoUrl + "\"}";
        return sendCameraSurveillance(payload);
    }

    public synchronized String saveGestionnaireTrous(String nom, String prenom, String email, String motDePasse) {
        String payload = "{\"nom\": \"" + nom + "\", \"prenom\": \"" + prenom + "\", \"email\": \"" + email + "\", \"mot_de_passe\": \"" + motDePasse + "\"}";
        return sendGestionnaireTrous(payload);
    }

    public synchronized String saveDrapeau(int latitude, int longitude) {
        String payload = "{\"latitude\": " + latitude + ", \"longitude\": " + longitude + "}";
        return sendDrapeau(payload);
    }

    public synchronized String saveGolfeur(String nom, String prenom, String email, String motDePasse) {
        String payload = "{\"nom\": \"" + nom + "\", \"prenom\": \"" + prenom + "\", \"email\": \"" + email + "\", \"mot_de_passe\": \"" + motDePasse + "\"}";
        return sendGolfeur(payload);
    }

    public synchronized String saveImageDrapeau(double distance) {
        String payload = "{\"distance_estimee\": " + distance + "}";
        return sendImageDrapeau(payload);
    }

    public synchronized  String saveMeasure(String type, String date, String value, String moduleKey) {

        String payload = "{\"type\": \""+type+"\", \"date\": \""+date+"\", \"value\": \""+value+"\", \"moduleKey\": \""+moduleKey+"\"}";
        return sendMeasure(payload);
    }

    public synchronized String saveAnalysis(String type, String date, String value) {

        String payload = "{\"type\": \""+type+"\", \"date\": \""+date+"\", \"value\": \""+value+"\"}";
        return sendMeasure(payload);
    }



    private String sendMeasure(String payload) {
        Document doc = postRequest("/measure/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }
    private String sendConditionMeteo(String payload) {
        Document doc = postRequest("/conditionsMeteo/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }
    private String sendStatistiqueCoup(String payload) {
        Document doc = postRequest("/statistiqueCoup/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }
    private String sendEtatSol(String payload) {
        Document doc = postRequest("/etatSol/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }

    private String sendTrou(String payload) {
        Document doc = postRequest("/trou/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }
    private String sendLocalisationBalle(String payload) {
        Document doc = postRequest("/localisationBalle/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }
    private String sendCameraSurveillance(String payload) {
        Document doc = postRequest("/cameraSurveillance/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }

    private String sendGestionnaireTrous(String payload) {
        Document doc = postRequest("/gestionnaireTrous/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }

    private String sendDrapeau(String payload) {
        Document doc = postRequest("/drapeau/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        if (err != null) return err;
        return "OK";
    }

    private String sendGolfeur(String payload) {
        Document doc = postRequest("/golfeur/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        return err;
    }

    private String sendImageDrapeau(String payload) {
        Document doc = postRequest("/imageDrapeau/create", payload);
        if (doc == null) {
            return "ERR cannot join the API";
        }
        String err = checkError(doc);
        return err;
    }
}
