package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class FloydImpl {

	public List<Map<Nodo, Integer>> ejecutar(Grafo grafo){
		DijkstraImpl dijkstra = new DijkstraImpl();
		
		List<Nodo> nodos = grafo.getNodos();
		
		List<Map<Nodo, Integer>> resultado = new ArrayList<Map<Nodo, Integer>>(nodos.size());
		
		for(Nodo nodo : nodos){
			Map<Nodo, Integer> resultadoNodo = dijkstra.ejecutar(grafo, nodo);
			resultado.add(resultadoNodo);
		}
		
		return resultado;
	}
}
