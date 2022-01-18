package univpm.esame.progetto.filtri;

/**
 * Interfaccia che deve essere implementata da tutti le classi di filtro 
 * effettuando overriding dei metodi dichiarati in questa. 
 * 
 * @author Simone Di Battista
 *
 */
public interface InterfacciaFiltri {
	
	/**
	 * Metodo che prende una stringa di valori da filtrare, 
	 * la divide in più parti se ci sono parole separate dalla ","
	 * 
	 * 
	 * @param args Parametri da filtrare
	 * @return Un array di parametri da filtrare
	 */
	public abstract String[] split(String args);
	
	/**
	 * Metodo implementato per filtrare secondo tutti i parametri divisi
	 * dallo split, se stringa di valori da filtrare è nulla, non vengono applicati filtri
	 *
	 */
	public abstract void filtra();
}
