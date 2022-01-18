package univpm.esame.progetto.jsonObject.JSONObjectStatistiche;

import java.util.List;
import java.util.Vector;

/**
 * Classe implementata per poter visualizzare un JSON contenente un anno, e i suoi relativi mesi
 * 
 * @author Nicola Biagioli
 *
 */
public class JSONObjectAnni {
	private String anno;
	private List<JSONObjectMese> mesi = new Vector<>();

	/**
	 * Costruttore di default
	 */
	public JSONObjectAnni() {

	}

	/**
	 * 
	 * @param anno Anno in cui avvengono determinati eventi
	 * @param mesiEventi Dati relativi di tutti i mesi dell'anno 
	 */
	public JSONObjectAnni(String anno, List<JSONObjectMese> mesiEventi) {
		this.anno = anno;
		this.mesi = mesiEventi;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public List<JSONObjectMese> getMesi() {
		return mesi;
	}

	public void addMesi(JSONObjectMese mesi) {
		this.mesi.add(mesi);
	}
}
