package pServer;

// Librerie.
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	
	// Dichiarazione del client.
	Socket externalClient;
	// Dichiarazione dei due stream di input e output.
	DataInputStream inStream;
	DataOutputStream outStream;
	
	// Costruttore.
	public ServerThread (Socket socket) { 
	    this.externalClient = socket; 
	}
		  
	public void run(){ 
		try {
			pServer();
		} catch (Exception e) {}	
	}
	
	// Codice del server.
	public void pServer() {
		int clientNumber;
		try {
			// Inizializzazione dei due stream di input e output.
			inStream = new DataInputStream (externalClient.getInputStream());
			outStream = new DataOutputStream(externalClient.getOutputStream());
			
			// E' un numero che manada il client: se 0 la finestra del login è chiusa se 1 ha mandato le credenziali.
			clientNumber = inStream.readInt(); 
			
			if(clientNumber == 1) {
				
				System.out.println(inStream.readUTF());
				System.out.println(inStream.readUTF());
				System.out.println(inStream.readUTF());
			}
			
			if(clientNumber == 0) {
				// Chiude i due stream di input e output.
				inStream.close();
				outStream.close();
				// Chiude la connessione con il client.
				externalClient.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}