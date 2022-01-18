package univpm.esame.progetto.statistiche;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;

/**
 * Classe implementata per calcolare il numero di eventi totale
 * 
 * @author Nicola Biagioli
 *
 */
public class NumTotEventi extends Statistiche {

	/**
	 * Imposta il numero di eventi totali di un paese
	 * 
	 * @param paese Il nome del paese del quale vogliamo conoscere gli eventi totali
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public int getEventiTot(String paese) throws ExceptionPaeseInesistente {
		return super.getReader().getRaccolta().getNumEventi(paese);
	}

	/**
	 * Imposta il numero di eventi totali di una regione di un determinato paese
	 * 
	 * @param paese Il nome del paese del quale vogliamo conoscere gli eventi totali
	 * @param regione Il nome della regione della quale vogliamo conoscere gli eventi totali
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	public int getEventiTot(String paese, String regione) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		super.resetEventiFiltrati(paese, regione);
		return super.getFiltro().getEventiFiltrati().size();
	}
}