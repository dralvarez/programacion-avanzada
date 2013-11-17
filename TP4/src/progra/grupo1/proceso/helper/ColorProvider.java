package progra.grupo1.proceso.helper;


public class ColorProvider {

	String[] coloresPosibles = new String[]{"ROJO", "BLANCO", "AZUL", "NEGRO", 
											"VERDE", "NARANJA", "MARRON", "BEIGE", 
											"BORDO", "DORADO", "PLATEADO"};

	public String getColor(int indice){
		return String.format("COLOR_%d",indice);
	}
	
}
