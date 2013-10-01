package tda;

public class PilaD implements Pila {
	
	public class Nodo {
		private Object dato;
		private Nodo sig;
		
		//CONSTRUCTORES NODO
		public Nodo(Object dato){
		this.dato=dato;
		this.sig=null;
		}
		
		public Nodo(){
		this(null);
		}
	}
	private Nodo pila;
	
	// CONSTRUCTORES
	
	public PilaD() {
		pila=new Nodo();
	}

	
	// METODOS
	

	public void mostrar() {
		Nodo aux = new Nodo();
		aux=pila;
		
		System.out.print("[");
		while(aux.sig!=null)
		{
			System.out.print(aux.dato + " - ");
			aux=aux.sig;
		}
		System.out.print("]\n");
	}


	
	// METODOS DE PILA
	
	public boolean empty(){
		if(pila==null)
			return true;
		else 
			return false;
	}
	
	public Object peek(){
		return pila.dato;
	}
	
	public void push(Object dato){

		Nodo aux= new Nodo();
		aux.sig=pila;
		aux.dato=dato;
		pila=aux;
		
	}
	
	public Object pop() throws Exception{
		if(this==null)
			throw new Exception("Pila Vacia");
		
		Object obj;
		obj=pila.dato;
		pila=pila.sig;
	
		return obj;
	}
	
	public void vaciar(){
		pila=new Nodo();
	}
	
	
	
	public static void main(String[] args) {
		
		try{
		Pila p=new PilaD();
		double inicio, fin;
		String nombre= "Apilar";
		
		inicio = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			p.push(i);
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre= "Desapilar";
		inicio = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			p.pop();
		
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}

}