package progra.grupo1.generador;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class GeneradorGrafosRegulares {

	private static final int CANTIDAD_NODOS_DEFAULT = 500;

	private static final double[] PORCENTAJES_ADYACENCIA_DEFAULT = { 0.50,
			0.70, 0.90 };

	private static final int CANTIDAD_CORRIDAS_DEFAULT = 10000;

	public static void main(String[] args) {
		GeneradorGrafosRegulares gga = new GeneradorGrafosRegulares();
		
		int cantidadNodos = 500;
		for(int i=2; i<cantidadNodos;i++){
			
			Grafo g = gga.generar(cantidadNodos, i);
			
			System.out.println("Grado: " + i);
			System.out.println(g.toStringGradosYNodos());
			boolean chequearSiEsRegularDeGrado = g.chequearSiEsRegularDeGrado(i);
			if(!chequearSiEsRegularDeGrado){
				throw new RuntimeException("Se cago todoooooooo");
			}
		}
	}
	
	public Grafo generar(int cantidadNodos, int gradoNodos) {

		chequearSiEsPosibleGenerarGrafo(cantidadNodos, gradoNodos);

		Grafo g = new Grafo(cantidadNodos);

		int ciclo = 1;
		int gradoActual = 0;
		
		while (gradoActual < gradoNodos -1 ) {
			generarAdyacencias(g, ciclo);
			gradoActual+=2;
			ciclo++;
		}
		
		if(!esPar(gradoNodos)){
			ciclo = cantidadNodos / 2;
			generarAdyacencias(g, ciclo);
		}
		
		return g;
	}

	private boolean esPar(int numero) {
		return numero % 2 == 0;
	}

	private void generarAdyacencias(Grafo g, int ciclo) {
		int n = g.getCantidadNodos();
		
		for(int i=0; i<n; i++){
			
			Nodo nodo = g.getNodo(i);
			Nodo otroNodo = g.getNodo((i+ciclo)%n);
			
			if(!nodo.esAdyacente(otroNodo)){
				g.addAdyacencia(nodo, otroNodo);
			}
		}
	}
	
	private void chequearSiEsPosibleGenerarGrafo(int cantidadNodos,
			int gradoNodos) {
		if (cantidadNodos * gradoNodos % 2 != 0 || cantidadNodos < gradoNodos)
			throw new NoSePuedeGenerarUnGrafoRegularException(cantidadNodos, gradoNodos);
	}


	
}
