package data_struct.pila;

import java.util.Arrays;

public class PilaEstatica<T> implements Pila<T> {

	private T[] vectorElementos;
	private int tamanioPila;
	private int cursor;
	
	public PilaEstatica(int tamanioPila) {
		this.tamanioPila = tamanioPila;
	}

	@Override
	public T pop() {
		if(!this.isEmpty()){
			return vectorElementos[cursor--];
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
		return vectorElementos[cursor];
	}

	@Override
	public void vaciar() {
		vectorElementos = null;
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
}
