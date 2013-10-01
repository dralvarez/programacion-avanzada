package tda;

public class ColaPrioridad implements Cola {
	private Monticulo cola;

	ColaPrioridad() {
		cola = new Monticulo();
	}

	public boolean empty() {
		return cola.esMonticuloVacio();
	}

	public void push(Object dato) {
		cola.insertar((Integer) dato);
	}

	public Integer poll() {
		return cola.eliminar();
	}

	public Integer peek() {
		return cola.verPadre();
	}

	public void vaciar() {
		cola.vaciarMonticulo();
	}
	
	public void mostrar() {
		cola.mostrar();
	}
	
	public static void main(String[] args) {
		
		Cola c = new ColaPrioridad();
		
		try {
			c.push(new Integer(5));
			c.push(new Integer(3));
			c.push(new Integer(30));
			c.push(new Integer(2));
			c.mostrar();
			System.out.println(c.poll());
			System.out.println(c.peek());
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}