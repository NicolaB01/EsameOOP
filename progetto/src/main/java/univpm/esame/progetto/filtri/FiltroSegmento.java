package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base al segmento
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroSegmento extends Filtro implements InterfacciaFiltri{

	private String[] segmento;

	/**
	 * Costruttore di default
	 */
	public FiltroSegmento() {
		super();
	}
	
	/**
	 * 
	 * @param segmenti I segmenti da filtrare scritti in CSV (separati da virgola)
	 */
	public FiltroSegmento(String segmenti) {
		this.segmento = this.split(segmenti);
	}

	/**
	 * Divide i segmenti passati se sono più di uno
	 * 
	 * @param segmenti I segmenti da filtrare scritti in CSV (separati da virgola)
	 * @return Un vettore di segmenti da filtrare 
	 */
	public String[] split(String segmenti) {
		if (segmenti != null && segmenti.length() != 0) {
			return segmenti.split(",");
		}
		return null;
	}


	/**
	 * Filtra gli eventi con i segmenti (segmenti)
	 */
	public void filtra() {
		if (this.segmento != null) {

			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {
				for (int i = 0; i < this.segmento.length; i++) {
					if (e.getClassificazione().getSegmento().equalsIgnoreCase(this.segmento[i].trim()))
						temp.add(e);
				}
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}