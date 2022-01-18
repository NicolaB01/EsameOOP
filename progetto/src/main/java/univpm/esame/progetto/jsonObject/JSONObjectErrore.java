package univpm.esame.progetto.jsonObject;


/**
 * Classe implementata per visualizzare su postman un JSON di errore
 * 
 * @author Simone Di Battista
 *
 */
public class JSONObjectErrore {
	private String message;
	private String date;
	private String errore;

	/**
	 * 
	 * @param msg Messaggio di errore
	 * @param date La data e l'orario in cui avviene l'errore
	 * @param errore Errore 
	 */
	public JSONObjectErrore(String msg, String date, String errore) {
		this.setMsg(msg);
		this.setDate(date);
		this.setErrore(errore);
	}

	/**
	 * 
	 * @return message
	 */
	public String getMsg() {
		return message;
	}

	/**
	 * 
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.message = msg;
	}

	/**
	 * 
	 * @return path
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return errore
	 */
	public String getErrore() {
		return errore;
	}

	/**
	 * 
	 * @param errore 
	 */
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
