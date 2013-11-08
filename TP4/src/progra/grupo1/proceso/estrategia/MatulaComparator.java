package progra.grupo1.proceso.estrategia;

import java.util.Comparator;

import progra.grupo1.modelo.Nodo;

public class MatulaComparator implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o1.getGrado() - o2.getGrado();
	}

}
