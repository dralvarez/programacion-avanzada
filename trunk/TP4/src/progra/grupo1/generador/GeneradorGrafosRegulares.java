package progra.grupo1.generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;

public class GeneradorGrafosRegulares {
	
	private static final int CANTIDAD_NODOS_DEFAULT = 500;
	
	private static final double[] PORCENTAJES_ADYACENCIA_DEFAULT = {0.50,0.70,0.90};
	
	private static final int CANTIDAD_CORRIDAS_DEFAULT = 10000;
	
	public Grafo generar(int cantidadNodos, int gradoNodos){
		
		Grafo g = new Grafo(cantidadNodos);
	/*
		for(int i=0; i < cantidadNodos; i++){
			for(int j=0; j < cantidadNodos; j++){
				if ((i < j) && (g.getNodo(i).getGrado() < gradoNodos) && (g.getNodo(j).getGrado() < gradoNodos)){
					Adyacencia a = new Adyacencia(i, j);
					g.addAdyacencia(a);
					Adyacencia a2 = new Adyacencia(j, i);
					g.addAdyacencia(a2);
					g.getNodo(i).setGrado(g.getNodo(i).getGrado() + 1);
					g.getNodo(j).setGrado(g.getNodo(j).getGrado() + 1);
				}
			}
		}
*/
		return g;
	}

	public static void main(String[] args) {
		GeneradorGrafosRegulares gga = new GeneradorGrafosRegulares();
		Grafo grafo = gga.generar(5, 2);
		System.out.println("Cantidad de adyacencias: " + grafo.getCantidadAdyacenciasRegulares(2));
		System.out.println("Cantidad maxima de adyacencias: " + grafo.getMaximaCantidadDeAristasPosibles());
		System.out.println("Porcentaje adyacencia: " + grafo.getPorcentajeAdyacenciaRegulares(2));
		System.out.println(grafo);
	}
}
