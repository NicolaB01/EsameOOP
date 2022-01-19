package univpm.esame.progetto.testStatistiche;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.eccezioni.ExceptionRegioneInesistente;
import univpm.esame.progetto.statistiche.Statistiche;


/**
 * Classe implementata per effetuare test unitari su alcuni statistiche 
 * 
 * @author Nicola Biagioli 
 *
 */
class TestStatistiche {

	/**
	 * Controllo eventi totali nelle statistiche della Polonia 
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */

	@Test
	@DisplayName("Controllo eventi totali nelle statistiche paese(Polonia)")
	void testStatistichePaese() throws ExceptionListaVuota, ExceptionPaeseInesistente {
		assertEquals(465, new Statistiche("Polonia").getStatistiche().getEventi());
	}

	/**
	 * Controllo eventi totali nelle statistiche della regione Wellington della Nuova Zelanda 
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 * @throws ExceptionRegioneInesistente Viene lanciata quando la regione passata come argomento non è presente fra le regioni del paese passato
	 */
	@Test
	@DisplayName("Controllo eventi totali nelle statistiche della regione Wellington della Nuova Zelanda")
	void testStatisticheRegione() throws ExceptionListaVuota, ExceptionPaeseInesistente, ExceptionRegioneInesistente {
		assertEquals(9, new Statistiche("NuovaZelanda","Wellington").getStatistiche().getEventi());
	}

}
