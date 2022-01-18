package univpm.esame.progetto.statistiche;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;
import univpm.esame.progetto.filtri.Filtro;
import univpm.esame.progetto.filtri.FiltroRegione;
import univpm.esame.progetto.jsonObject.JSONObjectStatistiche.JSONObjectStatistiche;
import univpm.esame.progetto.model.LuogoEvento.StrumentiRegioni;
import univpm.esame.progetto.parsing.ReaderAPI;

/**
 * Classe implementata per calcolare le statistiche
 * 
 * @author Nicola Biagioli
 *
 */
public class Statistiche {

	private List<JSONObjectStatistiche> listaStatistiche = new Vector<>();
	private JSONObjectStatistiche statistiche;
	private static ReaderAPI reader = new ReaderAPI();
	private String[] regioni;
	private Filtro filtro;

	/**
	 * Costruttore di default
	 */
	public Statistiche() {

	}

	/**
	 * Costruttore di statistiche per un singolo paese
	 * 
	 * @param paese Paese di cui si vogliono calcolare le statistiche
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	public Statistiche(String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		this.impostaStatistiche(paese);
	}
	
	private void impostaStatistiche(String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		Statistiche.reader.getRaccolta().getEventi(paese).clear();
		if (this.èUnPaeseControllato(paese)) {
			Statistiche.reader.parser(paese);
			this.statistiche = new JSONObjectStatistiche();
			this.statistiche.setPaese(paese);
			this.statistiche.setRegioni(new StrumentiRegioni().tutteRegioni(paese));
			this.statistiche.setEventi(new NumTotEventi().getEventiTot(paese));
			this.statistiche.setSegmenti(new EventiPerSegmento().getEventiPerSegmento(paese));
			this.statistiche.setAnni(new NumEventiMensili().getEventiMensili(paese));
			this.listaStatistiche.add(statistiche);
		} 
	}
	
	/**
	 * Costruttore di statistiche per una o più regioni di un singolo paese
	 * 
	 * @param paese Il paese contenente la regione
	 * @param regioni La regione di cui si vogliono calcolare le statistiche
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 * @throws ExceptionRegioneInesistente  Eccezione lanciata nel momento in cui la regione immessa non coincide con le regioni di Polonia o Nuova Zelanda
	 */
	public Statistiche(String paese, String regioni) throws ExceptionPaeseInesistente, ExceptionRegioneInesistente, ExceptionListaVuota {
		this.impostaStatistiche(paese, regioni);
	}
	
	private void impostaStatistiche(String paese, String regioni) throws ExceptionRegioneInesistente, ExceptionPaeseInesistente, ExceptionListaVuota {
		Statistiche.reader.getRaccolta().getEventi(paese).clear();
		new StrumentiRegioni().controlloRegioni(paese, regioni);
		if (this.èUnPaeseControllato(paese)) {
			Statistiche.reader.parser(paese);
			this.statistiche = new JSONObjectStatistiche();
			this.statistiche.setPaese(paese);	
			this.statistiche.setRegioni(this.regioniDivise(regioni));
			this.statistiche.setEventi(new NumTotEventi().getEventiTot(paese, regioni));
			this.statistiche.setSegmenti(new EventiPerSegmento().getEventiPerSegmento(paese, regioni));
			this.statistiche.setAnni(new NumEventiMensili().getEventiMensili(paese, regioni));
			this.listaStatistiche.add(statistiche);
			
		}
	}
	
	
	private List<String> regioniDivise(String regioni) {
		List<String> temp = new Vector<>();
		String[] regione = new FiltroRegione().split(regioni);
		for(int i = 0; i < regione.length; i++) {
			temp.add(regione[i]);
		}
		return temp;
	}
	
	private boolean èUnPaeseControllato(String paese) throws ExceptionPaeseInesistente {
		if (paese.equalsIgnoreCase("Polonia") || paese.equalsIgnoreCase("NuovaZelanda")) {
			return true;
		}else {
			throw new ExceptionPaeseInesistente("Inserisci Polonia o NuovaZelanda come paese");
		}
	}
	
	/**
	 * Metodo implementato per inizializzare il confronto tra le statistiche relative alla Polonia e alla Nuova Zelanda
	 * 
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	public void confrontoStatistiche() throws ExceptionPaeseInesistente, ExceptionListaVuota{
		this.impostaStatistiche("Polonia");
		this.impostaStatistiche("NuovaZelanda");
	}
	
	protected void resetEventiFiltrati(String paese) throws ExceptionPaeseInesistente {
		this.setFiltro(new Filtro(paese, this.getReader().getRaccolta().getEventi(paese)));
	}
	
	protected void resetEventiFiltrati(String paese, String regione) throws ExceptionListaVuota, ExceptionPaeseInesistente {
		this.setFiltro(new Filtro(paese, this.getReader().getRaccolta().getEventi(paese)));
		this.getFiltro().filtraRegione(regione);
	}

	public List<JSONObjectStatistiche> getListaStatistiche() {
		return listaStatistiche;
	}

	public void setListaStatistiche(List<JSONObjectStatistiche> listaStatistiche) {
		this.listaStatistiche = listaStatistiche;
	}

	public JSONObjectStatistiche getStatistiche() {
		return statistiche;
	}

	public void setStatistiche(JSONObjectStatistiche statistiche) {
		this.statistiche = statistiche;
	}

	protected Filtro getFiltro() {
		return filtro;
	}

	protected void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public String[] getRegioni() {
		return regioni;
	}

	public void setRegioni(String[] regioni) {
		this.regioni = regioni;
	}

	public ReaderAPI getReader() {
		return reader;
	}
}
