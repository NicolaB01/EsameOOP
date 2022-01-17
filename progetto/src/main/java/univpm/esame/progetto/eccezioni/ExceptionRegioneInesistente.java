package univpm.esame.progetto.eccezioni;

/**
 * Eccezione lanciata nel momento in cui la regione immessa non coincide con le regioni di Polonia o  
 * Nuova Zelanda
 * 
 * @author Simone Di Battista
 *
 */
@SuppressWarnings("serial")
public class ExceptionRegioneInesistente extends Exception {

	/**
	 * Costruttore di default
	 */
	public ExceptionRegioneInesistente() {
		super();
	}

	/**
	 * 
	 * @param msg  Variabile d'istanza dell'oggetto eccezione creato
	 */
	public ExceptionRegioneInesistente(String msg) {
		super(msg);
	}
}