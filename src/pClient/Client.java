package pClient;

//Librerie
import clientGrafica.clientGrafica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// Classe del Client.
public class Client {
	public static void main(String[] args) {
		try {
			
			Socket pClient = new Socket("localhost",50001); // Creazione del client.
			
			// Apre i due stream di input e output.
			DataInputStream inStream= new DataInputStream(pClient.getInputStream());
			DataOutputStream outStream= new DataOutputStream(pClient.getOutputStream());
	
			// Oggeto grafica del client.
			clientGrafica clientGrafica = new clientGrafica();
			
			// Quando viene cliccato il bottone per fare l'accesso.
			clientGrafica.bottoneAccesso.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					try {
						outStream.writeInt(1); // Viene detto al server che è stato cliccato il bottone per l'accesso.
						// Vengono mandati al server i vari dati dell'utente: nome, cognome e numero di telefono.
						outStream.writeUTF(clientGrafica.getName());
						outStream.writeUTF(clientGrafica.getSurname());
						outStream.writeUTF(clientGrafica.getPhoneNumber());
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			});
			
			// Quando viene chiusa la schermata del login.
			clientGrafica.loginScreen.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent windowEvent) {
						try {
							outStream.writeInt(0); // Viene detto al server che è stata chiusa la schermata del login.
							// Chiude i due stream di input e output.
							inStream.close();
							outStream.close();
							// Chiude la connessione con il client.
							pClient.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
			        }
			});
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
