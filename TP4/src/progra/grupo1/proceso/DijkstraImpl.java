package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progra.grupo1.generador.GeneradorGrafosAleatoriosPorProbabilidad;
import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class DijkstraImpl {

	public Map<Nodo, Integer> ejecutar(Grafo grafo, Nodo nodoInicial) {
		Map<Nodo, Integer> distancias = crearMapaDeDistancias(grafo);
		
		List<Nodo> nodos = new ArrayList<Nodo>();
		nodos.add(nodoInicial);
		distancias.put(nodoInicial, 0);
		List<Nodo> nodosYaRevisados = new ArrayList<Nodo>();
		analizarDistancias(nodos, nodosYaRevisados, grafo, distancias);
		
		return distancias;
	}	

	private void analizarDistancias(List<Nodo> nodosOrigen, List<Nodo> nodosYaRevisados, Grafo grafo,
			Map<Nodo, Integer> distancias) {
		
		List<Nodo> nodosAdyacentes = new ArrayList<Nodo>();
		List<Nodo> nodosDestino = grafo.getNodos();
		
		for(int i=0; i < nodosOrigen.size();i++){
			Nodo nodoOrigen = nodosOrigen.get(i);
			for(int j=0; j < nodosDestino.size(); j++){
				Nodo nodoDestino = nodosDestino.get(j);
				
				if(nodoOrigen.esAdyacente(nodoDestino)){
					// Evito recursividad infinita
					if(!nodosYaRevisados.contains(nodoDestino)){
						nodosAdyacentes.add(nodoDestino);
					}
					Integer distanciaAlOrigen = distancias.get(nodoOrigen);
					Integer distanciaAlDestino = distancias.get(nodoDestino);
					Adyacencia adyacencia = grafo.getAdyacencia(nodoOrigen, nodoDestino);
					
					int nuevaDistancia = distanciaAlOrigen + adyacencia.getDistancia();
					
					if(nuevaDistancia < distanciaAlDestino){
						distancias.put(nodoDestino, nuevaDistancia);
					}
				}
			}
			nodosYaRevisados.add(nodoOrigen);
		}
		// Condicion de corte, si todavia tengo mas nodos por revisar
		if(!nodosAdyacentes.isEmpty()){
			analizarDistancias(nodosAdyacentes, nodosYaRevisados, grafo, distancias);
		}
		
	}

	private Map<Nodo, Integer> crearMapaDeDistancias(Grafo grafo) {
		int cantidadNodos = grafo.getCantidadNodos();
		Map<Nodo, Integer> distancias = new HashMap<Nodo, Integer>(cantidadNodos);
		
		for(int i=0;i < cantidadNodos ; i++){
			distancias.put(grafo.getNodo(i), Integer.MAX_VALUE);
		}
		
		return distancias;
	}
	
	public static void main(String[] args) {
		Grafo grafo = new GeneradorGrafosAleatoriosPorProbabilidad().generar(10, 0.5);
		DijkstraImpl dijkstra = new DijkstraImpl();
		
		Map<Nodo, Integer> distancias = dijkstra.ejecutar(grafo, grafo.getNodo(0));
		
		for(Nodo n : distancias.keySet()){
			System.out.println(n + " <<=>>" + distancias.get(n));
			
		}
		
	}
}
