package univpm.esame.progetto.parsing;

import java.util.List;
import java.util.Vector;

import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.model.LuogoEvento.StrumentiRegioni;

/**
 * Classe implementata per memorizzare gli eventi della Polonia e Nuova Zelanda
 * 
 * @author Simone Di Battista 
 *
 */
public class RaccoltaEventi {

	private List<Evento> eventiPolonia = new Vector<>();
	private List<Evento> eventiNuovaZelanda = new Vector<>();
	private StrumentiRegioni toolRegioni = new StrumentiRegioni();
	
	/**
	 * Costruttore di default
	 */
	public RaccoltaEventi() {
	}

	/**
	 * Il metodo restituisce gli eventi relativi al paese passato come parametro
	 * 
	 * @param paese Paese di cui si vuole ottenere un Vector di eventi 
	 * @return Un vettore di eventi
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public List<Evento> getEventi(String paese) throws ExceptionPaeseInesistente {
		if(toolRegioni.controlloPaese(paese)) {
			return this.eventiPolonia;
		}else {
			return this.eventiNuovaZelanda;
		}
	}

	/**
	 * Restituisce il numero di eventi contenuti nella lista corrispondente al parametro
	 * fornito(paese)
	 * 
	 * @param paese Il paese di cui si vuole conoscere il numero di eventi totali
	 * @return Il numero totale di eventi
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public int getNumEventi(String paese) throws ExceptionPaeseInesistente {
		if(toolRegioni.controlloPaese(paese)) {
			return this.eventiPolonia.size();
		}else {
			return this.eventiNuovaZelanda.size();
		}
	}

	/**
	 * Aggiunge alla lista di eventi della Nuova zelanda o Polonia un nuovo evento
	 * 
	 * @param countryCode Diminutivo della Polonia (PL) o Nuova Zelanda (NZ) 
	 * @param evento Un evento con tutte le sue informazioni
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public void addEventi(String countryCode, Evento evento) throws ExceptionPaeseInesistente {
		if(toolRegioni.controlloPaese(toolRegioni.countryCodeToPaese(countryCode))) {
			this.addEventiPolonia(evento);
		}else {
			this.addEventiNuovaZelanda(evento);
		}
	}

	private void addEventiPolonia(Evento eventoPolonia) {
		this.eventiPolonia.add(eventoPolonia);
	}

	private void addEventiNuovaZelanda(Evento eventoNuovaZelanda) {
		this.eventiNuovaZelanda.add(eventoNuovaZelanda);
	}

}
