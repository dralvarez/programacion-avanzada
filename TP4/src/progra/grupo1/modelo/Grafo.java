package progra.grupo1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

	private List<Nodo> nodos;
	
	protected MatrizAdyacencia matrizAdyacencia;

	public Grafo(int cantidadNodos){
		nodos = generarNodos(cantidadNodos);
		matrizAdyacencia = crearMatrizAdyacencia(cantidadNodos);
	}

	protected MatrizAdyacencia crearMatrizAdyacencia(int cantidadNodos) {
		return new MatrizSimetrica(cantidadNodos);
	}
	
	protected List<Nodo> generarNodos(int cantidadNodos) {
		List<Nodo> nodos = new ArrayList<Nodo>(cantidadNodos);
		for(int i=0 ; i<cantidadNodos; i++){
			Nodo nodo = new Nodo(i);
			nodo.setGrafo(this);
			nodos.add(nodo);
		}
		return nodos;
	}

	public void addAdyacencia(Adyacencia adyacencia){
		this.addAdyacencia(adyacencia.getIndice1(), adyacencia.getIndice2(), adyacencia);
	}
	
	public void addAdyacencia(Nodo nodo1, Nodo nodo2, int distancia){
		Adyacencia adyacencia = new Adyacencia(nodo1.getIndice(), nodo2.getIndice());
		adyacencia.setDistancia(distancia);
		this.addAdyacencia(adyacencia);
	}
	
	public void addAdyacencia(Nodo nodo1, Nodo nodo2){
		Adyacencia adyacencia = new Adyacencia(nodo1.getIndice(), nodo2.getIndice());
		this.addAdyacencia(adyacencia);
	}
	
	private void addAdyacencia(int indice1, int indice2, Adyacencia adyacencia){
		matrizAdyacencia.put(indice1, indice2, adyacencia);
		this.getNodo(indice1).agregarGrado();
		this.getNodo(indice2).agregarGrado();
	}
	
	public boolean sonAdyacentes(int indice1, int indice2){
		return indice1 != indice2 ? matrizAdyacencia.get(indice1, indice2) != null : false;
	}
	
	public boolean sonAdyacentes(Nodo nodo1, Nodo nodo2){
		return sonAdyacentes(nodo1.getIndice(), nodo2.getIndice());
	}
	
	public Adyacencia getAdyacencia(Nodo nodo1, Nodo nodo2){
		return matrizAdyacencia.get(nodo1.getIndice(), nodo2.getIndice());
	}
	
	public void addNodo(Nodo nodo){
		
	}
	
	public Nodo getNodo(int indice){
		return nodos.get(indice);
	}
	
	@Override
	public String toString() {
		int cantidadNodos = getCantidadNodos();

		StringBuilder b = new StringBuilder();
		
//		for(int i=0; i<cantidadNodos; i++){
//			Nodo n = this.getNodo(i);
//			b.append(n + ", ");
//		}
		
		for(int i=0; i<cantidadNodos; i++){
			for(int j=1; j<cantidadNodos;j++){
				
				if(i != j && sonAdyacentes(i, j)){
					Adyacencia adyacencia = this.matrizAdyacencia.get(i, j);
					b.append(String.format("(%d,%d) = %d, ", i, j, adyacencia.getDistancia()));
				}
			}
		}
		
		return b.toString();
	}
	
	public List<Nodo> getNodos() {
		return nodos;
	}

	public int getCantidadNodos() {
		return this.nodos.size();
	}
	
	public int getCantidadAdyacencias(){
		int cantidad = 0;
		
		for(Nodo n1 : nodos){
			for(Nodo n2 : nodos){
				if(n1.esAdyacente(n2)){
					cantidad++;
				}
			}
		}
		
		return cantidad;
	}
	
	public double getPorcentajeAdyacencia(){
		double dimension = this.getMaximaCantidadDeAristasPosibles();
		double cantidadAdyacencias = this.getCantidadAdyacencias();
		
		return cantidadAdyacencias / dimension;
	}
	
	public double getMaximaCantidadDeAristasPosibles(){
		int cantidadNodos = getCantidadNodos();
		return cantidadNodos * (cantidadNodos - 1) / 2;
	}
	
	public int getGradoNodo(int indice) {
		return this.nodos.get(indice).getGrado();
	}
	
	public double getPorcentajeAdyacenciaRegulares(int grado){
		return grado * 100 / (getCantidadNodos() - 1);
	}
	
	public int getGradoSegunPorcentajeAdyacenciaRegulares(double porcentajeAdyacencia){
		return (int) porcentajeAdyacencia * (getCantidadNodos() - 1);
	}
	
	public double getCantidadAdyacenciasRegulares(int grado){
		int cantidadNodos = getCantidadNodos();
	 //todos los nodos tienen igual grado en los grafos regulares
		return cantidadNodos * grado / 2;
	}
	
	public Set<String> getColores(){
		Set<String> colores = new HashSet<String>();
		
		for(Nodo n : nodos){
			colores.add(n.getColor());
		}
		return colores;
	}
	
	public int getNumeroCromatico(){
		return getColores().size();
	}
	
	public String toStringGradosYNodos(){
		StringBuilder b = new StringBuilder();
		for(Nodo n : nodos){
			String format = String.format("N%dG%d | ", n.getIndice(),n.getGrado());
			b.append(format);
		}
		
		return b.toString();
	}
	
	public boolean chequearSiEsRegularDeGrado(int grado){
		boolean esValido = true;
		
		for(Nodo n : nodos){
			if(n.getGrado() != grado){
				esValido = false;
				break;
			}
		}

		return esValido;
	}
	
	
}
