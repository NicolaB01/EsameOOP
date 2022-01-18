package univpm.esame.progetto.jsonObject.JSONObjectFiltri;

import java.util.HashMap;

/**
 * Classe implementata per poter visualizzare alcuni dati relativi agli eventi filtrati di un paese inserito
 * 
 * @author Simone Di Battista 
 *
 */
public class JSONObjectConfronto {
	private String paese;
	private int eventiTot;
	private int eventiFiltrati;
    private HashMap <String, Integer> regioni= new HashMap <>();
    private HashMap <String, Integer> generi= new HashMap <>();
	
    /**
     * Costruttore di default
     */
	public JSONObjectConfronto() {
	}
	
	/**
	 * 
	 * @param paese Il paese di cui si vogliono conoscere dei dati
	 */
	public JSONObjectConfronto(String paese) {
		this.paese = paese;
	}
	
	/**
	 * 
	 * @param eventiTot Numero di eventi totali programmati per quel paese
	 * @param eventiFiltrati Numero di eventi restituiti dopo aver applicato dei filtri
	 */
	public JSONObjectConfronto(int eventiTot, int eventiFiltrati) {
		this.eventiTot = eventiTot;
		this.eventiFiltrati = eventiFiltrati;
	}
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public int getEventiTot() {
		return eventiTot;
	}
	public void setEventiTot(int eventiTot) {
		this.eventiTot = eventiTot;
	}
	public int getEventiFiltrati() {
		return eventiFiltrati;
	}
	public void setEventiFiltrati(int eventiFiltrati) {
		this.eventiFiltrati = eventiFiltrati;
	}

	public HashMap <String, Integer> getGeneri() {
		return generi;
	}

	public void setGeneri(HashMap <String, Integer> generi) {
		this.generi = generi;
	}

	public HashMap <String, Integer> getRegioni() {
		return regioni;
	}

	public void setRegioni(HashMap <String, Integer> regioni) {
		this.regioni = regioni;
	}
	
	
}