package univpm.esame.progetto.model.DataEvento;

/**
 * Enum che definisce i mesi dell'anno
 * 
 * @author Nicola Biagioli
 *
 */
public enum Mesi {
	Gennaio(31, "01"), Febbraio(28, "02"), Marzo(31, "03"), Aprile(30, "04"), Maggio(31, "05"), Giugno(30, "06"),
	Luglio(31, "07"), Agosto(31, "08"), Settembre(30, "09"), Ottobre(10, "10"), Novembre(11, "11"),
	Dicembre(31, "12");

	private String mese;
	private int giorni;

	/**
	 * Costruttore di Mesi
	 * 
	 * @param giorno Il numero di giorni del mese
	 * @param intMese Il numero che identifica il mese(es. 01 = Gennaio)
	 */
	Mesi(int giorno, String intMese) {
		this.giorni = giorno;
		this.mese = intMese;
	}

	/**
	 * 
	 * @return Il numero che indica il mese
	 */
	public String getMese() {
		return mese;
	}

	/**
	 * 
	 * @return Il numero dei giorni di un mese
	 */
	public int getGiorni() {
		return giorni;
	}
}
