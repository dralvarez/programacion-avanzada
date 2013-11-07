package progra.grupo1.generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;

public class GeneradorGrafosAleatoriosPorPorcentajeAdyacencia {
	
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
		
		for(int i=0; i < cantidadNodos; i++){
			for(int j=0; j < cantidadNodos ;j++){
				if(i != j)
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

	public static void main(String[] args) {
		GeneradorGrafosAleatoriosPorPorcentajeAdyacencia gga = new GeneradorGrafosAleatoriosPorPorcentajeAdyacencia();
		Grafo grafo = gga.generar(5, 0.5);
		
		System.out.println(grafo);
	}
}
