package metodosOrdenamiento;

public class Insercion {

	private int dim;
	private int vec[];
	

	public Insercion(int dim, int vec[]) {
		this.dim = dim;
		this.vec = new int[dim];
		
		for(int j=0;j<dim;j++) {
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
		
		int aux,
			j;
		
		for (int i=1; i<vec.length; i++) {
		    aux = vec[i];
		    
		    for (j=i-1; j>=0 && vec[j]>aux; j--)
		        vec[j+1] = vec[j];
		    
		    vec[j+1] = aux;
		}
	}
}
