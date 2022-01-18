package univpm.esame.progetto.jsonObject.JSONObjectStatistiche;

import java.util.List;

/**
 * Classe implementata per poter visualizzare un JSON contenente un segmento e i suoi relativi dati 
 * 
 * @author Nicola Biagioli
 *
 */
public class JSONObjectSegmenti {
	private String segmento;
	private int eventi;
	private String percentuale;
	private List<String> generi;

	/**
	 * 
	 * @param segmento Nome segmento
	 * @param eventi Numero totale di eventi 
	 * @param percentuale Percentuale di avvenimento di quel segmento
	 * @param generi Lista di generi contenuti in quel segmento
	 */
	public JSONObjectSegmenti(String segmento, int eventi, String percentuale, List<String> generi) {
		this.setSegmento(segmento);
		this.setEventi(eventi);
		this.setPercentuale(percentuale);
		this.setGeneri(generi);
	}

	
	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public int getEventi() {
		return eventi;
	}

	public void setEventi(int eventi) {
		this.eventi = eventi;
	}

	public String getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(String percentuale) {
		this.percentuale = percentuale;
	}

	public List<String> getGeneri() {
		return generi;
	}

	public void setGeneri(List<String> generi) {
		this.generi = generi;
	}	
}
