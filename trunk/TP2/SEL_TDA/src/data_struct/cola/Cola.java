package data_struct.cola;

public interface Cola<T> {
	
	boolean isEmpty();
	void offer(T element);
	T poll();
	T peek();
	void vaciar();
}
