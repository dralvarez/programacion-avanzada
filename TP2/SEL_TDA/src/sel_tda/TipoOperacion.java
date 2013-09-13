package sel_tda;

public enum TipoOperacion {
	SUMA("Suma"),
	RESTA("Resta"),
	MULTIPLICACION_POR_ESCALAR ("Multiplicacion por un escalar"),
	PRODUCTO_ESCALAR ("Producto escalar") ,
	DIVISION("Division");

	private String nombre;
	
	TipoOperacion(String nombre){
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
}
