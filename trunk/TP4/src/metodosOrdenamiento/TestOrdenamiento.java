package metodosOrdenamiento;

import java.util.Calendar;

public class TestOrdenamiento {

	public static void main(String[] args) {

		Archivo info = new Archivo();
		String linea;
		String[] array = null;
		int[] dato = null;
				
		//LEER ARCHIVO
		if((linea = info.LeerArchivo()) != null) {
				array = linea.split(" ");
		}
			
		dato = new int[array.length];
			
		for(int i=0;i<array.length;i++) {			
				dato[i] = Integer.parseInt(array[i]);
		}
		
		//Métodos De Ordenamiento
//		Burbujeo b = new Burbujeo(array.length,dato);
//		Seleccion b = new Seleccion(array.length,dato);
//		Insercion c = new Insercion(array.length,dato);
//	    Shell b = new Shell(array.length,dato);
//		QuickSort c = new QuickSort(array.length,dato);
		Fusion c = new Fusion(array.length,dato);
		
		
		//Ordenar
//		Calendar ini = Calendar.getInstance();
	   
//		b.ordenar();
	   
//		Calendar fin = Calendar.getInstance();
//		System.out.println("TiempoB: " + (fin.getTimeInMillis()-ini.getTimeInMillis()));
	///////////////////////////////////////////////////////////////////////////////////	
		Calendar inic = Calendar.getInstance();
		   
		c.ordenar();
	   
		Calendar finc = Calendar.getInstance();
		System.out.println("TiempoC: " + (finc.getTimeInMillis()-inic.getTimeInMillis()));
	//////////////////////////////////////////////////////////////////////////////////////	
		//ESCRIBIR ARCHIVO
//		info.EscribirArchivo(b.getDim(), b.getVec());
		info.EscribirArchivo(c.getDim(), c.getVec());
		
		System.out.println("Se creó el archivo de salida");
	}
}
