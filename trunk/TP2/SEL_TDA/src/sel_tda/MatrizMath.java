package sel_tda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class MatrizMath {

	private int fil, 
				col;
	private double mat[][];
	
	
	public MatrizMath() {
		fil = 3;
		col = 3;
		mat = new double [fil][col];
		
		for(int i = 0; i < fil; i++)
			for(int j = 0; j < col; j++)
				mat[i][j] = 0;
	}

	public MatrizMath(int a, int b) {
		fil = a;
		col = b;

		mat = new double[fil][col];
		
		for(int i = 0; i < fil; i++)
			for(int j = 0; j < col; j++)
				mat[i][j] = 0;
	}	

	public MatrizMath(String path) {  
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			f = new File(path);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String[] datos;
			
			linea = br.readLine();
			datos= linea.split(" ");
			fil = Integer.parseInt(datos[0]);
			col = Integer.parseInt(datos[1]);
				
			mat = new double [fil][col];
			int i = 0;
			
			while((linea = br.readLine())!=null)
			{
				datos= linea.split(" ");
				
				for(int j = 0; j < col; j++)
				mat[i][j] = Double.parseDouble(datos[j]);
				i++;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public int getFil() {
		return fil;
	}

	public void setFil(int fil) {
		this.fil = fil;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public double[][] getMat() {
		return mat;
	}

	public void setMat(double[][] mat) {
		this.mat = mat;
	}	
	
	public void setValue (int fil, int col, double x) throws Exception {
		if(col >= this.col || fil >= this.fil)
			throw new Exception("La columna o la fila elegida es mayor a la capacidad de la matriz");
		else
			mat[fil][col] = x;
	}
	
	public double getValue(int fil, int col) throws Exception {
		if(col >= this.col || fil >= this.fil)
			throw new Exception("La columna o la fila elegida es mayor a la capacidad de la matriz");
		else
			return mat[fil][col];
	}
	
	public String toString() {
		String s = "";
		
		for(int i = 0; i < fil; i++)
		{
			for(int j = 0; j < col; j++)
				s += mat[i][j] + "\t";
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
		
		if (col != other.col)
			return false;
		
		if (fil != other.fil)
			return false;
		
		for (int i = 0; i < fil; i++) { // Para que compare todas las filas
			if (!Arrays.equals(mat[i], other.mat[i]))
				return false;
		}
		return true;
	}

	public MatrizMath Clone(){
		MatrizMath aux = new MatrizMath(fil,col);
		
		for(int i =0 ; i < fil; i++)
			for (int j = 0; j < col; j++)
				aux.mat[i][j] = mat[i][j];
		
		return aux;
	}	
	
	public void sumaMatrizMath(MatrizMath obj) throws Exception {
		if (this.fil != obj.fil || this.col != obj.col)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden sumar.");
		
		for(int i = 0; i < fil; i++)
			for(int j = 0; j < col; j++)
				this.mat[i][j] += obj.mat[i][j];
	}
	
	public static MatrizMath sumaMatrizMath(MatrizMath obj1, MatrizMath obj2) throws Exception {
		if (obj1.fil != obj2.fil|| obj1.col != obj2.col)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden sumar.");
		
			MatrizMath resultado = new MatrizMath(obj1.fil, obj1.col);
			
			for(int i = 0; i < obj1.fil; i++)
				for(int j = 0; j < obj1.col; j++)
					resultado.mat[i][j] = obj1.mat[i][j]+obj2.mat[i][j];

			return resultado;
	}

	public void restaMatrizMath(MatrizMath obj) throws Exception {
		if (this.fil != obj.fil || this.col != obj.col)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden restar.");
		
			for(int i = 0; i < fil; i++) {
				for(int j = 0; j < col; j++)
					this.mat[i][j]-=obj.mat[i][j];
			
			}
	}
		
	public static MatrizMath restaMatrizMath(MatrizMath obj1, MatrizMath obj2) throws Exception {
		if (obj1.fil != obj2.fil || obj1.col != obj2.col)
			throw new Exception("Las matrices tienen distinta dimensión y no se pueden restar.");
		
			MatrizMath resultado= new MatrizMath(obj1.fil, obj1.col);
			
			for(int i = 0; i < obj1.fil; i++)
				for(int j = 0; j < obj1.col; j++)
					resultado.mat[i][j] = obj1.mat[i][j]-obj2.mat[i][j];

			return resultado;
	}	
		
	public MatrizMath productoPorUnEscalar(double escalar) {
		MatrizMath aux = new MatrizMath(this.fil, this.col);

		for (int i = 0; i < fil; i++)
			for (int j = 0; j < col; j++)
				aux.mat[i][j] = escalar * this.mat[i][j];

		return aux;
	}
	
	public MatrizMath multiplicar ( MatrizMath obj ) throws Exception {
		if(col != obj.fil)
			throw new Exception("Las matrices no pueden multiplicarse.");
		
		MatrizMath r = new MatrizMath(this.fil, obj.col);

	        for(int i = 0; i < fil; i++)
	            for (int j = 0; j < col; j++)
	                for (int k = 0; k < col; k++)
	                	r.mat[i][j] += this.mat[i][k] * obj.mat[k][j];

			return r;
	}

	public static MatrizMath multiplicar (MatrizMath obj1, MatrizMath obj2 )throws Exception {
		if(obj1.col != obj2.fil)
			throw new Exception("Las matrices no pueden multiplicarse.");
		
		MatrizMath r = new MatrizMath(obj1.fil, obj2.col);

	        for(int i = 0; i < obj1.fil; i++)
	            for (int j = 0; j < obj1.col; j++)
	                for (int k = 0; k < obj1.col; k++)
	                    r.mat[i][j] += obj1.mat[i][k] * obj2.mat[k][j];

			return r;
	}	
	
	public VectorMath multiplicar (VectorMath v) throws Exception  {
		if(col != v.getLongitud())
			throw new Exception("No se puede realizar la multiplicación entre la matriz y el vector");
		
		VectorMath v1 = new VectorMath(fil);
		double suma = 0;
		int cont = 0;
		
		for(int i = 0; i < fil; i++)	
		{	
			suma = 0;
			cont = 0;
				
			while (cont<this.col)
			{
				suma+=this.mat[i][cont]*v.getValue(cont);
				cont++;
			}
			v1.setValue(i,suma);
		}
		return v1;
	}
	
	public double normaUno() throws Exception {
		if (this.fil != this.col)
			throw new Exception("No se puede calcular la Norma Uno de la matriz.");

		double aux;
		VectorMath vec = new VectorMath(this.col);

		for (int j = 0; j < this.col; j++) {
			aux = 0;
			for (int i = 0; i < this.fil; i++)
				aux += Math.abs(this.mat[i][j]);
			vec.setValue(j,aux);
		}
		return vec.normaInfinito();
	}
	
	public double normaDos() throws Exception {
		if (this.fil != this.col)
			throw new Exception("No se puede calcular la Norma Dos de la matriz.");

		double aux = 0;

		for (int i = 0; i < this.fil; i++)
			for (int j = 0; j < this.col; j++)
				aux += Math.pow(Math.abs(mat[i][j]), 2);

		return Math.sqrt(aux);
	}

	public double normaInfinito() throws Exception {
		if (this.fil != this.col)
			throw new Exception("No se puede calcular la Norma Infinito de la matriz.");

		double aux;
		VectorMath vec = new VectorMath(this.col);

		for (int i = 0; i < fil; i++) {
			aux = 0;
			for (int j = 0; j < col; j++)
				aux += Math.abs(this.mat[i][j]);
			vec.setValue(i, aux);
		}
		return vec.normaInfinito();
	}

	public double determinante () throws Exception {
		
		if (this.fil != this.col)
			throw new Exception ("No se puede calcular el determinante de la matriz.");
		
		if(this.fil == 1)
			return mat[0][0];
		
		double result = 0;
		
		if (this.fil == 2)
			result= mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
		else {	  
			for (int i = 0; i < fil; i++) {
				MatrizMath submatriz = new MatrizMath(this.fil -1, this.col -1);
				submatriz = subMatriz(this, i, 0); // Elimino fila i y col 0
				result += Math.pow(-1,i) * mat[i][0] * submatriz.determinante();
			}
		}
	return result;
	}
	
	private MatrizMath subMatriz(MatrizMath obj, int fil, int col) {
		
		MatrizMath matriz = new MatrizMath((obj.fil - 1), (obj.col - 1));
		int posfil = 0;
		int poscol = 0;

		for (int i = 0; i < obj.fil; i++) {
			poscol = 0;
			if (i != fil) { // Saco la fila x
				for (int j = 0; j < obj.col; j++) {// Saco la columna 0
					if (j != col) {
						matriz.mat[posfil][poscol] = obj.mat[i][j];
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
		
		for(i = 0; i < fil; i++)
			mat[i][i] = 1;
	}
	
	public MatrizMath inversa() {
		MatrizMath Ainv = new MatrizMath(this.col, this.fil);
		Ainv = this.Clone();
		
        int n = col;
        int k, 
        	j, 
        	i;

        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                Ainv.mat[i][j] = mat[i][j];

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if ((i != k) && (j != k))
                        Ainv.mat[i][j] -= (Ainv.mat[i][k] * Ainv.mat[k][j]) / Ainv.mat[k][k];
                }

           		for (j = 0; j < n; j++) {
           			if (j != k)
           				Ainv.mat[k][j] = -Ainv.mat[k][j] / Ainv.mat[k][k];
           		}

	            for (i = 0; i < n; i++) {
	                if (i != k)
	                    Ainv.mat[i][k] = Ainv.mat[i][k] / Ainv.mat[k][k];
	            }
	        Ainv.mat[k][k] = 1 / Ainv.mat[k][k];
            }
        }
    return Ainv;
  }

	public double errorCometido() throws Exception {
		if(this.col != this.fil)
			throw new Exception("La matriz no es cuadrada.");
		
		double error = 0;
		
		MatrizMath m= new MatrizMath(fil, col); //matriz identidad 
		m.identidad();
		
		m.restaMatrizMath(this.multiplicar(this.inversa()));   //resto I-I' y saco su normaDos
		error += m.normaDos();
		
		return error;
	}
	
	public static void main(String[] args) throws Exception {

		
		MatrizMath m = new MatrizMath(2,2);
		m.setValue(0,0,2);
		m.setValue(0,1,1);
		m.setValue(1,0,5);
		m.setValue(1,1,3);
		System.out.println(m);
		System.out.println(m.inversa());
		System.out.println(m.errorCometido());
		
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
		System.out.println(m2.inversa());
		System.out.println(m2.determinante());
		System.out.println(m2.errorCometido());
	}
}