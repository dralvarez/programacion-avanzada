package progra.grupo1.proceso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import progra.grupo1.InterpreteArchivoGrafo;
import progra.grupo1.generador.GeneradorGrafosAleatoriosPorPorcentajeAdyacencia;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.estrategia.WelshPowellComparator;
import progra.grupo1.proceso.helper.ColorProvider;
import progra.grupo1.proceso.helper.VerificadorColoreador;
import progra.grupo1.proceso.helper.VerificadorColoreadorWelshPowell;

public class ColoreadorGrafos {

	private static final Comparator<Nodo> DEFAULT_STRATEGY = new WelshPowellComparator();
	
	ColorProvider provider = new ColorProvider();
	
	public void colorear(Grafo grafo, Comparator<Nodo> estrategiaOrden){
		
		int cantidadNodos = grafo.getCantidadNodos();
		int indiceColores = 0;
		int cantidadNodosPintados = 0;
		
		List<Nodo> nodos = new ArrayList<Nodo>(grafo.getNodos());
		
		Collections.sort(nodos, estrategiaOrden);
		
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
	
	public void colorear(Grafo grafo){		
		colorear(grafo, getDefaultStrategy());	
	}
	
	
	private Comparator<Nodo> getDefaultStrategy() {
		return DEFAULT_STRATEGY;
	}

	public static void main(String[] args) {
		ColoreadorGrafos coloreador = new ColoreadorGrafos();
		GeneradorGrafosAleatoriosPorPorcentajeAdyacencia gga = new GeneradorGrafosAleatoriosPorPorcentajeAdyacencia();
		InterpreteArchivoGrafo interprete = new InterpreteArchivoGrafo();
		VerificadorColoreador verificador = new VerificadorColoreadorWelshPowell();
		
//		Grafo grafo = interprete.generarGrafo("recursos/grafo.in");
		Grafo grafo = gga.generar(30,0.4);
		assert grafo.getCantidadNodos() == 6;
		assert grafo.getCantidadAdyacencias() == 7;
//		Grafo grafo = gga.generar(1000, 0.5);
		
		coloreador.colorear(grafo);		
		System.out.println(grafo);
		System.out.println("Colores: " + grafo.getColores());
		System.out.println("Numero cromatico: " + grafo.getNumeroCromatico());
		
		boolean pasoVerificacion = verificador.verificar(grafo);
		
		System.out.println("Resultado verificacion: " + pasoVerificacion);
		
		
	}
}