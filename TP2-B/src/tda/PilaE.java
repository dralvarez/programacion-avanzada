package tda;

import java.util.Arrays;
import tda.PilaD.Nodo;

public class PilaE implements Pila {
	private int tope;
	private Object pila[];
	private int tam;
	
		// CONSTRUCTORES
	public PilaE(int tam) {
		this.tam = tam;
		tope = -1;
		pila = new Object[tam];
	}
	
	public PilaE() {
		this(1);
	}
	
	// METODOS
	
	@Override
	public String toString() {
		return Arrays.toString(pila);
	}

	public void mostrar() {
		System.out.print("[");
		for (int i = 0; i < tope; i++)
			System.out.print(pila[i] + " - ");

		System.out.print("]\n");
	}
	
	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
	
	// METODOS DE PILA
	public boolean empty() {
		if (tope == -1)
			return true;
		return false;
	}
		
	public Object peek() {
		return pila[tope];
	}
		
	public void push (Object dato) {
		if (tope + 1 == tam)
			this.resize();
		
		tope++;
		pila[tope] = dato;
	}
	
	private void resize() {
		tam = tam * 2;
		PilaE c = new PilaE(tam);
		for (int i = 0; i < (pila.length); i++)
			c.pila[tope-i] = this.pila[(tope-i)];
		
		this.pila = c.pila;
	}
	
	public Object pop() throws Exception {
		if (tope == -1)
			throw new Exception("Pila Vacia");
		Object obj;
		obj = pila[tope];
		tope--;
	
	return obj;
	}
		
	public void vaciar() {
		tope = -1;
	}
		
	public static void main(String[] args) {
		
		try{
		Pila p= new PilaE();
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
