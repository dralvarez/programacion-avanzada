package tda;

public class Monticulo {
	private Integer[] vector;
	private int tamUsado;

	public Monticulo() {
		vector = new Integer[1];
		tamUsado = 0;
	}

	private void resize() {
		Integer[] aux = new Integer[vector.length * 2];
		for (int i = 1; i <= tamUsado; i++) {

			aux[i] = this.vector[i];
		}
		this.vector = aux;
	}

	private Integer hijoIzquierda(int i) {

		if (tamUsado < 2 * i) {
			return null;
		} else {
			return vector[2 * i];
		}
	}

	private Integer hijoDerecha(int i) {

		if (tamUsado < (2 * i + 1)) {
			return null;
		} else {
			return vector[2 * i + 1];
		}
	}

	public boolean esMonticuloVacio() {

		return (tamUsado == 0);
	}

	private boolean esMonticuloLleno() {

		return (tamUsado == vector.length - 1);
	}

	public void vaciarMonticulo() {

		for (int i = 1; i < tamUsado; i++) {

			vector[i] = null;
		}
		tamUsado = 0;
	}

	public Integer verPadre() {

		if (tamUsado < 1) {

			return null;
		} else {

			return vector[1];
		}
	}

	private Integer padre(int i) {

		if (tamUsado < i) {

			return null;
		}

		int parteEntera = (int) (i / 2);
		return vector[parteEntera];
	}

	private boolean esNodoHoja(int pos) {

		if (hijoIzquierda(pos) != null && hijoDerecha(pos) != null) {

			return false;
		}

		return true;
	}

	public void insertar(Integer value) {
		if (esMonticuloLleno()) {
			resize();
		}

		this.tamUsado++;
		vector[tamUsado] = value;

		int i = tamUsado;
		
		while (i > 1 && vector[i / 2].intValue() < vector[i].intValue()) {

			Integer aux = vector[i / 2];
			vector[i / 2] = vector[i];
			vector[i] = aux;

			i = i / 2;
		}
	}

	public Integer eliminar() {

		if (esMonticuloVacio()) {
			return null;
		}

		if (tamUsado == 1) {
			Integer aux = vector[1];
			vector[1] = null;
			tamUsado--;
			return aux;
		}

		Integer retorno = vector[1];
		vector[1] = vector[tamUsado];
		vector[tamUsado] = null;
		tamUsado--;

		int i = 1;
		
		while (i * 2 < tamUsado) {
			// Se calculan las posiciones de los hijos
			int izq = i * 2;
			int der = i * 2 + 1;
			int ladoACambiar; // lado candidato a cambiar

			// Si no tiene un hijo derecho solo cambia al izquierdo
			if (der > tamUsado) {
				
				ladoACambiar = izq;
			} else {

				// En cambio si tiene dos hijos, se fija quien es el mayor para cambiar
				if (vector[izq] >= vector[der]) {

					ladoACambiar = izq;
				} else {

					ladoACambiar = der;
				}
			}

			// Si el valor de la posicion actual es mayor al candidato se cambia
			if (vector[i] < vector[ladoACambiar]) {

				Integer aux = vector[i];
				vector[i] = vector[ladoACambiar];
				vector[ladoACambiar] = aux;
				i = ladoACambiar;

			}
		}

		return retorno;
	}

	public void mostrar() {

		if (!esMonticuloVacio()) {

			int i = 1;

			while (i <= tamUsado) {

				System.out.println(i + ": " + vector[i]);
				i++;
			}
		}
	}

	public static void main(String[] args) {
		Monticulo m = new Monticulo();

		m.insertar(7);
		m.insertar(20);
		m.insertar(2);
		m.insertar(8);
		m.insertar(90);
		m.insertar(5);
		m.insertar(1);
		System.out.println(m.eliminar());
		m.mostrar();
	}
}