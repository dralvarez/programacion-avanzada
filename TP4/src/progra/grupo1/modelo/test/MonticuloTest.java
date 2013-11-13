package progra.grupo1.modelo.test;

import java.util.ArrayList;
import java.util.List;

import progra.grupo1.modelo.Monticulo;

public class MonticuloTest {
	public static void main(String[] args) {
		int cantidadNodosInicial = 10;
		Monticulo m = new Monticulo(cantidadNodosInicial);
		
		for(int i=0 ; i < cantidadNodosInicial ; i++){
			String contenido = "LALALA_"+i;
			int prioridad = (int)(Math.random() * 100);
			m.push(prioridad, contenido);
		}
		
		System.out.println(m.deepToString());

		List<Object> contenidos = new ArrayList<Object>(cantidadNodosInicial);
		
		for(int i=0 ; i < cantidadNodosInicial ; i++){
			contenidos.add(m.pop());
		}
		
		System.out.println(contenidos);
		
	}

}
