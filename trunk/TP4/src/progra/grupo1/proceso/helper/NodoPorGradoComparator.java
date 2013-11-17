package progra.grupo1.proceso.helper;

import java.util.Comparator;

import progra.grupo1.modelo.Nodo;

public class NodoPorGradoComparator implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o1.getGrado() - o2.getGrado();
	}

}
