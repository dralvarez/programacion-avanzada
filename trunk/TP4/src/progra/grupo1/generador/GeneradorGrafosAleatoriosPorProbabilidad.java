package progra.grupo1.generador;

import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;

public class GeneradorGrafosAleatoriosPorProbabilidad {
	
	private static final int CANTIDAD_NODOS_DEFAULT = 500;
	
	private static final double[] PORCENTAJES_ADYACENCIA_DEFAULT = {0.50,0.70,0.90};
	
	private static final int CANTIDAD_CORRIDAS_DEFAULT = 10000;
	
	public Grafo generar(int cantidadNodos, double probabilidadAdyacencia){
		
		Grafo g = new Grafo(cantidadNodos);
		
		for(int i=0; i < cantidadNodos; i++){
			for(int j=0; j < cantidadNodos; j++){
				if(i != j){
					Adyacencia a = new Adyacencia(i, j);
					
					if(a.getOrden() < probabilidadAdyacencia){
						g.addAdyacencia(a);
					}
				}
			}
		}

		return g;
	}

	public static void main(String[] args) {
		GeneradorGrafosAleatoriosPorProbabilidad gga = new GeneradorGrafosAleatoriosPorProbabilidad();
		Grafo grafo = gga.generar(5000, 0.5);
		System.out.println("Cantidad de adyacencias: " + grafo.getCantidadAdyacencias());
		System.out.println("Cantidad maxima de adyacencias: " + grafo.getMaximaCantidadDeAristasPosibles());
		System.out.println("Porcentaje adyacencia: " + grafo.getPorcentajeAdyacencia());
		System.out.println(grafo);
	}
}
