package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base ai generi
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroGenere extends Filtro implements InterfacciaFiltri{

	private String[] genere;

	/**
	 * Costruttore di default
	 */
	public FiltroGenere() {
		super();
	}
	
	/**
	 * 
	 * @param generi I generi da filtrare scritte in CSV (separati da virgola)
	 */
	public FiltroGenere(String generi) {
		this.genere = this.split(generi);
	}

	/**
	 * Divide i generi passati se sono più di uno
	 * 
	 * @param generi I generi da filtrare scritte in CSV (separati da virgola)
	 * @return Un vettore di generi da filtrare 
	 */
	public String[] split(String generi) {
		if (generi != null && generi.length() != 0) {
			return generi.split(",");
		}
		return null;
	}

	/**
	 * Filtra gli eventi con i generi (generi)
	 */
	public void filtra() {
		if (genere != null) {

			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {
				for (int i = 0; i < genere.length; i++) {
					if (e.getClassificazione().getGenere().contains(genere[i].trim()))
						temp.add(e);
				}
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}