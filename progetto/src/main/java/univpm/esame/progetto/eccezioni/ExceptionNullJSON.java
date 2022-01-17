package univpm.esame.progetto.eccezioni;


/**
 * Eccezione lanciata nel momento in cui il Json letto è nullo
 * 
 * @author Simone Di Battista
 *
 */
@SuppressWarnings("serial")
public class ExceptionNullJSON extends Exception {

	/**
	 * Costruttore di default
	 */
	public ExceptionNullJSON() {
		super();
	}

	/**
	 * 
	 * @param msg  Variabile d'istanza dell'oggetto eccezione creato
	 */
	public ExceptionNullJSON(String msg) {
		super(msg);
	}
}
