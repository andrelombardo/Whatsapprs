package pServer;

// Librerie.
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		int numeroControllo; // Serve a capire cosa deve fare il server in base alla scelta del client.
		String nomeUtente = ""; // Contiene il nome dell'utente.
		String cognomeUtente = ""; // Contiene il cognome dell'utente.
		String numeroUtente = ""; // Contiene il numero telefonico dell'utente.
		try {
			// Inizializzazione dei due stream di input e output.
			inStream = new DataInputStream (externalClient.getInputStream());
			outStream = new DataOutputStream(externalClient.getOutputStream());
			
			// E' un numero che manada il client: se 0 la finestra del login è chiusa se 1 ha mandato le credenziali.
			numeroControllo = inStream.readInt(); 
			
			if(numeroControllo == 1) {
				
				// Riceve dal client nomeUtente, cognomeUtente e numeroUtente.
				nomeUtente = inStream.readUTF();
				cognomeUtente = inStream.readUTF();
				numeroUtente = inStream.readUTF();
	
				try {
					// Connessione al database.
					Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Whatsapprs", "root", "");
					// Dichiarazione.
					Statement myStatement = myConnection.createStatement();
					
					// Aggiunta di dati al database.
					String credenzialiUtente = "insert into utentiRegistrati (nomeUtente,cognomeUtente,numeroUtente)"
							   + "value ('" + nomeUtente + "','" + cognomeUtente + "','" + numeroUtente + "')";
					
					// Aggiunta della stringa sql al database.
				    myStatement.executeUpdate(credenzialiUtente);
					
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
			
			if(numeroControllo == 0) {
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