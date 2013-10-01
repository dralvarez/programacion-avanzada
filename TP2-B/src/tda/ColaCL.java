package tda;

public class ColaCL implements Cola{
	private Lista l;
	
	public ColaCL(){
		l = new Lista();
	}

	public boolean empty(){
		return l.empty();
	}

	public void push(Object datos){
		l.push_back(datos);
	}

	public Object poll(){
		return l.pop_front();
	}

	public Object peek() throws Exception {
		return l.buscar(1);
	}

	public void vaciar(){
		l.vaciar();
	}
	
	public void mostrar() {
		l.mostrar();
	}

	public static void main(String[] args) {
		
		try{
		Cola p=new ColaCL();
		double inicio, fin;
		String nombre= "Encolar";
		
		inicio = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			p.push(i);
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre= "Desencolar";
		inicio = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			p.poll();
		
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}
}
