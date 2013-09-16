package data_struct.pila;

import java.util.ArrayList;
import java.util.List;

public class PilaDinamica<T> implements Pila<T> {

	private List<T> lista = new ArrayList<T>(); 
	
	public PilaDinamica(List<T> lista) {
		this.lista = lista;
	}

	@Override
	public T pop() {
		return this.isEmpty() ? null : lista.remove(lista.size()-1);
	}

	@Override
	public void push(T element) {
		lista.add(element);
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public T peek() {
		return lista.get(lista.size()-1);
	}

	@Override
	public void vaciar() {
		lista.clear();
	}
}
