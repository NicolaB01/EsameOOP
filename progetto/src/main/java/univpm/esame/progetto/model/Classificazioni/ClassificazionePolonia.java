package univpm.esame.progetto.model.Classificazioni;


/**
 * Classe che definisce in modo specifico la classificazione della Polonia, estendendo la classificazione generale
 * 
 * @author Simone Di Battista
 *
 */
public class ClassificazionePolonia extends ClassificazioneGenerale {

	/**
	 * Costruttore di default
	 */
	public ClassificazionePolonia() {
		this("", "", "");
	}

	/**
	 * Costruttore per la classificazione della Polonia
	 * 
	 * @param segmento Segmenti degli eventi della polonia
	 * @param genere Generi degli eventi della polonia
	 * @param sottoGenere Sottogeneri degli eventi della polonia
	 */
	public ClassificazionePolonia(String segmento, String genere, String sottoGenere) {
		super(segmento, genere, sottoGenere);

	}
}