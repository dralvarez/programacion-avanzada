package progra.grupo1;

import java.util.List;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.utils.FileUtils;

public class InterpreteArchivoGrafo {
	
	public Grafo generarGrafo(String path){
		
		List<String> contenido = FileUtils.leerArchivo(path);
		String primeraLinea = contenido.iterator().next();
		String[] camposPrimeraLinea = primeraLinea.split(" ");
		
		int cantidadNodos = Integer.parseInt(camposPrimeraLinea[0]);
		int cantidadAristas = Integer.parseInt(camposPrimeraLinea[1]);
		
		Grafo g = new Grafo(cantidadNodos);
		
		for(int i=1 ; i <= cantidadAristas ; i++){
			String[] linea = contenido.get(i).split(" ");
			int indiceNodo1 = Integer.parseInt(linea[0]);
			int indiceNodo2 = Integer.parseInt(linea[1]);
			int distancia = Integer.parseInt(linea[2]);
			
			Nodo nodo1 = g.getNodo(indiceNodo1);
			Nodo nodo2 = g.getNodo(indiceNodo2);
			
			g.addAdyacencia(nodo1, nodo2, distancia);
		}
		
		return g;
	}
}
