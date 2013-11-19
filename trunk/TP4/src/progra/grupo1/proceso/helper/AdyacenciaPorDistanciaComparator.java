package progra.grupo1.proceso.helper;

import java.util.Comparator;

import progra.grupo1.modelo.Adyacencia;

public class AdyacenciaPorDistanciaComparator implements Comparator<Adyacencia> {

	@Override
	public int compare(Adyacencia o1, Adyacencia o2) {
		return o1.getDistancia() - o2.getDistancia();
	}

}
