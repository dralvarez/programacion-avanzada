package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import progra.grupo1.InterpreteArchivoGrafo;
import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.helper.AdyacenciaPorDistanciaComparator;
import progra.grupo1.proceso.helper.Interprete;

public class KruskalImpl {

	public List<Adyacencia> ejecutar(Grafo grafo){
		List<Adyacencia> arbolAbarcador = new ArrayList<Adyacencia>();
		
		List<Adyacencia> adyacencias = getAdyacencias(grafo);
		
		Collections.sort(adyacencias, new AdyacenciaPorDistanciaComparator());
		
		Set<Nodo> nodosUnidos = new HashSet<Nodo>();
		
		int i=0;
		while(nodosUnidos.size() < grafo.getCantidadNodos() && i < adyacencias.size()){
			Adyacencia a = adyacencias.get(i);
			
			if(!cierraCiclo(a, grafo, nodosUnidos)){
				arbolAbarcador.add(a);
				Nodo nodo1 = grafo.getNodo(a.getIndice1());
				Nodo nodo2 = grafo.getNodo(a.getIndice2());
				nodosUnidos.add(nodo1);
				nodosUnidos.add(nodo2);
			}
			i++;
		}
		
		if(nodosUnidos.size() < grafo.getCantidadNodos()){
			throw new RuntimeException("No se puede hallar arbol abarcador de costo minimo sin cerrar un ciclo.");
		}
		
		return arbolAbarcador;
	}

	private boolean cierraCiclo(Adyacencia a, Grafo grafo, Set<Nodo> nodosUnidos) {
		Nodo nodo1 = grafo.getNodo(a.getIndice1());
		Nodo nodo2 = grafo.getNodo(a.getIndice2());

		return nodosUnidos.contains(nodo1) && nodosUnidos.contains(nodo2);
	}

	private List<Adyacencia> getAdyacencias(Grafo grafo) {
		List<Adyacencia> adyacencias = new ArrayList<Adyacencia>();
		
		for(Nodo n1 : grafo.getNodos()){
			for(Nodo n2 : grafo.getNodos()){
				if(n1.esAdyacente(n2)){
					Adyacencia adyacencia = grafo.getAdyacencia(n1, n2);
					adyacencias.add(adyacencia);
				}
			}
		}
		
		return adyacencias;
	}
	
	public static void main(String[] args) {
		Interprete<Grafo> interprete = new InterpreteArchivoGrafo();
		KruskalImpl kruskal = new KruskalImpl();
		Grafo grafo = interprete.generar("recursos/dijkstra2.in");
		
		List<Adyacencia> arbol = kruskal.ejecutar(grafo);
		
		System.out.println(arbol);
		
	}
	
	
}
