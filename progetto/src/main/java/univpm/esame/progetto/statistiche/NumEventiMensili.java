package univpm.esame.progetto.statistiche;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.jsonObject.JSONObjectStatistiche.JSONObjectAnni;
import univpm.esame.progetto.jsonObject.JSONObjectStatistiche.JSONObjectMese;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.model.DataEvento.StrumentiMesi;

/**
 * Classe implementata per calcolare i dati di tutti gli anni
 * @author Nicola Biagioli
 *
 */
public class NumEventiMensili extends Statistiche {
	private StrumentiMesi convertitoreMese = new StrumentiMesi();
	private List<JSONObjectAnni> temp = new Vector<>();
	
	/**
	 * Suddivide gli eventi di un paese in anni con i suoi relativi dati
	 * 
	 * @param paese Il nome del paese del quale vogliamo sapere gli eventi per mese
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */
	public List<JSONObjectAnni> getEventiMensili(String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		List<String> anni = new Vector<>();
		this.estraiAnni(paese, anni);
		this.setAnnoMesiEventi(paese, anni);
		return this.temp;
	}
	
	/**
	 * Suddivide gli eventi di una regione di un paese in anni con i suoi relativi dati
	 * 
	 * @param paese Il nome del paese
	 * @param regione Il nome della regione della quale vogliamo sapere gli eventi per mese
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */
	public List<JSONObjectAnni> getEventiMensili(String paese, String regione) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		
		List<String> anni = new Vector<>();
		this.estraiAnni(paese, regione, anni);
		this.setAnnoMesiEventi(paese, regione, anni);
		return temp;
	}
	
	private void setAnnoMesiEventi(String paese, List<String> anni) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		for (String anno : anni) {
			super.resetEventiFiltrati(paese);
			super.getFiltro().filtraAnno(anno);

			List<String> mesi = new Vector<>();
			this.estraiMesi(mesi);
			
			JSONObjectAnni jsonObjectAnno = new JSONObjectAnni();
			jsonObjectAnno.setAnno(anno);
			
			for (String mese : mesi) {
				super.resetEventiFiltrati(paese);
				this.calcoloMaxMin(mese, anno, jsonObjectAnno);	
			}
			this.temp.add(jsonObjectAnno);
		}
	}
	
	
	
	private void setAnnoMesiEventi(String paese, String regione, List<String> anni) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		for (String anno : anni) {
			super.resetEventiFiltrati(paese, regione);
			super.getFiltro().filtraAnno(anno);

			List<String> mesi = new Vector<>();
			this.estraiMesi(mesi);
			
			JSONObjectAnni jsonObjectAnno = new JSONObjectAnni();
			jsonObjectAnno.setAnno(anno);
			
			for (String mese : mesi) {
				super.resetEventiFiltrati(paese, regione);
				this.calcoloMaxMin(mese, anno, jsonObjectAnno);
			}
			temp.add(jsonObjectAnno);
		}
	}
	
	private void calcoloMaxMin(String mese, String anno, JSONObjectAnni jsonObjectAnno) throws ExceptionListaVuota {
		super.getFiltro().filtraAnno(anno);
		super.getFiltro().filtraMese(mese);
		int tot = 0;
		HashMap<String, Integer> meseEventi = new HashMap<>();
		for (Evento e : super.getFiltro().getEventiFiltrati()) {
			tot++;
			if (meseEventi.keySet().contains(e.getData().getGiorno())) {
				int i = meseEventi.get(e.getData().getGiorno());
				meseEventi.replace(e.getData().getGiorno(), ++i);
			} else {
				meseEventi.put(e.getData().getGiorno(), 1);
			}
		}
		int max = Collections.max(meseEventi.values());
		int min = Collections.min(meseEventi.values());
		
		jsonObjectAnno.addMesi(new JSONObjectMese(mese, tot, max, min, new DecimalFormat("##.##").format(this.calcolaMedia(tot, mese))));
	}
	
	private double calcolaMedia(int tot, String mese) {
		return (double) tot / convertitoreMese.giorniMensili(mese);
	}
	
	private void estraiAnni(String paese, List<String> anni) throws ExceptionPaeseInesistente {
		for (Evento e : super.getReader().getRaccolta().getEventi(paese)) {
			if (!anni.contains(e.getData().getAnno())) {
				anni.add(e.getData().getAnno());
			}
		}
	}
	
	private void estraiAnni(String paese, String regione, List<String> anni) throws ExceptionListaVuota, ExceptionPaeseInesistente {
		super.resetEventiFiltrati(paese, regione);
		for (Evento e : super.getFiltro().getEventiFiltrati()) {
			if (!anni.contains(e.getData().getAnno())) {
				anni.add(e.getData().getAnno());
			}
		}
	}
	
	private void estraiMesi(List<String> mesi) throws ExceptionPaeseInesistente {
		for (Evento e : super.getFiltro().getEventiFiltrati()) {
			if (!mesi.contains(e.getData().getMese())) {
				mesi.add(e.getData().getMese());
			}
		}
	}
	
}