package univpm.esame.progetto.filtri;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per effettuare un filtro in base alla size
 * 
 * @author Simone Di Battista 
 *
 */
public class FiltroSize extends Filtro implements InterfacciaFiltri{

	private int size;
	

	/**
	 * Costruttore di default
	 */
	public FiltroSize() {
		super();
	}
	
	/**
	 * 
	 * @param size Il numero di eventi che si vuole avere
	 */
	public FiltroSize(String size) {
		this.size = Integer.valueOf(size);
	}

	@Override
	/**
	 * @return la size da filtrare
	 */
	public String[] split(String size) {
		String[] temp = {size};
		return temp;
	}

	/**
	 * Setta gli eventi filtrati con la dimensione (size)
	 */
	public void filtra() {
		List<Evento> temp = new Vector<>();
		if (this.size < super.getEventiFiltrati().size() ) {
			for (int i = 0; i < this.size; i++) {
				temp.add(super.getEventiFiltrati().get(i));
			}
			super.setEventiFiltrati(temp);
		}else {
			super.setEventiFiltrati(super.getEventiFiltrati());
		}
	}
}


