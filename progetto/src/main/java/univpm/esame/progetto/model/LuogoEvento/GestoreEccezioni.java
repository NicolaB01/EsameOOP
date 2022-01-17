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

@ControllerAdvice
public class GestoreEccezioni {
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {ExceptionListaVuota.class})
	public ResponseEntity<JSONObjectErrore> handleEmptyListException (ExceptionListaVuota e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()), "Sono applicati troppi filtri la lista è vuota"), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {ExceptionPaeseInesistente.class})
	public ResponseEntity<JSONObjectErrore> handleUncorrectStateException (ExceptionPaeseInesistente e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()) , "Paese inesistente prova /Polonia o /NuovaZelanda"), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {ExceptionRegioneInesistente.class})
	public ResponseEntity<JSONObjectErrore> handleUncorrectGenreException (ExceptionRegioneInesistente e)
	{
		return new ResponseEntity<>(
				new JSONObjectErrore(e.getMessage(), dtf.format(LocalDateTime.now()), "Regione inesistente per il paese inserito" ), HttpStatus.BAD_REQUEST);
	}
}




