package univpm.esame.progetto.jsonObject.JSONObjectStatistiche;

/**
 * Classe implementata per poter visualizzare un JSON contenente il nome di un mese e i suoi relativi dati 
 * 
 * @author Nicola Biagioli
 *
 */
public class JSONObjectMese {
	private String mese;
	private int eventi;
	private int max;
	private int min;
	private String media;

	/**
	 * 
	 * @param mese Mese in cui avvengono gli eventi
	 * @param tot Totale degli eventi in quel mese 
	 * @param max Il massimo numero di eventi in uno dei giorni del mese in cui avvengono eventi
	 * @param min Il minimo numero di eventi in uno dei giorni del mese in cui avvengono eventi
	 * @param media La media calcolata dividendo il numero totale di eventi in quel mese con il  numero di giorni totali del mese
	 */
	public JSONObjectMese(String mese, int tot, int max, int min, String media) {
		this.mese = mese;
		this.eventi = tot;
		this.max = max;
		this.min = min;
		this.media = media;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public int getEventi() {
		return eventi;
	}

	public void setEventi(int eventi) {
		this.eventi = eventi;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
}
