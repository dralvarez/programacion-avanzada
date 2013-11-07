package progra.grupo1.modelo;

public class MatrizSimetrica<T> {

	private final boolean[] vectorEquivalente;
	private final int cantidadNodos;
	
	@SuppressWarnings("unchecked")
	public MatrizSimetrica(int cantidadNodos){
		this.cantidadNodos = cantidadNodos;
		vectorEquivalente = new boolean[ calcularDimensionVector(cantidadNodos) ];
	}
	
	private int calcularDimensionVector(int dimensionMatriz) {
		return (int) ((Math.pow(cantidadNodos, 2) - cantidadNodos) / 2);
	}

	public boolean get(int i, int j) {
		int posicion = calcularPosicion(i,j);
		return vectorEquivalente[posicion];
	}
	
	public void put(int i, int j, Boolean object) {
		int posicion = calcularPosicion(i,j);
		vectorEquivalente[posicion] = object;
	}


	protected int calcularPosicion(int i, int j) {
		int cantidadNodos = this.getCantidadNodos();
		if(i > j){
			return (int) (i*cantidadNodos + j - (Math.pow(i, 2) + 3*i + 2)/2);
		} else {
			return (int) (j*cantidadNodos + i - (Math.pow(j, 2) + 3*j + 2)/2);
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
	
	public boolean[] getVectorEquivalente(){
		return vectorEquivalente;
	}
	
	
	
}
