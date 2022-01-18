package univpm.esame.progetto.testEccezioni;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;
import univpm.esame.progetto.model.LuogoEvento.StrumentiRegioni;

/**
 * Classe implementata per effetuare test unitari su alcune eccezioni
 * 
 * @author Simone Di Battista 
 *
 */
class TestEccezioni {
	
	/**
	 * Verifica eccezione paese
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Verifica eccezione paese")
	void testEccezionePaese() {
		ExceptionPaeseInesistente thrown = assertThrows(ExceptionPaeseInesistente.class , ()-> new StrumentiRegioni().controlloPaese("Italia"));
		assertEquals("Inserisci Polonia o NuovaZelanda come paese", thrown.getMessage());
	}
	
	/**
	 * Verifica eccezione regione
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Verifica eccezione regione")
	void testEccezioneRegione() {
		ExceptionRegioneInesistente thrown = assertThrows(ExceptionRegioneInesistente.class , ()-> new StrumentiRegioni().controlloRegioni("Polonia", "Marche"));
		assertEquals("Inserisci regione esistente", thrown.getMessage());
	}	
}