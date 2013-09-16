package figura;

public class Punto {

	private int x;
	private int y;
	
	public Punto() {
		x = 0;
		y = 0;
	}
	
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Object clone() {
		return new Punto(x,y);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Punto))
			return false; //true cuando es punto
			
		Punto other = (Punto) obj;
		if (x != other.x || y != other.y)
			return false;
		return true;
	}

	public String toString() {
		return "Punto [x = " + x + ", y = " + y + "]";
	}

	public static void main(String[] args) {

	}
}
