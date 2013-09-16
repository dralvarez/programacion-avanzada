package data_struct.pila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PilaDinamica<T> implements Pila<T> {

	private List<T> lista = new ArrayList<T>(); 
	
	public PilaDinamica() {
		this.lista = lista;
	}
	
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
		
	@Override
	public String toString() {
		return "PilaDinamica [lista=" + lista + "]";
	}

	public static void main(String[] args) {
		// prueba isEmpty
		System.out.println("Inicio Prueba isEmpty()");
		PilaDinamica<Integer> pilaVacia = new PilaDinamica<>();
		System.out.println("Pila vacia: " + pilaVacia.isEmpty());
		System.out.println("Fin Prueba isEmpty()");
			
		//prueba vaciar pila
		System.out.println("Inicio Prueba vaciar()");
		Pila<Integer> pilaVaciada = new PilaDinamica<Integer>();
		pilaVaciada.push(2);
		pilaVaciada.push(8);
		pilaVaciada.push(9);
		System.out.println("Elemento proximo a desapilar: " + pilaVaciada.peek());

		System.out.println("Pila no vacia: " + pilaVaciada.isEmpty());
		pilaVaciada.vaciar();
		System.out.println("Pila vacia: " + pilaVaciada.isEmpty());
		System.out.println("Fin Prueba vaciar()");
		
		//prueba push
		Pila<Integer> pilaPush = new PilaDinamica<Integer>();
		pilaPush.push(1);
		pilaPush.push(5);
		pilaPush.push(2);
		System.out.println(pilaPush);
		System.out.println("pop: " + pilaPush.pop());
		System.out.println(pilaPush);
		System.out.println("peek: " + pilaPush.peek());
		pilaPush.vaciar();
		System.out.println(pilaPush);
	}
}