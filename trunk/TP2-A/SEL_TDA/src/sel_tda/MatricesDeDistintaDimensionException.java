package sel_tda;

/**
 * Excepcion que se lanza al intentar multiplicar dos matrices 
 * cuyas dimensiones hacen que no se puedan multiplicar
 *
 */
public class MatricesDeDistintaDimensionException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 538286287948514461L;

	private MatrizMath matriz1;
	
	private MatrizMath matriz2;

	public MatricesDeDistintaDimensionException(MatrizMath matriz1,
			MatrizMath matriz2) {
		super();
		this.matriz1 = matriz1;
		this.matriz2 = matriz2;
	}

	@Override
	public String getMessage() {
		return String.format("Las matrices tienen distinta dimensión y no se puede efectuar la operacion.(%s - %s)", matriz1.getDimensionAsString(), matriz2.getDimensionAsString());
	}
	
	
	
	
	
}
