package univpm.esame.progetto.testStrumenti;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.model.DataEvento.StrumentiMesi;
import univpm.esame.progetto.model.LuogoEvento.StrumentiRegioni;

/**
 * Classe implementata per effetuare test unitari su alcune classi di supporto
 * 
 * @author Nicola Biagioli 
 *
 */
class TestStrumenti {

	/**
	 * Controllo che il convertitore da int a mese funzioni
	 * @throws ExceptionListaVuota  Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che il convertitore da int a mese funzioni")
	void testConvertitoreToMesi() throws ExceptionListaVuota {
		String meseInt = "01";
		String meseString = new StrumentiMesi().intToMesi(meseInt);

		assertEquals("Gennaio", meseString);
	}
	
	/**
	 * Controllo che il convertitore da postal code a nomeRegione funzioni
	 */
	@Test
	@DisplayName("Controllo che il convertitore da postalcode a nomeRegione funzioni")
	void testConvertiPostalCodeInRegione() {
		int postalCode=9600;
		String nomeRegione = new StrumentiRegioni().convertiPostalCodeInRegione(postalCode);

		assertEquals("Southland", nomeRegione);
	}
	
	/**
	 * Controlo che il metodo controlloPaese funzioni	 
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
     *
	 */
	@Test
	@DisplayName("Controlo che il metodo controlloPaese funzioni")
	void testControlloPaese() throws ExceptionPaeseInesistente {
		String paese= "polonia";
		boolean check= new StrumentiRegioni().controlloPaese(paese);
		
		assertEquals(true,check);
	}
	
	

}
