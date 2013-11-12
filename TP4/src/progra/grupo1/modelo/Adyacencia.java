package progra.grupo1.modelo;

public	class Adyacencia implements Comparable<Adyacencia>{
	
	private int indice1;
	private int indice2;
	private double orden;
	private int distancia;
	
	public Adyacencia(int indice1, int indice2){
		this.indice1 = indice1;
		this.indice2 = indice2;
		orden = Math.random();
		distancia = (int) (Math.random() * 100); 
	}

	public int getIndice1() {
		return indice1;
	}

	public int getIndice2() {
		return indice2;
	}

	public double getOrden() {
		return orden;
	}
	
	public int getDistancia() {
		return distancia;
	}

	@Override
	public int compareTo(Adyacencia o) {
		return (int) (this.orden*100 - o.orden*100);
	}
	
	@Override
	public String toString() {
		return "(" + indice1 + " <-> " + indice2 + ")";
	}
	
}