package tda;

public class PilaHL extends Lista implements Pila {

	public PilaHL() {
		super();
	}

	public boolean empty() {
		return super.empty();
	}

	public void push(Object datos) {
		super.push_front(datos);
	}

	public Object pop() {
		return super.pop_front();
	}

	public Object peek() throws Exception {
		return super.buscar(1);
	}

	public void vaciar() {
		super.vaciar();
	}

	public static void main(String[] args) {
		
		try{
			Pila p = new PilaHL();
			double  inicio, 
					fin;
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
