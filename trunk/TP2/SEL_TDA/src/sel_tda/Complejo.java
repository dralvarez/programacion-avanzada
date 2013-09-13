package sel_tda;
import java.util.ArrayList;
import java.util.Collections;


public class Complejo implements Comparable<Complejo> {
	private final double parteReal;
	private final double parteCompleja;
	
	public Complejo(double parteReal, double parteCompleja) {
		this.parteReal = parteReal;
		this.parteCompleja = parteCompleja;
	}

	public Complejo() {
		this(0, 0);		
	}

	public double getParteReal() {
		return parteReal;
	}

	public double getParteCompleja() {
		return parteCompleja;
	}

	public Complejo sumar(Complejo complejo){
		return new Complejo(this.parteReal + complejo.parteReal, this.parteCompleja + complejo.parteCompleja);
	}
	
	public Complejo sumar(Double real){
	}
	
	public Complejo multiplicar(Complejo complejo){
		double parteReal = (this.parteReal * complejo.parteReal) - (this.parteCompleja * complejo.parteCompleja);
		double parteCompleja = (this.parteReal * complejo.parteCompleja) - (this.parteCompleja * complejo.parteReal);
		
		return new Complejo(parteReal, parteCompleja);
	}
	
	public Complejo multiplicar(Double real){
		
	}
	
	
	
	
	public Complejo dividir(Double divisor){
		double parteReal = this.parteReal / divisor;
		double parteCompleja = this.parteCompleja / divisor;
		
		return new Complejo(parteReal, parteCompleja);
	}
	
	public Complejo dividir(Complejo divisor){
		double divisorDouble = (divisor.parteReal * divisor.parteReal) + (divisor.parteCompleja * divisor.parteCompleja);
		
		Complejo dividendo = new Complejo( this.parteReal * divisor.parteReal + this.parteCompleja * divisor.parteCompleja, (this.parteCompleja * divisor.parteCompleja) - (this.parteReal * divisor.parteReal)); 
		Collections.sort(new ArrayList<Complejo>());
		
		return dividendo.dividir(divisorDouble);
	}
	
	
	public double modulo(){
		double parteReal = this.parteReal * this.parteReal;
		double parteCompleja = this.parteCompleja * this.parteCompleja;
		
		return Math.sqrt(parteReal + parteCompleja);
	}

	@Override
	public String toString() {
		return parteReal + " + " + parteCompleja + "i";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Complejo complejo = (Complejo) obj;
		return this.parteReal == complejo.parteReal && this.parteCompleja == complejo.parteCompleja;
	}

	public static void main(String[] args) {
		
		// Prueba constructor por defecto
		Complejo complejo1 = new Complejo();
		System.out.println(complejo1);
		
		// Prueba constructor parametrizado
		Complejo complejo2 = new Complejo(1, 2);
		System.out.println(complejo2);
		
		// Prueba suma
		Complejo primerSumando = new Complejo(1, 2);
		Complejo segundoSumando = new Complejo(4,3);
		
		Complejo sumaEsperada = new Complejo(5, 5);
		
		Complejo sumar = primerSumando.sumar(segundoSumando);
		
		System.out.println(sumar);
		
		// Prueba equals		
		boolean sonIguales = sumaEsperada.equals(sumar);
//		System.gc();
		System.out.println("Son iguales: " + sonIguales);
		String a = "a";
		String a1 = new String(a);
		String a2 = new String(a);
		boolean sonIgualeString = a1 == a2;
		
		System.out.println("Son iguales String: " + sonIgualeString);
		
		Comparable c = new Complejo();
		String s ="";
		c.compareTo(s);
		// Prueba division
		
		double divisor = 2;
		
		Complejo dividendo = new Complejo(5, 6);
		System.out.println(dividendo + " dividido " + divisor + " = " + dividendo.dividir(divisor));
		
		// Prueba división compleja
		
		Complejo comp = new Complejo(5, 6);
		Complejo comp1 = new Complejo(7, 8);
		
		Complejo comp2 = comp.dividir(comp1);
		System.out.println(comp2);
		
	}

	@Override
	public int compareTo(Complejo arg0) {
		if(this.modulo() > arg0.modulo())
			return 1;
		else if(this.modulo() < arg0.modulo()) 
			return -1;
		else
			return 0;
	}
	
	public interface Interfaz{
		int compareTo(Object arg0);
	}


}
