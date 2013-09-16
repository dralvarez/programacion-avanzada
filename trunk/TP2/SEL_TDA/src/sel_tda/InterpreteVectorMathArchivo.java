package sel_tda;

import java.util.Iterator;
import java.util.List;

import utils.FileUtils;

public class InterpreteVectorMathArchivo {

	public static VectorMath interpretar(String path){
		List<String> lineas = FileUtils.leerArchivo(path);
		Iterator<String> iteratorLineas = lineas.iterator();

		String[] datos;

		String linea = iteratorLineas.next();
		int cantidadElementos = Integer.parseInt(linea);
		double[] vector = new double[1];

		if (cantidadElementos > 0) {
			vector = new double[cantidadElementos];
			int i=0;
			while(iteratorLineas.hasNext()){
				linea = iteratorLineas.next();
				vector[i] = Integer.parseInt(linea);
				i++;
			}
		}
		
		VectorMath vectorMath = new VectorMath(cantidadElementos);
		vectorMath.setVector(vector);
		
		return vectorMath;			
	}
}
