package univpm.esame.progetto.jsonObject.JSONObjectStatistiche;

import java.util.List;
import java.util.Vector;

/**
 * Classe implementata per poter visualizzare un JSON contenente le statistiche calcolate per un paese o per una sua regione
 * 
 * @author Nicola Biagioli
 *
 */
public class JSONObjectStatistiche {

	private String paese;
	private List<String> regioni = new Vector<>();
	private int eventi;
	private List<JSONObjectSegmenti> segmenti = new Vector<>();
	private List<JSONObjectAnni> anni = new Vector<>();

	/**
	 * Costruttore di default
	 */
	public JSONObjectStatistiche() {
		super();
	}

	/**
	 * 
	 * @param paese Paese di cui sono state calcolate le statistiche 
	 * @param regioni Lista di regioni di cui sono state calcolate le statistiche
	 * @param eventi Numero di eventi totale ottenuto in base alla rotta chiamata
	 * @param segmenti Lista di segmenti contenente i loro relativi dati 
	 * @param anni Lista di anni contenente i loro relativi dati
	 */
	public JSONObjectStatistiche(String paese, List<String> regioni, int eventi, List<JSONObjectSegmenti> segmenti, List<JSONObjectAnni> anni) {
		this.setPaese(paese);
		this.setRegioni(regioni);
		this.setEventi(eventi);
		this.setSegmenti(segmenti);
		this.setAnni(anni);
	}

	public void setSegmenti(List<JSONObjectSegmenti> segmenti) {
		this.segmenti = segmenti;
	}

	public void setAnni(List<JSONObjectAnni> anni) {
		this.anni = anni;
	}

	public List<JSONObjectSegmenti> getSegmenti() {
		return this.segmenti;
	}

	public void addSegmenti(JSONObjectSegmenti segmento) {
		this.segmenti.add(segmento);
	}

	public List<JSONObjectAnni> getAnni() {
		return anni;
	}

	public void addAnni(JSONObjectAnni anno) {
		this.anni.add(anno);
	}

	public int getEventi() {
		return eventi;
	}

	public void setEventi(int eventi) {
		this.eventi = eventi;
	}

	public List<String> getRegioni() {
		return regioni;
	}

	public void setRegioni(List<String> regioni) {
		this.regioni = regioni;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}
}
