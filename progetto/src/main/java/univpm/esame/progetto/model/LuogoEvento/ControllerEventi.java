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
import univpm.esame.progetto.jsonObject.JSONObjectStats.JSONObjectStatistiche;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.statistiche.Statistiche;

/**
 * 
 * @author Nicola
 *
 */
@RestController
public class ControllerEventi {

	private Filtro filtro;
	private Statistiche statistiche;
	
	@GetMapping("/eventi")
	/**
	 * Rotta che prende come variabile il paese del quale vogliamo sapere gli eventi
	 * a cui è possibile fare un filtraggio con zero, uno o più elementi
	 * 
	 * @param paese   - inidica i paesi da filtrare
	 * @param regioni - inidica le regioni da filtrare
	 * @param generi  - inidica i generi da filtrare
	 * @param anni    - inidica gli anni da filtrare
	 * @param mesi    - inidica i mesi da filtrare
	 * @param giorni  - inidica i giorni da filtrare
	 * @return un JSONObject su postman con tutti gli eventi del paese se non vi
	 *         sono aggiunti filtri
	 * @throws ExceptionPaeseInesistente 
	 */
	public ResponseEntity<JSONObjectEventi> eventi(
			@RequestParam(name = "regioni", required = false) String regioni,
			@RequestParam(name = "generi", required = false) String generi) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		
			filtro = new Filtro();
			filtro.inizializzaEventiFiltrati(generi,regioni);
			return new ResponseEntity<>(filtro.getJsonEventi(), HttpStatus.OK);
	}
	
	@GetMapping("/{paese}")
	/**
	 * Rotta che prende come variabile il paese del quale vogliamo sapere gli eventi
	 * a cui è possibile fare un filtraggio con zero, uno o più elementi
	 * 
	 * @param paese   - inidica i paesi da filtrare
	 * @param regioni - inidica le regioni da filtrare
	 * @param generi  - inidica i generi da filtrare
	 * @param anni    - inidica gli anni da filtrare
	 * @param mesi    - inidica i mesi da filtrare
	 * @param giorni  - inidica i giorni da filtrare
	 * @return un JSONObject su postman con tutti gli eventi del paese se non vi
	 *         sono aggiunti filtri
	 * @throws ExceptionListaVuota 
	 */
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

	@GetMapping("/statistiche")
	/**
	 * rotta che restituisce le statistiche di un paese effettivo
	 * 
	 * @param paese paese da filtrare
	 * @return JSONObject con le statistiche del paese divise in numero eventi
	 *         totali, eventi per genere ed eventi per mese
	 * @throws ExceptionListaVuota 
	 * @throws ExceptionPaeseInesistente 
	 */
	public ResponseEntity<List<JSONObjectStatistiche>> stats() throws ExceptionPaeseInesistente, ExceptionListaVuota  {
			statistiche = new Statistiche();
			statistiche.confrontoStatistiche();
			return new ResponseEntity<>(statistiche.getListaStatistiche(), HttpStatus.OK);	
	}
	
	@GetMapping("/statistiche/{paese}")
	/**
	 * rotta che restituisce le statistiche di un paese effettivo
	 * 
	 * @param paese paese da filtrare
	 * @return JSONObject con le statistiche del paese divise in numero eventi
	 *         totali, eventi per genere ed eventi per mese
	 * @throws ExceptionListaVuota 
	 * @throws ExceptionPaeseInesistente 
	 */
	public ResponseEntity<JSONObjectStatistiche> statsPaese(@PathVariable String paese) throws ExceptionPaeseInesistente, ExceptionListaVuota {
		
		statistiche = new Statistiche(paese);
		return new ResponseEntity<>(statistiche.getStatistiche(), HttpStatus.OK);
		
	}

	
	@GetMapping("/statistiche/{paese}/{regione}")
	/**
	 * rotta aggiuntiva sulle statistiche di una determinata regione di un
	 * determinato paese
	 * 
	 * @param paese paese da filtrare
	 * @param regione - una o più regioni di cui sapere le statistiche 
	 * @return JSONObject con le statistiche della regione d'interesse
	 * @throws ExceptionListaVuota 
	 * @throws ExceptionRegioneInesistente 
	 * @throws ExceptionPaeseInesistente 
	 */
	public ResponseEntity<JSONObjectStatistiche> statsRegione(@PathVariable String paese, @PathVariable String regione) throws ExceptionPaeseInesistente, ExceptionRegioneInesistente, ExceptionListaVuota {
			statistiche = new Statistiche(paese, regione);
			return new ResponseEntity<>(statistiche.getStatistiche(), HttpStatus.OK);
	}
}
