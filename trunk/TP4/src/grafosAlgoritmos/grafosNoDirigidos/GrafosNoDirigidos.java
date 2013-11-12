package grafosAlgoritmos.grafosNoDirigidos;

import java.io.*;

public class GrafosNoDirigidos {

	private int [][]matAdyacencia;
	private int nodos;
	private int prim;
	private int costo;
	
	
	public GrafosNoDirigidos(String path){
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
				this.matAdyacencia[Integer.parseInt(vec1[1]) - 1][Integer.parseInt(vec1[0]) - 1] = Integer.parseInt(vec1[2]);		
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
					this.matAdyacencia[i][j] = 10000;
	}
	
	//******************************************************PRIM****************************************************************************
	//Prim parte de un nodon y va añadiendo nodos cuyo peso sea mínimo, hasta pasar por todos los nodos.
	public void resolverPrim(){
		int []v = new int[nodos];
		int []u = new int[nodos];
		int []vMu = new int[nodos];
		
		//Lleno los vectores		
		u[0] = prim;
		
		for(int i = 0; i < nodos; i++){
			v[i] = i + 1;
			if(i + 1 != prim)
				vMu[i] = i + 1;
		}
		
		for(int i = 0; i < nodos - 1; i++)
			menorArista(u, vMu);
		
		escribirArch("src/grafosAlgoritmos/grafosNoDirigidos/prim.out", u); 
		//primera línea es el costo total, las que siguen, los nodos en el orden en que fueron recorridos
		
	}
	
	public void menorArista(int []u, int []vMu){
		int min = 1000000, 
			pos = 0;
		
		for(int i = 0; i < nodos; i++) {
			if(u[i] != 0)
				for(int j = 0; j < nodos; j++){
					if(vMu[j] != 0)
						if(this.matAdyacencia[u[i] - 1][vMu[j] - 1] < min){
							min = this.matAdyacencia[u[i] - 1][vMu[j] - 1];
							pos = j + 1;
						}
				}
		}
		this.costo += min; 
		sacarDevMu(vMu, pos);
		ponerEnU(u, pos);
	}
	
	public void sacarDevMu(int []vMu, int pos){
		for(int i = 0; i < nodos; i ++)
			if(vMu[i] == pos)
				vMu[i] = 0;
	}
	

	public void ponerEnU(int []u, int pos){
		for(int i = 0; i < nodos; i ++)
			if(u[i] == 0){
				u[i] = pos;
				i = nodos;
			}
				
	}
	
	public void escribirArch(String path, int []u){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try{
			archivo = new File(path);
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			
			pw.println(this.costo);
			for(int i = 0; i < nodos; i++)
				pw.println(u[i]);
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
	
	
	//******************************************************KRUSKAL****************************************************************************
	public void Kruskal(String path){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			String []vec = new String [3];
			
			linea = br.readLine();
			int [][]mat = new int[nodos * nodos][3];
			int fil = 0;
			
			while((linea = br.readLine()) != null){
				vec = linea.split(" ");
				mat[fil][0] = Integer.parseInt(vec[0]);
				mat[fil][1] = Integer.parseInt(vec[1]);
				mat[fil][2] = Integer.parseInt(vec[2]);
				fil++;
			} 
			ordenarDeMayAMen(mat, fil);
			resolverKruskal(mat, fil);		
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
	
	public void ordenarDeMayAMen(int [][]mat, int fil){
		int aux1, aux2, auxcosto;
		for(int i = 1; i <= fil - 1; i++)
			for(int j = 0; j <= fil-2; j++)
				if(mat[j][2] > mat[j+1][2]){
					aux1 = mat[j+1][0];
					aux2 = mat[j+1][1];
					auxcosto = mat[j+1][2];
					mat[j+1][0] = mat[j][0];
					mat[j+1][1] = mat[j][1];
					mat[j+1][2] = mat[j][2];
					mat[j][0] = aux1;
					mat[j][1] = aux2;
					mat[j][2] = auxcosto;
				}
	}
	
	//Kruskall ordena por el peso de las aristas y va eligiendo las de menor peso con dos premisas: no repetir nodos y sin hacer ningun bucle hasta el final.
	public void resolverKruskal(int [][]mat, int fil){
		int cantNodos = 0;
		int [][]matSal = new int[nodos][3];
		
		for(int i = 0; i < nodos; i++)
			if((noEsta(mat, matSal, i) == true)){
				matSal[cantNodos][0] = mat[i][0];
				matSal[cantNodos][1] = mat[i][1];
				matSal[cantNodos][2] = mat[i][2];
				cantNodos++;
				this.costo += mat[i][2]; 
			}
		escribirArch("src/grafosAlgoritmos/grafosNoDirigidos/kruskal.out", matSal);				
	}
	
	public boolean noEsta(int [][]mat, int [][]matSal, int i){
		int marca1 = 0, marca2 = 0;
		
		for(int j = 0; j < nodos; j++){
			if(matSal[j][0] == mat[i][0] || matSal[j][1] == mat[i][0])
				marca1 = 1;
			if(matSal[j][0] == mat[i][1] || matSal[j][1] == mat[i][1])
				marca2 = 1;
		}
		if(marca1 == 1 && marca2 == 1)
			return false;
		else
			return true;
	}
	
	public void escribirArch(String path, int [][]matSal){
		File archivo = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try{
			archivo = new File(path);
			fw = new FileWriter(archivo);
			pw = new PrintWriter(fw);
			
			pw.println(this.costo);
			for(int i = 0; i < matSal.length; i++)
				if(matSal[i][1] != 0)
					pw.println(matSal[i][0] + " " + matSal[i][1] + " " + matSal[i][2]);
				else
					i = matSal.length;
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

		GrafosNoDirigidos gn = new GrafosNoDirigidos("src/grafosAlgoritmos/grafosNoDirigidos/prim.in");
		//gn.resolverPrim(); //FUNCIONA!
		gn.Kruskal("src/grafosAlgoritmos/grafosNoDirigidos/kruskal.in"); //Averiguar si Prim y Kruskal dan el mismo resultado
	}

}
