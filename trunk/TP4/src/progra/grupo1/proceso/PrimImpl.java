package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.List;

import progra.grupo1.generador.GeneradorGrafosAleatoriosPorProbabilidad;
import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class PrimImpl {

	public List<Adyacencia> ejecutar(Grafo grafo){
		List<Nodo> nodos = grafo.getNodos();
		List<Adyacencia> arbol = new ArrayList<Adyacencia>();
		List<Nodo> nodosYaEvaluados = new ArrayList<Nodo>();
			
		if(!nodos.isEmpty()){
			
			int cantidadNodos = nodos.size();
			Nodo siguienteNodo = nodos.get(0);
			
			while(arbol.size() < cantidadNodos -1){
				
				nodosYaEvaluados.add(siguienteNodo);
				
				Adyacencia adyacenciaMinima = new Adyacencia(-1,-1);
				adyacenciaMinima.setDistancia(Integer.MAX_VALUE);
				for(Nodo nodoEnEvaluacion : nodosYaEvaluados){
					
					for(Nodo nodoDestino : nodos){
						if(nodoEnEvaluacion.esAdyacente(nodoDestino) && !nodosYaEvaluados.contains(nodoDestino)){
							Adyacencia adyacencia = grafo.getAdyacencia(nodoEnEvaluacion, nodoDestino);
							
							if(adyacencia.getDistancia() < adyacenciaMinima.getDistancia()){
								adyacenciaMinima = adyacencia;
								siguienteNodo = nodoDestino;
							}
						}
					}
				}
				
				arbol.add(adyacenciaMinima);
			}
			
		}

		return arbol;
	}
	
	public static void main(String[] args) {
		PrimImpl prim = new PrimImpl();
		GeneradorGrafosAleatoriosPorProbabilidad gen = new GeneradorGrafosAleatoriosPorProbabilidad();
		Grafo grafo = gen.generar(5, 0.4);
		
		List<Adyacencia> arbolAbarcador = prim.ejecutar(grafo);
		
		System.out.println(grafo);
		System.out.println("==========================");
		
		int costoTotal = 0;
				
		for(Adyacencia ady : arbolAbarcador){
			System.out.println(ady + " = " + ady.getDistancia());
			costoTotal +=ady.getDistancia();
		}
		
		System.out.println("Costo total: " + costoTotal);
		
	}
	
}
