package sel_tda;

public class SELTest {

	private static final double ERROR_ACEPTABLE = Math.exp(-12);
	
	/**
	 * A.X-B = 0
	 */
	private static double calcularErrorSolucionConResta(SEL sel) {
		return (sel.getA().multiplicar(sel.getX()).restar(sel.getB())).normaDos();
	}
	
	/**
	 * A*A-1= I  &&   ||I-I'||2 = error
	 */
	@SuppressWarnings("unused")
	private double calcularErrorSolucionConInversa(SEL sel) {
		MatrizMathCuadrada a = sel.getA();
		MatrizMathCuadrada inversa = a.inversa();
		MatrizMathCuadrada identidad = MatrizMathCuadrada.identidad(sel.getDimension());
		MatrizMath identidadPrima = a.multiplicar(inversa);
		
		return identidad.restar(identidadPrima).normaDos(); 
	}
	
	private static boolean esErrorAceptable(double error){
		return error < ERROR_ACEPTABLE;
	}

	public static void main(String[] args) {
		String path = "SEL_TDA/pruebas/sel_test/";
		String entrada = "/IN/";
		String salida = "/OUT/";
//		String nombre = "01_caso2x2simple_AUTOCORRECCION";
//		String nombre = "02_caso01_levementePeturbado_AUTOCORRECCION";
//		String nombre = "03_4x4_Normal_AUTOCORRECCION";
//		String nombre = "04_caso2x2cCasiLDsimple_AUTOCORRECCION";
//		String nombre = "2x2";
//		String nombre = "10x10";
//		String nombre = "20x20";
//		String nombre = "50x50";
//		String nombre = "100x100";
//		String nombre = "200x200";
//		String nombre = "300x300";
//		String nombre = "400x400";
//		String nombre = "500x500";
//		String nombre = "600x600";
//		String nombre = "750x750";
//		String nombre = "850x850";
//		String nombre = "1000x1000";		
//		String nombre = "1200x1200";
//		String nombre = "1500x1500";
		String nombre = "2000x2000";
	
		SEL a = new SEL(path + entrada + nombre + ".in");
		try {
			long inicio = System.currentTimeMillis();
			a.resolucionGaussJordan(path + salida + nombre + ".out");
			long fin = System.currentTimeMillis();
			System.out.println(nombre + ": " + (fin - inicio) + " ms");
			if (esErrorAceptable(calcularErrorSolucionConResta(a)))
					System.out.println("El error es aceptable");
			System.out.println("======================================");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
