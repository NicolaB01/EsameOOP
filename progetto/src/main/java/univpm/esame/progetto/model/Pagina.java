package univpm.esame.progetto.model;

/**
 * Questa classe 
 * 
 * @author Simone Di Battista
 *
 */
public class Pagina {
	private int elementiTot;
	private int totalPage;
	private int numeroPagina;

	/**
	 * dimensione predefinita, corrisponde al numero di eventi per pagina
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
	 * costruttore di pagina
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
	 * @return elementiTot il numero di elementi contenuto nel API
	 */
	public int getElementiTot() {
		return elementiTot;
	}

	/**
	 * 
	 * @param elementiTot viene ricevuto come stringa dalla API per questo usiamo
	 *                    Integer.valueof
	 */
	public void setElementiTot(String elementiTot) {
		this.elementiTot = Integer.valueOf(elementiTot);
	}

	/**
	 * 
	 * @return totalPage il numero di pagine dell'API
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 
	 * @param totalPage viene ricevuto come stringa dalla API per questo usiamo
	 *                  Integer.valueof
	 */
	public void setTotalPage(String totalPage) {
		this.totalPage = Integer.valueOf(totalPage);
	}

	/**
	 * 
	 * @return numeroPagina il numero attuale della pagina dell'evento
	 */
	public int getNumeroPagina() {
		return numeroPagina;
	}

	/**
	 * 
	 * @param numeroPagina viene ricevuto come stringa dalla API per questo usiamo
	 *                     Integer.valueof
	 */
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = Integer.valueOf(numeroPagina);
	}

	/**
	 * 
	 * @return numeroPagina+1 la pagina successiava a quella attuale
	 */
	public int getPaginaSuccessiva() {
		return this.numeroPagina + 1;
	}
}