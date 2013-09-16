package figura;

public class Test {
	
	public static void main(String[] args) {
		Figura f;
		Circulo c = new Circulo(new Punto(2,3),5);
		Rectangulo r = new Rectangulo(4,5);

		f = c;
		System.out.println(f.Superficie());
		f = r;
		System.out.println(f.Perimetro());
	}
}
