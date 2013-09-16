package sel_tda;

import java.util.Arrays;

public class VectorMath {
	private int cantidadElementos; //n = cantidad de elementos
	private double[] vector;


	public VectorMath() {
		this(1);
	}
	
	public VectorMath(int cantidadDeElementos) {
		this.cantidadElementos = cantidadDeElementos;
		inicializarVector();
	}
	
	public VectorMath(VectorMath vector) {
		this.cantidadElementos = vector.cantidadElementos;
		this.vector = vector.vector.clone();
	}
	
	public void inicializarVector(){
		this.vector = new double[cantidadElementos];
		for(int i = 0; i < cantidadElementos; i++)
			this.vector[i] = 0;
	}

	public VectorMath(String path) {
		this(InterpreteVectorMathArchivo.interpretar(path));
	}
	
	public int getCantidadElementos() {
		return cantidadElementos;
	}

	public void setCantidadElementos(int cantidadElementos) {
		this.cantidadElementos = cantidadElementos;
	}

	public double[] getVector() {
		return vector;
	}

	public void setVector(double[] vector) {
		this.vector = vector;
	}

	public double getValue(int posicion){
		validarPosicion(posicion);
		return vector[posicion];
	}
	
	private void validarPosicion(int posicion) {
		if (posicion >= this.getCantidadElementos())
			throw new RuntimeException("No existe la posición " + posicion + " en el vector.");
	}

	public void setValue(int posicion, double valor) {
		validarPosicion(posicion);
		this.vector[posicion]=valor;
	}
		
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(this instanceof VectorMath))
			return false;

		VectorMath other = (VectorMath) obj;
		
		if (cantidadElementos != other.cantidadElementos)
			return false;
		
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(vector);
	}

	public VectorMath sumar(VectorMath v2){
		validarLongitudesIguales(this,v2, TipoOperacion.SUMA);
		
		VectorMath v1 = new VectorMath(cantidadElementos);

		for (int i = 0; i < cantidadElementos; i++) {
			v1.vector[i] = vector[i] + v2.vector[i];
		}
		return v1;
	}

	private void validarLongitudesIguales(VectorMath vectorMath, VectorMath v2, TipoOperacion tipoOperacion) {
		if(cantidadElementos != v2.cantidadElementos)
			throw new RuntimeException ("Las longitudes de los vectores son diferentes, no se puede efectuar la operacion " + tipoOperacion);
	}

	public VectorMath restar(VectorMath v2) throws Exception{
		validarLongitudesIguales(this,v2, TipoOperacion.RESTA);

		VectorMath v1 = new VectorMath(cantidadElementos);

		for (int i = 0; i < cantidadElementos; i++) {
			v1.vector[i] = vector[i] - v2.vector[i];
		}
		return v1;
	}
	
	public VectorMath producto(double r) {
		VectorMath v1 = new VectorMath(cantidadElementos);

		for (int i = 0; i < cantidadElementos; i++) {
			v1.vector[i] = vector[i] * r;
		}
		return v1;
	}
		
	public double producto(VectorMath v2) throws Exception{
		validarLongitudesIguales(this,v2, TipoOperacion.PRODUCTO_ESCALAR);
		
		double r = 0;

		for (int i = 0; i < this.cantidadElementos; i++) 
			r += this.vector[i] * v2.vector[i];

		return r;
	}
	
	public VectorMath producto(MatrizMath m) throws Exception  {

		//if(m.getCantidadColumnas() != cantidadElementos)
		if(m.getCantidadFilas()!= cantidadElementos)
			throw new Exception("No se puede realizar el producto. Las cantidad de filas de la matriz debe ser igual a la cantidad de elementos del vector.");
		
		VectorMath v1 = new VectorMath(cantidadElementos);
		double suma;
		for(int i = 0; i < cantidadElementos; i++) {
			suma = 0;
			
			//for(int j = 0; j < m.getCantidadFilas(); j++) {
			for(int j = 0; j < m.getCantidadColumnas(); j++) {
				suma += m.getValue(i,j) * vector[i];  
			}
			v1.vector[i] = suma;
		}
		return v1;
	}
	
	
	public double normaUno() {
		double r = 0;
		
		for(int i = 0; i < cantidadElementos; i++) {
			r += Math.abs(vector[i]);
		}
		return r;
	}
	
	public double normaDos() {
		double r = 0;
		
		for(int i = 0; i < cantidadElementos; i++) {
			r += Math.pow(Math.abs(vector[i]), 2);
		}
		return Math.sqrt(r);
	}
	
	public double normaInfinito() {
		double max = 0;
		
		for(int i = 0; i < cantidadElementos; i++) {
			if(max < Math.abs(vector[i]))
				max = Math.abs(vector[i]);
		}
		return max;
	}
	
	public static void main(String[] args) throws Exception {
		//VectorMath v1 = new VectorMath("prueba1.in");
		VectorMath v1 = new VectorMath("C:/Users/Ale Menutti/Desktop/vector1.txt");
		//VectorMath v2 = new VectorMath("C:/Users/Ale Menutti/Desktop/vector2.txt");
		//MatrizMath m = new MatrizMath("C:/Users/Ale Menutti/Desktop/matriz1.txt");
		//VectorMath vr = v.multiplicar(m);
		//VectorMath vr1 = m.multiplicar(v);

		MatrizMath m = new MatrizMath(3,2);
					   m.setValue(0,0,2);
					   m.setValue(0,1,1);
					   m.setValue(1,0,5);
					   m.setValue(1,1,3);
					   m.setValue(2,0,3);
					   m.setValue(2,1,2);

		//VectorMath vr = v1.producto(3);
		
		//System.out.println(res);
		System.out.println(v1.normaInfinito());
		
		/*VectorMath v3=v1.multiplicar(2);
		VectorMath v4= new VectorMath(5);
		v4.setValor(4,10);

		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(v1.normaUno());
		System.out.println(v1.normaDos());
		System.out.println(v1.normaInfinito());*/
	}
}

