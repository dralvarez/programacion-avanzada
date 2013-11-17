package progra.grupo1.proceso.helper;

import java.util.Collections;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class VerificadorColoreadorWelshPowell implements VerificadorColoreador {

	@Override
	public boolean verificar(Grafo grafo) {
		int numeroCromatico = grafo.getNumeroCromatico();
		double porcentajeAdyacencia = grafo.getPorcentajeAdyacencia();
		Nodo nodoGradoMinimo = Collections.min(grafo.getNodos(), new NodoPorGradoComparator());
		int gradoMinimo = nodoGradoMinimo.getGrado();
		
		int numeroCromaticoEsperado = (int) Math.pow(3+0.019*(gradoMinimo + porcentajeAdyacencia), 2);
		
		return numeroCromatico <= numeroCromaticoEsperado;
	}

}
