package univpm.esame.progetto.model;

import univpm.esame.progetto.model.Classificazioni.ClassificazioneGenerale;
import univpm.esame.progetto.model.DataEvento.Data;
import univpm.esame.progetto.model.LuogoEvento.Luogo;

/**
 * Questa classe descrive un evento genrico preso dall'API di ticketmaster
 * 
 * @author Nicola Biagioli
 *
 */
public class Evento  {
	private String nome;
	private Luogo luogo;
	private Data data;
	private ClassificazioneGenerale classificazione;
	private Pagina pagina;

	/**
	 * Costruttore di default
	 */
	public Evento() {
		this(null, null, null, null, null);
	}

	/**
	 * costruttore di un evento
	 * 
	 * @param nome Nome dell'evento 
	 * @param luogo Luogo dell'evento 
	 * @param dataEvento Data dell'evento
	 * @param classificazione Tipo di classificazione dell'evento
	 * @param pagina Pagina dell'evento
	 */
	public Evento(String nome, Luogo luogo, Data dataEvento, ClassificazioneGenerale classificazione, Pagina pagina) {
		this.nome = nome;
		this.luogo = luogo;
		this.data = dataEvento;
		this.classificazione = classificazione;
		this.pagina = pagina;
	}

	/**
	 * 
	 * @return L'oggetto pagina
	 */
	public Pagina getPagina() {
		return pagina;
	}

	/**
	 * 
	 * @param pagina Imposta l'oggetto pagina all'interno dell'evento
	 */
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	/**
	 * 
	 * @return Il nome dell'evento
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome Imposta il nome dell'evento
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return L'oggetto luogo dell'evento
	 */
	public Luogo getLuogo() {
		return luogo;
	}

	/**
	 * 
	 * @param luogo Imposta l'oggetto luogo dell'evento

	 */
	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}

	/**
	 * 
	 * @return L'oggetto data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * 	 
	 * @param dataEvento Imposta l'oggetto data dell'evento
	 */
	public void setData(Data dataEvento) {
		this.data = dataEvento;
	}

	/**
	 * 
	 * @return classificazione L'oggetto classificazione 
	 */
	public ClassificazioneGenerale getClassificazione() {
		return classificazione;
	}

	/**
	 * 	 
	 * @param classificazione Imposta l'oggetto classificazione dell'evento

	 */
	public void setClassificazione(ClassificazioneGenerale classificazione) {
		this.classificazione = classificazione;
	}
}