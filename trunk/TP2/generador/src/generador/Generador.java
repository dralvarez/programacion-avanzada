package generador;

import java.io.FileWriter;
import java.io.PrintWriter;


public class Generador {

	public static void main(String[] args) {
		// Se genera la dimensión aleatoriamente con un valor entre 2 y 4
		//(int)generarNumeroAleatorio(2,5);
		
		int n = 4; //dimensión
		FileWriter fichero = null;
        PrintWriter pw = null;
       
        try
        {
            fichero = new FileWriter("sel.in");
            pw = new PrintWriter(fichero);
            
            pw.println(n); //La primer linea del archivo .in es la dimensión
            
            // Las siguientes n^2 líneas van a tener números aleatorios entre -10 y 10. n^2 es la cantidad de elementos 
            for (int i = 0; i < n; i++)
            	for (int j = 0; j < n; j++)
            		pw.println(i + " " + j + " " + generarNumeroAleatorio(-10,10));
            
            // Las siguientes n líneas van a tener números aleatorios para el vector
            for (int k = 0; k < n; k++)
            	pw.println(generarNumeroAleatorio(-10,10));

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
	
	public static double generarNumeroAleatorio (double minimo, double maximo){
		return Math.random()*(maximo-minimo)+minimo;
	}
}
