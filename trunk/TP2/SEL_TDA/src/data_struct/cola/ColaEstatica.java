package data_struct.cola;

import java.util.Arrays;


public class ColaEstatica<T> implements Cola<T> {

	private static final Integer LONGITUD_ESTANDAR = 10;
	private T[] vectorElementos;
	private int tamanioCola;
	private int cabeza;
	private int cola;
	
	public ColaEstatica() {
		this(LONGITUD_ESTANDAR);
	}

	@SuppressWarnings("unchecked")
	public ColaEstatica(int tamanioCola) {
		this.tamanioCola = tamanioCola;
		vectorElementos = (T[]) new Object[tamanioCola];
	}

	public ColaEstatica(T[] vectorElementos) {
		this.vectorElementos = vectorElementos;
		this.tamanioCola = vectorElementos.length;
		this.cabeza = 0;
		this.cola = 0;
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = true;
		
		for(int i=0; i< tamanioCola; i++){
			if(vectorElementos[i] != null){
				isEmpty = false;
				break;
			}
		}
		
		return isEmpty;
	}

	@Override
	public void offer(T element) {
		if(this.isFull()){
			throw new ColaLlenaException();
		}
		vectorElementos[cola++] = element;
	}

	private boolean isFull() {
		boolean isFull = true;
		
		for(int i=0; i< tamanioCola; i++){
			isFull &= vectorElementos[i] != null;
		}
		
		return isFull;
	}

	@Override
	public T poll() {
		if(!this.isEmpty()){
			return vectorElementos[cabeza++];
		}
		return null;
	}

	@Override
	public T peek() {
		return vectorElementos[cabeza];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void vaciar() {
		cabeza = 0;
		cola = 0;
		tamanioCola = LONGITUD_ESTANDAR;
		vectorElementos = (T[]) new Object[tamanioCola];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(vectorElementos);
	}

	public static void main(String[] args) {
		// prueba isEmpty
		System.out.println("Inicio Prueba isEmpty()");
		ColaEstatica<Integer> colaVacia = new ColaEstatica<Integer>();
		System.out.println("Cola vacia: " + colaVacia.isEmpty());
		System.out.println("Cola llena: " + colaVacia.isFull());
		System.out.println("Fin Prueba isEmpty()");
		
		System.out.println();
		
		//prueba vaciar cola
		System.out.println("Inicio Prueba vaciar()");
		Cola<Integer> colaVaciada = new ColaEstatica<Integer>();
		colaVaciada.offer(2);
		System.out.println("Elemento proximo a descolar: " + colaVaciada.peek());
		System.out.println("Cola no vacia: " + colaVaciada.isEmpty());
		colaVaciada.vaciar();
		System.out.println("Cola vacia: " + colaVaciada.isEmpty());
		System.out.println("Fin Prueba vaciar()");
		
		//prueba offer
		Cola<Integer> colaOffer = new ColaEstatica<Integer>();
		colaOffer.offer(2);
		colaOffer.offer(3);
		System.out.println(colaOffer);
		System.out.println(colaOffer.poll());
		System.out.println(colaOffer.poll());
	}

}
