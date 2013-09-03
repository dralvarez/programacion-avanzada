package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {
	public static List<String> devolverLista(){
		List<String> lista = new ArrayList<String>();
		lista.add("A");
		lista.add("B");
		lista.add("C");
		lista.add("E");
		lista.add("F");
		return lista;
	}
	
	public static List<String> leerArchivo(String path){
		   File archivo = null;
	       FileReader fr = null;
	       BufferedReader br = null;
	       
	       try {
	          // Apertura del fichero y creacion de BufferedReader para poder
	          // hacer una lectura comoda (disponer del metodo readLine()).
	          archivo = new File (path);
	          fr = new FileReader (archivo);
	          br = new BufferedReader(fr);
	          List<String> lineas = new ArrayList<String>();   
	          
	          // Lectura del fichero
	          String linea;
	          while((linea=br.readLine())!=null)
	        	  lineas.add(linea);
	          
	          return lineas;
	       }
	       catch(Exception e){
	          e.printStackTrace();
	       }finally{
	          // En el finally cerramos el fichero, para asegurarnos
	          // que se cierra tanto si todo va bien como si salta 
	          // una excepcion.
	          try{                    
	             if( null != fr ){   
	                fr.close();     
	             }                  
	          }catch (Exception e2){ 
	             e2.printStackTrace();
	          }
	       }
	       return null;
	}
	
	public static void escribirArchivo(String path, List<String> contenido){
	      FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter(path);
	            pw = new PrintWriter(fichero);
	            for(String linea : contenido){
	            	pw.println(linea);	            	
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	}
}
