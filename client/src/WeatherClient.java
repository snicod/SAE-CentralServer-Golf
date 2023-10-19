import java.io.*;
import java.net.*;
import java.util.*;

class WeatherClient {

  public static void main(String []args) {

    MainClient client;

    if (args.length != 2) {
      System.out.println("usage: java WeatherClient ip_server port");
      System.exit(1);
    }

    try {
      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);
      client = new MainClient(serverAddr,port);
      client.mainLoop();
    }
    catch(IOException e) {
      System.out.println("cannot connect to server : "+e.getMessage());
      System.exit(1);
    }
  }
}
		
