package algoritmosGrafos.modeloAlternativo;

import java.io.*;

 

public class Prim {
	private int cantNodos;
	private int[] marcados;
	private int[][] ady;
	private char[] nombre = null;
	private int total;
	private String[] cadenaParaGuardar = null;
	private int cadenasGuardadas;
	private int aristas=0;

	public Prim(Grafo g) {
		
		this.nombre = new char[g.getCantNodos()];
		
		this.nombre[0] = '1';
		this.nombre[1] = '2';
		this.nombre[2] = '3';
		this.nombre[3] = '4';
		this.nombre[4] = '5';
		this.nombre[5] = '6';
		
		this.cantNodos = g.getCantNodos();
		this.aristas = g.getAristas();
		
		this.ady = g.getAdy();
		this.marcados = new int[g.getCantNodos()];
		this.cadenaParaGuardar = new String[g.getCantNodos()];
		
		for (int i = 0; i < this.cantNodos -1; i++)
			this.menorArista();
	}
	
	public Prim(String PATHin) {

		this.cadenasGuardadas = 0;

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(PATHin);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();
			String[] d = linea.split(" ");
			this.cantNodos = Integer.parseInt(d[0]);
			this.aristas = Integer.parseInt(d[1]);
			
			this.nombre = new char[this.cantNodos];
			this.ady = new int[this.cantNodos][this.cantNodos];
			this.marcados = new int[this.cantNodos];
			this.cadenaParaGuardar = new String[this.cantNodos];
			
			for (int i = 0; i < this.cantNodos; i++) {
				for (int j = 0; j < this.cantNodos; j++) {
					this.ady[i][j] = 0;
				}
			}

			String line;
			line = br.readLine();
			 
			while(line != null)	{
				String [] datos;
				datos = line.split(" ");
	         	int desde = Integer.parseInt(datos[0]);
	 			int hasta = Integer.parseInt(datos[1]);
				int distancia = Integer.parseInt(datos[2]);
				//System.out.println("Desde: "+ desde + " Hasta: "+ hasta +" Distancia: "+distancia );
				this.ady[desde-1][hasta-1] = distancia;
				this.ady[hasta-1][desde-1] = distancia;
				line = br.readLine();
			}			
			
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
		
		this.nombre[0] = '1';
		this.nombre[1] = '2';
		this.nombre[2] = '3';
		this.nombre[3] = '4';
		this.nombre[4] = '5';
		this.nombre[5] = '6';
	}

	public void llenar() {
		for (int i = 0; i < this.cantNodos; i++) {
			for (int j = 0; j < this.cantNodos; j++) {
				ady[i][j] = 9999;
				ady[j][i] = 9999;
			}
		}

		ady[0][1] = ady[1][0] = 9;
		ady[0][4] = ady[4][0] = 8;
		ady[0][5] = ady[5][0] = 1;
		ady[1][2] = ady[2][1] = 3;
		ady[1][5] = ady[5][1] = 2;
		ady[2][3] = ady[3][2] = 5;
		ady[2][5] = ady[5][2] = 4;
		ady[3][4] = ady[4][3] = 7;
		ady[4][5] = ady[5][4] = 6;

		this.marcados[0] = 1;
	}

	public boolean estaMarcado(int v) {
		return this.marcados[v] == 1;
	}

	public void verAdy() {
		for (int i = 0; i < this.cantNodos; i++) {
			for (int j = 0; j < this.cantNodos; j++) {
				System.out.print(this.ady[i][j] + "  ");
			}
		System.out.println("");
		}
	}

	public void menorArista() {
		int menor = 9999;
		this.marcados[0] = 1;
		int desde = -1;
		int hasta = -1;

		for (int i = 0; i < this.cantNodos; i++) {
			for (int j = 0; j < this.cantNodos; j++) {
				if (estaMarcado(i) && !estaMarcado(j) && this.ady[i][j] < menor && this.ady[i][j] != 0) {
					menor = this.ady[i][j];
					desde = i;
					hasta = j;

				}
			}
		}
		this.total += this.ady[desde][hasta];
		this.marcados[hasta] = 1;
		this.cadenaParaGuardar[this.cadenasGuardadas] = this.nombre[desde] + " " + this.nombre[hasta] + " " + menor;
		this.cadenasGuardadas++;
	}

	public void aplicarPrim() {
		for (int i = 0; i < this.cantNodos -1; i++)
			menorArista();
	}

	public void mostrarResultado() {
		for (int i = 0; i < this.cadenasGuardadas; i++)
			System.out.println(this.cadenaParaGuardar[i]);
		System.out.println(this.total);
	}

	public static void main(String args[]) {

		Grafo g = new Grafo ("src/algoritmosGrafos/modeloAlternativo/prueba.txt", "P");
		Prim p = new Prim(g);
		System.out.println("\n" + "Camino:");
		p.mostrarResultado();
		
	}
}
