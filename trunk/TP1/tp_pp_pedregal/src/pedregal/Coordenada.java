package pedregal;

public class Coordenada {
	
	private int x;
	private int y;
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordenada() {
		this(0, 0);
	}
	
	public Coordenada(String coordenadaCruda){
		String[] coordenadas = coordenadaCruda.split(" ");
		this.x = Integer.parseInt(coordenadas[0]);
		this.y = Integer.parseInt(coordenadas[1]);
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

	@Override
	public boolean equals(Object arg0) {
		boolean sonIguales = false;
		if(arg0 instanceof Coordenada){
			Coordenada coord = (Coordenada) arg0;
			sonIguales = coord.x == this.x && coord.y == this.y;	
		}
		return sonIguales;
	}

	@Override
	public int hashCode() {
		int primo = 31;
		int primox = 17;
		int primoy = 37;
		return primo * ( (primox * x) - (primoy * y));
	}

	@Override
	public String toString() {
		return String.format("(%d , %d)", x,y);
	}
	
}
