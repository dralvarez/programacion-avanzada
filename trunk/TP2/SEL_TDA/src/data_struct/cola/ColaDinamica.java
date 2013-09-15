package data_struct.cola;

import java.util.LinkedList;

public class ColaDinamica<T> implements Cola<T> {

	private LinkedList<T> lista = new LinkedList<T>();

	public ColaDinamica(LinkedList<T> lista) {
		this.lista = lista;
	}
	
	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public void offer(T element) {
		lista.addFirst(element);
	}

	@Override
	public T poll() {
		return lista.removeFirst();
	}

	@Override
	public T peek() {
		return lista.getFirst();
	}

	@Override
	public void vaciar() {
		lista.clear();
	}

}
