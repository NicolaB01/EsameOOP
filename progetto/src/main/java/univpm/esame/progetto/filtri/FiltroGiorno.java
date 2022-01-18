package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;


/**
 * Classe implementata per effettuare un filtro in base ai giorni
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroGiorno extends Filtro implements InterfacciaFiltri{

	private String[] giorno;

	/**
	 * Costruttore di default
	 */
	public FiltroGiorno() {
		super();
	}
	
	/**
	 * 
	 * @param giorni I giorni da filtrare scritti in CSV (separati da virgola)
	 */
	public FiltroGiorno(String giorni) {
		this.giorno = this.split(giorni);
	}

	/**
	 * Divide i giorni passati se sono più di uno
	 * 
	 * @param giorni I giorni da filtrare scritti in CSV (separati da virgola)
	 * @return Un vettore di giorni da filtrare 
	 */
	public String[] split(String giorni) {
		if (giorni != null && giorni.length() != 0) {
			return giorni.split(",");
		}
		return null;
	}

	/**
	 * Filtra gli eventi con i giorni (giorni)
	 */
	public void filtra() {
		if (this.giorno != null) {
			
			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {

				for (int i = 0; i < giorno.length; i++) {

					if (e.getData().getGiorno().equals(this.giorno[i].trim())) {
						temp.add(e);
					}

				}
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}
