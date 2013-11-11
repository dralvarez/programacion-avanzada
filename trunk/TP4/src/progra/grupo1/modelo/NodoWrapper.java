package progra.grupo1.modelo;

public class NodoWrapper implements Indexable, Priorizable {

	private final Object contenido;
	
	private int indice;
	
	private int prioridad;
	
	public NodoWrapper(Object contenido, int indice, int prioridad) {
		this.contenido = contenido;
		this.indice = indice;
		this.prioridad = prioridad;
	}

	@Override
	public int getIndice() {
		return indice;
	}

	@Override
	public void setIndice(int indice) {
		this.indice = indice;
		
	}

	@Override
	public int getPrioridad() {
		return prioridad;
	}

	public Object getContenido() {
		return contenido;
	}
	
}
