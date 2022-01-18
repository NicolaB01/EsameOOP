package univpm.esame.progetto.filtri;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.jsonObject.JSONObjectFiltri.JSONObjectConfronto;
import univpm.esame.progetto.jsonObject.JSONObjectFiltri.JSONObjectEventi;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.parsing.ReaderAPI;

/**
 * Classe implementata :
 * Per effettuare un confronto tra gli eventi programmati in Polonia e Nuova Zelanda
 * Per restituire una lista di eventi filtrate in base a dei parametri 
 * 
 * @author Simone Di Battista
 *
 */
public class Filtro {

	private String paese;
	private JSONObjectEventi jsonEventi = new JSONObjectEventi();
	private static List <Evento> eventiFiltrati = new Vector<>();
	private JSONObjectConfronto[] confronti = this.jsonEventi.getConfronto();
	private ReaderAPI reader = new ReaderAPI();

	/**
	 * Costruttore di default
	 */
	public Filtro() {
	}

	/**
	 * 
	 * @param paese Il paese da settare
	 */
	public Filtro(String paese) {
		this.paese = paese;
	}

	/**
	 * Costruttore che imposta il paese e gli eventi da filtrare
	 * 
	 * @param paese Il paese da settare 
	 * @param eventiDaFiltrare Eventi da filtrare
	 */
	public Filtro(String paese, List<Evento> eventiDaFiltrare) {
		this.paese = paese;
		Filtro.eventiFiltrati = eventiDaFiltrare; 

	}
	
	/**
     * Metodo implementato per inizializzare gli eventi filtrati 
     * con tutti gli eventi programmati per il paese 
     * 
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public void inizializzaEventiFiltrati() throws ExceptionPaeseInesistente {
		Filtro.eventiFiltrati.clear();
		this.reader.parser(this.paese);
		this.setEventiFiltrati(this.reader.getRaccolta().getEventi(this.paese));

	}

	/**
	 * Metodo implementato per realizzare il confronto tra gli eventi filtrati per genere e regione
	 * 
	 * @param genere I generi da filtrare scritte in CSV (separati da virgola)
	 * @param regione Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	public void inizializzaEventiFiltrati(String genere, String regione) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		Filtro.eventiFiltrati.clear();

		this.paese = "Polonia";
		this.setNumEventiConfronto(0, genere , regione);
		this.setConfrontoGeneriRegioni(0);

		this.paese = "NuovaZelanda";
		this.setNumEventiConfronto(1, genere , regione);
		this.setConfrontoGeneriRegioni(1);
		 if       (this.confronti[0].getRegioni().size() == 0 
				&& this.confronti[0].getGeneri().size()  == 0
				&& this.confronti[1].getRegioni().size() == 0 
				&& this.confronti[1].getGeneri().size()  == 0 )
			throw new ExceptionListaVuota("Le liste sono vuote, utilizza parametri opportuni");
	}

	private void setNumEventiConfronto(int indice,String genere,String regione) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		this.reader.parser(this.paese);
		confronti[indice].setEventiTot(this.reader.getRaccolta().getNumEventi(this.paese));
		this.setEventiFiltrati(this.reader.getRaccolta().getEventi(this.paese));
		this.filtraRegione(regione);
		this.filtraGenere(genere);
		confronti[indice].setEventiFiltrati(Filtro.eventiFiltrati.size());
	}
	
	private void setConfrontoGeneriRegioni(int indice){

		this.confronti[indice].setRegioni(new HashMap<>());
		this.confronti[indice].setGeneri(new HashMap<>());
		for(Evento e: Filtro.eventiFiltrati) {

			if (confronti[indice].getGeneri().keySet().contains(e.getClassificazione().getGenere())) {
				int i = confronti[indice].getGeneri().get(e.getClassificazione().getGenere());
				confronti[indice].getGeneri().replace(e.getClassificazione().getGenere(), ++i);
			} else {
				confronti[indice].getGeneri().put(e.getClassificazione().getGenere(), 1);
			}
			if (!e.getLuogo().getRegione().equals("null")) {
				if (confronti[indice].getRegioni().keySet().contains(e.getLuogo().getRegione())) {
					int i = confronti[indice].getRegioni().get(e.getLuogo().getRegione());
					confronti[indice].getRegioni().replace(e.getLuogo().getRegione(), ++i);
				} else {
					confronti[indice].getRegioni().put(e.getLuogo().getRegione(), 1);
				}
			}
		}
		

	}
	/**
	 * Metodo implementato per filtrare gli eventi secondo i parametri 
	 * 
	 * @param regione Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @param segmento I segmenti da filtrare scritti in CSV (separati da virgola)
	 * @param anno Gli anni da filtrare scritti in CSV (separati da virgola)
	 * @param mese I mesi da filtrare scritti in CSV (separati da virgola)
	 * @param giorno I giorni da filtrare scritti in CSV (separati da virgola)
	 * @param genere I generi da filtrare scritti in CSV (separati da virgola)
	 * @param size Il numero di eventi che si vuole avere
	 * @throws ExceptionListaVuota Viene lanciata se vengono effetuati troppi filtri
	 */
	public void filtraggioComplessivo(String regione, String segmento, String anno, String mese, String giorno, String genere, String size)
			throws ExceptionListaVuota {

		new FiltroRegione(regione).filtra();
		new FiltroSegmento(segmento).filtra();
		new FiltroAnno(anno).filtra();
		new FiltroMese(mese).filtra();
		new FiltroGiorno(giorno).filtra();
		new FiltroGenere(genere).filtra();
		new FiltroSize(size).filtra();

		if (this.getEventiFiltrati().size() == 0)
			throw new ExceptionListaVuota("Utilizza solamente parametri corretti");
	}

	/**
	 * Metodo implementato per filtrare per anno
	 * 
	 * @param anno Gli anni da filtrare scritti in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraAnno(String anno) throws ExceptionListaVuota {
		new FiltroAnno(anno).filtra();
	}

	/**
	 * Metodo implementato per filtrare per mese
	 * 
	 * @param mese I mesi da filtrare scritti in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraMese(String mese) throws ExceptionListaVuota {
		new FiltroMese(mese).filtra();
	}

	/**
	 * Metodo implementato per filtrare per regione
	 * 
	 * @param regione Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraRegione(String regione) throws ExceptionListaVuota {
		new FiltroRegione(regione).filtra();
	}

	/**
	 * Metodo implementato per filtrare per segmento
	 * 
	 * @param segmento I segmenti da filtrare scritti in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraSegmento(String segmento) throws ExceptionListaVuota {
		new FiltroSegmento(segmento).filtra();
	}

	/**
	 * Metodo implementato per filtrare per giorno
	 * 
	 * @param giorno I giorni da filtrare scritti in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraGiorno(String giorno) throws ExceptionListaVuota {
		new FiltroGiorno(giorno).filtra();
	}

	/**
	 * Metodo implementato per filtrare per genere
	 * 
	 * @param genere I generi da filtrare scritti in CSV (separati da virgola)
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraGenere(String genere) throws ExceptionListaVuota {
		new FiltroGenere(genere).filtra();
	}

	/**
	 * Metodo implementato per filtrare per size
	 * 
	 * @param size Il numero di eventi che si vuole avere
	 * @throws ExceptionListaVuota Viene lanciata se la lista è vuota
	 */
	public void filtraSize(String size) throws ExceptionListaVuota {
		new FiltroSize(size).filtra();
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public List<Evento> getEventiFiltrati() {
		return eventiFiltrati;
	}

	public void setEventiFiltrati(List<Evento> eventiFiltrati) {
		Filtro.eventiFiltrati = eventiFiltrati;
	}

	public void addEventiFiltrati(Evento evento) {
		Filtro.eventiFiltrati.add(evento);
	}

	
	public JSONObjectEventi getJsonEventi() {
		return jsonEventi;
	}

	public void setJsonEventi(JSONObjectEventi jsonEventi) {
		this.jsonEventi = jsonEventi;
	}

}
