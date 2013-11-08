package progra.grupo1.proceso.estrategia;

import java.util.Comparator;

import progra.grupo1.modelo.Nodo;

public class OrdenComparator implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o1.getIndice() - o2.getIndice();
	}

}
