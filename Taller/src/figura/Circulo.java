package figura;


public class Circulo extends Figura implements Dibujable, Rotable {

	private int radio;
	public static final double PI = Math.PI;
	
	public Circulo() { //por defecto
		super(); //uso el constructor de la clase padre
		this.radio = 0;
	}
	
	public Circulo(int x, int y, int r) { //parametrizado
		super(x, y); //uso el constructor de la clase padre
		this.radio = r;
	}

	public Circulo(Punto p, int r) { //parametrizado
		this.setOrigen(p);
		this.radio = r;
	}
	
	public Circulo(Figura f, int r) { //otro constructor usando Figura
		super (f.getOrigenX(), f.getOrigenY());
		this.radio = r;
	}
	
	public int getX() {
		return getOrigenX();
	}

	public int getY() {
		return getOrigenY();
	}
	
	public int getRadio() {
		return this.radio;
	}
	
	public void setX(int x) {
		super.origen.setX(x);
	}
	
	public void setY(int y) {
		super.origen.setY(y);
	}
	
	public void setRadio(int r) {
		this.radio = r;
	}
	
	protected Object clone() {
		return new Circulo(super.getOrigenX(),super.getOrigenY(),this.radio);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Rectangulo) 
			return false; 			
		if (!(obj instanceof Circulo) || (obj instanceof Punto)) 
			return false; 												
		if (obj instanceof Punto) { //tiene sentido?
			Punto other = (Punto) obj;
			if (this.getX() != other.getX() || this.getY() != other.getY())
				return false;
		}
		if (obj instanceof Circulo)	{
			Circulo other = (Circulo) obj;
			return other.equals(this);
		}		
		return true;
	}

	public String toString() {
		return "Circulo [radio=" + radio + "centro en x = " + super.getOrigenX() + ",y = " + super.getOrigenY() + "]";
	}

	public double Superficie() {
		double s = PI * this.radio * this.radio;
		return s;
	}
	
	public double Perimetro() {
		double p = 2 * PI * this.radio;
		return p;
	}
	
	public void dibujar() {
		
	}
			
	public void rotar()	{
		
	}
	
	public static void main(String[] args) 
	{
/*		Circulo c1 = new Circulo(1,2,3);
		Circulo c2 = new Circulo();
		Circulo c3 = new Circulo();
		Rectangulo r1 = new Rectangulo(1,2);
		Rectangulo r2 = new Rectangulo();
		Rectangulo r3 = new Rectangulo();
		Rectangulo r4 = new Rectangulo(5,10);
		Punto p1 = new Punto(1,2);
		Punto p2 = new Punto(11,82);
		
		
		int x = 0,
			y = 0,
			r = 0;
		double superficie = c1.Superficie();
		//System.out.println(superficie);
		double perimetro = c1.Perimetro();
		//System.out.println(perimetro);
		
		x = c1.getX();
		y = c1.getY();
		r = c1.getRadio();
		
		c2.setX(3);
		c2.setY(33);
		c2.setRadio(2);
		c3 = (Circulo) c2.clone();
		
		
		boolean bool = 
				c1.equals(c1); //true
		//		c1.equals(5); //false
		//		c1.equals("daiana"); //false
		//		c1.equals(r2); //false
		//		c1.equals(p1); //tiene sentido?
		//		c1.equals(p2); //tiene sentido?
		//		c1.equals(r1); //false
				
		//System.out.println(r2.toString());
		//System.out.println(x + " " + y);
		//System.out.println(r3.toString()); //clone
		System.out.println(bool);
*/
	}

	
}

