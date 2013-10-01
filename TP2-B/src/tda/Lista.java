package tda;

import tda.PilaD.Nodo;

public class Lista {

	public class Nodo {
		
		private Object dato;
		private Nodo sig;
		
		public Nodo( Object dato) {
			this.dato = dato;
			this.sig = null;
		}
		
		public Nodo() {
			this(null);
		}
	}
	
	private Nodo primero;
	private Nodo ultimo;
	
	public Lista () {
		primero = null;
		ultimo = null;
	}
	
	//METODOS 
	
	public void mostrar() {
		Nodo aux = new Nodo();
		aux = primero;
		
		System.out.print("[");
		while(aux != null) {
			System.out.print(aux.dato + " - ");
			aux=aux.sig;
		}
		System.out.print("]\n");
	}

	public void push_back(Object dato) {
		if(primero == null) {
			ultimo = new Nodo(dato);
			primero = ultimo;
		}
		else {
			Nodo aux = new Nodo(dato);
			ultimo.sig = aux;
			
			if (ultimo == primero)
				primero.sig = aux;
			ultimo = aux;	
		}
	}
	
	public Object pop_back() {
		
		if (primero == null)
			return null;
		
		Nodo aux = new Nodo();
		Nodo aux1 = new Nodo();
		aux = ultimo;
		
		if(primero == ultimo) {
			primero = null;
			ultimo = null;
		}
		else {
			aux1 = primero;
			while (aux1.sig != aux)
				aux1 = aux1.sig;
			ultimo = aux1;
			ultimo.sig = null;
		}
		return aux.dato;
	}
		
	public void push_front(Object dato) {
		if(primero == null) {
			primero = new Nodo(dato);
			ultimo = primero;
		}
		else {
			Nodo aux = new Nodo(dato);
			aux.sig = primero;
			primero = aux;	
		}
	}
		
	public Object pop_front() {
		
		if (primero == null)
			return null;
		
		Nodo aux = new Nodo();
		aux=primero;
		
		if (primero == ultimo)
			ultimo = null;

		primero = primero.sig;
		return aux.dato;
	}

	public void insert (int pos, Object dato) throws Exception{
			
		int cont = 0;
		Nodo aux1 = new Nodo(dato);
		aux1 = primero;
		
		while (aux1 != null) {
			cont++;
			aux1 = aux1.sig;
		}
		
		if (pos <= 0 || cont < pos)
			throw new Exception("La posición no existe");
		
		Nodo aux=new Nodo(dato);
		
		if (pos == 1) {
			aux.sig=this.primero;
			this.primero=aux;
			if(ultimo==null)
				ultimo=aux;
		}
		else {
			Nodo rec = new Nodo();
			rec = this.primero;
			cont = 1;
			
			while (cont < pos)	{
				if (cont + 1 == pos) {
					aux.sig = rec.sig;
					rec.sig = aux;
					cont++;
					
					if (aux.sig==null)
						ultimo=aux;
				}
				else {
					rec = rec.sig;
					cont++;
				}
			}
		}
	}
	
	public void remove(Object dato) throws Exception {
		if (primero == null)
			throw new Exception ("Lista Vacia");
		
		Nodo rec = new Nodo();
		rec = this.primero;
		Nodo aux = new Nodo();
		aux = this.primero;
		
		if (rec == ultimo && rec.dato == dato)
			aux = primero = ultimo = null;
		if (rec.dato == dato)
			aux = rec.sig;
		rec = rec.sig;
		
		while (rec != null) {
			if (rec.dato == dato) {
				primero.sig = rec.sig;
			}
			rec = rec.sig;
			primero = primero.sig;
		}
		primero = aux;
	}
	
	public void reverse () {
		
		Lista l = new Lista();
		
		while (primero!=null)
			l.push_back(this.pop_back());
		
		this.primero = l.primero;
		this.ultimo = l.ultimo;
	}
	
	public Object buscar(int pos) throws Exception {
		int cont = 1;
		Nodo aux1 = new Nodo();
		aux1 = primero;
		
		while(aux1 != null && cont < pos) {
			cont++;
			aux1 = aux1.sig;
		}
		if (pos < 1 || cont != pos)
			throw new Exception("La posición no existe");
		
		return aux1.dato;
	}
	
	public Object buscar(Object dato) throws Exception {  //revisar
		int cont = 1;
		Nodo aux1 = new Nodo();
		aux1 = primero;
		
		while(aux1!=null && aux1.dato!=dato) {
			cont++;
			aux1 = aux1.sig;
		}
		
		if(aux1.dato!=dato)
			throw new Exception("El objeto no existe");
		
		return cont;
	}
	
	public boolean empty() {
		if (primero == null)
			return true;
		return false;
	}
	
	public void vaciar() {
		primero = ultimo = null;	
	}
	
	public Object erase(int pos) throws Exception {
		if (primero == null)
			throw new Exception ("La pila esta vacia");
		
		int prob = 1;
		Nodo sec = new Nodo();
		sec = primero;
		
		while (sec.sig != null) {
			sec = sec.sig;
			prob++;
		}
		
		if (prob < pos|| pos < 1)
			throw new Exception ("La posición elegida no existe");
			
		int cont = 1;
		Nodo rec = new Nodo();
		rec = primero;
		sec = primero.sig;
		
		if (pos == 1)	{
			primero = primero.sig;
			
			if (ultimo == rec)
				ultimo = null;
			return rec.dato;
		}
		else {
			while (cont+1 < pos) {
				rec = rec.sig;
				sec = sec.sig;
				cont++;
			}
			rec.sig = sec.sig;
			if (ultimo == sec)
				ultimo = rec;
			return sec.dato;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		int d = 5;
		int e = 55;
		Lista lis = new Lista();
		lis.push_front(10);
		lis.push_front(30);
		lis.push_back(25);
		lis.insert(1, 77);
		lis.mostrar();
		lis.insert(1, d);
		lis.mostrar();
		lis.insert(3, e);
		lis.mostrar();
		System.out.println(lis.buscar(3));
		lis.erase(3);
		lis.mostrar();
		lis.reverse();
		lis.mostrar();
		lis.pop_back();
		lis.remove(10);
		lis.mostrar();
		lis.pop_front();
		lis.mostrar();
	}
}
