package univpm.esame.progetto.model.DataEvento;

/**
 * Classe rappresenta la data dell'evento
 * 
 * @author Nicola Biagioli
 *
 */
public class Data {

	private StrumentiMesi convertitore = new StrumentiMesi();
	private String giorno;
	private String mese;
	private String anno;


	/**
	 * Costruttore di default
	 */
	public Data() {
	}

	/**
	 * Costruttore di una data 
	 * 
	 * @param data La data in cui avviene un evento nel formato(YYYY-MM-DD)
	 */
	public Data(String data) {
		String[] elementiData = data.split("-");
		this.anno = elementiData[0];
		this.mese = convertitore.intToMesi(elementiData[1]);
		this.giorno = elementiData[2];
	}

	/**
	 * 
	 * @return Il giorno in cui avviene l'evento
	 */
	public String getGiorno() {
		return giorno;
	}

	/**
	 * 
	 * @param giorno Imposta il giorno in cui avviene l'evento
	 */
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	/**
	 * 
	 * @return Il mese in cui avviene l'evento
	 */
	public String getMese() {
		return mese;
	}

	/**
	 * 
	 * @param mese Imposta mese in cui avviene l'evento
	 */
	public void setMese(String mese) {
		this.mese = mese;
	}

	/**
	 * 
	 * @return L'anno in cui avviene l'evento
	 */
	public String getAnno() {
		return anno;
	}

	/**
	 * 
	 * @param anno Imposta l'anno in cui avviene l'evento
	 */
	public void setAnno(String anno) {
		this.anno = anno;
	}
}