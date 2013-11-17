package progra.grupo1.modelo;

public class MatrizSimetrica implements MatrizAdyacencia{

	private final Adyacencia[] vectorEquivalente;
	private final int cantidadNodos;
	
	public MatrizSimetrica(int cantidadNodos){
		this.cantidadNodos = cantidadNodos;
		vectorEquivalente = new Adyacencia[ calcularDimensionVector(cantidadNodos) ];
	}
	
	private int calcularDimensionVector(int dimensionMatriz) {
		return (int) ((Math.pow(cantidadNodos, 2) - cantidadNodos) / 2);
	}

	public Adyacencia get(int i, int j) {
		int posicion = calcularPosicion(i,j);
		return vectorEquivalente[posicion];
	}
	
	public void put(int i, int j, Adyacencia object) {
		int posicion = calcularPosicion(i,j);		
		vectorEquivalente[posicion] = object;
	}

	/**
	 * TODO: Chequear si realmente funciona bien, el if esta para 
	 * @return
	 */
	protected int calcularPosicion(int i, int j) {
		int cantidadNodos = this.getCantidadNodos();
		if(i > j){
			return (int) (j*cantidadNodos + i - (Math.pow(j, 2) + 3*j + 2)/2);
		} else {
			return (int) (i*cantidadNodos + j - (Math.pow(i, 2) + 3*i + 2)/2);
		}
	}

	public int getDimension() {
		return vectorEquivalente.length;
	}
	
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		for(int i=0; i<getDimension(); i++){
			b.append(vectorEquivalente[i]);
		}
		
		return b.toString();
	}
	
	public int getCantidadNodos() {
		return cantidadNodos;
	}
	
}
