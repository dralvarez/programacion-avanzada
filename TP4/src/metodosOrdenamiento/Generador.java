package metodosOrdenamiento;

import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Generador {
	
	private long num;
	private Random aleat;
	
	
	public Generador(long num) {
		this.num = num;
		this.aleat = new Random(3816);
	}

	public void EscribirArchivo() {
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		long i=0;
		
		try	{
			fichero = new FileWriter("src/metodosOrdenamiento/ordenamiento.in");
			pw = new PrintWriter(fichero);
			long nro;

			while(i<num) {
				nro = (long)(aleat.nextDouble()* num);
				pw.write("" + nro + " ");
				i++;
			}
								
		}
		catch(Exception e3) {
			e3.printStackTrace();
		}
		finally {
			try {
				if(null!=fichero)
					fichero.close();
			}
			catch(Exception e4) {
				e4.printStackTrace();
			}
		}
	}
		
	public static void main(String[] args) {	
		
		Generador gen = new Generador(30);	
		gen.EscribirArchivo();
	}
}
