package progra.grupo1.modelo;

public class Matriz implements MatrizAdyacencia{

	private final Adyacencia[][] matriz;
	private final int cantidadNodos;
	
	public Matriz(int cantidadNodos){
		this.cantidadNodos = cantidadNodos;
		matriz = new Adyacencia[cantidadNodos][cantidadNodos];
	}

	public Adyacencia get(int i, int j) {
		return matriz[i][j];
	}
	
	public void put(int i, int j, Adyacencia object) {
		matriz[i][j] = object;
	}


	public int getDimension() {
		return cantidadNodos;
	}
	
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		for(int i=0; i<cantidadNodos ; i++){
			for(int j=0 ; i<cantidadNodos; i++){
				b.append(matriz[i][j]);
			}
		}
		
		return b.toString();
	}
	
	public int getCantidadNodos() {
		return cantidadNodos;
	}

	public Adyacencia[][] getMatriz() {
		return matriz;
	}
}
