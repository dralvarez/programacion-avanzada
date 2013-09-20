package data_struct.pila;

import java.util.Arrays;

public class PilaEstatica<T> implements Pila<T> {

	private static final Integer LONGITUD_ESTANDAR = 10;
	private T[] vectorElementos;
	private int tamanioPila;
	private int cursor;
	
	public PilaEstatica() { 
		this(LONGITUD_ESTANDAR);
		this.cursor = 0;
	}
	
	public PilaEstatica(int tamanioPila) {
		this.tamanioPila = tamanioPila;
		vectorElementos = (T[]) new Object[tamanioPila];
		this.cursor = 0;
	}
	
	public PilaEstatica(T[] vectorElementos) {
		this.vectorElementos = vectorElementos;
		this.tamanioPila = vectorElementos.length;
		this.cursor = 0;
	}
	
	@Override
	public T pop() { //elimina el primer elemento de la pila (tope) y devuelve el nuevo tope de pila
		if(!this.isEmpty()){
			T elemento = vectorElementos[--cursor];
			vectorElementos[cursor] = null;
			return elemento;
		}
		return null;
	}

	@Override
	public void push(T element) {
		if(this.isFull()){
			throw new PilaLlenaException();
		}
		vectorElementos[cursor++] = element;
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = true;
		
		for(int i=0; i< tamanioPila; i++){
			if(vectorElementos[i] != null){
				isEmpty = false;
				break;
			}
		}
		
		return isEmpty;
	}
	
	@Override
	public T peek() {
		if(!this.isEmpty()){
			return vectorElementos[cursor-1];
		}
		return null;
	}

	@Override
	public void vaciar() {
		cursor = 0;
		tamanioPila = LONGITUD_ESTANDAR;
		vectorElementos = (T[]) new Object[tamanioPila];
	}
	
	public boolean isFull(){
		boolean isFull = true;
		
		for(int i=0; i< tamanioPila; i++){
			isFull &= vectorElementos[i] != null;
		}
		
		return isFull;
	}

	@Override
	public String toString() {
		return Arrays.toString(vectorElementos);
	}
	
	
	public static void main(String[] args) {
		// prueba isEmpty
		System.out.println("Inicio Prueba isEmpty()");
		PilaEstatica<Integer> pilaVacia = new PilaEstatica<Integer>();
		System.out.println("Pila vacia: " + pilaVacia.isEmpty());
		System.out.println("Pila llena: " + pilaVacia.isFull());
		System.out.println("Fin Prueba isEmpty()");
			
		//prueba vaciar pila
		System.out.println("Inicio Prueba vaciar()");
		Pila<Integer> pilaVaciada = new PilaEstatica<Integer>();
		pilaVaciada.push(2);
		pilaVaciada.push(8);
		pilaVaciada.push(9);
		System.out.println("Elemento proximo a desapilar: " + pilaVaciada.peek());

		System.out.println("Pila no vacia: " + pilaVaciada.isEmpty());
		pilaVaciada.vaciar();
		System.out.println("Pila vacia: " + pilaVaciada.isEmpty());
		System.out.println("Fin Prueba vaciar()");
		
		//prueba push
		Pila<Integer> pilaPush = new PilaEstatica<Integer>();
		pilaPush.push(1);
		pilaPush.push(5);
		pilaPush.push(2);
		System.out.println(pilaPush);
		System.out.println("pop: " + pilaPush.pop());
		System.out.println(pilaPush);
		System.out.println("peek: " + pilaPush.peek());
		pilaPush.vaciar();
		System.out.println(pilaPush);
		
		//prueba isFull
		PilaEstatica<Integer> pilaFull = new PilaEstatica<Integer>();
		pilaFull.push(1);
		pilaFull.push(5);
		pilaFull.push(2);
		pilaFull.push(1);
		pilaFull.push(5);
		pilaFull.push(2);
		pilaFull.push(1);
		pilaFull.push(5);
		pilaFull.push(1);
		pilaFull.push(5);
		System.out.println("isFull: " + pilaFull.isFull());
		pilaFull.vaciar();
	}
}
