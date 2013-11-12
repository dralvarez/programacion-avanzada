package grafosAlgoritmos.grafosDirigidos;

import java.io.*;

public class GrafosDirigidos {
	
	private int [][]matAdyacencia;
	private int nodos;
	private int aristas;
	private int prim;
	
	
	public GrafosDirigidos(String path){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			String []vec = new String [2];
			String []vec1 = new String [3];
			
			linea = br.readLine();
			vec = linea.split(" ");
			this.nodos = Integer.parseInt(vec[0]);
			this.prim = Integer.parseInt(vec[1]);
			this.matAdyacencia = new int [nodos][nodos];
			
			while((linea = br.readLine()) != null){
				vec1 = linea.split(" ");
				this.matAdyacencia[Integer.parseInt(vec1[0]) - 1][Integer.parseInt(vec1[1]) - 1] = Integer.parseInt(vec1[2]);
			} 
						
			completarMat();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fr != null)
					fr.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	
	public void completarMat(){
		for(int i = 0; i < nodos; i++)
			for(int j = 0; j < nodos; j++)
				if(this.matAdyacencia[i][j] == 0)
					this.matAdyacencia[i][j] = 100000;
	}
	
	
	//******************************************************FLOYD****************************************************************************
	public void resolverFloyd(){
		int [][]ant = new int[nodos][nodos];
		int [][]act = new int[nodos][nodos];

		ant = this.matAdyacencia;
		
		//Coloco 0 en toda la diagonal principal		
		for(int i = 0; i < nodos; i++)
			for(int j = 0; j < nodos; j++)
				if(i==j)
					ant[i][j] = 0;
		
		act = ant;
		//Voy armando una matriz por nodo, y me quedo con la que corresponde al ultimo nodo
		for(int i = 0; i < nodos; i++){
			if(i > 0)
				ant = act;
			for(int j = 0; j < nodos; j++)
				for(int m = 0; m < nodos; m++)
					if(j != m && j != i && m != i)
						if(ant[j][m] < ant[j][i] + ant[i][m])
							act[j][m] = ant[j][m]; 
						else
							act[j][m] = ant[j][i] + ant[i][m];
		}
		
		escribirArch(act, "src/grafosAlgoritmos/grafosDirigidos/floyd.out");
			
	}
	
	//******************************************************WARSHALL****************************************************************************
		public void resolverWarshall(){
			int [][]ant = new int[nodos][nodos];
			int [][]act = new int[nodos][nodos];

			ant = this.matAdyacencia;
			
			//Coloco 0 en toda la diagonal principal y dnd hay infinito coloco 0 y sino 1		
			for(int i = 0; i < nodos; i++)
				for(int j = 0; j < nodos; j++){
					if(ant[i][j] < 100000)
						ant[i][j] = 1;

					if(ant[i][j] == 100000)
						ant[i][j] = 0;

					if(i==j)
						ant[i][j] = 0;
				}
					
			act = ant;
			
			//Voy armando una matriz por nodo, y me quedo con la que corresponde al ultimo nodo
			for(int i = 0; i < nodos; i++){
				if(i > 0)
					ant = act;
				for(int j = 0; j < nodos; j++)
					for(int m = 0; m < nodos; m++)
						if(j != m && j != i && m != i){
							act[j][m] = ant[j][m] + (ant[j][i] * ant[i][m]);
							if(act[j][m] > 1)
								act[j][m] -= 1;
						}
							
			}
			
			escribirArch(act, "src/grafosAlgoritmos/grafosDirigidos/warshall.out");
				
		}
	
	public void escribirArch(int [][]mat, String path){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try{
			archivo = new File(path);
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			
			for(int i = 0; i < nodos; i++)
				for(int j = 0; j < nodos; j++)
					pw.println(mat[i][j]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(pw != null)
					pw.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//String archivo = "floyd.in";
		String archivo = "warshall.in";
		GrafosDirigidos g = new GrafosDirigidos("src/grafosAlgoritmos/grafosDirigidos/" + archivo);
		//g.resolverFloyd();
		g.resolverWarshall();
	}

}
