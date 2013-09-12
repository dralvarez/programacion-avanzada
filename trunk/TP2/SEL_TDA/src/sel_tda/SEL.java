package sel_tda;
import java.io.*;

public class SEL {

	private MatrizMath m;
	private VectorMath b;
	private VectorMath x;
	private double error;
	private int dim;
	
	
	public SEL (int d) {
		dim = d;
		m = new MatrizMath(d,d);
		b = new VectorMath(d);
		x = new VectorMath(d);
		
	}
		
	public SEL(String path)
	{
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try	{   
			f = new File(path);
			fr = new FileReader(f);
			br = new BufferedReader(fr);		
			
			String linea = br.readLine();
			
			if(linea != null)
			{
				dim = Integer.parseInt(linea);
				
				if(dim < 2) {
					System.out.println("El tamaño del SEL es inferior a 2");
					System.exit(1);
				}
				m = new MatrizMath(dim, dim);
				
				int tope_mat = 0;
					
				while((linea = br.readLine()) != null && tope_mat < dim * dim)
				{
					String[] datos=linea.split(" ");
					m.setValue(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Double.parseDouble(datos[2]));
					tope_mat++;
				}
							 
				b = new VectorMath(dim);
				x = new VectorMath(dim);	
				
				b.setValue(0, Double.parseDouble(linea));
				int j = 1;
				
				while((linea = br.readLine()) != null) {	
					b.setValue(j, Double.parseDouble(linea)) ;
					j++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{ 
				if(fr!= null)
					fr.close();
			}	
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	public MatrizMath getM() {
		return m;
	}

	public void setM(MatrizMath m) {
		this.m = m;
	}

	public VectorMath getV() {
		return b;
	}

	public void setV(VectorMath b) {
		this.b = b;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
	
	public void mostrarResultado(){
		//System.out.println(this.x.toString());
	
		//System.out.println("Matriz: \n"+this.m.toString()+"\nVector: \n"+this.b.toString() + "\n\nResultado: \n" + this.x.toString());
		System.out.println("Resultado: \n" + this.x.toString());
		System.out.println("Error: " + error);
	}
	
	public void resolverSEL() throws Exception {
//		this.x = m.inversa().multiplicar(this.b);
		this.calcularErrorSolucion();
	}
	
	private void calcularErrorSolucion() throws Exception {
		error = (m.multiplicar(x).restar(b)).normaDos();
	}
	
	public String toString(){
		return "Matriz: \n"+this.m.toString()+"\nVector: "+this.b.toString();
	}
	
	public  boolean test() throws Exception{  // probador de matriz inversa, A*A-1= I  &&   ||I-I'||2 = error
		this.ErrorSolucion();

		if(error<Math.exp(-12))		// si el error es menor a e a la -12, entonces es un buen error
			return true;
		else
			return false;
	}	
	
	private void ErrorSolucion() throws Exception {
		this.error = 0;
		
		MatrizMath m1 = new MatrizMath(dim, dim); // matriz inversa
//		m1 = m.inversa();
		MatrizMath m2 = new MatrizMath(dim, dim); //matriz identidad 
		m2.identidad();
		MatrizMath m3 = new MatrizMath(dim, dim); //matriz identidad surgida de multiplicar la matriz por su inversa		
		m3 = m.multiplicar(m1);
		m2.restaMatrizMath(m3);   //resto I-I' y saco su normaDos
//		error += m2.normaDos();
	}
	
	public static void main(String[] args) throws Exception {
	/*	
		double inicio, fin;
		String nombre = "2x2.in";
		
		inicio = System.currentTimeMillis();
		SEL a=new SEL(nombre);
		a.resolverSEL();
		a.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "3x3.in";
		inicio = System.currentTimeMillis();
		SEL b=new SEL(nombre);
		b.resolverSEL();
		b.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "5x5.in";
		inicio = System.currentTimeMillis();
		SEL c=new SEL(nombre);
		c.resolverSEL();
		c.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "10x10.in";
		inicio = System.currentTimeMillis();
		SEL d=new SEL(nombre);
		d.resolverSEL();
		d.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "20x20.in";
		inicio = System.currentTimeMillis();
		SEL e=new SEL(nombre);
		e.resolverSEL();
		e.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "50x50.in";
		inicio = System.currentTimeMillis();
		SEL f=new SEL(nombre);
		f.resolverSEL();
		f.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");

		nombre = "100x100.in";
		inicio = System.currentTimeMillis();
		SEL g=new SEL(nombre);
		g.resolverSEL();
		g.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "200x200.in";
		inicio = System.currentTimeMillis();
		SEL h=new SEL(nombre);
		h.resolverSEL();
		h.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "400x400.in";
		inicio = System.currentTimeMillis();
		SEL i=new SEL(nombre);
		i.resolverSEL();
		i.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "500x500.in";
		inicio = System.currentTimeMillis();
		SEL j=new SEL(nombre);
		j.resolverSEL();
		j.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "750x750.in";
		inicio = System.currentTimeMillis();
		SEL k=new SEL(nombre);
		k.resolverSEL();
		k.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "850x850.in";
		inicio = System.currentTimeMillis();
		SEL l=new SEL(nombre);
		l.resolverSEL();
		l.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "1000x1000.in";
		inicio = System.currentTimeMillis();
		SEL m=new SEL(nombre);
		m.resolverSEL();
		m.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "1200x1200.in";
		inicio = System.currentTimeMillis();
		SEL n=new SEL(nombre);
		n.resolverSEL();
		n.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
		
		nombre = "1500x1500.in";
		inicio = System.currentTimeMillis();
		SEL o=new SEL(nombre);
		o.resolverSEL();
		o.mostrarResultado();
		fin = System.currentTimeMillis();
		System.out.println(nombre + ": " + (fin - inicio) + " ms");
	*/
	}
	
	
}
