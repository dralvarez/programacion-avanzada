package sel_tda;

import java.util.Iterator;
import java.util.List;

import utils.FileUtils;

public class InterpreteMatrizMathArchivo {

	public static MatrizMath interpretar(String path){
		List<String> lineas = FileUtils.leerArchivo(path);
		Iterator<String> iteratorLineas = lineas.iterator();

		String linea;
		String[] datos;

		linea = iteratorLineas.next();
		datos = linea.split(" ");
		int cantidadFilas = Integer.parseInt(datos[0]);
		int cantidadColumnas = Integer.parseInt(datos[1]);

		double[][] matriz = new double[cantidadFilas][cantidadColumnas];
		int i = 0;

		while (iteratorLineas.hasNext()) {

			linea = iteratorLineas.next();
			datos = linea.split(" ");

			for (int j = 0; j < cantidadColumnas; j++)
				matriz[i][j] = Double.parseDouble(datos[j]);
			i++;
		}
		
		MatrizMath matrizMath = new MatrizMath(cantidadFilas,cantidadColumnas);
		matrizMath.setMatriz(matriz);
		
		return matrizMath;
				
		
	}
}
