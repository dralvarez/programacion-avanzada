package sel_tda;

public class MatrizMathCuadrada extends MatrizMath{

	private static final int CANTIDAD_FILAS_DEFAULT = 3;
	
	private static final int CANTIDAD_COLUMNAS_DEFAULT = 3;
	
	
	public MatrizMathCuadrada() {
		super(CANTIDAD_FILAS_DEFAULT,CANTIDAD_COLUMNAS_DEFAULT);
	}

	public MatrizMathCuadrada(int dimension) {
		super(dimension, dimension);
	}

	public MatrizMathCuadrada(String path) {
		super(path);
	}

	public double normaUno() throws Exception {
		double aux;
		VectorMath vec = new VectorMath(this.getCantidadColumnas());

		for (int j = 0; j < this.getCantidadColumnas(); j++) {
			aux = 0;
			for (int i = 0; i < this.getCantidadFilas(); i++)
				aux += Math.abs(this.getMatriz()[i][j]);
			vec.setValue(j,aux);
		}
		return vec.normaInfinito();
	}
	
	public double normaDos() throws Exception {
		double aux = 0;

		for (int i = 0; i < this.getCantidadFilas(); i++)
			for (int j = 0; j < this.getCantidadColumnas(); j++)
				aux += Math.pow(Math.abs(this.getMatriz()[i][j]), 2);

		return Math.sqrt(aux);
	}

	public double normaInfinito() throws Exception {
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
	
	public double errorCometido() throws Exception {
		double error = 0;
		
		MatrizMathCuadrada m= new MatrizMathCuadrada(this.getDimension()); //matriz identidad 
		m.identidad();
		
		m.restaMatrizMath(this.multiplicar(this.inversa()));   //resto I-I' y saco su normaDos
		error += m.normaDos();
		
		return error;
	}
	
	public int getDimension(){
		return this.getCantidadFilas();
	}
	
	public MatrizMathCuadrada inversa() {
		MatrizMathCuadrada aInv = new MatrizMathCuadrada(this.getDimension());
		aInv = this.clone();
		
        int n = this.getCantidadColumnas();
        int k, 
        	j, 
        	i;

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (i != k && j != k)
                        aInv.getMatriz()[i][j] -= (aInv.getMatriz()[i][k] * aInv.getMatriz()[k][j]) / aInv.getMatriz()[k][k];
                }

           		for (j = 0; j < n; j++) {
           			if (j != k)
           				aInv.getMatriz()[k][j] = -aInv.getMatriz()[k][j] / aInv.getMatriz()[k][k];
           		}

	            for (i = 0; i < n; i++) {
	                if (i != k)
	                    aInv.getMatriz()[i][k] = aInv.getMatriz()[i][k] / aInv.getMatriz()[k][k];
	            }
	        aInv.getMatriz()[k][k] = 1 / aInv.getMatriz()[k][k];
            }
        }
        
    return aInv;
  }

	@Override
	public MatrizMathCuadrada clone() {
		//TODO: Hacer el clone.
//		MatrizMath clone = super.clone();
		return new MatrizMathCuadrada();
		
	}
	
	

	

}
