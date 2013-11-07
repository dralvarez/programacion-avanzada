package progra.grupo1.modelo;

public class Nodo {

	private String color;

	private int indice;

	private Grafo grafo;
	
	public Nodo(int indice){
		this.indice = indice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public boolean esAdyacente(Nodo nodo) {
		return this.grafo.sonAdyacentes(this, nodo);
	}
	
	@Override
	public String toString() {
		return String.format("Nodo %d: | Color %s", indice, color); 
	}
}
