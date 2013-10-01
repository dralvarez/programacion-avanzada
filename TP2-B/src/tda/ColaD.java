package tda;

public class ColaD implements Cola {

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
	
	private Nodo pri;
	private Nodo ult;
	
	// CONSTRUCTOR
	
	public ColaD() {
		pri=null;
		ult=null;
	}
	
	// METODOS
	
	public void mostrar() {
			
		Nodo aux = new Nodo();
		aux=this.pri;
		
		System.out.print("[");
		while(aux!=null) {
			System.out.print(aux.dato + " - ");
			aux=aux.sig;
		}
		System.out.print("]\n");
	}

	// METODOS DE COLA
	
	public boolean empty() {
		if(pri==null)
			return true;
		return false;
	}

	public void push(Object dato) {
		
		Nodo aux=new Nodo();
		aux.dato=dato;
		aux.sig=null;
		
		if(empty()==true) {
			pri=aux;
			ult=aux;
		}
		else {
			ult.sig=aux;
			ult=aux;
		}
	}

	@Override
	public Object poll() throws Exception {
		if(pri==null)
			throw new Exception ("La cola está vacía");
		
		Nodo aux=new Nodo();
		aux.dato=pri.dato;
		if(pri.sig == null)
			return null;
		
		pri.sig=(pri.sig).sig;
		
		return aux.dato;
	}

	@Override
	public Object peek() throws Exception {
	if(pri==null)
		throw new Exception ("La cola está vacía");
	
	return pri.dato;
	}

	@Override
	public void vaciar() {
		pri=null;
		ult=null;	
	}

	public static void main(String[] args) {
		try{
		Cola p=new ColaD();
		double inicio, fin;
		String nombre= "Encolar";
		
		inicio = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			p.push(i);
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre= "Desencolar";
		inicio = System.currentTimeMillis();
		for(int i=0; i<999999; i++)
			p.poll();
		
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");

		
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}
}
