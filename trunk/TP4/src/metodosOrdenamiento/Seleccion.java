package metodosOrdenamiento;

public class Seleccion {

	private int dim;
	private int vec[];
	

	public Seleccion(int dim, int vec[]) {
		this.dim = dim;
		this.vec = new int[dim];
		
		for(int j = 0; j < dim; j++) {
			this.vec[j] = vec[j];
		}
	}
	
	public void setVec(int vec[]) {
		this.vec = vec;
	}
	
	public int[] getVec() {
		return vec;
	}
	
	public int getDim() {
		return dim;
	}
	
	public void ordenar() {
		
		int menor = 0,
			aux = 0, 
			i = 0,
			dimAux = 0;
		
		while(dimAux != dim -1) {
			for(i = dimAux; i < dim; i++)
				if (vec[menor] > vec[i])
					menor = i;
			i = dimAux;
			aux =  vec[i];
			vec[i] = vec[menor];
			vec[menor] = aux;
			dimAux++;
			menor = dimAux;
		}
	}
}
