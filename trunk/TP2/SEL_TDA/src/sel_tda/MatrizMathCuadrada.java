package sel_tda;

public class MatrizMathCuadrada extends MatrizMath{

	private static final int CANTIDAD_FILAS_DEFAULT = 3;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 3;
	
	
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
	
	public MatrizMathCuadrada inversa() 
	{
		if(determinante() != 0)
		{	
			int n = getDimension();
			MatrizMathCuadrada matInd = new MatrizMathCuadrada(this);
			MatrizMathCuadrada matIde = MatrizMathCuadrada.identidad(n);
			MatrizMathCuadrada matUni = new MatrizMathCuadrada(n);
			
			// transformaci�n de la matriz y de los t�rminos independientes
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
			// c�lculo de las inc�gnitas, elementos de la matriz inversa
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
		
		MatrizMathCuadrada inversa = mi.inversa();
		System.out.println("La inversa de ");
		System.out.println(mi); 
		System.out.println(" es: ");
		System.out.println(inversa);
		
		double error = mi.errorCometido();
		System.out.println("error" + error);

	}
	
}