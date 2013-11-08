package progra.grupo1.proceso;

import progra.grupo1.generador.GeneradorGrafosAleatoriosPorPorcentajeAdyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.helper.ColorProvider;

public class ColoreadorGrafos {

	ColorProvider provider = new ColorProvider();
	
	public void colorear(Grafo grafo){
		
		int cantidadNodos = grafo.getCantidadNodos();
		int indiceColores = 0;
		int cantidadNodosPintados = 0;
		
		while(cantidadNodosPintados < cantidadNodos){
			
			String color = provider.getColor(indiceColores);

			for(int i=0 ; i<cantidadNodos; i++){
				
				Nodo nodo = grafo.getNodo(i);
				
				if(nodo.getColor() != null){
					continue;
				}
				
				boolean sePuedePintar = true;
				
				for(int j=0 ; j<cantidadNodos; j++){
					if(i!=j){
						Nodo otroNodo = grafo.getNodo(j);
						
						if(	grafo.sonAdyacentes(nodo, otroNodo) && 
							color.equals(otroNodo.getColor())){
							
							sePuedePintar = false;
							break;
						}
					}
				}
				
				if(sePuedePintar){
					nodo.setColor(color);
					cantidadNodosPintados++;
				}
			}
			
			indiceColores++;
			
		}
		
	}
	
	
	public static void main(String[] args) {
		ColoreadorGrafos coloreador = new ColoreadorGrafos();
		
		GeneradorGrafosAleatoriosPorPorcentajeAdyacencia gga = new GeneradorGrafosAleatoriosPorPorcentajeAdyacencia();
		Grafo grafo = gga.generar(5, 0.5);
		
		coloreador.colorear(grafo);
		
		System.out.println(grafo);
		
	}
}
