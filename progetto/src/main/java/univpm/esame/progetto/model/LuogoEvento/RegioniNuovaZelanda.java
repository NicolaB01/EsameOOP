package univpm.esame.progetto.model.LuogoEvento;

import java.util.ArrayList;

/**
 * Enum che descrive le regioni della Nuova Zelanda 
 * 
 * @author Nicola Biagioli
 *
 */
public enum RegioniNuovaZelanda {

	Northland(0101, 594), 
	Auckland(0600, 2681), 
	BayofPlenty(3001, 3198), 
	Waikato(3200, 3988), 
	Gisborne(4010, 4094),
	HawkesBay(4112, 4293), 
	Taranaki(4310, 4399, 4588, 4685), 
	ManawatuWanganui(4410, 6015),
	Wellington(5010, 6972),
	Tasman(7020, 7025, 7048, 7198),
	Nelson(7040, 7049), 
	Marlborough(7145, 7285), 
	Canterbury(7300, 7791, 7901, 8972),
	WestCoast(7810, 7895), 
	Otago(9001, 9598), 
	Southland(9600, 9893);

	private ArrayList<Integer> inizio = new ArrayList<>();
	private ArrayList<Integer> fine = new ArrayList<>();

	/**
	 * Inizializza le regioni con l'intervallo passato
	 * 
	 * @param intervallo I valori di inizio e di fine dei codici postali
	 */
	RegioniNuovaZelanda(int... intervallo) {
		for (int i = 0; i < intervallo.length - 1; i += 2) {
			this.setInizio(intervallo[i]);
			this.setFine(intervallo[i + 1]);
		}
	}

	private void setInizio(int inizio) {
		this.inizio.add(inizio);
	}

	private void setFine(int fine) {
		this.fine.add(fine);
	}

	/**
	 * Verifica se un codice postale è presente nell'intervallo di una regione 
	 * 
	 * @param postalCode Il codice postale
	 * @return Restituisce true se il postalCode è presente nell'intervallo, altrimenti restituisce false
	 */
	public boolean contains(int postalCode) {
		for (int i = 0; i < inizio.size(); i++) {
			if (inizio.get(i) <= postalCode && fine.get(i) >= postalCode)
				return true;
		}
		return false;
	}
}

