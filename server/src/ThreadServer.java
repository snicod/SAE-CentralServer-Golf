import org.bson.types.ObjectId;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class ThreadServer extends Thread {

	BufferedReader br;
	PrintStream ps;
	Socket sock;
	DataExchanger exchanger;
	int idThread;

	public ThreadServer(int idThread, Socket sock, DataExchanger data) {
		this.sock = sock;
		this.idThread = idThread;
		this.exchanger = data;
	}

	public void run() {

		try {
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			ps = new PrintStream(sock.getOutputStream());
		}
		catch(IOException e) {
			System.err.println("Thread "+ idThread +": cannot create streams. Aborting.");
			return;
		}
		requestLoop();
		System.out.println("end of thread "+ idThread);
	}

	public void requestLoop() {

		String req = "";
		String idReq;
		String[] reqParts;

		try {
			while(true) {
				req = br.readLine();
				if ((req == null) || (req.isEmpty())) {
					break;
				}

				reqParts = req.split(" ");
				idReq = reqParts[0];

				if ("AUTOREGISTER".equals(idReq)) {
					requestAutoRegister(reqParts);
				}
				else if ("STOREMEASURE".equals(idReq)) {
					requestStoreMeasure(reqParts);
				}
				else if ("STOREANALYSIS".equals(idReq)) {
					requestStoreAnalysis(reqParts);
				}
				else if("STORECONDITIONMETEO".equals(idReq)) {
					requestStoreConditionMeteo(reqParts);
				}
				else if("STORESTATISTIQUECOUP".equals(idReq)) {
					requestStoreStatistiqueCoup(reqParts);
				}
				else if("STOREETATSOL".equals(idReq)) {
					requestStoreEtatSol(reqParts);
				}
				else if("STORETROU".equals(idReq)){
					requestStoreTrou(reqParts);
				}
				else if("STORELOCBALLE".equals(idReq)){
					requestStoreLocBalle(reqParts);
				}
				else if("STORECAMERASURVEILLANCE".equals(idReq)){
					requestStoreCameraSurveillance(reqParts);
				}
				else if("STOREGESTIONNAIRETROUS".equals(idReq)){
					requestStoreGestionnaireTrous(reqParts);
				}
				else if("STOREDRAPEAU".equals(idReq)){
					requestStoreDrapeau(reqParts);
				}
				else if("STOREGOLFEUR".equals(idReq)){
					requestStoreGolfeur(reqParts);
				}
				else if ("STOREIMAGEDRAPEAU".equals(idReq)) {
					requestStoreImageDrapeau(reqParts);
				}
				else {
					ps.println("ERR unknown request");
				}
			}
			System.out.println("end of request loop");
		}
		catch(IOException e) {
			System.out.println("problem with receiving request: "+e.getMessage());
		}
	}

	protected void requestAutoRegister(String[] params) throws IOException {
		// remove the identifier+uc from params
		List<String> lst = new ArrayList<>();
		for(int i=2;i<params.length;i++) {
			lst.add(params[i]);
		}
		System.out.println("processing request AUTO REGISTER");
		if (params.length < 3) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().autoRegisterModule(params[1], lst);
		String answer = exchanger.getHttpDriver().autoRegisterModule(params[1], lst);
		System.out.println(answer);
		ps.println(answer);
	}
	protected void requestStoreMeasure(String[] params) throws IOException {
		System.out.println("processing request STORE MEASURE");

		if (params.length != 5) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveMeasure(params[1], params[2], params[3], params[4]);
		String answer = exchanger.getHttpDriver().saveMeasure(params[1], params[2], params[3], params[4]);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreConditionMeteo(String[] params) throws IOException {
		System.out.println("processing request STORE CONDITION METEO");

		if (params.length != 7) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		//params[2] to params[5] are int
		int temperature = Integer.parseInt(params[3]);
		int humidite = Integer.parseInt(params[4]);
		int vitesseVent = Integer.parseInt(params[5]);

		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveConditionMeteo(params[1], params[2], temperature, humidite, vitesseVent, params[6]);*
		String answer = exchanger.getHttpDriver().saveConditionMeteo(params[1], params[2], temperature, humidite, vitesseVent, params[6]);
		System.out.println(answer);
		ps.println(answer);
	}
	protected void requestStoreStatistiqueCoup(String[] params) throws IOException {
		System.out.println("processing request STORE STATISTIQUE COUP");

		if (params.length != 5) {
			ps.println("ERR invalid number of parameters");
			return;
		}

		int vitesse = Integer.parseInt(params[2]);
		int trajectoire = Integer.parseInt(params[3]);


		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveStatistiqueCoup(params[1], vitesse, trajectoire, params[4]);*
		String answer = exchanger.getHttpDriver().saveStatistiqueCoup(params[1], vitesse, trajectoire, params[4]);
		System.out.println(answer);
		ps.println(answer);
	}
	protected void requestStoreEtatSol(String[] params) throws IOException {
		System.out.println("processing request STORE ETAT SOL");

		if (params.length != 6) {
			ps.println("ERR invalid number of parameters");
			return;
		}

		int humiditeSol = Integer.parseInt(params[5]);

		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveEtatSol(params[1], params[2], params[3], params[4], humiditeSol);*
		String answer = exchanger.getHttpDriver().saveEtatSol(params[1], params[2], params[3], params[4], humiditeSol);
		System.out.println(answer);
		ps.println(answer);
	}
	protected void requestStoreTrou(String[] params) throws IOException {
		System.out.println("processing request STORE TROU");

		if (params.length != 4) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		int numero = Integer.parseInt(params[1]);

		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveTrou(numero, params[2], params[3]);*
		String answer = exchanger.getHttpDriver().saveTrou(numero, params[2], params[3]);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreLocBalle(String[] params) throws IOException {
		System.out.println("processing request STORE LOC BALLE");

		if (params.length != 4) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		int latitude = Integer.parseInt(params[2]);
		int longitude = Integer.parseInt(params[3]);

		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveLocalisationBalle(params[1], latitude, longitude);*
		String answer = exchanger.getHttpDriver().saveLocalisationBalle(params[1], latitude, longitude);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreCameraSurveillance(String[] params) throws IOException {
		System.out.println("processing request STORE CAMERA SURVEILLANCE");

		if (params.length != 4) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveCameraSurveillance(params[1], params[2], params[3]);*
		String answer = exchanger.getHttpDriver().saveCameraSurveillance(params[1], params[2], params[3]);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreGestionnaireTrous(String[] params) throws IOException {
		System.out.println("processing request STORE GESTIONNAIRE TROUS");

		if (params.length != 5) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveGestionnaireTrous(params[1], params[2], params[3], params[4]);*
		String answer = exchanger.getHttpDriver().saveGestionnaireTrous(params[1], params[2], params[3], params[4]);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreDrapeau(String[] params) throws IOException {
		System.out.println("processing request STORE DRAPEAU");

		if (params.length != 3) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		int latitude = Integer.parseInt(params[1]);
		int longitude = Integer.parseInt(params[2]);

		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveDrapeau(latitude, longitude);*
		String answer = exchanger.getHttpDriver().saveDrapeau(latitude, longitude);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreGolfeur(String[] params) throws IOException {
		System.out.println("processing request STORE GOLFEUR");

		if (params.length != 5) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveGolfeur(params[1], params[2], params[3], params[4]);*
		String answer = exchanger.getHttpDriver().saveGolfeur(params[1], params[2], params[3], params[4]);
		System.out.println(answer);
		ps.println(answer);
	}

	protected void requestStoreImageDrapeau(String[] params) throws IOException {
		System.out.println("processing request STORE IMAGE DRAPEAU");

		if (params.length != 2) {
			ps.println("ERR invalid number of parameters");
			return;
		}

		int distance = Integer.parseInt(params[1]);
		// (un)comment to choose direct mongo access or through the node API
		//String answer = exchanger.getMongoDriver().saveImageDrapeau(distance);*
		String answer = exchanger.getHttpDriver().saveImageDrapeau(distance);
		System.out.println(answer);
		ps.println(answer);
	}


	protected void requestStoreAnalysis(String[] params) throws IOException {
		System.out.println("processing request STORE ANALYSIS");

		if (params.length != 4) {
			ps.println("ERR invalid number of parameters");
			return;
		}
		// (un)comment to choose direct mongo access or through the node API
		String answer = exchanger.getMongoDriver().saveAnalysis(params[1], params[2], params[3]);
		//String answer = exchanger.getHttpDriver().saveAnalysis(params[1], params[2], params[3]);
		System.out.println(answer);
		ps.println(answer);
	}

}

		
