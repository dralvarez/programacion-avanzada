package sel_tda;
import java.io.*;

public class SEL {

	private MatrizMathCuadrada a;
	private VectorMath b;
	private VectorMath x;
	private int dimension;
	
	public SEL (int dimension) {
		this.dimension = dimension;
		a = new MatrizMathCuadrada(dimension);
		b = new VectorMath(dimension);
		x = new VectorMath(dimension);
	}
	
	public SEL(MatrizMathCuadrada mc, VectorMath b) {
		validarRangosMatrizYVector(mc,b);
		this.a = mc;
		this.b = b;
	}

	private void validarRangosMatrizYVector(MatrizMathCuadrada matriz,
			VectorMath vector) {
		if(matriz.getDimension() != vector.getCantidadElementos()){
			throw new MatrizYVectorDeDistintaDimensionException(matriz, vector);
		}
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
			
			if(linea != null) {
				dimension = Integer.parseInt(linea);
				
				if(dimension < 2) {
					System.out.println("El tamaño del SEL es inferior a 2");
					System.exit(1);
				}
				a = new MatrizMathCuadrada(dimension);
				
				int tope_mat = 0;
					
				while((linea = br.readLine()) != null && tope_mat < dimension * dimension) {
					String[] datos=linea.split(" ");
					a.setValue(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Double.parseDouble(datos[2]));
					tope_mat++;
				}
							 
				b = new VectorMath(dimension);
				x = new VectorMath(dimension);	
				
				b.setValue(0, Double.parseDouble(linea));
				int j = 1;
				
				while((linea = br.readLine()) != null) {	
					b.setValue(j, Double.parseDouble(linea)) ;
					j++;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally	{
			try	{ 
				if(fr!= null)
					fr.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public MatrizMathCuadrada getA() {
		return a;
	}

	public void setA(MatrizMathCuadrada a) {
		this.a = a;
	}

	public VectorMath getB() {
		return b;
	}

	public void setB(VectorMath b) {
		this.b = b;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDim(int dim) {
		this.dimension = dim;
	}
	
	public VectorMath getX() {
		return x;
	}
	
	
	/**
	 * AX = B
	 * A-1.A.X = A-1.B
	 * I X - A-1.B
	 * X = A-1.B
	 * @throws Exception
	 */
	public void resolverSEL() {
		this.x = a.inversa().multiplicar(this.b);
	}
	
	public String toString(){
		return "Matriz: \n"+this.a.toString()+"\nVector: "+this.b.toString();
	}
	
}
