package sel_tda;

import java.util.Arrays;
import java.util.Collections;

public class VectorComplejos {
	
	private Complejo[] vector;
	
	public VectorComplejos(Complejo... vecComplejos) {
		this.vector = vecComplejos;
	}
	
	public VectorComplejos(int tamanio) {
		this.vector = new Complejo[tamanio];
	}

	public void ordenar(){
		Collections.sort(Arrays.asList(vector));
//		for(int i = 0 ; i < vector.length ; i++){
//			for(int j = 0; j < vector.length - 2; j++){
//				if(vector[j].compareTo(vector[j+1]) == 1){
//					Complejo aux = vector[j];
//					vector[j] = vector[j+1];
//					vector[j+1] = aux;
//					
//				}
//			}
//		}
	}

	@Override
	public String toString() {
		String toString = new String();
		
		for(Complejo c : vector)
			toString += c.toString() + " , ";
		
		return toString;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean sonIguales = true;
		
		if(obj == null){
			return false;
		}
		
		if(!(obj instanceof VectorComplejos))
			return false;
			
		VectorComplejos vectorRecibido = (VectorComplejos) obj;
		
		if(vectorRecibido.vector.length != this.vector.length)
			return false;
		
		for(int i = 0; i<this.vector.length; i++){
			sonIguales &= vector[i].equals(vectorRecibido.vector[i]);
		}
		
		return sonIguales;
	}
	
	public Complejo[] getVector() {
		return vector;
	}

	public void setVector(Complejo[] vector) {
		this.vector = vector;
	}

	public static void main(String[] args) {
/*		Complejo c1 = new Complejo(0,0);
		Complejo c2 = new Complejo(1,1);
		Complejo c3 = new Complejo(2,2);
		Complejo c4 = new Complejo(3,3);
		Complejo c5 = new Complejo(4,4);
		Complejo c6 = new Complejo(5,5);
		Complejo c7 = new Complejo(6,6);
		Complejo c8 = new Complejo(7,7);
		Complejo c9 = new Complejo(8,8);
		
		//Ordenar vector desordenado
		System.out.println("Ordenando vector desordenado");
		VectorComplejos vectorDesordenado = new VectorComplejos(c2, c8, c3, c7, c9, c1, c4, c6, c5);
		System.out.println(vectorDesordenado);
		vectorDesordenado.ordenar();
		System.out.println(vectorDesordenado);
		
		//Ordenar vector ordenado
		System.out.println("Ordenando vector ordenado");
		VectorComplejos vectorOrdenado = new VectorComplejos(c1, c2, c3, c4, c5, c6, c7, c8, c9);
		System.out.println(vectorOrdenado);
		vectorOrdenado.ordenar();
		System.out.println(vectorOrdenado);
*/		
	}	
}
