package sel_tda;
import java.lang.Math;

public class MatrizMathCuadrada extends MatrizMath{

	private static final int CANTIDAD_FILAS_DEFAULT = 20;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 20;
	
	
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

	public double errorCometido() throws Exception {
		double error = 0;
		
		MatrizMathCuadrada identidad = identidad(getDimension()); //matriz identidad 
		
		identidad.restar(this.multiplicar(this.inversa()));   //resto I-I' y saco su normaDos
		error += identidad.normaDos();
		
		return error;
	}
	
	public int getDimension(){
		return this.getCantidadFilas();
	}
	
// NO ANDA
	
	/* for k = 1 ... m:
   Find pivot for column k:
   i_max  := argmax (i = k ... m, abs(A[i, k]))
   if A[i_max, k] = 0
     error "Matrix is singular!"
   swap rows(k, i_max)
   Do for all rows below pivot:
    for i = k + 1 ... m:
     Do for all remaining elements in current row:
      for j = k ... n:
       A[i, j]  := A[i, j] - A[k, j] * (A[i, k] / A[k, k])
     Fill lower triangular matrix with zeros:
     A[i, k]  := 0
*/
	/*
	public MatrizMathCuadrada inversa() {
		int i_max = 0;
				
		if(determinante() != 0)
		{	
			int n = getDimension();
			MatrizMathCuadrada matInd = new MatrizMathCuadrada(n);
			
			for (int k = 0; k < n; k++) {
				//buscar pivot para la columna k
				//i_max = maximo(k, (int)Math.abs(matInd.matriz[k][k])); //NOSE QUE SIGNIFICA ESTA LINEA DE PSEUDOCODIGO i_max  := argmax (i = k ... m, abs(A[i, k]))
				if (matInd.matriz[i_max][n] == 0) System.out.println();
					//intercambiar_filas(n, i_max);
			
				for (int i = k + 1; i < n; i++) {
					for (int j = k; j < n; j++) {
						matInd.matriz[i][j] = matInd.matriz[i][j] - matInd.matriz[k][j] * (matInd.matriz[i][k] / matInd.matriz[k][k]);
					}
					matInd.matriz[i][k] = 0;
			    }
			}
			return matInd;
		}
		return null;
	}
*/

//NO RESUELVE TODOS LOS CASOS DE PRUEBA PERO RESUELVE DIMENSIONES GRANDES

	public MatrizMathCuadrada inversa() 
	{
		if(determinante() != 0)
		{	
			int n = getDimension();
			MatrizMathCuadrada matInd = new MatrizMathCuadrada(this);
			MatrizMathCuadrada matIde = MatrizMathCuadrada.identidad(n);
			MatrizMathCuadrada matUni = new MatrizMathCuadrada(n);
			
			// transformación de la matriz y de los términos independientes
			for (int k = 0; k < n - 1; k++) 
			{
				for (int i = k + 1; i < n; i++) 
				{
					//independientes
					for (int s = 0; s < n; s++) 
						matIde.getMatriz()[i][s] -= (matInd.getMatriz()[i][k] * matIde.getMatriz()[k][s]) / matInd.getMatriz()[k][k];
					//elementos matriz
					for (int j = k + 1; j < n; j++) 
						matInd.getMatriz()[i][j] -= (matInd.getMatriz()[i][k] * matInd.getMatriz()[k][j]) / matInd.getMatriz()[k][k];
				}
			}
			// cálculo de las incógnitas, elementos de la matriz inversa
			for (int s = 0; s < n; s++) 
			{
				matUni.getMatriz()[n - 1][s] = matIde.getMatriz()[n - 1][s] / matInd.getMatriz()[n - 1][n - 1];
				for (int i = n - 2; i >= 0; i--) 
				{
					matUni.getMatriz()[i][s] = matIde.getMatriz()[i][s] / matInd.getMatriz()[i][i];
					for (int k = n - 1; k > i; k--)
						matUni.getMatriz()[i][s] -= (matInd.getMatriz()[i][k] * matUni.getMatriz()[k][s]) / matInd.getMatriz()[i][i];
				}
			}
			return matUni;
		}
		
		return null;
	}

/*
//	RESUELVE LOS CASOS DE PRUEBA PERO HASTA DIMENSION 10

	public MatrizMathCuadrada inversa()	{ //suponiendo que es cuadrada, Método de Gauss-Jordan
		if(this.determinante()!=0) {
		MatrizMathCuadrada m = new MatrizMathCuadrada(getDimension());
		m = this.clone();
		
		double numero;
		int i,
			j,
			pivote = 0;
		MatrizMathCuadrada aux = new MatrizMathCuadrada(getDimension());
		aux.identidad();
			
		while(pivote < m.cantidadFilas-1) {	 //recorro diagonal inferior
			i = pivote;
			if (m.matriz[i][i]!=0) {
				numero = m.matriz[i][i]; 				//convierto en 1 la diagonal
				for(j = i;j < m.cantidadFilas; j++)
					m.matriz[i][j] = m.matriz[i][j] / numero;
				for (j = 0; j < m.cantidadFilas; j++)	//recorro la auxiliar cambiando los valores
					aux.matriz[i][j] = aux.matriz[i][j]/numero;
			}
			else
			{
				//si fuera 0 algun valor de la diagonal
				// habria que mover filas o columas de ambas matrices
			}
			for (i=pivote+1;i<m.cantidadFilas;i++)
			{
				if (m.matriz[i][pivote] != 0) {	
					numero = m.matriz[i][pivote] * (-1); //triangular
					for (j = pivote;j < cantidadFilas; j++)
						m.matriz[i][j] = m.matriz[i][j] + numero * m.matriz[pivote][j];
					for (j = 0; j < cantidadFilas; j++)
						aux.matriz[i][j] = aux.matriz[i][j] + numero * aux.matriz[pivote][j];	
				}
			}
			pivote++;		
		}
		if(m.matriz[pivote][pivote]!=1) {		//reviso el ultimo valor de la diagonal
			numero = m.matriz[pivote][pivote];
			m.matriz[pivote][pivote] = m.matriz[pivote][pivote] / numero;
			for (j = cantidadFilas-1; j >= 0; j--)
				aux.matriz[pivote][j]=aux.matriz[pivote][j] / numero;
		}
		while (pivote>0) {			//recorro diagonal superior
			for (i = pivote - 1;i >= 0; i--) {
				if (m.matriz[i][pivote]!=0)	{
					numero = m.matriz[i][pivote] * (-1); //triangulo la columna que esta para arriba del pivote
					for (j = pivote; j > pivote - i; j--)
						m.matriz[i][j] = m.matriz[pivote][j] + numero;
					for (j = cantidadFilas - 1; j >= 0; j--) 						//recorro la matriz auxiliar
						aux.matriz[i][j] = aux.matriz[i][j] + numero * aux.matriz[pivote][j];
				}	
			}
			pivote--;
		}
		return aux;
	}	
		return null;
}
*/	
	@Override
	public MatrizMathCuadrada clone() {
		MatrizMath clone = super.clone();
		return new MatrizMathCuadrada(clone);
	}
	
	
	public static void main(String[] args) throws Exception {

		MatrizMathCuadrada m = new MatrizMathCuadrada(2);
		m.setValue(0,0,2);
		m.setValue(0,1,1);
		m.setValue(1,0,5);
		m.setValue(1,1,3);
		System.out.println(m);
		System.out.println(m.inversa());
		/*
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
		
		double error = mi.errorCometido();
		System.out.println("error" + error);
*/
	}
	
}
