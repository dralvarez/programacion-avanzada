package data_struct.pila;

public interface Pila<T> {
	
	T pop();
	void push(T element);
	boolean isEmpty();
	T peek();
	void vaciar();
	
}
