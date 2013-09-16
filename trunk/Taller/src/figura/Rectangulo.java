package figura;

public class Rectangulo extends Figura implements Dibujable, Rotable
{

	public Rectangulo()	{ //por defecto
		super(); //uso el constructor de la clase padre
	}
	
	public Rectangulo(int x, int y) { //parametrizado
		super(x, y); //uso el constructor de la clase padre
	}
	
	public Rectangulo(Figura f) { //otro constructor usando Figura
		super (f.getOrigenX(), f.getOrigenY());
	}
	
	public int getX() {
		return getOrigenX();
	}

	public int getY() {
		return getOrigenY();
	}
	
	public void setX(int x) {
		super.origen.setX(x);
	}
	
	public void setY(int y) {
		super.origen.setY(y);
	}

	protected Object clone() {
		return new Rectangulo(super.getOrigenX(),super.getOrigenY());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Circulo) 
			return false; 
		if (!(obj instanceof Rectangulo) || (obj instanceof Punto)) 
			return false; 												
		if (obj instanceof Punto)
		{
			Punto other = (Punto) obj;
			if (this.getX() != other.getX() || this.getY() != other.getY())
				return false;
		}
		if (obj instanceof Rectangulo)
		{
			Rectangulo other = (Rectangulo) obj;
			return other.equals(this);
		}		
		return true;
	}

	public String toString() {
		return super.toString();
	}

	public double Superficie()	{
		double s = this.getX() * this.getY();
		return s;
	}
	
	public double Perimetro() {
		double p = this.getX() * 2 + this.getY() * 2;
		return p;
	}
	
	public void dibujar() {
			
	}
			
	public void rotar() {
		
	}
	
	public static void main(String[] args) {
/*		Rectangulo r1 = new Rectangulo(1,2);
		Rectangulo r2 = new Rectangulo();
		Rectangulo r3 = new Rectangulo();
		Rectangulo r4 = new Rectangulo(5,10);
		Circulo c1 = new Circulo(1,1,1);
		Punto p1 = new Punto(1,2);
		Punto p2 = new Punto(11,82);
		
		
		int x = 0,
			y = 0;
		double superficie = r4.Superficie();
		//System.out.println(superficie);
		double perimetro = r4.Perimetro();
		//System.out.println(perimetro);
		
		x = r1.getX();
		y = r1.getY();
		r2.setX(3);
		r2.setY(33);
		r3 = (Rectangulo) r1.clone();
		
		
		boolean bool = 
				r1.equals(r1); //true
		//		r1.equals(5); //false
		//		r1.equals("daiana"); //false
		//		r1.equals(r2); //false
		//		r1.equals(p1); //tiene sentido?!
		//		r1.equals(p2); //tiene sentido?!
		//		r1.equals(c1);
				
		//System.out.println(r2.toString());
		//System.out.println(x + " " + y);
		//System.out.println(r3.toString()); //clone
		//System.out.println(bool);
*/
	}
}
