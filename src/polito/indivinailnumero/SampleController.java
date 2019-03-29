package polito.indivinailnumero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SampleController {
	
	private NumeroModel model;
	

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
    	    	
    	// Gestione dell'interfaccia
    	boxControlloPartita.setDisable(true);
    	boxControlloTentativi.setDisable(false);
    	txtMessaggi.clear();
    	txtTentativo.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    	
    	model.newGame();
    	// dico al modello che e' iniziata una nuova partita
    }

    @FXML
    void handleNuovoTentativo(ActionEvent event) {
    	
    	// Leggi il valore del tentativo
    	String ts = txtTentativo.getText();
    	int tentativo;
    	
    	// Controlla se e' valido il tipo di dato
    	
    	try {
    	tentativo = Integer.parseInt(ts);
    	}catch (NumberFormatException e) {
    	// la stringa inserita non e' un numero valido
    		txtMessaggi.appendText("Non e' un numero valido\n");
    		return;
    	}
    	if(!model.tentativoValido(tentativo)) {
    		txtMessaggi.appendText("Range non valido \n");
    		return;
    	}
    	
    	int risultato = model.tentativo(tentativo);
    	if(risultato == 0) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi \n");
    		boxControlloPartita.setDisable(false);
    		boxControlloTentativi.setDisable(true);
    		// this.inGioco = false; non serve, lo fa il model
    	}
    	else if(risultato < 0) {
    		txtMessaggi.appendText("Tentativo troppo BASSO\n");
    	}
    	else {
    		txtMessaggi.appendText("Tentativo troppo ALTO\n");
    	}
    	/*tentativiFatti++;
    	    	
    	// Controlla se ha indovinato
    	// -> fine partita
    	if(tentativo == segreto) {
    		
    		
    		
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
    		
    	}
    	else {
    		
    	}*/
    	
    	//Aggiornare interfaccia con n. tentativi rimasti
    		txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
    		
    	if(!model.isInGioco()) {
    		// la partita e' finita
    		// ho vinto o ho esaurito i tentatiti
    		if(risultato != 0 ) {
    			txtMessaggi.appendText("Hai perso!");
    			txtMessaggi.appendText(String.format("\n Il numero segreto era: %d", model.getSegreto()));
    			boxControlloPartita.setDisable(false);
        		boxControlloTentativi.setDisable(true);
    		}
    	}
    }

    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'IndovinaIlNumero.fxml'.";

    }
    

	public void setModel(NumeroModel model) {
		this.model = model;
	}
}
