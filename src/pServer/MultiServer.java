package pServer;

// Librerie.
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Classe multiServer che permette di accettare più client.
public class MultiServer {
 
	public void startServer() {
		try (ServerSocket pMultiServer = new ServerSocket(50001)) {
			System.out.println("Il server è stato avviato correttamente.\n");
			while(true) {
				Socket externalClient = pMultiServer.accept();
				System.out.println("E' stato accettato un client: " + externalClient);
		        ServerThread serverThread = new ServerThread(externalClient); 
		        serverThread.start(); 
			}
		} catch (IOException e) {}
	}
	
	 public static void main (String[] args){ 
	     MultiServer multiServer = new MultiServer(); 
	     multiServer.startServer(); 
	   } 
}