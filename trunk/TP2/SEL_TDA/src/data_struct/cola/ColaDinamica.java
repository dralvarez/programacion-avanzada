package data_struct.cola;

import java.util.ArrayList;
import java.util.List;

public class ColaDinamica<T> implements Cola<T> {

	private List<T> lista = new ArrayList<T>();

	public ColaDinamica(List<T> lista) {
		this.lista = lista;
	}
	
	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public void offer(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void vaciar() {
		lista.clear();
	}

}
