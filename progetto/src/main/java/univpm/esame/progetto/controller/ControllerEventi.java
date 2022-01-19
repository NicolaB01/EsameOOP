package univpm.esame.progetto.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;
import univpm.esame.progetto.filtri.Filtro;
import univpm.esame.progetto.jsonObject.JSONObjectFiltri.JSONObjectEventi;
import univpm.esame.progetto.jsonObject.JSONObjectStatistiche.JSONObjectStatistiche;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.statistiche.Statistiche;

/**
 * Classe implementata per effettuare le chiamate alle rotte 
 * 
 * @author Nicola Biagioli
 *
 */
@RestController
public class ControllerEventi {

	private Filtro filtro;
	private Statistiche statistiche;


	/**
	 * Rotta che restituisce un confronto tra gli eventi programmati in Polonia e Nuova Zelanda 
	 * 
	 * @param regioni Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @param generi  I generi da filtrare scritti in CSV (separati da virgola)
	 * @return Ritorna in formato JSON su postman il confronto tra gli eventi programmati in Polonia e Nuova Zelanda 
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda 
	 * @throws ExceptionListavuota Eccezione lanciata se vengono applicati troppi filtri 
	 */
	@GetMapping("/eventi")
	public ResponseEntity<JSONObjectEventi> eventi(
			@RequestParam(name = "regioni", required = false) String regioni,
			@RequestParam(name = "generi", required = false) String generi) throws ExceptionPaeseInesistente, ExceptionListaVuota {

		filtro = new Filtro();
		filtro.inizializzaEventiFiltrati(generi,regioni);
		return new ResponseEntity<>(filtro.getJsonEventi(), HttpStatus.OK);
	}


	/**
	 * Rotta che prende come parametro il paese del quale vogliamo conoscere gli eventi
	 * a cui è possibile effetuare un filtraggio con zero, uno o più elementi per ogni parametro
	 * 
	 * @param paese   Il paese di cui si vogliono conoscere gli eventi
	 * @param regioni Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @param generi  I generi da filtrare scritti in CSV (separati da virgola)
	 * @param anni    Gli anni da filtrare scritti in CSV (separati da virgola)
	 * @param mesi    I mesi da filtrare scritti in CSV (separati da virgola)
	 * @param giorni  I giorni da filtrare scritti in CSV (separati da virgola)
	 * @return Ritorna in formato JSON su postman la lista degli eventi relativa al paese passato, filtrati con gli opportuni parametri
	 * @throws ExceptionListaVuota Eccezione lanciata se vengono applicati troppi filtri 
	 */
	@GetMapping("/eventi/{paese}")
	public ResponseEntity<List<Evento>> eventiPaese(@PathVariable String paese,
			@RequestParam(name = "regioni", required = false) String regioni,
			@RequestParam(name = "segmenti", required = false) String segmenti,
			@RequestParam(name = "anni", required = false) String anni,
			@RequestParam(name = "mesi", required = false) String mesi,
			@RequestParam(name = "giorni", required = false) String giorni,
			@RequestParam(name = "generi", required = false) String generi,
			@RequestParam(name = "size", required = false, defaultValue = "20") String size) throws ExceptionListaVuota, ExceptionPaeseInesistente {

		filtro = new Filtro(paese);
		filtro.inizializzaEventiFiltrati();
		filtro.filtraggioComplessivo(regioni, segmenti, anni, mesi, giorni, generi, size);
		return new ResponseEntity<>(filtro.getEventiFiltrati(), HttpStatus.OK);
	}

	/**
	 * Rotta che restituisce un confronto tra le statistiche complessive calcolate sugli eventi programmati in Polonia e Nuova Zelanda
	 * 
	 * @return Ritorna in formato JSON su postman un confronto tra le statistiche complessive calcolate sugli eventi programmati in Polonia e Nuova Zelanda
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	@GetMapping("/statistiche")
	public ResponseEntity<List<JSONObjectStatistiche>> stats() throws ExceptionPaeseInesistente, ExceptionListaVuota  {
		statistiche = new Statistiche();
		statistiche.confrontoStatistiche();
		return new ResponseEntity<>(statistiche.getListaStatistiche(), HttpStatus.OK);	
	}

	/**
	 * Rotta aggiuntiva sulle statistiche di un determinato paese passato come parametro 
	 * 
	 * @param paese Paese di cui si vogliono calcolare le statistiche
	 * @return Ritorna in formato JSON le statistiche del paese
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	@GetMapping("/statistiche/{paese}")
	public ResponseEntity<JSONObjectStatistiche> statsPaese(@PathVariable String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {

		statistiche = new Statistiche(paese);
		return new ResponseEntity<>(statistiche.getStatistiche(), HttpStatus.OK);

	}

	/**
	 * Rotta aggiuntiva sulle statistiche di una o più determinate regioni di un determinato paese 
	 * 
	 * @param paese  Paese di cui si vogliono calcolare le statistiche
	 * @param regione Le regioni da filtrare scritte in CSV (separati da virgola)
	 * @return Ritorna in formato JSON le statistiche relative ad una o più regioni passate come parametro
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 * @throws ExceptionRegioneInesistente Eccezione lanciata nel momento in cui la regione immessa non coincide con una regione della Polonia o della Nuova Zelanda
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	@GetMapping("/statistiche/{paese}/{regione}")
	public ResponseEntity<JSONObjectStatistiche> statsRegione(@PathVariable String paese, @PathVariable String regione) throws ExceptionPaeseInesistente, ExceptionRegioneInesistente, ExceptionListaVuota {
		statistiche = new Statistiche(paese, regione);
		return new ResponseEntity<>(statistiche.getStatistiche(), HttpStatus.OK);
	}
}
