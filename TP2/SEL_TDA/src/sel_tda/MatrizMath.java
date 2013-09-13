package sel_tda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import utils.FileUtils;

public class MatrizMath implements Cloneable{

	private static final int CANTIDAD_FILAS_DEFAULT = 3;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 3;

	private int cantidadFilas; 
	
	private int cantidadColumnas;
	
	private double matriz[][];
	
	
	public MatrizMath() {
		this(CANTIDAD_FILAS_DEFAULT, CANTIDAD_COLUMNAS_DEFAULT);
	}
	
	public MatrizMath(MatrizMath matriz) {
		this(matriz.cantidadFilas, matriz.cantidadColumnas);
		this.matriz = matriz.matriz.clone();
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
	
	private void validarDimensionesNoNegativas(int cantidadFilas,
			int cantidadColumnas) {
		if(cantidadFilas <= 0 || cantidadColumnas <=0)
			throw new RuntimeException("La dimension de una matriz debe ser positiva.");
	}
	
	protected void validarRango(int fila, int columna){
		if(columna >= this.cantidadColumnas || fila >= this.cantidadFilas)
			throw new RuntimeException("La columna o la fila elegida es mayor a la capacidad de la matriz");
	}
	
	public String toString() {
		String s = "";
		
		for(int i = 0; i < cantidadFilas; i++)
		{
			for(int j = 0; j < cantidadColumnas; j++)
				s += matriz[i][j] + "\t";
			s += "\n";
		}
		return s;
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
	
	public MatrizMath sumaMatrizMath(MatrizMath matriz) throws Exception {
		return sumaMatrizMath(this, matriz);
	}
	
	public static MatrizMath sumaMatrizMath(MatrizMath matriz1, MatrizMath matriz2) throws Exception {
		if (matriz1.cantidadFilas != matriz2.cantidadFilas|| matriz1.cantidadColumnas != matriz2.cantidadColumnas)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden sumar.");
		
			MatrizMath resultado = new MatrizMath(matriz1.cantidadFilas, matriz1.cantidadColumnas);
			
			for(int i = 0; i < matriz1.cantidadFilas; i++)
				for(int j = 0; j < matriz1.cantidadColumnas; j++)
					resultado.matriz[i][j] = matriz1.matriz[i][j]+matriz2.matriz[i][j];

			return resultado;
	}

	public MatrizMath restaMatrizMath(MatrizMath obj) throws Exception {
		return restaMatrizMath(this, obj);
	}
		
	public static MatrizMath restaMatrizMath(MatrizMath obj1, MatrizMath obj2) throws Exception {
		if (obj1.cantidadFilas != obj2.cantidadFilas || obj1.cantidadColumnas != obj2.cantidadColumnas)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden restar.");
		
			MatrizMath resultado= new MatrizMath(obj1.cantidadFilas, obj1.cantidadColumnas);
			
			for(int i = 0; i < obj1.cantidadFilas; i++)
				for(int j = 0; j < obj1.cantidadColumnas; j++)
					resultado.matriz[i][j] = obj1.matriz[i][j]-obj2.matriz[i][j];

			return resultado;
	}	
		
	public MatrizMath productoPorUnEscalar(double escalar) {
		MatrizMath aux = new MatrizMath(this.cantidadFilas, this.cantidadColumnas);

		for (int i = 0; i < cantidadFilas; i++)
			for (int j = 0; j < cantidadColumnas; j++)
				aux.matriz[i][j] = escalar * this.matriz[i][j];

		return aux;
	}
	
	public MatrizMath multiplicar ( MatrizMath matriz ) throws Exception {
		return multiplicar(this, matriz);
	}

	public static MatrizMath multiplicar (MatrizMath matriz1, MatrizMath matriz2 ) {
		if(matriz1.cantidadColumnas != matriz2.cantidadFilas)
			throw new RuntimeException("Las matrices no pueden multiplicarse.");
		
		MatrizMath r = new MatrizMath(matriz1.cantidadFilas, matriz2.cantidadColumnas);

	        for(int i = 0; i < matriz1.cantidadFilas; i++)
	            for (int j = 0; j < matriz1.cantidadColumnas; j++)
	                for (int k = 0; k < matriz1.cantidadColumnas; k++)
	                    r.matriz[i][j] += matriz1.matriz[i][k] * matriz2.matriz[k][j];

			return r;
	}	
	
	public VectorMath multiplicar (VectorMath v) throws Exception  {
		if(cantidadColumnas != v.getCantidadElementos())
			throw new Exception("No se puede realizar la multiplicación entre la matriz y el vector");
		
		VectorMath v1 = new VectorMath(cantidadFilas);
		double suma = 0;
		int cont = 0;
		
		for(int i = 0; i < cantidadFilas; i++)	
		{	
			suma = 0;
			cont = 0;
				
			while (cont<this.cantidadColumnas)
			{
				suma+=this.matriz[i][cont]*v.getValue(cont);
				cont++;
			}
			v1.setValue(i,suma);
		}
		return v1;
	}
	
	public double determinante () throws Exception {
		if(this.getCantidadFilas() == 1)
			return matriz[0][0];
		
		double result = 0;
		
		if (this.getCantidadFilas() == 2)
			result= matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
		else {	  
			for (int i = 0; i < this.getCantidadFilas(); i++) {
				MatrizMath submatriz = new MatrizMath(this.cantidadFilas -1, this.getCantidadColumnas() -1);
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
		int i;
		
		for(i = 0; i < cantidadFilas; i++)
			matriz[i][i] = 1;
	}
	

	public static void main(String[] args) throws Exception {

		
		MatrizMath m = new MatrizMath(2,2);
		m.setValue(0,0,2);
		m.setValue(0,1,1);
		m.setValue(1,0,5);
		m.setValue(1,1,3);
		System.out.println(m);
//		System.out.println(m.inversa());
//		System.out.println(m.errorCometido());
		
		MatrizMath m2 = new MatrizMath(4,4);

		
		m2.setValue(0,0,1);
		m2.setValue(0,1,0);
		m2.setValue(0,2,2);
		m2.setValue(0,3,1);
		m2.setValue(1,0,0);
		m2.setValue(1,1,3);
		m2.setValue(1,2,1);
		m2.setValue(1,3,2);
		m2.setValue(2,0,1);
		m2.setValue(2,1,0);
		m2.setValue(2,2,1);
		m2.setValue(2,3,4);
		m2.setValue(3,0,0);
		m2.setValue(3,1,1);
		m2.setValue(3,2,1);
		m2.setValue(3,3,2);
		System.out.println(m2);
//		System.out.println(m2.inversa());
		System.out.println(m2.determinante());
//		System.out.println(m2.errorCometido());
	}
}