package metodosOrdenamiento;

public class Fusion {
	
	private int vec[];
	private int dim;
	
	
	public Fusion(int dim, int vec[]) {
		this.dim = dim;
		this.vec = new int[dim];
		
		for(int j=0;j<dim;j++) {
			this.vec[j] = vec[j];
		}
	}
	
	public int getDim() {
		return dim;
	}
	
	public int[] getVec() {
		return vec;
	}
	
	private void intercalar(int vecAux[], int izq,int centro, int der) {
		
		int ap = izq, 
			bp = centro, 
			cp = izq;
		
		while ( (ap < centro) && bp <= der) {
			if(vec[ap]<= vec[bp]) {
				vecAux[cp] = vec[ap];
				ap++;
			}
			else {
				vecAux[cp] = vec[bp];
				bp++;
			}
			cp++;
		}
		while(ap < centro) {
			vecAux[cp] = vec[ap];
			cp++;
			ap++;
		}
		while(bp <= der) {
			vecAux[cp] =  vec[bp];
			cp++;
			bp++;
		}
		for(ap = izq;ap <= der;ap++)
			vec[ap] = vecAux[ap];
	}
	
	private void intercalacion(int vecAux[], int izq, int der) {
		
		if (izq < der) {
			int centro = (izq + der) / 2;
			intercalacion(vecAux,izq,centro);
			intercalacion(vecAux,centro+1,der);
			intercalar(vecAux,izq,centro+1,der);
		}
	}
	
	public void ordenar() {
		int[] vecAux = new int[dim];
		intercalacion(vecAux,0,dim-1);
	}
}
