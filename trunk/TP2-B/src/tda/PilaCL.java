package tda;

public class PilaCL implements Pila {
	
	private Lista l;
	
	public 	PilaCL() {
		l = new Lista();
	}
	
	public boolean empty() {
		return l.empty();
	}
	
	public void push(Object datos) {
		l.push_front(datos);
	}

	public Object pop(){
		return l.pop_front();
	}

	public Object peek() throws Exception {
		return l.buscar(1);
	}

	public void vaciar() {
		l.vaciar();
	}
	
	public void mostrar() {
		l.mostrar();
	}
	
	public static void main(String[] args) {
		
		try{
			Pila p = new PilaCL();
			double inicio, fin;
			String nombre = "Apilar";
			
			inicio = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++)
				p.push(i);
			fin = System.currentTimeMillis();
			System.out.println(nombre + ": " + (fin - inicio) + " ms");
			
			nombre= "Desapilar";
			inicio = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++)
				p.pop();
			
			fin = System.currentTimeMillis();
			System.out.println(nombre + ": " + (fin - inicio) + " ms");			
			}
			catch (Exception e) {
				System.out.println(e);
		}
	}
}
