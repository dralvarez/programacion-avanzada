package progra.grupo1.modelo;

public class MatrizSimetrica<T> {

	private T[] vectorEquivalente;
	private int dimension;
	
	@SuppressWarnings("unchecked")
	public MatrizSimetrica(int dimension){
		this.dimension = dimension;
		vectorEquivalente = (T[]) new Object[calcularDimensionVector(dimension)];
	}
	
	private int calcularDimensionVector(int dimensionMatriz) {
		return (int) ((Math.pow(dimensionMatriz, 2) - dimensionMatriz) / 2);
	}

	public T get(int i, int j) {
		int posicion = calcularPosicion(i,j);
		return vectorEquivalente[posicion];
	}
	
	public void put(int i, int j, T object) {
		int posicion = calcularPosicion(i,j);
		vectorEquivalente[posicion] = object;
	}


	protected int calcularPosicion(int i, int j) {
		int dimension = this.getDimension();		
		return (int) (i*dimension + j - (Math.pow(i, 2) + 3*i + 2)/2);
	}

	private int getDimension() {
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
	
}
