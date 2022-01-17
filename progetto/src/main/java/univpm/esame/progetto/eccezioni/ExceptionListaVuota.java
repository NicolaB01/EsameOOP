package univpm.esame.progetto.eccezioni;


/**
 *  Eccezione lanciata se vengono applicati troppi filtri
 *
 * @author Simone Di Battista
 * 
 */
@SuppressWarnings("serial")
public class ExceptionListaVuota extends Exception {

	/**
	 * Costruttore di default
	 */
	public ExceptionListaVuota() {
		super();
	}

	/**
	 * 
	 * @param msg Variabile d'istanza dell'oggetto eccezione creato
	 */
	public ExceptionListaVuota(String msg) {
		super(msg);
	}
}