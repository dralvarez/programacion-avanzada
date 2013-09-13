package sel_tda;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sel_tda.Complejo;



public class VectorComplejos {
	
	private Complejo[] vecComplejos;
	
	
	public VectorComplejos(Complejo... vecComplejos) {
		this.vecComplejos = vecComplejos;
	}
	
	public VectorComplejos(int tamanio) {
		this.vecComplejos = new Complejo[tamanio];
	}

	public void ordenar(){
		List<Complejo> asList = Arrays.asList(vecComplejos);
		
		Collections.sort(asList);
		for(int i = 0 ; i < vecComplejos.length ; i++){
			for(int j = 0; j < vecComplejos.length - 2; j++){
				if(vecComplejos[j].modulo() > vecComplejos[j+1].modulo()){
					Complejo aux = vecComplejos[j];
					vecComplejos[j] = vecComplejos[j+1];
					vecComplejos[j+1] = aux;
					
				}
			}
		}
	}

	@Override
	public String toString() {
		String toString = new String();
		
		for(Complejo c : vecComplejos)
			toString += c.toString() + " , ";
		
		return toString;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		boolean sonIguales = true;
		VectorComplejos vectorRecibido = (VectorComplejos) obj;
		for(int i = 0; i<this.vecComplejos.length; i++){
			sonIguales &= vecComplejos[i] == vectorRecibido.vecComplejos[i];
		}
		return sonIguales;
	}

	public static void main(String[] args) {
		
	}
}
