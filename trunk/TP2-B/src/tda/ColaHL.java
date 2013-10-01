package tda;

public class ColaHL extends Lista implements Cola {

	public ColaHL() {
		super();
	}

	public boolean empty() {
		return super.empty();
	}

	public void push(Object datos) {
		super.push_back(datos);
	}

	public Object poll() {
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
		Cola p=new ColaHL();
		double inicio, fin;
		String nombre = "Encolar";
		
		inicio = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
			p.push(i);
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre= "Desencolar";
		inicio = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
			p.poll();
		
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
