package tda;
import java.util.Arrays;

public class ColaE implements Cola {
	
	private int tam;
	private int pri;
	private int ult;
	private Object cola[];

	// CONSTRUCTORES
	public ColaE(int tam) {
		this.tam = tam;
		pri=-1;
		ult=-1;
		cola= new Object[tam];
	}
	
	public ColaE() {
		this(1);
	}
		
	// METODOS	
	public void mostrar() {
			
		System.out.print("[");
		for(int i=pri; i<=ult; i++)
			System.out.print(cola[i] + " - ");

		System.out.print("]\n");
	}
		
	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
		
	// METODOS DE COLA

	public boolean empty() {
		if(pri==-1)
			return true;
		return false;
	}

	public Object peek() {
		return cola[pri];
	}
		
	public void push(Object dato) {
		if(ult+1>=tam)
			this.resize();
		ult++;
		cola[ult]=dato;
				
		if(pri==-1)
			pri++;
	}
		
	private void resize() {
		tam = tam * 2;
		ColaE c = new ColaE(tam);
		int i=0;
				
		for(int j=pri; j<=ult; j++)	{
			c.cola[i]=this.cola[j];
			i++;
		}
		this.cola = c.cola;
		pri=0;
		ult=i-1;
	}
		
	public Object poll() throws Exception {
		if(pri==-1)
			throw new Exception("Pila Vacia");
		
		Object obj;
		obj=cola[pri];
		pri++;
			
		return obj;	
	}
		
	public void vaciar( ) {
		pri=-1;
		ult=-1;
	}
				
	public static void main(String[] args) {
		
		try{
		Cola p = new ColaE();
		double  inicio, 
				fin;
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

