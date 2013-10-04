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
	
//NO RESUELVE TODOS LOS CASOS DE PRUEBA PERO RESUELVE DIMENSIONES GRANDES

	public MatrizMathCuadrada inversa() {
		MatrizMathCuadrada Ainv = new MatrizMathCuadrada(getDimension());
		Ainv = this.clone();
		
        int n = cantidadColumnas;
        int k, 
        	j, 
        	i;

        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                Ainv.matriz[i][j] = matriz[i][j];

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if ((i != k) && (j != k))
                        Ainv.matriz[i][j] -= (Ainv.matriz[i][k] * Ainv.matriz[k][j]) / Ainv.matriz[k][k];
                }

           		for (j = 0; j < n; j++) {
           			if (j != k)
           				Ainv.matriz[k][j] = -Ainv.matriz[k][j] / Ainv.matriz[k][k];
           		}

	            for (i = 0; i < n; i++) {
	                if (i != k)
	                    Ainv.matriz[i][k] = Ainv.matriz[i][k] / Ainv.matriz[k][k];
	            }
	        Ainv.matriz[k][k] = 1 / Ainv.matriz[k][k];
            }
        }
    return Ainv;
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
