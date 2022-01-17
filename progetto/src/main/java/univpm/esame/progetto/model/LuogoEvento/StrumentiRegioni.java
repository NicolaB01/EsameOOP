package univpm.esame.progetto.model.LuogoEvento;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;

/**
 * Classe di supporto per conversioni e calcoli sulle regioni
 * 
 * @author Nicola Biagioli
 *
 */
public class StrumentiRegioni {

	/**
	 * Costruttore di default
	 */
	public StrumentiRegioni() {
	}

	/**
	 * Controlla se la regione è presente nel paese passato come parametro 
	 * 
	 * @param paese Il nome del paese
	 * @param regione Il nome della regione
	 * @throws ExceptionRegioneInesistente Viene lanciata quando la regione passata come argomento non è presente fra le regioni del paese passato
	 * @throws ExceptionPaeseInesistente Viene lanciata quando il paese impostato non è Polonia o Nuova Zelanda
	 */
	public void controlloRegioni(String paese, String regione) throws ExceptionRegioneInesistente, ExceptionPaeseInesistente {
		if (this.controlloPaese(paese)) {
			this.cercaRegionePolonia(regione);
		}else {
			this.cercaRegioneNuovaZelanda(regione);
		}
	}

	private boolean cercaRegionePolonia(String regione) throws ExceptionRegioneInesistente {

		for (RegioniPolonia rP : RegioniPolonia.values()) {
			if (rP.name().equalsIgnoreCase(regione))
				return true;

		}
		throw new ExceptionRegioneInesistente("Regione inesistente");

	}

	private boolean cercaRegioneNuovaZelanda(String regione) throws ExceptionRegioneInesistente {
		for (RegioniNuovaZelanda rNZ : RegioniNuovaZelanda.values()) {
			if (rNZ.name().equalsIgnoreCase(regione))
				return true;
		}
		throw new ExceptionRegioneInesistente("Regione inesistente");
	}
	
	/**
	 * Restituisce tutte le regioni del paese inserito come parametro
	 * 
	 * @param paese Il nome del paese
	 * @return Restituisce tutte le regioni del paese inserito
	 * @throws ExceptionPaeseInesistente Viene lanciata quando il paese passato come parametro non è Polonia o Nuova Zelanda
	 */
	public List<String> tutteRegioni(String paese) throws ExceptionPaeseInesistente {
		if(this.controlloPaese(paese)) {
			return this.tutteRegioniPolonia();
		}else {
			return this.tutteRegioniNuovaZelanda();
		}
	}

	private List<String> tutteRegioniPolonia() {
		List<String> temp = new Vector<>();
		for (RegioniPolonia rP : RegioniPolonia.values()) {
			temp.add(rP.name());
		}
		return temp;
	}

	private List<String> tutteRegioniNuovaZelanda() {
		List<String> temp = new Vector<>();
		for (RegioniNuovaZelanda rNZ : RegioniNuovaZelanda.values()) {
			temp.add(rNZ.name());
		}
		return temp;
	}

	/**
	 * Converte il codice postale nel nome della regione corrispondente
	 * 
	 * @param postalCode Il codice postale da convertire
	 * @return Il nome della regione corrispondente al codice postale passato come parametro
	 */
	public String convertiPostalCodeInRegione(int postalCode) {

		for (RegioniNuovaZelanda rNZ : RegioniNuovaZelanda.values()) {
			if (rNZ.contains(postalCode))
				return rNZ.name();
		}
		return "nessuno";
	}

	/**
	 * Controlla se il paese inserito è la Polonia o la Nuova Zelanda
	 * 
	 * @return Restituisce true se è Polonia, false se è Nuova Zelanda
	 * @throws ExceptionPaeseInesistente Viene lanciata quando il paese impostato non è Polonia o Nuova Zelanda
	 */
	public Boolean controlloPaese(String paese) throws ExceptionPaeseInesistente {
		if (paese.equalsIgnoreCase("polonia")) {
			return true;
		} else if (paese.equalsIgnoreCase("nuovazelanda")) {
			return false;
		} else {
			throw new ExceptionPaeseInesistente("Paese non presente");
		}
	}

	/**
	 * Converte il countrycode passato come parametro nel nome effettivo della nazione
	 * 
	 * @param countryCode Il country code del paese
	 * @return Restituisce Il nome del paese corrispondente al country code
	 * @throws ExceptionPaeseInesistente Viene lanciata quando il paese impostato non è Polonia o Nuova Zelanda
	 */
	public String countryCodeToPaese(String countryCode) throws ExceptionPaeseInesistente {
		if(countryCode.equals("PL")) {
			return "Polonia";
		} else if(countryCode.equals("NZ")) {
			return "NuovaZelanda";
		}else {
			throw new ExceptionPaeseInesistente("Paese non presente");
		}

	}
}

