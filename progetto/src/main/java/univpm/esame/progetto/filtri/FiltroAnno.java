package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;
import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base agli anni
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroAnno extends Filtro implements InterfacciaFiltri{

	private String[] anno;

	/**
	 * Costruttore di default
	 */
	public FiltroAnno() {
		super();

	}
	

	/**
	 * 
	 * @param anni Gli anni da filtrare scritti in CSV (separati da virgola)
	 */
	public FiltroAnno(String anni) {
		this.anno = this.split(anni);
	}

	/**
	 * Divide gli anni passati se sono più di uno
	 * 
	 * @param anni Gli anni da filtrare scritti in CSV (separati da virgola)
	 * @return Un vettore di anni da filtrare 
	 */
	public String[] split(String anni) {
		if (anni != null && anni.length() != 0) {
			return anni.split(",");
		}
		return null;
	}

	/**
	 * Filtra gli eventi con gli anni (anni)
	 */
	public void filtra() {
		if (anno != null) {
			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {

				for (int i = 0; i < anno.length; i++) {
					if (e.getData().getAnno().equals(anno[i].trim()))
						temp.add(e);
				}

			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}