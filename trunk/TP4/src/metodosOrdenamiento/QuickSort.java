package metodosOrdenamiento;

public class QuickSort {

	private int[] vec;
	private int dim;
	
	public int[] getVec() {
		return vec;
	}

	public int getDim() {
		return dim;
	}
	
	public QuickSort(int dim, int vec[]) {
		
		this.vec = new int[dim];
		this.dim = dim;
		
		for (int j = 0; j < dim; j++) {
			this.vec[j] = vec[j];
		}
	}

	private void ordenamiento(int izq, int der) {
		
		if (der - izq >= 1) {
			int i,
				j,
				aux,
				centro = (izq + der) / 2;

			if(vec[izq] > vec[centro]) {
				aux = vec[izq];
				vec[izq] = vec[centro];
				vec[centro] = aux;
			}
			
			if(vec[izq] > vec[der]) {
				aux = vec[izq];
				vec[izq] = vec[der];
				vec[der] = aux;
			}
			
			if(vec[centro] > vec[der])	{
				aux = vec[centro];
				vec[centro] = vec[der];
				vec[der] = aux;
			}
			
			i = izq;
			j = der - 1;
			
			if (centro >= 1) {
				aux = vec[centro];
				vec[centro] = vec[der - 1];
				vec[der-1] = aux;

				//pivot
				int pivot = vec[der-1];
				do {			
					do
						i++;
					
					while(vec[i] < pivot);
					do
						j--;
					
					while(vec[j] > pivot);
					
					if (j > i) {
						aux = vec[i];
						vec[i] = vec[j];
						vec[j] = aux;
					}
					
				} while(j > i);
			
				//ubico pivot
				if(i<der-1) {
					aux = vec[i];
					vec[i] = vec[der - 1];
					vec[der - 1] = aux;
				}
			}
			//recursiva
			ordenamiento(izq,i - 1);
			ordenamiento(i + 1,der);
		}
	}
		
	public void ordenar()
	{
		ordenamiento(0,dim - 1);
	}
}
