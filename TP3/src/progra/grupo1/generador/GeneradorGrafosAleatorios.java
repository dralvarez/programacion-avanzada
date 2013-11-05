package progra.grupo1.generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class GeneradorGrafosAleatorios {
	
	private static final int CANTIDAD_NODOS_DEFAULT = 500;
	
	private static final double[] PORCENTAJES_ADYACENCIA_DEFAULT = {0.50,0.70,0.90};
	
	private static final int CANTIDAD_CORRIDAS_DEFAULT = 10000;
	
	public Grafo generar(int cantidadNodos, double porcentajeAdyacencia){
		
		Grafo g = new Grafo(cantidadNodos);
		int cantidadDeAristas = calcularCantidadDeAristas(cantidadNodos, porcentajeAdyacencia);
		
		List<Adyacencia> adyacenciasGeneradas = generarAdyacencias(cantidadNodos);

		for(int i=0 ; i< cantidadDeAristas ; i++){
			Adyacencia adyacencia = adyacenciasGeneradas.get(i);
			g.addAdyacencia(adyacencia.getIndice1(), adyacencia.getIndice2());
		}
		
		return g;
	}

	private List<Adyacencia> generarAdyacencias(int cantidadNodos) {
		List<Adyacencia> listaAdyacencias = new ArrayList<Adyacencia>(cantidadNodos);
		
		for(int i=0; i<cantidadNodos-1; i++){
			for(int j=i+1; j< cantidadNodos-1;j++){
				
				listaAdyacencias.add(new Adyacencia(i,j));
			}
		}
		
		Collections.sort(listaAdyacencias);
		
		return listaAdyacencias;
	}
	
	protected int calcularCantidadTotalDeAristas(final int cantidadNodos){
		return (int) (cantidadNodos*(cantidadNodos -1) / 2);
	}

	protected int calcularCantidadDeAristas(final int cantidadNodos,
			final double porcentajeAdyacencia) {
		return (int) (calcularCantidadTotalDeAristas(cantidadNodos) * porcentajeAdyacencia);
	}

	class Adyacencia implements Comparable<Adyacencia>{
		
		private int indice1;
		private int indice2;
		private double orden;
		
		public Adyacencia(int indice1, int indice2){
			this.indice1 = indice1;
			this.indice2 = indice2;
			orden = Math.random();
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

		@Override
		public int compareTo(Adyacencia o) {
			return this.orden > o.orden ? 1 : -1;
		}
		
	}
	
	public static void main(String[] args) {
		GeneradorGrafosAleatorios gga = new GeneradorGrafosAleatorios();
		Grafo grafo = gga.generar(200, 0.5);
		
		System.out.println(grafo);
	}
}
