package generador;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Generador {

	private int[] dimensiones = null;

	private int[] LeerArchivoDimensiones(String nomArch) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File (nomArch);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			int cantDimensiones=Integer.parseInt(br.readLine());
			dimensiones = new int[cantDimensiones];
			for(int i = 0; i < cantDimensiones; i++)
				dimensiones[i] = Integer.parseInt(br.readLine());
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {                    
				if( null != fr ) {   
					fr.close();     
				}                  
			} catch (Exception e2) { 
				e2.printStackTrace();
			}
		}
		return dimensiones;
	}

	public void GenerarArchivosInput(String nomArchDimensiones){
		this.dimensiones = LeerArchivoDimensiones(nomArchDimensiones);
		String[] input = null;

		for (int n : dimensiones) {
			input= new String[1 + n * n + n];
			input[0] = String.valueOf(n);
			int linea = 1;

			//Se carga la matriz
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					input[linea] = String.valueOf(i) +" "+ String.valueOf(j) + " " + String.valueOf(Math.random() * 10);
					linea++;
				}
			}
			//Se carga el vector
			for(int k = 0; k < n; k++){
				input[linea] = String.valueOf(Math.random() * 10);
				linea++;
			}
			//Se guarda el caso en el archivo .in
			GrabarArchivoGenerado(input,n);
		}
	}

	private void GrabarArchivoGenerado(String[] datos, int numeroCaso){
		FileWriter fichero = null;
		PrintWriter pw = null;
		try
		{
			fichero = new FileWriter("SEL_TDA/pruebas/sel_test/IN/" + String.valueOf(numeroCaso) + "x" + 
					String.valueOf(numeroCaso) + ".in");
			pw = new PrintWriter(fichero);

			for (String linea : datos) {
				pw.println(linea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Generador gen = new Generador();
		gen.GenerarArchivosInput("SEL_TDA/src/generador/Archivos.txt");
		System.out.println("Se han generado los archivos de input correctamente.");
	}
}
