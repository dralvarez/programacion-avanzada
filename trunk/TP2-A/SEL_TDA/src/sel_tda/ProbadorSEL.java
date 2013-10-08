package sel_tda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ProbadorSEL {
	MatrizMathCuadrada A = null;
	VectorMath Xprima = null;
	VectorMath B = null;
	
	public MatrizMathCuadrada getA() {
		return A;
	}

	public void setA(MatrizMathCuadrada a) {
		A = a;
	}

	public VectorMath getXprima() {
		return Xprima;
	}

	public void setXprima(VectorMath xprima) {
		Xprima = xprima;
	}

	public VectorMath getB() {
		return B;
	}

	public void setB(VectorMath b) {
		B = b;
	}
	
	@SuppressWarnings("resource")
	private void LeerArchivos(String archIN, String archOUT) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {

			archivo = new File(archIN);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			String[] valoresLinea;

			int dim = Integer.parseInt(br.readLine());
			A = new MatrizMathCuadrada(dim);
			B = new VectorMath(dim);

			for(int i = 0; i < dim * dim; i++){
				linea = br.readLine();
				valoresLinea = linea.split(" ");
				A.setValue(
						Integer.parseInt(valoresLinea[0]), 
						Integer.parseInt(valoresLinea[1]), 
						Double.parseDouble(valoresLinea[2]));
			}
			
			for(int i=0;i<dim;i++){
				linea = br.readLine();
				B.setVector(i, Double.parseDouble(linea));
			}
			
			//Leo el archivo de salida
			archivo = new File(archOUT);
			fr = new FileReader(archivo);
			br=new BufferedReader(fr);
			
			dim = Integer.parseInt(br.readLine());
			Xprima = new VectorMath(dim);
			
			for(int i=0;i<dim;i++){
				linea=br.readLine();
				Xprima.setVector(i, Double.parseDouble(linea));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public Boolean Test(String archIn, String archOut) throws Exception {
		
		LeerArchivos(archIn, archOut);
		VectorMath Bprima = A.producto(Xprima);
		double e = (B.restar(Bprima)).normaDos();

		System.out.println("Error: " + e);
		
		if(e>Math.pow(10, -12))
			return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		ProbadorSEL p = new ProbadorSEL();
		String path = "SEL_TDA/pruebas/sel_test/";
		String entrada = "/IN/";
		String salida = "/OUT/";
		String nombre = "100x100";
	    try {
			p.Test(path + entrada + nombre + ".in", path + salida + nombre + ".out");
			} catch (Exception e) {
			e.printStackTrace();
			}
		System.out.println("A->");
	    System.out.println(p.getA());
	    System.out.println("B->");
	    System.out.println(p.getB());
	    System.out.println("X' ->");
	    System.out.println(p.getXprima());
	}
}

