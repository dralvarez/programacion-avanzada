package algoritmosGrafos.modeloAlternativo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Grafo {
	
	private int cantNodos;
	int[][] ady;
	int[] distancia; //vector resultado
	int visto[];
	int vistos;
	int caminos[][];
	int precede[];
	int origen;
	int cantAristas;
	char nombre[];

	public Grafo (String PathIn, String metodo) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
	
		try {
			archivo = new File(PathIn);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
	
			String linea = br.readLine();
	
			String[] datos = linea.split(" ");
	
			this.cantNodos = Integer.parseInt(datos[0]);
			this.cantAristas = Integer.parseInt(datos[1]);
	
			this.ady = new int[this.cantNodos][this.cantNodos];
			
			if ( metodo == "D"){
				
				for (int i = 0; i < this.cantNodos; i++) {
					for (int j = 0; j < this.cantNodos; j++) {
						if (i == j)
							this.ady[i][j] = 0;
						else
							this.ady[i][j] = 999;
					}
				}
		
				for (int i = 0; i < cantAristas; i++) {
					datos = br.readLine().split(" ");
		
					int nodoOrig = Integer.parseInt(datos[0]);
					int nodoDest = Integer.parseInt(datos[1]);
					int peso = Integer.parseInt(datos[2]);
		
					this.ady[nodoOrig - 1][nodoDest - 1] = peso;
		
				}
			}
			else if (metodo == "P") {
				for (int i = 0; i < this.cantNodos; i++) {
					for (int j = 0; j < this.cantNodos; j++) {
						this.ady[i][j] = 0;
					}
				}
	
				String line;
				line = br.readLine();
				 
				while(line != null) {
					datos = line.split(" ");
		         	int desde = Integer.parseInt(datos[0]);
		 			int hasta = Integer.parseInt(datos[1]);
					int distancia = Integer.parseInt(datos[2]);
					System.out.println("Desde: "+ desde + " Hasta: "+ hasta +" Distancia: "+distancia );
					this.ady[desde-1][hasta-1] = distancia;
					this.ady[hasta-1][desde-1] = distancia;
					line = br.readLine();
				}		
			}		
	
			this.verAdy();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	
		}
	}

	public void verAdy() {
		for (int i = 0; i < this.cantNodos; i++) {
			for (int j = 0; j < this.cantNodos; j++) {
				System.out.print(this.ady[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
	public int buscarNodo(char n) {
		for(int i=0; i < this.cantNodos; i++)
			if(nombre[i] == n)
				return i;
		return 0;
	}
	
	public char[] getNombre() {
		return nombre;
	}
	
	public int getCantNodos(){
		return this.cantNodos;
	}

	public int getAristas(){
		return this.cantAristas;
	}
	
	public int [][] getAdy(){
		return this.ady;
	}
/*	
	public static void main(String[] args) {
		
		Grafo g = new Grafo("src/algoritmosGrafos/modeloAlternativo/prueba.txt", "P");
		g.verAdy();
	}
*/
}
