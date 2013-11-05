package progra.grupo1.modelo;

public class Grafo {

	private Nodo[] nodos;
	
	private MatrizSimetrica<Boolean> matrizAdyacencia;

	public Grafo(int cantidadNodos){
		nodos = new Nodo[cantidadNodos];
		matrizAdyacencia = new MatrizSimetrica<Boolean>(cantidadNodos);
	}
	
	public void addAdyacencia(Nodo nodo1, Nodo nodo2){
		this.addAdyacencia(nodo1.getIndice(), nodo2.getIndice());
	}
	
	public void addAdyacencia(int indice1, int indice2){
		matrizAdyacencia.put(indice1, indice2, Boolean.TRUE);
	}
	
	public boolean sonAdyacentes(Nodo nodo1, Nodo nodo2){
		return matrizAdyacencia.get(nodo1.getIndice(), nodo2.getIndice());
	}
	
	public void addNodo(Nodo nodo){
		
	}
	
	public Nodo getNodo(int indice){
		return nodos[indice];
	}
	
	@Override
	public String toString() {
		int cantidadNodos = getCantidadNodos();

		StringBuilder b = new StringBuilder();
		
		b.append("Cantidad de nodos: " + cantidadNodos);
		b.append("|| Aristas: " + this.matrizAdyacencia);
		
		for(int i=0; i<cantidadNodos-1; i++){
			for(int j=i+1; j< cantidadNodos-1;j++){
				if(matrizAdyacencia.get(i, j)){
					b.append("i j");
				}
			}
		}
		
		return b.toString();
	}

	public int getCantidadNodos() {
		return this.nodos.length;
	}
	
}
