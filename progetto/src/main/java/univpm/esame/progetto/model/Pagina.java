package univpm.esame.progetto.model;

/**
 * Questa classe rappresenta la posizione di ogni evento nell'API
 * 
 * @author Simone Di Battista
 *
 */
public class Pagina {
	private int elementiTot;
	private int totalPage;
	private int numeroPagina;

	/**
	 * Dimensione predefinita, corrisponde al numero di eventi per pagina
	 */
	public final static int SIZE = 20;
	
	/**
	 * Pagina iniziale(0) 
	 */
	public final static int PAGINA_INIZIALE = 0;

	/**
	 * costruttore di default
	 */
	public Pagina() {
		this(0, 0, 0);
	}

	/**
	 * Costruttore di pagina
	 * 
	 * @param elementiTot - il numero di elementi contenuto nel API
	 * @param totalPage - il numero totale di pagine dell'API
	 * @param numeroPagina - l numero attuale della pagina dell'evento
	 */
	public Pagina(int elementiTot, int totalPage, int numeroPagina) {
		this.elementiTot = elementiTot;
		this.totalPage = totalPage;
		this.numeroPagina = numeroPagina;

	}

	/**
	 * 
	 * @return Il numero di elementi contenuto nel API
	 */
	public int getElementiTot() {
		return elementiTot;
	}

	/**
	 * 
	 * @param elementiTot Imposta il numero di elementi totali 
	 */
	public void setElementiTot(String elementiTot) {
		this.elementiTot = Integer.valueOf(elementiTot);
	}

	/**
	 * 
	 * @return Il numero di pagine dell'API
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 
	 * @param totalPage Imposta il numero totale delle pagine
	 */
	public void setTotalPage(String totalPage) {
		this.totalPage = Integer.valueOf(totalPage);
	}

	/**
	 * 
	 * @return Il numero attuale della pagina dell'evento
	 */
	public int getNumeroPagina() {
		return numeroPagina;
	}

	/**
	 * 
	 * @param numeroPagina Imposta il numero della pagina attuale
	 */
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = Integer.valueOf(numeroPagina);
	}

	/**
	 * 
	 * @return La pagina successiava a quella attuale
	 */
	public int getPaginaSuccessiva() {
		return this.numeroPagina + 1;
	}
}
