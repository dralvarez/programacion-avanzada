package sel_tda;

import java.util.Arrays;

public class MatrizMath implements Cloneable{

	private static final int CANTIDAD_FILAS_DEFAULT = 3;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 3;

	private int cantidadFilas; 
	
	private int cantidadColumnas;
	
	protected double matriz[][];
	
	public MatrizMath() {
		this(CANTIDAD_FILAS_DEFAULT, CANTIDAD_COLUMNAS_DEFAULT);
	}
	
	public MatrizMath(MatrizMath matriz) {
		this(matriz.cantidadFilas, matriz.cantidadColumnas);
		this.matriz = deepCopyIntMatrix(matriz.getMatriz());
	}
	
	public static double[][] deepCopyIntMatrix(double[][] input) {
	    if (input == null)
	        return null;
	    double[][] result = new double[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}

	public MatrizMath(int cantidadFilas, int cantidadColumnas) {
		validarDimensionesNoNegativas(cantidadFilas, cantidadColumnas);
		this.cantidadFilas = cantidadFilas;
		this.cantidadColumnas = cantidadColumnas;
		
		inicializarMatriz();
	}
	
	protected void inicializarMatriz(){
		matriz = new double [this.cantidadFilas][this.cantidadColumnas];
		for(int i = 0; i < this.cantidadFilas; i++)
			for(int j = 0; j < this.cantidadColumnas; j++)
				matriz[i][j] = 0;
	}
	
	public MatrizMath(String path) {
		this(InterpreteMatrizMathArchivo.interpretar(path));
	}
	
	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public void setCantidadFilas(int cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}

	public int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public void setCantidadColumnas(int cantidadColumnas) {
		this.cantidadColumnas = cantidadColumnas;
	}

	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public void setValue (int fila, int columna, double valor) {
		validarRango(fila,columna);
		matriz[fila][columna] = valor;
	}
	
	public double getValue(int fila, int columna) {
		validarRango(fila, columna);
		return matriz[fila][columna];
	}
	
	public MatrizMath sumar(MatrizMath matriz) throws Exception {
		return sumar(this, matriz);
	}
	
	public static MatrizMath sumar(MatrizMath matriz1, MatrizMath matriz2) throws Exception {
		validarDimensionesParaSumaOResta(matriz1,matriz2);
		
		MatrizMath resultado = new MatrizMath(matriz1.cantidadFilas, matriz1.cantidadColumnas);
			
			for(int i = 0; i < matriz1.cantidadFilas; i++)
				for(int j = 0; j < matriz1.cantidadColumnas; j++)
					resultado.matriz[i][j] = matriz1.matriz[i][j]+matriz2.matriz[i][j];

			return resultado;
	}

	public MatrizMath restar(MatrizMath obj) {
		return restar(this, obj);
	}
		
	public static MatrizMath restar(final MatrizMath matriz1, final MatrizMath matriz2) {
		validarDimensionesParaSumaOResta(matriz1,matriz2);
		
		MatrizMath resultado= new MatrizMath(matriz1.cantidadFilas, matriz1.cantidadColumnas);
		
		for(int i = 0; i < matriz1.cantidadFilas; i++)
			for(int j = 0; j < matriz1.cantidadColumnas; j++)
				resultado.matriz[i][j] = matriz1.matriz[i][j]-matriz2.matriz[i][j];

		return resultado;
	}	
		
	public MatrizMath multiplicar(double escalar) {
		MatrizMath aux = new MatrizMath(this.cantidadFilas, this.cantidadColumnas);

		for (int i = 0; i < cantidadFilas; i++)
			for (int j = 0; j < cantidadColumnas; j++)
				aux.matriz[i][j] = escalar * this.matriz[i][j];

		return aux;
	}
	
	public MatrizMath multiplicar ( MatrizMath matriz ) {
		return multiplicar(this, matriz);
	}

	public static MatrizMath multiplicar (MatrizMath matriz1, MatrizMath matriz2) {
		validarDimensionesParaMultiplicacion(matriz1, matriz2);
		
		MatrizMath r = new MatrizMath(matriz1.cantidadFilas, matriz2.cantidadColumnas);

	        for(int i = 0; i < matriz1.cantidadFilas; i++)
	            for (int j = 0; j < matriz2.cantidadColumnas; j++)
	                for (int k = 0; k < matriz1.cantidadColumnas; k++)
	                    r.matriz[i][j] += matriz1.matriz[i][k] * matriz2.matriz[k][j];

			return r;
	}	
	
	public VectorMath multiplicar (VectorMath v) {
		
		validarDimensionesParaMultiplicacion(this, v);
		
		VectorMath v1 = new VectorMath(cantidadFilas);
		double suma = 0;
		int cont = 0;
		
		for(int i = 0; i < cantidadFilas; i++) {	
			suma = 0;
			cont = 0;
				
			while (cont<this.cantidadColumnas){
				suma+=this.matriz[i][cont]*v.getValue(cont);
				cont++;
			}
			v1.setValue(i,suma);
		}
		return v1;
	}
	
	public VectorMath multiplicar (MatrizMath matrizMath, VectorMath vectorMath) throws Exception  {
		
		validarDimensionesParaMultiplicacion(matrizMath, vectorMath);
		
		VectorMath v1 = new VectorMath(cantidadFilas);
		double suma = 0;
		int cont = 0;
		
		double[][] matriz = matrizMath.getMatriz();
		
		for(int i = 0; i < cantidadFilas; i++){	
			suma = 0;
			cont = 0;
				
			while (cont<this.cantidadColumnas)	{
				suma += matriz[i][cont]*vectorMath.getValue(cont);
				cont++;
			}
			v1.setValue(i,suma);
		}
		return v1;
	}
	
	public double determinante () {
		double result = 0;
		
		if(this.getCantidadFilas() == 1)
			result = matriz[0][0];
		else if (this.getCantidadFilas() == 2)
			result= matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
		else {	  
			for (int i = 0; i < this.getCantidadFilas(); i++) {
				MatrizMath submatriz = new MatrizMath(this.getCantidadFilas() -1, this.getCantidadColumnas() -1);
				submatriz = subMatriz(this, i, 0); // Elimino fila i y col 0
				result += Math.pow(-1,i) * matriz[i][0] * submatriz.determinante();
			}
		}
		return result;
	}
	
	private MatrizMath subMatriz(MatrizMath obj, int fil, int col) {
		
		MatrizMath matriz = new MatrizMath((obj.cantidadFilas - 1), (obj.cantidadColumnas - 1));
		int posfil = 0;
		int poscol = 0;

		for (int i = 0; i < obj.cantidadFilas; i++) {
			poscol = 0;
			if (i != fil) { // Saco la fila x
				for (int j = 0; j < obj.cantidadColumnas; j++) {// Saco la columna 0
					if (j != col) {
						matriz.matriz[posfil][poscol] = obj.matriz[i][j];
						poscol++;
					}
				}
			posfil++;
			}
		}
	return matriz;
	}
	
	public void identidad() {
		for(int i = 0; i < cantidadFilas; i++)
			matriz[i][i] = 1;
	}
	
	public double normaUno() { //máxima suma de las columnas
		double aux;
		VectorMath vec = new VectorMath(this.getCantidadFilas());

		for (int j = 0; j < this.getCantidadFilas(); j++) {
			aux = 0;
			for (int i = 0; i < this.getCantidadFilas(); i++)
				aux += Math.abs(this.getMatriz()[i][j]);
			vec.setValue(j,aux);
		}
		return vec.normaInfinito();
	}
	
	public double normaDos() {
		double aux = 0;

		for (int i = 0; i < this.getCantidadFilas(); i++)
			for (int j = 0; j < this.getCantidadColumnas(); j++)
				aux += Math.pow(Math.abs(this.getMatriz()[i][j]), 2);

		return Math.sqrt(aux);
	}

	public double normaInfinito() { //máxima suma de las filas
		double aux;
		VectorMath vec = new VectorMath(this.getCantidadColumnas());

		for (int i = 0; i < this.getCantidadFilas(); i++) {
			aux = 0;
			for (int j = 0; j < this.getCantidadColumnas(); j++)
				aux += Math.abs(this.getMatriz()[i][j]);
			vec.setValue(i, aux);
		}
		return vec.normaInfinito();
	}
	
	public String getDimensionAsString(){
		return String.format("%dx%d", cantidadFilas, cantidadColumnas);
	}
	
	public String toString() {
		String s = "";
		
		for(int i = 0; i < cantidadFilas; i++) {
			for(int j = 0; j < cantidadColumnas; j++)
				s += matriz[i][j] + "\t";
			s += "\n";
		}
		return s;
	}	
	
	private void validarDimensionesNoNegativas(int cantidadFilas, int cantidadColumnas) {
		if(cantidadFilas <= 0 || cantidadColumnas <=0)
			throw new ArrayIndexOutOfBoundsException("La dimension de una matriz debe ser positiva.");
	}
	
	protected void validarRango(int fila, int columna){
		if(columna >= this.cantidadColumnas || fila >= this.cantidadFilas)
			throw new ArrayIndexOutOfBoundsException("La columna o la fila elegida es mayor a la capacidad de la matriz");
	}
	
	private static void validarDimensionesParaMultiplicacion(MatrizMath matriz1, MatrizMath matriz2) {
		if(matriz1.cantidadColumnas != matriz2.cantidadFilas)
			throw new MatricesDeDistintaDimensionException(matriz1,matriz2);
	}
	
	private static void validarDimensionesParaMultiplicacion(MatrizMath matriz1, VectorMath vector) {
		if(matriz1.cantidadColumnas != vector.getCantidadElementos())
			throw new MatrizYVectorDeDistintaDimensionException(matriz1,vector);
	}
	
	private static void validarDimensionesParaSumaOResta(MatrizMath matriz1, MatrizMath matriz2) {
		if (matriz1.cantidadFilas != matriz2.cantidadFilas|| matriz1.cantidadColumnas != matriz2.cantidadColumnas)
			throw new MatricesDeDistintaDimensionException(matriz1,matriz2);
	}

	
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(obj instanceof MatrizMath))
			return false;
		
		MatrizMath other = (MatrizMath) obj;
		
		if (cantidadColumnas != other.cantidadColumnas)
			return false;
		
		if (cantidadFilas != other.cantidadFilas)
			return false;
		
		for (int i = 0; i < cantidadFilas; i++) { // Para que compare todas las filas
			if (!Arrays.equals(matriz[i], other.matriz[i]))
				return false;
		}
		return true;
	}

	public MatrizMath clone(){
		return new MatrizMath(this);
	}	
	
	public static void main(String[] args) throws Exception {
/*
		MatrizMath m = new MatrizMath(2,2);
		m.setValue(0,0,2);
		m.setValue(0,1,1);
		m.setValue(1,0,5);
		m.setValue(1,1,3);
		System.out.println(m);
				
		MatrizMath m2 = new MatrizMath(2,2);
		m2.setValue(0,0,1);
		m2.setValue(0,1,1);
		m2.setValue(1,0,1);
		m2.setValue(1,1,1);
		System.out.println(m2);
		
		MatrizMath suma = new MatrizMath(2,2);
		suma.inicializarMatriz();
		suma = m2.sumaMatrizMath(m);
		System.out.println("suma: " + suma);

		MatrizMath resta = new MatrizMath(2,2);
		resta.inicializarMatriz();
		resta = m2.restaMatrizMath(m);
		System.out.println("resta: " + resta);
		
		MatrizMath identidad = new MatrizMath(2,2);
		identidad.inicializarMatriz();
		identidad.identidad();
		System.out.println("identidad: " + identidad);	
		
		MatrizMath producto_escalar = new MatrizMath(2,2);
		producto_escalar.inicializarMatriz();
		producto_escalar = m2.multiplicar(2);
		System.out.println("producto escalar: " + producto_escalar);
		
		MatrizMath m3 = new MatrizMath(2,2);
		MatrizMath producto_matricial = new MatrizMath(2,2);
		producto_matricial.inicializarMatriz();
		producto_matricial = m2.multiplicar(identidad);
		System.out.println("producto matricial: " + producto_matricial);
		producto_matricial = m2.multiplicar(m3);
		System.out.println("producto matricial: " + producto_matricial);

		MatrizMath producto_matricial_2 = new MatrizMath(2,2);
		producto_matricial_2 = multiplicar(m2,identidad);
		System.out.println("producto matricial: " + producto_matricial_2);
		
		VectorMath vector = new VectorMath(m2.cantidadFilas);
		vector.setValue(0, 2);
		vector.setValue(1, 2);
		VectorMath producto_matriz_por_vector = new VectorMath();
		producto_matriz_por_vector = m2.multiplicar(vector);
		System.out.println("producto de un vector por una matriz: " + producto_matriz_por_vector);
		
		double determinante = m.determinante();
		System.out.println("determinante: " + determinante);
		
		MatrizMath m4 = new MatrizMath(4,4);
		m4.setValue(0,0,1);
		m4.setValue(0,1,2);
		m4.setValue(0,2,3);
		m4.setValue(0,3,4);
		m4.setValue(1,0,2);
		m4.setValue(1,1,1);
		m4.setValue(1,2,5);
		m4.setValue(1,3,3);
		m4.setValue(2,0,6);
		m4.setValue(2,1,8);
		m4.setValue(2,2,9);
		m4.setValue(2,3,2);
		m4.setValue(3,0,3);
		m4.setValue(3,1,3);
		m4.setValue(3,2,3);
		m4.setValue(3,3,3);
		
		double determinante_2 = m4.determinante();
		System.out.println("determinante: " + determinante_2);
*/		
	}
}