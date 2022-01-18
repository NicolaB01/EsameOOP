package univpm.esame.progetto.statistiche;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.jsonObject.JSONObjectStatistiche.JSONObjectSegmenti;
import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per calcolare i dati di tutti i segmenti 
 * 
 * @author Nicola Biagioli
 *
 */
public class EventiPerSegmento extends Statistiche {
	private HashMap<String, Integer> segmentoEventi = new HashMap<>();
	private List<JSONObjectSegmenti> temp = new Vector<>();
	private String percentuale;

	/**
	 * Suddivide gli eventi di un paese in segmenti con i suoi relativi dati
	 * 
	 * @param paese Il nome del paese del quale vogliamo sapere gli eventi per segmento
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */
	public List<JSONObjectSegmenti> getEventiPerSegmento(String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		for (Evento e : super.getReader().getRaccolta().getEventi(paese)) {
			this.estraiSegmenti(e);
		}
		this.estraiGeneri(paese);
		return this.temp;
	}

	/**
	 * Suddivide gli eventi di una regione di un paese in segmenti con i suoi relativi dati
	 * 
	 * @param paese Il nome del paese
	 * @param regione Il nome della regione della quale vogliamo sapere gli eventi per segmento
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */ List<JSONObjectSegmenti> getEventiPerSegmento(String paese, String regione) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		this.resetEventiFiltrati(paese, regione);

		for (Evento e : super.getFiltro().getEventiFiltrati()) {
			this.estraiSegmenti(e);
		}
		this.estraiGeneri(paese, regione);
		return temp;
	}

	private void estraiSegmenti(Evento e) {
		if (segmentoEventi.keySet().contains(e.getClassificazione().getSegmento())) {
			int i = segmentoEventi.get(e.getClassificazione().getSegmento());
			segmentoEventi.replace(e.getClassificazione().getSegmento(), ++i);
		} else {
			segmentoEventi.put(e.getClassificazione().getSegmento(), 1);
		}
	}

	private void estraiGeneri(String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		for (String key : segmentoEventi.keySet()) {
			List <String> generi = new Vector<>();
			this.resetEventiFiltrati(paese);
			super.getFiltro().filtraSegmento(key);
			this.aggiungiGeneri(generi);
			this.resetEventiFiltrati(paese);
			this.calcolaPercentualeSegmento(paese, key);
			this.temp.add(new JSONObjectSegmenti(key, this.segmentoEventi.get(key), this.percentuale, generi));
		}
	}

	private void estraiGeneri(String paese, String regione) throws ExceptionListaVuota, ExceptionPaeseInesistente {
		for (String key : segmentoEventi.keySet()) {
			List <String> generi = new Vector<>();
			this.resetEventiFiltrati(paese, regione);
			super.getFiltro().filtraSegmento(key);
			this.aggiungiGeneri(generi);
			this.resetEventiFiltrati(paese, regione);
			this.calcolaPercentuale(key);
			this.temp.add(new JSONObjectSegmenti(key, this.segmentoEventi.get(key), this.percentuale, generi));
		}
	}

	private void aggiungiGeneri(List<String> generi) {
		for (Evento e : super.getFiltro().getEventiFiltrati()) {
			if(!generi.contains(e.getClassificazione().getGenere()))
				generi.add(e.getClassificazione().getGenere());
		}
	}
	
	private void calcolaPercentuale(String key) {
		this.percentuale = new DecimalFormat("#%")
				.format((double) segmentoEventi.get(key) / super.getFiltro().getEventiFiltrati().size());	
	}

	private void calcolaPercentualeSegmento(String paese, String key) throws ExceptionPaeseInesistente {
		this.percentuale = new DecimalFormat("#%")
				.format((double) segmentoEventi.get(key) / super.getReader().getRaccolta().getNumEventi(paese));
	}
}