package it.polito.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel(){
		inGioco = false;		
	}
	
	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int)(Math.random()*NMAX)+1;
    	this.tentativiFatti = 0;
	}
	
	
	/**
	 * Metodo per effettuare un tentativo
	 * @param t il tentativo 
	 * @return 1 se il tentativo e' troppo alto, -1 se e' troppo basso, 0 se l'utente ha indovinato
	 */
	public int tentativo(int t) {
		
		// controllo se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' terminata");
		}
		
		// controllo se l'input e' corretto
		// se e' un intero va controllato nel controller, qui 
		// nel model non ne ho bisogno perche' il metodo tentativo(int t)
		//ha bisogno che gli si passi un intero
		
		
		// controllo se l' input e' nel range corretto
		 if(!tentativoValido(t)){
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d", 1, NMAX));
			// uso String.format per concatenare le stringhe con %d, potevo scrivere direttamente
			// e concatenare chiudendo le " e mettento +
		}
		 
		 //gestisci il tentativo
		 this.tentativiFatti++;
		 if(this.tentativiFatti == this.TMAX) {
			 // la partita e' finita perche' ho esaurito i tentativi
			 this.inGioco = false;
		 }
		 if(t == this.segreto) {
			 //ho indovinato
			 this.inGioco = false;
			 return 0;
		 }
		 if(t>this.segreto) {
			 return 1;
			 //ho deciso che 1 per troppo alto
		 }
		
		 return -1;
			
	}
	
	// metto il controllo sul tentativo valido in un metodo
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false;
		}
		else {
			return true;
		}
	}

	
	public int getSegreto() {
		return segreto;
	}

	
	public int getTentativiFatti() {
		return tentativiFatti;
	}

	
	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	

}

