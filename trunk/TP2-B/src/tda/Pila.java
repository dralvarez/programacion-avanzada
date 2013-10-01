package tda;

public interface Pila {
	
	public boolean empty( );
	
	public void push(Object dato) throws Exception; 
	
	public Object pop( ) throws Exception; 
	
	public Object peek( ) throws Exception; 
	
	public void vaciar( );
	
	public void mostrar();

}
