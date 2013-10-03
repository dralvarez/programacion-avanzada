package sel_tda;

public class SELTest {

	private static final double ERROR_ACEPTABLE = Math.exp(-12);
	
	/**
	 * A.X-B = 0
	 * @throws Exception
	 */
	private double calcularErrorSolucionConResta(SEL sel) {
		return (sel.getA().multiplicar(sel.getX()).restar(sel.getB())).normaDos();
	}
	
	/**
	 * A*A-1= I  &&   ||I-I'||2 = error
	 */
	private double calcularErrorSolucionConInversa(SEL sel) {
		MatrizMathCuadrada a = sel.getA();
		MatrizMathCuadrada inversa = a.inversa();
		MatrizMathCuadrada identidad = MatrizMathCuadrada.identidad(sel.getDimension());
		MatrizMath identidadPrima = a.multiplicar(inversa);
		
		return identidad.restar(identidadPrima).normaDos(); 
	}
	
	private boolean esErrorAceptable(double error){
		return error < ERROR_ACEPTABLE;
	}
	
	private void test(String path){
		long inicio = System.currentTimeMillis();
		//String fullPath = "pruebas/sel_test/" + path;
		//String fullPath = "C:/Users/Daiana/Desktop/programacion-avanzada/TP2/SEL_TDA/pruebas/sel_test/" + path;
		String fullPath = "C:/Users/dwilson/Desktop/programacion-avanzada/TP2/SEL_TDA/pruebas/sel_test/" + path;
		
		System.out.println("Inicio Prueba: " + path);
		
		SEL sel = new SEL(fullPath);
		sel.resolverSEL();
		
		System.out.println("Resultado: " + sel.getX());
		double errorSolucionConInversa = calcularErrorSolucionConInversa(sel);
		double errorSolucionConResta = calcularErrorSolucionConResta(sel);
		
		System.out.println("Error calculado usando inversa: " + errorSolucionConInversa + ". Es aceptable: " + esErrorAceptable(errorSolucionConInversa));
		System.out.println("Error calculado usando resta: " + errorSolucionConResta + ". Es aceptable: " + esErrorAceptable(errorSolucionConResta));
		
		long fin = System.currentTimeMillis();
		
		System.out.println(path + ": " + (fin - inicio) + " ms");
		System.out.println("======================================");
	}
	
	
	public static void main(String[] args) throws Exception {
		SELTest tester = new SELTest();
		//tester.test("01_caso2x2simple_AUTOCORRECCION.in");
		//tester.test("02_caso01_levementePeturbado_AUTOCORRECCION.in");
		//tester.test("03_4x4_Normal_AUTOCORRECCION.in");
		//tester.test("04_caso2x2cCasiLDsimple_AUTOCORRECCION.in");
				
		
//		tester.test("2x2.in");
//		tester.test("3x3.in");
//		tester.test("5x5.in");
//		tester.test("10x10.in");
		tester.test("14x14.in");
//		tester.test("15x15.in");
//		tester.test("20x20.in");
//		tester.test("30x30.in");
//		tester.test("100x100.in");
//		tester.test("200x200.in");
//		tester.test("400x400.in");
//		tester.test("500x500.in");
//		tester.test("750x750.in");
//		tester.test("850x850.in");
//		tester.test("1000x1000.in");
//		tester.test("1200x1200.in");
//		tester.test("1500x1500.in");
	}
}
