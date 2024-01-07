import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

/* COMMENTS

   This client allows to test the server by sending two types of requests :
   1 µC_type chipset1 chipset2 ...
   2 type value
   3 type value

   The first request is an autoregister request so that a module with an µC can
   register to the server. It just sends its µC type and a list of chipset that are
   hosted in the module (like a chipset acting as a temperature, voltage, ... sensor)
   If no error occurs, the server sends back OK module_name,module_short_name,module_key
   Name, short name and key are generated by the server (or API depending on accessing the DB
   directly in Java or using the node API)

   The second request and the third are used to store a value of a given type. The server sends back just OK
   Note that req 2 uses a constant module key that correspond to module 2 in DB. Indeed module key
   are long, so it is simpler to fix it.
   The difference between the two reqs is that 2 is used to store a value coming from a µC module, and 3 from an analysis
   server. For the latter, there is no key to provide.

   Examples based on the initial state of the DB (see db.init.js in the WeatherAPI project):
   1 esp32 lm35 bme280
   1 esp8266 lm35
   2 temperature 23.1
   2 pressure 1013
   3 voltage 5
   Meteo to store a condition meteo
   StatCoup to store a statistique coup
   EtatSol to store a etat sol
   Trou to store a trou
   LocBalle to store a localisation balle
   GestTrou to store a gestionnaire trou
   CamSurv to store a camera surveillance
   Drapeau to store a drapeau
   Golfeur to store a golfeur
 */

class MainClient  {

	BufferedReader br;
	PrintStream ps;
	Socket sock;
	BufferedReader consoleIn; // to read from keyboard

	public MainClient(String serverAddr, int port) throws IOException {

		consoleIn = new BufferedReader(new InputStreamReader(System.in));

		sock = new Socket(serverAddr,port);
		br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		ps = new PrintStream(sock.getOutputStream());
	}

	public void mainLoop() {

		String req = "";
		boolean stop = false;

		try {
			// reading requests from keyboard
			while (!stop) {
				System.out.print("WeatherClient [type request]> ");
				req = consoleIn.readLine();
				if (req == null) {
					stop = true;
				}
				else {
					String[] parts = req.split(" "); // separate req. name from params.
					if ("1".equals(parts[0])) {
						requestAutoRegister(parts);
					}
					else if ("2".equals(parts[0])) {
						requestStoreMeasure(parts[1], parts[2]);
					}
					else if ("3".equals(parts[0])) {
						requestStoreAnalysis(parts[1], parts[2]);
					}
					else if ("Meteo".equals(parts[0])) {
						requestStoreConditionMeteo(parts);
					}
					else if ("StatCoup".equals(parts[0])) {
						requestStoreStatistiqueCoup(parts);
					}
					else if ("EtatSol".equals(parts[0])) {
						requestStoreEtatSol(parts);
					}
					else if("Trou".equals(parts[0])){
						requestStoreTrou(parts);
					}
					else if ("LocBalle".equals(parts[0])) {
						requestStoreLocBalle(parts);
					}
					else if ("GestTrou".equals(parts[0])) {
						requestStoreGestionnaireTrous(parts);
					}
					else if ("CamSurv".equals(parts[0])) {
						requestStoreCameraSurveillance(parts);
					}
					else if ("Drapeau".equals(parts[0])) {
						requestStoreDrapeau(parts);
					}
					else if ("Golfeur".equals(parts[0])) {
						requestStoreGolfeur(parts);
					}
					else if("Image".equals(parts[0])){
						requestStoreImageDrapeau(parts);
					}
					else if (parts[0].equals("quit")) {
						stop = true;
					}
				}
			}
		}
		catch(IOException e) {
			System.out.println("cannot communicated with server. Aborting");
		}
	}

	protected void requestAutoRegister(String[] params) throws IOException {

		String answer="";
		String req = "AUTOREGISTER";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with request auto-register:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreConditionMeteo(String[] params) throws IOException {

		String answer="";
		String req = "STORECONDITIONMETEO";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store condition meteo:"+answer);
		}
		System.out.println(answer);
	}
	protected void requestStoreStatistiqueCoup(String[] params) throws IOException {

		String answer="";
		String req = "STORESTATISTIQUECOUP";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store statistique coup:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreEtatSol(String[] params) throws IOException {

		String answer="";
		String req = "STOREETATSOL";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store etat sol:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreTrou(String[] params) throws IOException {

		String answer="";
		String req = "STORETROU";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store trou:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreLocBalle(String[] params) throws IOException {

		String answer="";
		String req = "STORELOCBALLE";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store localisation balle:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreGestionnaireTrous(String[] params) throws IOException {

		String answer="";
		String req = "STOREGESTIONNAIRETROUS";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store gestionnaire trous:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreCameraSurveillance(String[] params) throws IOException {

		String answer="";
		String req = "STORECAMERASURVEILLANCE";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store camera surveillance:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreDrapeau(String[] params) throws IOException {

		String answer="";
		String req = "STOREDRAPEAU";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with store drapeau:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreGolfeur(String[] params) throws IOException {

		String answer="";
		String req = "STOREGOLFEUR";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if(answer.startsWith("ERR")) {
			System.out.println("error with store golfeur:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreImageDrapeau(String[] params) throws IOException {
		String answer="";
		String req = "STOREIMAGEDRAPEAU";
		for(int i=1;i<params.length;i++) req = req+" "+params[i];
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();

		if(answer.startsWith("ERR")) {
			System.out.println("error with store image drapeau:"+answer);
		}
		System.out.println(answer);
	}

	protected void requestStoreMeasure(String type, String value) throws IOException {
		String moduleKey = "2e46990d-3e85-45f8-82c8-f05eec1a1212";
		String answer="";
		String req = "STOREMEASURE "+type+" "+ LocalDateTime.now() +" "+value+" "+moduleKey;
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with request store measure:"+answer);
		}
		else {
			System.out.println(answer);
		}
	}

	protected void requestStoreAnalysis(String type, String value) throws IOException {
		String answer="";
		String req = "STOREANALYSIS "+type+" "+ LocalDateTime.now() +" "+value;
		System.out.println(req);
		ps.println(req);
		answer = br.readLine();
		if (answer.startsWith("ERR")) {
			System.out.println("error with request store measure:"+answer);
		}
		else {
			System.out.println(answer);
		}
	}
}
		
