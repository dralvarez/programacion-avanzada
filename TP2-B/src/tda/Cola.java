package tda;

public interface Cola {
	
	public boolean empty( );
	
	public void push(Object dato) throws Exception;
	
	public Object poll() throws Exception;
	
	public Object peek( ) throws Exception; 
	
	public void vaciar( );

	public void mostrar();
}
