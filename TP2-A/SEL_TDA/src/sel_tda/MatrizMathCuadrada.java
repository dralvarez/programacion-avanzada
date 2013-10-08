package sel_tda;
import java.lang.Math;

public class MatrizMathCuadrada extends MatrizMath{

	private static final int CANTIDAD_FILAS_DEFAULT = 0;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 0;
	
	public MatrizMathCuadrada() {
		super(CANTIDAD_FILAS_DEFAULT,CANTIDAD_COLUMNAS_DEFAULT);
	}
	
	public MatrizMathCuadrada(MatrizMath matriz) {
		super(matriz);
	}

	public MatrizMathCuadrada(int dimension) {
		super(dimension, dimension);
	}

	public MatrizMathCuadrada(String path) {
		super(path);
	}
	
	public static MatrizMathCuadrada identidad(int dimension) {
		MatrizMathCuadrada matriz = new MatrizMathCuadrada(dimension);

		for(int i = 0; i < dimension; i++)
			matriz.getMatriz()[i][i] = 1;
		
		return matriz;
	}

	public int getDimension(){
		return this.getCantidadFilas();
	}
	
	public MatrizMathCuadrada transpuesta() {
		MatrizMathCuadrada aux = new MatrizMathCuadrada(getDimension());

		for (int i = 0; i < this.cantidadFilas; i++)
			for (int j = 0; j < this.cantidadColumnas; j++)
				aux.matriz[j][i] = this.matriz[i][j];
		return aux;
	}

	private MatrizMathCuadrada subMatriz(MatrizMathCuadrada obj, int fila, int columna) {
		MatrizMathCuadrada matriz = new MatrizMathCuadrada((obj.getDimension() - 1));
		int posfil = 0;
		int poscol;

		for (int i = 0; i < obj.cantidadFilas; i++) {
			poscol = 0;
			if (i != fila) { // Saco la fila x
				for (int j = 0; j < obj.cantidadColumnas; j++) { // Saco la columna 0
					if (j != columna) {
						matriz.matriz[posfil][poscol] = obj.matriz[i][j];
						poscol++;
					}
				}
				posfil++;
			}
		}
		return matriz;
	}

	public VectorMath producto(VectorMath obj) throws Exception {
		if (this.cantidadColumnas != obj.getCantidadElementos())
			throw new Exception("La Matriz y el Vector no pueden multiplicarse");

		VectorMath aux = new VectorMath(this.cantidadFilas);

		for (int i = 0; i < this.cantidadFilas; i++) {
			double suma = 0;
			for (int j = 0; j < obj.getCantidadElementos(); j++)
				suma += this.matriz[i][j] * obj.getValue(j);
			aux.setVector(i, suma);
		}

		return aux;
	}
	
	public MatrizMathCuadrada producto(double escalar) {
		MatrizMathCuadrada aux = new MatrizMathCuadrada(this.getDimension());

		for (int i = 0; i < this.cantidadFilas; i++)
			for (int j = 0; j < this.cantidadColumnas; j++)
				aux.matriz[i][j] = escalar * this.matriz[i][j];

		return aux;
	}
	
	public MatrizMathCuadrada inversa() {
		if (this.determinante() != 0) {
			MatrizMathCuadrada aux = new MatrizMathCuadrada(this.getDimension());
			
			double det = this.determinante();
	
			for (int i = 0; i < this.cantidadFilas; i++) {
				for (int j = 0; j < this.cantidadColumnas; j++) {
					MatrizMathCuadrada submat = new MatrizMathCuadrada(this.getDimension() - 1);
					submat = subMatriz(this, i, j);
					aux.matriz[i][j] = Math.pow(-1, i + j) * submat.determinante();
				}
			}
			return aux.transpuesta().producto(1 / det);
		}
		return null;
	}
	
	private void pivote(double matriz[][], int piv, int var) {
		double temp = 0;
		temp = matriz[piv][piv];
		for (int y = 0; y < (var + 1); y++) {
			matriz[piv][y] = matriz[piv][y] / temp;
		}
	}

	private void hacerceros(double matriz[][], int piv, int var) {
		for (int x = 0; x < var; x++) {
			if (x != piv) {
				double c = matriz[x][piv];
				for (int z = 0; z < (var + 1); z++) {
					matriz[x][z] = ((-1 * c) * matriz[piv][z]) + matriz[x][z];
				}
			}
		}
	}
	
	public VectorMath GaussJordan(VectorMath b) throws Exception {
		int var = b.getCantidadElementos(), 
			piv = 0;
		double matriz[][] = new double[var][var + 1];
		for(int x = 0; x < var; x++){
			for(int y = 0; y < (var + 1); y++){
				if(y == var)
					matriz[x][y] = b.getValue(x);
				else
					matriz[x][y] = this.matriz[x][y];
			}
		}
		for (int a = 0; a < var; a++) {
			pivote(matriz, piv, var);
			hacerceros(matriz, piv, var);
			piv++;
		}
		
		VectorMath res = new VectorMath(var);
		for (int x = 0; x < var; x++) {
			res.setVector(x, matriz[x][var]);
		}
		
		return res;
	}

	@Override
	public MatrizMathCuadrada clone() {
		MatrizMath clone = super.clone();
		return new MatrizMathCuadrada(clone);
	}
	
	public static void main(String[] args) throws Exception {
/*
		MatrizMathCuadrada m = new MatrizMathCuadrada(2);
		m.setValue(0,0,10);
		m.setValue(0,1,12);
		m.setValue(1,0,32);
		m.setValue(1,1,43);
		System.out.println(m);
		System.out.println(m.inversa());
		
		double norma1 = m.normaUno();
		System.out.println(norma1);

		double norma2 = m.normaInfinito();
		System.out.println(norma2);

		double norma_infinito = m.normaInfinito();
		System.out.println(norma_infinito);

		MatrizMathCuadrada mi = new MatrizMathCuadrada(2);
		mi.setValue(0,0,1);
		mi.setValue(0,1,2);
		mi.setValue(1,0,3);
		mi.setValue(1,1,4);
		System.out.println(mi);
		System.out.println(mi.inversa());
*/
	}
	
}
