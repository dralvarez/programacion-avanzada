package progra.grupo1.generador;

public class NoSePuedeGenerarUnGrafoRegularException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502731948003297996L;
	
	private final static String MENSAJE ="No se puede generar un grafo con %d nodos de grado %d. ";
	
	public NoSePuedeGenerarUnGrafoRegularException(int cantidadNodos, int gradoNodos) {
		super(String.format(MENSAJE, cantidadNodos,gradoNodos));
	}

}
