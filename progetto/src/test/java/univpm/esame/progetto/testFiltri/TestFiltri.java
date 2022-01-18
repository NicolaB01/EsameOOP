package univpm.esame.progetto.testFiltri;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import univpm.esame.progetto.eccezioni.ExceptionListaVuota;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.filtri.Filtro;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.parsing.ReaderAPI;


/**
 * Classe implementata per effetuare test unitari su alcuni filtri 
 * 
 * @author Simone Di Battista 
 *
 */
class TestFiltri {
	static ReaderAPI reader;
	Filtro filtro;
	
	/**
	 * Effettuato prima di tutti i test
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	@BeforeAll
	static void setUp() throws ExceptionPaeseInesistente  {
		TestFiltri.reader = new ReaderAPI();
		reader.parser("Polonia");
	}
	
	/**
	 * Effettuato prima di ogni test
	 * @throws ExceptionPaeseInesistente  Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	@BeforeEach
	void setUp1() throws ExceptionPaeseInesistente {
		filtro = new Filtro("Polonia", reader.getRaccolta().getEventi("Polonia"));
	}
	
	/**
	 * Controllo che la size corrisponda con quella degli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che la size corrisponda con quella degli eventi filtrati")
	void testFiltroSize() throws ExceptionListaVuota {
		int dimensione = 20;
		filtro.filtraSize(String.valueOf(dimensione));
		assertEquals(dimensione, filtro.getEventiFiltrati().size());
	}
	
	/**
	 * Controllo che il giorno sia lo stesso di tutti gli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che il giorno sia lo stesso di tutti gli eventi filtrati")
	void testFiltroGiorno() throws ExceptionListaVuota {
		String giorno = "15";
		filtro.filtraGiorno(giorno);
		for (Evento e : filtro.getEventiFiltrati()) {
			assertEquals(giorno, e.getData().getGiorno());
		}
	}
	
	/**
	 * Controllo che il segmento sia lo stesso di tutti gli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che il segmento sia lo stesso di tutti gli eventi filtrati")
	void testFiltroSegmento() throws ExceptionListaVuota {
		String segmento = "Music";
		filtro.filtraSegmento(segmento);
		for (Evento e : filtro.getEventiFiltrati()) {
			assertEquals(segmento, e.getClassificazione().getSegmento());
		}
	}
	
	/**
	 * Controllo che l'anno sia lo stesso di tutti gli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che l'anno sia lo stesso di tutti gli eventi filtrati")
	void testFiltroAnno() throws ExceptionListaVuota {
		String anno = "2022";
		filtro.filtraSegmento(anno);
		for (Evento e : filtro.getEventiFiltrati()) {
			assertEquals(anno, e.getData().getAnno());
		}
	}
	
	/**
	 * Controllo che il genere sia lo stesso di tutti gli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che il genere sia lo stesso di tutti gli eventi filtrati")
	void testFiltroGenere() throws ExceptionListaVuota {
		String genere = "Rock";
		filtro.filtraSegmento(genere);
		for (Evento e : filtro.getEventiFiltrati()) {
			assertEquals(genere, e.getClassificazione().getGenere());
		}
	}
	
	/**
	 * Controllo che i generi siano gli stessi di tutti gli eventi filtrati
	 * @throws ExceptionListaVuota   Eccezione lanciata se vengono applicati troppi filtri
	 */
	@Test
	@DisplayName("Controllo che i generi siano gli stessi di tutti gli eventi filtrati")
	void testFiltroGenereMultipli() throws ExceptionListaVuota {
		String genere = "Rock, Pop";
		filtro.filtraSegmento(genere);
		for (Evento e : filtro.getEventiFiltrati()) {
			assertEquals(genere, e.getClassificazione().getGenere());
		}
	}
}
	

	
