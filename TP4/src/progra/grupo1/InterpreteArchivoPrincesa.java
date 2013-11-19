package progra.grupo1;

import java.util.Iterator;
import java.util.List;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.helper.Interprete;
import progra.grupo1.utils.FileUtils;

public class InterpreteArchivoPrincesa implements Interprete<DatosPrincesa>{
	
	public DatosPrincesa generar(String path){
		
		DatosPrincesa resultado = new DatosPrincesa();
		
		List<String> contenido = FileUtils.leerArchivo(path);
		Iterator<String> iterator = contenido.iterator();
		String primeraLinea = iterator.next();
		String[] camposPrimeraLinea = primeraLinea.split(" ");
		
		int cantidadNodos = Integer.parseInt(camposPrimeraLinea[0]);
		int cantidadAristas = Integer.parseInt(camposPrimeraLinea[1]);
		int cantidadDragones = Integer.parseInt(camposPrimeraLinea[2]);
		
		String segundaLinea = iterator.next();
		String[] camposSegundaLinea = segundaLinea.split(" ");
		 
		int nodoPrincesa = Integer.parseInt(camposSegundaLinea[0])-1;
		int nodoPrincipe = Integer.parseInt(camposSegundaLinea[1])-1;
		
		String terceraLinea = iterator.next();
		String[] camposTerceraLinea = terceraLinea.split(" ");
		
		int[] nodosDragones = new int[cantidadDragones];
		for(int i=0; i<cantidadDragones; i++){
			nodosDragones[i] = Integer.parseInt(camposTerceraLinea[i])-1;
		}
		
		Grafo g = new Grafo(cantidadNodos);
		
		for(int i=3 ; i < cantidadAristas+3 ; i++){
			String[] linea = contenido.get(i).split(" ");
			int indiceNodo1 = Integer.parseInt(linea[0]) -1;
			int indiceNodo2 = Integer.parseInt(linea[1]) -1;
			int distancia = Integer.parseInt(linea[2]);
			
			Nodo nodo1 = g.getNodo(indiceNodo1);
			Nodo nodo2 = g.getNodo(indiceNodo2);
			
			g.addAdyacencia(nodo1, nodo2, distancia);
		}
		
		resultado.setGrafo(g);
		resultado.setIndicePrincesa(nodoPrincesa);
		resultado.setIndicePrincipe(nodoPrincipe);
		resultado.setIndicesDragones(nodosDragones);
		
		return resultado;
	}
}
