package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base alla regioni
 * 
 * @author Simone Di Battista
 *
 */
public class FiltroRegione extends Filtro implements InterfacciaFiltri{

	private String[] regione;

	/**
	 * Costruttore di default
	 */
	public FiltroRegione() {
		super();
	}
	
	/**
	 * 
	 * @param regioni Le regioni da filtrare scritte in CSV (separati da virgola)
	 */
	public FiltroRegione(String regioni) {
		this.regione = this.split(regioni);
	}

	/**
	 * Divide le regione passate se sono più di uno
	 * 
	 * @param regioni Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @return Un vettore di regioni da filtrare 
	 */
	public String[] split(String regioni) {
		if (regioni != null && regioni.length() != 0) {
			return regioni.split(",");
		}
		return null;
	}

	/**
	 * Filtra gli eventi con le regioni (regioni)
	 */
	public void filtra() {
		if (this.regione != null) {
			
			List<Evento> temp = new Vector<>();
			for (Evento e : super.getEventiFiltrati()) {
				for (int i = 0; i < this.regione.length; i++) {
					if (e.getLuogo().getRegione().equalsIgnoreCase(this.regione[i].trim()))
						temp.add(e);
				}
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}