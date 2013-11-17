package progra.grupo1.modelo;

public class Nodo implements Indexable{

	private String color;

	private int indice;

	private int grado;
	
	private Grafo grafo;
	

	public Nodo(int indice){
		this.indice = indice;
		this.grado = 0;
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
	
	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}
	
	public void agregarGrado(){
		this.grado++;
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
		return String.format("N%d", indice+1); 
	}
}
