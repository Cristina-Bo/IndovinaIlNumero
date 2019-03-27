package polito.indivinailnumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SampleController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;
    // numero tentativi rimasti ancora da provare

    @FXML
    private HBox boxControlloTentativi;

    @FXML
    private TextField txtTentativo;
    //tentativo inserito dall'utente

    @FXML
    private TextArea txtMessaggi;

    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	//Gestisce l'inizio di una nuova partita
    	
    	// logica del gioco
    	this.segreto = (int)(Math.random()*NMAX)+1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	// Gestione dell'interfaccia
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtRimasti.setText(Integer.toString(this.TMAX));
    	
    }

    @FXML
    void handleNuovoTentativo(ActionEvent event) {
    	
    	// Leggi il valore del tentativo
    	String ts = txtTentativo.getText();
    	int tentativo;
    	
    	// Controlla se e' valido
    	
    	try {
    	tentativo = Integer.parseInt(ts);
    	}catch (NumberFormatException e) {
    	// la stringa inserita non e' un numero valido
    		txtMessaggi.appendText("Non e' un numero valido\n");
    		return;
    	}
    	
    	tentativiFatti++;
    	    	
    	// Controlla se ha indovinato
    	// -> fine partita
    	if(tentativo == segreto) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+tentativiFatti+" tentativi \n");
    		
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	
    	// Verifica se ha esaurito tentativi
    	// -> fine partita
    	
    	if(tentativiFatti == TMAX) {
    		txtMessaggi.appendText("Hai PERSO, il numero segreto era: "+segreto+"\n");
    		
    		//chiudo la partita
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	// Informa se troppo alto/basso
    	//->stampa messaggio
    	
    	if(tentativo<segreto) {
    		txtMessaggi.appendText("Tentativo troppo BASSO\n");
    	}
    	else {
    		txtMessaggi.appendText("Tentativo troppo ALTO\n");
    	}
    	
    	//Aggiornare interfaccia con n. tentativi rimasti
    		txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";

    }
}
