package univpm.esame.progetto.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import univpm.esame.progetto.eccezioni.ExceptionNullJSON;
import univpm.esame.progetto.eccezioni.ExceptionPaeseInesistente;
import univpm.esame.progetto.model.Evento;
import univpm.esame.progetto.model.Pagina;
import univpm.esame.progetto.model.Classificazioni.ClassificazioneGenerale;
import univpm.esame.progetto.model.Classificazioni.ClassificazioneNuovaZelanda;
import univpm.esame.progetto.model.Classificazioni.ClassificazionePolonia;
import univpm.esame.progetto.model.DataEvento.Data;
import univpm.esame.progetto.model.LuogoEvento.Luogo;
import univpm.esame.progetto.model.LuogoEvento.StrumentiRegioni;

/**
 * Classe implementata per effettuare il collegamento con l'API di TicketMaster
 * e per convertire il Json in oggetti della classe Evento (Parsing)
 * 
 * @author Nicola Biagioli
 *
 */

public class ReaderAPI {

	private final String COUNTRY_CODE_NZ = "NZ";
	private final String COUNTRY_CODE_PL = "PL";
	private final String URL = "https://app.ticketmaster.com/discovery/v2/events";
	private final String API_KEY = "7elxdku9GGG5k8j0Xm8KWdANDgecHMV0";
	private RaccoltaEventi raccolta = new RaccoltaEventi();
	private StrumentiRegioni toolRegioni = new StrumentiRegioni();
	
	/**
	 * Costruttore di default
	 */
	public ReaderAPI(){	 
	}

	/**
	 * 
	 * @param paese Il paese di cui si vogliono conoscere l'eventi
	 * @throws ExceptionPaeseInesistente Eccezione lanciata nel momento in cui il paese immesso non coincide con Polonia o Nuova Zelanda
	 */
	public void parser(String paese) throws ExceptionPaeseInesistente {
		if(toolRegioni.controlloPaese(paese)) {
			this.evento(Pagina.PAGINA_INIZIALE, COUNTRY_CODE_PL);
		} else {
			this.evento(Pagina.PAGINA_INIZIALE, COUNTRY_CODE_NZ);
		} 
	}
	
	private void evento(int page, String countryCode) throws ExceptionPaeseInesistente {

		try {
			URLConnection openConnection = new URL(URL + "?apikey=" + this.API_KEY + "&countryCode=" + countryCode
					+ "&size=" + Pagina.SIZE + "&page=" + page).openConnection();
			InputStream in = openConnection.getInputStream();

			String info = "";
			String line = "";

			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(inR);

			while ((line = buf.readLine()) != null) {
				info += line;
			}

			JSONObject ticket = (JSONObject) JSONValue.parseWithException(info);

			this.getTicket(ticket, countryCode);
		} catch (ParseException e) {
			System.out.println("Errore di conversione");
		} catch (IOException e) {
			System.exit(0);
		}

	}

	private void getTicket(JSONObject ticket, String countryCode) throws ExceptionPaeseInesistente {
		JSONObject _embedded = (JSONObject) ticket.get("_embedded");
		JSONArray raccoltaEventi = (JSONArray) _embedded.get("events");
		Pagina paginaEvento = this.setPagina(ticket);

		for (int i = 0; i < raccoltaEventi.size(); i++) {
			JSONObject evento = (JSONObject) raccoltaEventi.get(i);

			String nomeEvento = String.valueOf(evento.get("name"));

			Data dataEvento = this.setData(evento);

			Luogo luogoEvento = this.setLuogo(evento, countryCode);

			ClassificazioneGenerale classificazione = this.setClassificazione(evento, countryCode);

			Evento e = new Evento(nomeEvento, luogoEvento, dataEvento, classificazione, paginaEvento);

			raccolta.addEventi(countryCode, e);
		}
		
		if ((paginaEvento.getNumeroPagina() + 1) < paginaEvento.getTotalPage()) {
			this.evento(paginaEvento.getPaginaSuccessiva(), countryCode);
		}

	}

	private Pagina setPagina(JSONObject ticket) {
		JSONObject page = (JSONObject) ticket.get("page");
		Pagina pagina = new Pagina();
		pagina.setTotalPage(String.valueOf(page.get("totalPages")));
		pagina.setElementiTot(String.valueOf(page.get("totalElements")));
		pagina.setNumeroPagina(String.valueOf(page.get("number")));
		return pagina;
	}

	private Data setData(JSONObject evento) {
		JSONObject dates = (JSONObject) evento.get("dates");
		JSONObject start = (JSONObject) dates.get("start");
		return new Data(String.valueOf(start.get("localDate")));
	}

	private Luogo setLuogo(JSONObject evento, String countryCode) throws ExceptionPaeseInesistente {
		JSONObject luogo = (JSONObject) evento.get("_embedded");
		JSONArray venues = (JSONArray) luogo.get("venues");
		JSONObject place = (JSONObject) venues.get(0);
		JSONObject country = (JSONObject) place.get("country");
		String latitude = null, longitude = null, postalCode = null, regione = null;
		Luogo luogoEvento = null;

		try {
			JSONObject location = (JSONObject) checkNullJSON(place).get("location");
			latitude = String.valueOf(checkNullJSON(location).get("latitude"));
			longitude = String.valueOf(checkNullJSON(location).get("longitude"));
		} catch (ExceptionNullJSON e) {
			latitude = "0.0";
			longitude = "0.0";
		}

		if (countryCode.equals(this.COUNTRY_CODE_NZ)) {
			postalCode = String.valueOf(place.get("postalCode"));

			if (postalCode.equals("null")) {
				luogoEvento = new Luogo(String.valueOf(country.get("name")), postalCode, longitude, latitude);
			} else {
				luogoEvento = new Luogo(String.valueOf(country.get("name")),
						new StrumentiRegioni().convertiPostalCodeInRegione(Integer.valueOf(postalCode.trim())), longitude,
						latitude);
			}

		} else if (countryCode.equals(this.COUNTRY_CODE_PL)) {
			JSONObject state = (JSONObject) place.get("state");
			try {
				regione = String.valueOf(checkNullJSON(state).get("name"));
			} catch (ExceptionNullJSON e) {
				regione = "null";
			}
			luogoEvento = new Luogo(String.valueOf(country.get("name")), regione, latitude, longitude);
		}
		return luogoEvento;
	}

	private ClassificazioneGenerale setClassificazione(JSONObject evento, String countryCode) throws ExceptionPaeseInesistente {
		JSONArray classifications = (JSONArray) evento.get("classifications");
		JSONObject classification = (JSONObject) classifications.get(0);
		JSONObject segment = (JSONObject) classification.get("segment");
		JSONObject genre = (JSONObject) classification.get("genre");
		JSONObject subGenre = (JSONObject) classification.get("subGenre");
		JSONObject type = (JSONObject) classification.get("type");
		JSONObject subType = (JSONObject) classification.get("subType");
		ClassificazioneGenerale classificazione = null;

		if (countryCode.equals(this.COUNTRY_CODE_NZ)) {
			classificazione = new ClassificazioneNuovaZelanda();
			classificazione.setSegmento(String.valueOf(segment.get("name")));
			classificazione.setGenere(String.valueOf(genre.get("name")));
			classificazione.setSottoGenere(String.valueOf(subGenre.get("name")));
			try {
				((ClassificazioneNuovaZelanda) classificazione)
				.setTipo(String.valueOf(checkNullJSON(type).get("name")));
			} catch (ExceptionNullJSON e) {
				((ClassificazioneNuovaZelanda) classificazione).setTipo(null);
			}
			try {
				((ClassificazioneNuovaZelanda) classificazione)
				.setSottoTipo(String.valueOf(checkNullJSON(subType).get("name")));
			} catch (ExceptionNullJSON e) {
				((ClassificazioneNuovaZelanda) classificazione).setSottoTipo(null);
			}

		} else if (countryCode.equals(this.COUNTRY_CODE_PL)) {
			classificazione = new ClassificazionePolonia();
			try {
				classificazione.setSegmento(String.valueOf(checkNullJSON(segment).get("name")));
			} catch (ExceptionNullJSON e) {
				classificazione.setSegmento(null);
			}
			try {
				classificazione.setGenere(String.valueOf(checkNullJSON(genre).get("name")));
			} catch (ExceptionNullJSON e) {
				classificazione.setGenere(null);
			}
			try {
				classificazione.setSottoGenere(String.valueOf(checkNullJSON(subGenre).get("name")));
			} catch (ExceptionNullJSON e) {
				classificazione.setSottoGenere(null);
			}
		}
		return classificazione;
	}

	private JSONObject checkNullJSON(JSONObject obj) throws ExceptionNullJSON {
		if (obj != null)
			return obj;
		else
			throw new ExceptionNullJSON("JSONObject impostato a null");
	}

	/**
	 * 
	 * @return raccolta Un oggetto della classe RaccoltaEventi
	 */
	public RaccoltaEventi getRaccolta() {
		return raccolta;
	}
}