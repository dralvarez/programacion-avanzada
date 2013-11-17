package progra.grupo1.modelo;

public class GrafoDirigido extends Grafo{

	public GrafoDirigido(int cantidadNodos) {
		super(cantidadNodos);
	}

	@Override
	protected MatrizAdyacencia crearMatrizAdyacencia(int cantidadNodos) {
		return new Matriz(cantidadNodos);
	}
	
	public double getMaximaCantidadDeAristasPosibles(){
		int cantidadNodos = getCantidadNodos();
		return cantidadNodos * (cantidadNodos - 1);
	}
	

	public Adyacencia[][] getMatrizAdyacencias(){
		return ((Matriz)matrizAdyacencia).getMatriz();
	}
	
}
