package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progra.grupo1.InterpreteArchivoGrafo;
import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class DijkstraImpl {

	public ResultadoDijkstra ejecutar(Grafo grafo, Nodo nodoInicial) {
		Map<Nodo, Integer> distancias = crearMapaDeDistancias(grafo);
		Map<Nodo, Nodo> camino = new HashMap<Nodo,Nodo>(grafo.getCantidadNodos());
		List<Nodo> nodosOrigen = new ArrayList<Nodo>();
		nodosOrigen.add(nodoInicial);
		distancias.put(nodoInicial, 0);
		List<Nodo> nodosYaRevisados = new ArrayList<Nodo>();
		
		analizarDistancias(nodosOrigen, nodosYaRevisados, grafo, distancias, camino);
		
		return new ResultadoDijkstra(distancias, camino);
	}	

	private void analizarDistancias(List<Nodo> nodosOrigen, List<Nodo> nodosYaRevisados, Grafo grafo,
			Map<Nodo, Integer> distancias, Map<Nodo, Nodo> camino) {
		
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
						camino.put(nodoDestino, nodoOrigen);
						distancias.put(nodoDestino, nuevaDistancia);
					}
				}
			}
			nodosYaRevisados.add(nodoOrigen);
		}
		// Condicion de corte, si todavia tengo mas nodos por revisar
		if(!nodosAdyacentes.isEmpty()){
			analizarDistancias(nodosAdyacentes, nodosYaRevisados, grafo, distancias,camino);
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
		DijkstraImpl dijkstra = new DijkstraImpl();
		InterpreteArchivoGrafo interprete = new InterpreteArchivoGrafo();
		
		Grafo grafo = interprete.generarGrafo("recursos/dijkstra2.in");
		Nodo origen = grafo.getNodo(0);
		Nodo destino = grafo.getNodo(4);
		
		List<Nodo> caminoMasCorto = dijkstra.getCaminoMasCorto(grafo, origen, destino);
		
		System.out.println(caminoMasCorto);
		
		System.out.println("Grado minimo: " + grafo.getGradoMinimo());
		System.out.println("Grado maximo: " + grafo.getGradoMaximo());
		
//		ResultadoDijkstra resultado = dijkstra.ejecutar(grafo, grafo.getNodo(0));
//		Map<Nodo, Integer> distancias = resultado.getDistancias();
//		Map<Nodo, Nodo> camino = resultado.getCamino();
		
//		for(Nodo n : distancias.keySet()){
//			System.out.println(n + " <<=>>" + distancias.get(n));
//		}
//		
//		for(Nodo n: camino.keySet()){
//			System.out.println(String.format("Para llegar a %s, el previo es %s", n, camino.get(n)));
//		}
		
	}
	
	public List<Nodo> getCaminoMasCorto(Grafo g, Nodo origen, Nodo destino){
		List<Nodo> caminoMasCorto = new ArrayList<Nodo>();
		
		ResultadoDijkstra ejecutar = this.ejecutar(g, origen);
		Map<Nodo, Nodo> camino = ejecutar.getCamino();
		
		Nodo nodoPrevio = camino.get(destino);
		caminoMasCorto.add(destino);
		while(!nodoPrevio.equals(origen)){
			caminoMasCorto.add(nodoPrevio);
			nodoPrevio = camino.get(nodoPrevio);
		}
		caminoMasCorto.add(origen);
		
		Collections.reverse(caminoMasCorto);
		
		return caminoMasCorto;
	}
	
	
	public class ResultadoDijkstra{
		private Map<Nodo, Integer> distancias;
		private Map<Nodo, Nodo> camino;

		public ResultadoDijkstra(Map<Nodo, Integer> distancias,
				Map<Nodo, Nodo> camino) {
			this.distancias = distancias;
			this.camino = camino;
		}

		public Map<Nodo, Integer> getDistancias() {
			return distancias;
		}

		public Map<Nodo, Nodo> getCamino() {
			return camino;
		}
		
	}
}
