package univpm.esame.progetto.model.LuogoEvento;

/**
 * Classe che definisce il luogo in cui è avvenuto un evento
 *
 * @author Simone Di Battista
 *
 */
public class Luogo {
	private String nazione;
	private String regione;
	private String longitudine;
	private String latitudine;

	/**
	 * Costruttore di default
	 */
	public Luogo() {
		this("", "", "", "");
	}

	/**
	 * Costruttore che inizializza le variabili d'istanza di un oggetto Luogo
	 * 
	 * @param regione La regione dell'evento 
	 * @param nazione La nazione dell'evento
	 * @param longitudine La longitudine in cui avviene l'evento
	 * @param latitudine La latitudine in cui avviene l'evento
	 * 
	 */
	public Luogo(String nazione, String regione, String longitudine, String latitudine) {

		this.nazione = nazione;
		this.regione = regione;
		this.longitudine = longitudine;

		this.latitudine = latitudine;
	}

	/**
	 * 
	 * @return La nazione in cui avviene l'evento
	 */
	public String getNazione() {
		return nazione;
	}

	/**
	 * 
	 * @param nazione Imposta la nazione in cui avviene l'evento
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	/**
	 * 
	 * @return La regione in cui avviene l'evento
	 */
	public String getRegione() {
		return regione;
	}

	/**
	 * 
	 * @param regione Imposta la regione in cui avviene l'evento
	 */
	public void setRegione(String regione) {
		this.regione = regione;
	}

	/**
	 * 
	 * @return La latitudine in cui avviene l'evento
	 */
	public String getLatitudine() {
		return latitudine;
	}

	/**
	 * 
	 * @param latitudine Imposta la latitudine in cui avviene l'evento
	 */
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	/**
	 * 
	 * @return La longitudine in cui avviene l'evento
	 */
	public String getLongitudine() {
		return longitudine;
	}

	/**
	 * 
	 * @param longitudine Imposta la longitudine in cui avviene l'evento
	 */
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
}
