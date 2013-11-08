package progra.grupo1.proceso.estrategia;

import java.util.Comparator;

import progra.grupo1.modelo.Nodo;

public class WelshPowellComparator implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o2.getGrado() - o1.getGrado();
	}

}
