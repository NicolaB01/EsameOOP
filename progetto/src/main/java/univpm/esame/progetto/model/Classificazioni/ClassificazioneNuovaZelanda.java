package univpm.esame.progetto.model.Classificazioni;

/**
 * Classe che definisce in modo specifico la classificazione della Nuova Zelanda, estendendo la classificazione generale
 * 
 * @author Simone Di Battista
 *
 */
public class ClassificazioneNuovaZelanda extends ClassificazioneGenerale {

	private String tipo;
	private String sottoTipo;

	/**
	 * Costruttore di default
	 */
	public ClassificazioneNuovaZelanda() {
		this("", "", "", "", "");
	}

	/**
	 * Costruttore della classificazione per la Nuova Zelanda
	 * 
	 * @param segmento Segmenti degli eventi della NuovaZelanda  
	 * @param genere Generi degli eventi della NuovaZelanda  
	 * @param sottoGenere SottoGeneri degli eventi della NuovaZelanda  
	 * @param tipo Tipi degli eventi della NuovaZelanda  
	 * @param sottoTipo SottoTipi degli eventi della NuovaZelanda  
	 */
	public ClassificazioneNuovaZelanda(String segmento, String genere, String sottoGenere, String tipo, String sottoTipo) {
		super(segmento, genere, sottoGenere);
		this.tipo = tipo;
		this.sottoTipo = sottoTipo;
	}

	/**
	 * 
	 * @return Il tipo per la classificazione della Nuova Zelanda
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo Imposta il tipo per la classificazione della Nuova Zelanda

	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return Il sotto tipo per la classificazione della Nuova Zelanda
	 */
	public String getSottoTipo() {
		return sottoTipo;
	}

	/**
	 * 
	 * @param sottoTipo Imposta il sotto tipo per la classificazione della Nuova Zelanda

	 */
	public void setSottoTipo(String sottoTipo) {
		this.sottoTipo = sottoTipo;
	}

}
