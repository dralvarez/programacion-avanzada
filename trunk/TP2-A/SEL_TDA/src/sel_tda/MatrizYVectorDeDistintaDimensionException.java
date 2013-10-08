package sel_tda;

/**
 * Excepcion que se lanza al intentar multiplicar una matriz y un vector
 * cuyas dimensiones hacen que no se puedan multiplicar
 * 
 *
 */
public class MatrizYVectorDeDistintaDimensionException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 538286287948514461L;

	private MatrizMath matriz;
	
	private VectorMath vector;

	public MatrizYVectorDeDistintaDimensionException(MatrizMath matriz,
			VectorMath vector) {
		super();
		this.matriz = matriz;
		this.vector = vector;
	}

	@Override
	public String getMessage() {
		return String.format("La matriz y el vector tienen distintas dimensiones.(%s - %s)", matriz.getDimensionAsString(), vector.getDimensionAsString());
	}
	
	
	
	
	
}
