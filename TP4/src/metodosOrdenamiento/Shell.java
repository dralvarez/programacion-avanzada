package metodosOrdenamiento;


public class Shell {
	
	private int[] vec;
	private int dim;
	
	public Shell(int dim,int[] vec) {
		
		this.dim = dim;	
		this.vec = new int[this.dim];
		
		for(int i = 0; i < this.dim; i++)
			this.vec[i] = vec[i];
	}
	
	public int[] getVec() {
		return vec;
	}
	
	public void setVec(int[] vec) {
		this.vec = vec;
	}
	
	public int getDim() {
		return dim;
	}
	
	public void setDim(int dim) {
		this.dim = dim;
	}
	
	public void ordenar() {
		
		int k = this.dim / 2;
		int i,
			aux,
			j,
			valor;
		
		while(k >= 1) {
			for(int t = 0; t < k;t++) {
				i = k;
				i += t;
				
				while(i<this.dim) {
					aux = this.vec[i];
					j = i-k;
					
					while(j >= 0 && this.vec[j] > aux) {	
						valor = j + k;
						this.vec[valor] = this.vec[j];
						j = j - k;
					}
					valor = j + k;
					this.vec[valor] = aux;
					i = i + k;
				}
			}
			k = k / 2;
		}	
	}
}
