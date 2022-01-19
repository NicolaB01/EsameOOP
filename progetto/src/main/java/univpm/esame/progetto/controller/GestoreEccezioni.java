package univpm.esame.progetto.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;
import univpm.esame.progetto.jsonObject.JSONObjectErrore;

/**
 * Classe implementata per gestire le eccezioni lanciate 
 * 
 * @author Simone Di Battista 
 *
 */
@ControllerAdvice
public class GestoreEccezioni {
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
	
	/**
	 * Questo metodo si occupa della gestione delle eccezioni ExceptionListaVuota lanciata dal controller
	 * 
	 * @param e Oggetto eccezione di tipo ExceptionListaVuota
	 * @return Un JSON di errore su postman
	 */
	@ExceptionHandler(value = {ExceptionListaVuota.class})
	public ResponseEntity<JSONObjectErrore> GestoreExceptionListaVuota (ExceptionListaVuota e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()), "Sono applicati troppi filtri, la lista è vuota"), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Questo metodo si occupa della gestione delle eccezioni ExceptionPaeseInesistente lanciata dal controller
	 * 
	 * @param e Oggetto eccezione di tipo ExceptionPaeseInesistente
	 * @return Un JSON di errore su postman
	 */
	@ExceptionHandler(value = {ExceptionPaeseInesistente.class})
	public ResponseEntity<JSONObjectErrore> GestoreExceptionPaeseInesistente (ExceptionPaeseInesistente e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()) ,"Paese inesistente"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Questo metodo si occupa della gestione delle eccezioni ExceptionRegioneInesistente lanciata dal controller
	 * 
	 * @param e Oggetto eccezione di tipo ExceptionRegioneInesistente
	 * @return Un JSON di errore su postman
	 */
	@ExceptionHandler(value = {ExceptionRegioneInesistente.class})
	public ResponseEntity<JSONObjectErrore> GestoreExceptionRegioneInesistente (ExceptionRegioneInesistente e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()), "Regione inesistente per il paese inserito" ), HttpStatus.BAD_REQUEST);
	}
}




