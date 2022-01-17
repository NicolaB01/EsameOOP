package univpm.esame.progetto.model.Classificazioni;

/**
 * Classe che definisce una classificazione di base 
 * 
 * @author Simone Di Battista
 *
 */
public abstract class ClassificazioneGenerale {

	private String segmento;
	private String genere;
	private String sottoGenere;

	/**
	 * Costruttore di defualt
	 */
	public ClassificazioneGenerale() {
		this("", "", "");
	}

	/**
	 * Costruttore di una classificazione generele indipendente dal paese
	 * 
	 * @param segmento Segmenti degli eventi 
	 * @param genere Genere degli eventi 
	 * @param sottoGenere Sottogenere degli eventi della polonia
	 */
	public ClassificazioneGenerale(String segmento, String genere, String sottoGenere) {
		this.segmento = segmento;
		this.genere = genere;
		this.sottoGenere = sottoGenere;
	}

	/**
	 * 
	 * @return Il segmento della classificazione generale
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * 
	 * @param segmento Imposta il segmento della classificazione generale

	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * 
	 * @return Il genere della classificazione generale
	 */ 
	public String getGenere() {
		return genere;
	}

	/**
	 * 
	 * @param genere Imposta il genere della classificazione generale

	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * 
	 * @return Il sottogenere della classificazione generale
	 */
	public String getSottoGenere() {
		return sottoGenere;
	}

	/**
	 * 
	 * @param sottoGenere Imposta il sottogenere della classificazione generale

	 */
	public void setSottoGenere(String sottoGenere) {
		this.sottoGenere = sottoGenere;
	}
}