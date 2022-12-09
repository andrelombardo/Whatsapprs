package clientGrafica;

// Librerie.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Classe che gestisce tutta la grafica del client.
public class clientGrafica {
	
	// Oggetti globali.
	public JFrame loginScreen;
	public JButton bottoneAccesso;
	private JTextField nameBox;
	private JTextField surnameBox;
	private JTextField phoneNumberBox;
	
	// Costruttore.
	public clientGrafica(){
		configLoginScreen(); // Creazione della schermata di login.
	}
	
	// Configurazione della schermata di login.
	public void configLoginScreen() {
		loginScreen = new JFrame();
		loginScreen.setSize(300,300); // Larghezza, altezza.
		loginScreen.setTitle("loginScreen");
		loginScreen.setLocationRelativeTo(null);
		loginScreen.setResizable(false);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Pannello che conterrà tutti gli elementi
		JPanel pannelloSchermata = new JPanel();
		// Casella per inserire il nome.
		nameBox = new JTextField("Nome",20);
		// Casella per inserire il cognome.
		surnameBox = new JTextField("Cognome",20);
		// Casella per inserire il numero di telefono.
		phoneNumberBox = new JTextField("Numero",20);
		// Bottone per fare l'accesso.
		bottoneAccesso = new JButton("Accesso");
		// Vengono aggiunti al pannello i vari elementi.
		pannelloSchermata.add(nameBox);
		pannelloSchermata.add(surnameBox);
		pannelloSchermata.add(phoneNumberBox);
		pannelloSchermata.add(bottoneAccesso);
		// Viene aggiunto nella schermata il pannello.
		loginScreen.add(pannelloSchermata);
		// Viene resa visibile la schermata.
		loginScreen.setVisible(true);
	}
	
	// Metodo per ottenere il contenuto della casella del nome.
	public String getName() {
		return nameBox.getText();
	}
	
	// Metodo per ottenere il contenuto della casella del cognome.
	public String getSurname() {
		return surnameBox.getText();
	}
	
	// Metodo per ottenere il contenuto della casella del numero di telefono.
	public String getPhoneNumber() {
		return phoneNumberBox.getText();
	}
	
}
