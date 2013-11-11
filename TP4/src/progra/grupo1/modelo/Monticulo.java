package progra.grupo1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Monticulo {

	private List<NodoWrapper> nodos;
	
	public Monticulo(int cantidadNodosInicial){
		nodos = new ArrayList<NodoWrapper>(cantidadNodosInicial);
	}
	
	protected NodoWrapper getPadre(NodoWrapper nodo){
		int posicionPadre = nodo.getIndice() / 2;
		return nodos.get(posicionPadre);
	}
	
	protected NodoWrapper getHijoIzquierdo(NodoWrapper nodo){
		int posicionHijo = 2 * nodo.getIndice();
		return nodos.get(posicionHijo);
	}
	
	protected NodoWrapper getHijoDerecho(NodoWrapper nodo){
		int posicionHijo = 2 * nodo.getIndice() + 1; 
		return nodos.get(posicionHijo);
	}
	
	public void push(int prioridad, Object contenido){
		int siguienteIndice = getSiguienteIndice();
		
		NodoWrapper wrapper = new NodoWrapper(contenido, siguienteIndice, prioridad);
		NodoWrapper padre = this.getPadre(wrapper);
		
		if(!esRaiz(padre) && padre.getPrioridad() < wrapper.getPrioridad()){
			swapPosition(wrapper, padre);
		}
	}
	

	private void swapPosition(NodoWrapper wrapper, NodoWrapper padre) {
		
		int posOriginalNodo = wrapper.getIndice();
		int posOriginalPadre = padre.getIndice();

		wrapper.setIndice(posOriginalPadre);
		padre.setIndice(posOriginalNodo);
		
		nodos.set(wrapper.getIndice(), wrapper);
		nodos.set(padre.getIndice(), padre);
		
		NodoWrapper nuevoPadre = this.getPadre(wrapper);
		
		if(!esRaiz(nuevoPadre) && nuevoPadre.getPrioridad() < wrapper.getPrioridad()){
			swapPosition(wrapper, nuevoPadre);
		}
		
	}

	private int getSiguienteIndice() {
		return this.nodos.size();
	}
	
	private int getIndiceUltimoInsertado() {
		return this.nodos.size() -1;
	}
	
	protected Integer getIndiceRaiz() {
		return 1;
	}
	

	protected boolean esRaiz(NodoWrapper nodo){
		NodoWrapper raiz = getRaiz();
		
		return 	raiz.getPrioridad() == nodo.getPrioridad() && 
				nodo.getContenido().equals(nodo.getContenido());
	}
	
	protected NodoWrapper getRaiz() {
		int indiceRaiz = this.getIndiceRaiz();
		return nodos.get(indiceRaiz);
	}

	protected NodoWrapper getUltimoInsertado() {
		int indiceUltimoInsertado = this.getIndiceUltimoInsertado();
		return nodos.get(indiceUltimoInsertado);
	}
	
	
	
}
