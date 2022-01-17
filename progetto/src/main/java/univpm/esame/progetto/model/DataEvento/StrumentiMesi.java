package univpm.esame.progetto.model.DataEvento;

/**
 * Classe di supposto per i mesi, effettua operazioni di conversione
 * 
 * @author Nicola Biagioli
 *
 */
public class StrumentiMesi {
	
	/**
	 * Converte il numero del mese nel nome effettivo del mese
	 *
	 * @param intMese il numero che corrisponde ad un mese
	 * @return Il nome del mese corrispondente
	 */
	public String intToMesi(String intMese) {

		for (Mesi m : Mesi.values()) {

			if (m.getMese().equals(intMese))
				return m.name();

		}
		return null;
	}

	/**
	 * Ridà il numero di giorni del mese passato col suo numero
	 * 
	 * @param intMese il numero che corrisponde ad un mese
	 * @return Il numero di giorni del mese corrispondente
	 */
	public int giorniMensili(String intMese) {

		for (Mesi m : Mesi.values()) {

			if (m.name().equals(intMese))
				return m.getGiorni();
		}

		return 0;

	}
}