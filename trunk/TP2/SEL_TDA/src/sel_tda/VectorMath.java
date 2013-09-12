package sel_tda;

import java.io.*;
import java.util.Arrays;

public class VectorMath {
	private int n; //n = cantidad de elementos
	private double[] v;


	public VectorMath() {
		this.n = 0;
		this.v = new double[n];
	}
	
	public VectorMath(int n) {
		this.n = n;
		this.v = new double[n];
		
		for(int i = 0; i < n; i++)
			this.v[i] = 0;
	}

	public VectorMath(String s) {
		
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			f = new File(s);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;

			if ((linea = br.readLine()) != null)
				n = Integer.parseInt(linea);

			if (n > 0) {
				v = new double[n];

				for (int i = 0; i < n; i++) {
					if ((linea = br.readLine()) != null)
						v[i] = Integer.parseInt(linea);				
				}
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

	public int getLongitud() {
		return n;
	}

	public void setLongitud(int n) {
		this.n = n;
	}
	
	public double getValue(int pos) throws Exception {
		
		if (pos >= this.getLongitud())
			throw new Exception("No existe la posición " + pos + " en el vector.");
		else
			return v[pos];
	}
	
	public void setValue(int pos, double valor) throws Exception {
		
		if (pos >= this.getLongitud())
			throw new Exception("No existe la posición " + pos + " en el vector.");
		else
			this.v[pos]=valor;
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
		
		if (n != other.n)
			return false;
		
		if (!Arrays.equals(v, other.v))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(v);
	}

	public VectorMath sumar(VectorMath v2) throws Exception{
		
		if(n != v2.n)
			throw new Exception ("Las longitudes de los vectores son diferentes, no se pueden sumar.");

		VectorMath v1 = new VectorMath(n);

		for (int i = 0; i < n; i++) {
			v1.v[i] = v[i] + v2.v[i];
		}
		return v1;
	}

	public VectorMath restar(VectorMath v2) throws Exception{
		
		if(n != v2.n)
			throw new Exception ("Las longitudes de los vectores son diferentes, no se pueden restar.");

		VectorMath v1 = new VectorMath(n);

		for (int i = 0; i < n; i++) {
			v1.v[i] = v[i] - v2.v[i];
		}
		return v1;
	}
	
	public VectorMath multiplicarPorUnEscalar(double r) {
		VectorMath v1 = new VectorMath(n);

		for (int i = 0; i < n; i++) {
			v1.v[i] = v[i] * r;
		}
		return v1;
	}
		
	public double productoEscalar(VectorMath v2) throws Exception{
		if(this.getLongitud()!=v2.getLongitud())
			throw new Exception ("Las longitudes de los vectores son diferentes, no se pueden multiplicar.");
		
		double r = 0;

		for (int i = 0; i < this.n; i++) 
			r += this.v[i] * v2.v[i];

		return r;
	}
	
	public VectorMath multiplicar(MatrizMath m) throws Exception  {
		if(m.getCol() != n)
			throw new Exception("No se puede realizar el producto entre el vector y la matriz");
		
		VectorMath v1 = new VectorMath(n);
		double suma;
		
		for(int i = 0; i < n; i++) {
			suma = 0;
			
			for(int j = 0; j < m.getFil(); j++) {
				suma += m.getValue(i,j) * v[i];  
			}
			v1.v[i] = suma;
		}
		return v1;
	}

	public double normaUno() {
		double r = 0;
		
		for(int i = 0; i < n; i++) {
			r += Math.abs(v[i]);
		}
		return r;
	}
	
	public double normaDos() {
		double r = 0;
		
		for(int i = 0; i < n; i++) {
			r += Math.pow(Math.abs(v[i]), 2);
		}
		return Math.sqrt(r);
	}
	
	public double normaInfinito() {
		double max = 0;
		
		for(int i = 0; i < n; i++) {
			if(max < Math.abs(v[i]))
				max = Math.abs(v[i]);
		}
		return max;
	}
	
	public static void main(String[] args) throws Exception {
		//VectorMath v1 = new VectorMath("prueba1.in");
		VectorMath v = new VectorMath("vector.txt");
		MatrizMath m = new MatrizMath("matriz.txt");
		VectorMath vr= v.multiplicar(m);
		VectorMath vr1= m.multiplicar(v);
		
		System.out.println(m);
		System.out.println(v);
		System.out.println(vr);
		System.out.println(vr1);
		
		
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

