package univpm.esame.progetto.eccezioni;


/**
 * Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
 * 
 * @author Simone Di Battista
 *
 */
@SuppressWarnings("serial")
public class ExceptionPaeseInesistente extends Exception {

	/**
	 * Costruttore di default
	 */
	public ExceptionPaeseInesistente() {
		super();
	}

	/**
	 * 
	 * @param msg  Variabile d'istanza dell'oggetto eccezione creato
	 */
	public ExceptionPaeseInesistente(String msg) {
		super(msg);
	}
}
