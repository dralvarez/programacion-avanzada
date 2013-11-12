package metodosOrdenamiento;

import java.io.*;

public class Archivo {
	
	private String linea;
	
	public Archivo() {
		this.linea = null;
	}
	
	public String getLinea() {
		return linea;
	}
	
	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String LeerArchivo() {
		//LEER ARCHIVO
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
	
		try
		{
			archivo = new File("src/metodosOrdenamiento/ordenamiento.in");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			if((this.linea = br.readLine())!=null)
				return this.linea;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		finally
		{
			try
			{
				if(archivo!=null)
					fr.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return this.linea;
	}
	
	public void EscribirArchivo(int dim,int[] linea ) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try	{
			fichero = new FileWriter("src/metodosOrdenamiento/ordenamiento.out");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < dim; i++) {
				pw.println(linea[i]);
			}
			
		}
		catch(Exception e3)	{
			e3.printStackTrace();
		}
		finally	{
			try	{
				if(null!=fichero)
					fichero.close();
			}
			catch(Exception e4)	{
				e4.printStackTrace();
			}
		}
	}
}
