package figura;

public abstract class Figura {

	Punto origen;
	public abstract double Superficie();
	public abstract double Perimetro();
	
	public Figura()	{
		origen = new Punto();
	}
	
	public Figura(int x, int y)	{
		origen = new Punto(x,y);
	}
	
	public Figura(Punto origen)	{
		this.origen = (Punto)origen.clone();		
	}
		
	public Punto getOrigen() {
		return origen;
	}

	public int getOrigenX() {
		return origen.getX();
	}

	public int getOrigenY() {
		return origen.getY();
	}
	
	public void setOrigen(Punto origen) {
		this.origen = origen;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Punto) || (obj instanceof Figura))
			return false; //true cuando es punto o figura
		if (obj instanceof Figura)
		{
			Figura other = (Figura) obj;
			if (origen.getX() != other.origen.getX() || origen.getY() != other.origen.getY())
				return false;
		}
		if (obj instanceof Punto)
		{
			Punto other = (Punto) obj;
			if (origen.getX() != other.getX() || origen.getY() != other.getY())
				return false;
		}
		return true;
	}

	public String toString() {
	//	return "Figura [x = " + origen.getX() + ", y = " + origen.getY() + "]";
		return origen.toString();
	}

	public static void main(String[] args) {
/*		Punto p1 = new Punto(5,2);
		Punto p2 = new Punto(5,1);
		Punto p3 = new Punto(5,1);
		
		int x = 0,
			y = 0;
		
		System.out.println(p1.equals(p2));
		System.out.println(p3.equals(p2));
*/
	}
}
