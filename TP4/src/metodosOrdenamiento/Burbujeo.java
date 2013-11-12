package metodosOrdenamiento;

public class Burbujeo {

	private int dim;
	private int[] Vec;
	
	
	public Burbujeo(int dim,int[] vector) {
		this.dim = dim;
		this.Vec = new int[dim];
		
		for(int j=0;j<dim;j++) {
			this.Vec[j] = vector[j];
		}
	}
	
	public int getDim() {
		return dim;
	}

	public void setDim(int dim)	{
		this.dim = dim;
	}

	public int[] getVec() {
		return Vec;
	}

	public void setVec(int[] vector) {
		this.Vec = vector;
	}
	
	public void ordenar() {
		int marca = 1;
		int i;
		int aux;
		int k=this.dim-1;
		
		while(k>=0 && marca == 1) {
			i = 0;
			marca = 0;
			
			while(i<=k-1) {
				if(this.Vec[i]>this.Vec[i+1]) {
					aux = this.Vec[i];
					this.Vec[i] = this.Vec[i+1];
					this.Vec[i+1] = aux;	
					marca = 1;
				}
				i++;
			}
			k--;
		}
	}
}
