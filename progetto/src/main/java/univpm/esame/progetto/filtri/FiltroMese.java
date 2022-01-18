package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base ai mesi
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroMese extends Filtro implements InterfacciaFiltri{

	private String[] mese;

	/**
	 * Costruttore di default
	 */
	public FiltroMese() {
		super();
	}
	
	/**
	 * 
	 * @param mesi I mesi da filtrare scritte in CSV (separati da virgola)
	 */
	public FiltroMese(String mesi) {
		this.mese = this.split(mesi);
	}

	/**
	 * Divide i mesi passati se sono più di uno
	 * 
	 * @param mesi I mesi da filtrare scritte in CSV (separati da virgola)
	 * @return Un vettore di mesi da filtrare 
	 */
	public String[] split(String mesi) {
		if (mesi != null && mesi.length() != 0) {
			return mesi.split(",");
		}
		return null;
	}

	/**
	 * Filtra gli eventi con i mesi (mesi)
	 */
	public void filtra() {
		if (this.mese != null) {

			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {

				for (int i = 0; i < mese.length; i++) {
					if (e.getData().getMese().equals(mese[i].trim()))
						temp.add(e);
				}
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}