package univpm.esame.progetto.jsonObject.JSONObjectFiltri;

import java.util.List;

import univpm.esame.progetto.model.Evento;

/**
 * Classe implementata per visualizzare il confronto degli eventi filtrati di Polonia e Nuova Zelanda
 * 
 * @author Simone Di Battista
 *
 */
public class JSONObjectEventi {
	
	private JSONObjectConfronto[] confronto = {new JSONObjectConfronto("Polonia"), new JSONObjectConfronto("Nuova Zelanda")};
	
	
	public JSONObjectConfronto[] getConfronto() {
		return confronto;
	}
	
	public void setConfronto(JSONObjectConfronto[] confronto) {
		this.confronto = confronto;
	}
	
}
