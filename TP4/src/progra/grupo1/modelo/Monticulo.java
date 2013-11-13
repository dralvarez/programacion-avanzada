package progra.grupo1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Monticulo {

	private List<NodoWrapper> nodos;

	public Monticulo(int cantidadNodosInicial) {
		nodos = new ArrayList<NodoWrapper>(cantidadNodosInicial);
		// Fuerzo a ocupar el primer lugar vacio para facilitar las cuentas con los indices
		nodos.add(null);
	}

	protected NodoWrapper getPadre(NodoWrapper nodo) {
		int posicionPadre = nodo.getIndice() / 2;
		return posicionPadre > 0 ? nodos.get(posicionPadre) : null;
	}

	protected NodoWrapper getHijoIzquierdo(NodoWrapper nodo) {
		int posicionHijo = 2 * nodo.getIndice();
		return posicionHijo < nodos.size() ? nodos.get(posicionHijo) : null;
	}

	protected NodoWrapper getHijoDerecho(NodoWrapper nodo) {
		int posicionHijo = 2 * nodo.getIndice() + 1;
		return posicionHijo < nodos.size() ? nodos.get(posicionHijo) : null;
	}

	public void push(int prioridad, Object contenido) {
		int siguienteIndice = getSiguienteIndice();

		NodoWrapper wrapper = new NodoWrapper(contenido, siguienteIndice,
				prioridad);
		NodoWrapper padre = this.getPadre(wrapper);

		nodos.add(wrapper);

		if (padre != null && !esRaiz(wrapper) && padre.getPrioridad() < wrapper.getPrioridad()) {
			swapPositionAscendente(wrapper, padre);
		}
	}

	public Object pop() {
		NodoWrapper raiz = this.getRaiz();
		NodoWrapper ultimo = this.getUltimoInsertado();

		Object contenido = raiz.getContenido();

		Integer indiceRaiz = this.getIndiceRaiz();
		Integer indiceUltimo = this.getIndiceUltimoInsertado();

		ultimo.setIndice(indiceRaiz);

		// Pongo en la raiz el ultimo insertado
		nodos.set(indiceRaiz, ultimo);
		// Elimino el ultimo insertado
		nodos.remove(indiceUltimo.intValue()); // NO SACAR EL intValue() !! No hace autounboxing

		NodoWrapper hijoIzquierdo = getHijoIzquierdo(ultimo);
		NodoWrapper hijoDerecho = getHijoDerecho(ultimo);
		
		NodoWrapper hijoToSwap = null;
		
		if(hijoIzquierdo != null && hijoDerecho != null){
			hijoToSwap = hijoIzquierdo.getPrioridad() > hijoDerecho.getPrioridad() ? hijoIzquierdo : hijoDerecho; 
		} else if(hijoIzquierdo != null){
			hijoToSwap = hijoIzquierdo;
		} else if(hijoDerecho != null){
			hijoToSwap = hijoDerecho;
		}

		if (hijoToSwap != null && hijoToSwap.getPrioridad() > ultimo.getPrioridad()) {
			swapPosicionDescendente(ultimo,hijoToSwap);
		} 

		return contenido;
	}

	protected void swapPosicionDescendente(NodoWrapper padre, NodoWrapper hijo) {

		swapPosiciones(padre, hijo);

		NodoWrapper hijoIzquierdo = getHijoIzquierdo(padre);
		NodoWrapper hijoDerecho = getHijoDerecho(padre);
		
		NodoWrapper hijoToSwap = null;
		
		if(hijoIzquierdo != null && hijoDerecho != null){
			hijoToSwap = hijoIzquierdo.getPrioridad() > hijoDerecho.getPrioridad() ? hijoIzquierdo : hijoDerecho; 
		} else if(hijoIzquierdo != null){
			hijoToSwap = hijoIzquierdo;
		} else if(hijoDerecho != null){
			hijoToSwap = hijoDerecho;
		}

		if (hijoToSwap != null && hijoToSwap.getPrioridad() > padre.getPrioridad()) {
			swapPosicionDescendente(padre,hijoToSwap);
		} 

	}

	protected void swapPosiciones(NodoWrapper padre, NodoWrapper hijo) {
		Integer indiceHijo = hijo.getIndice();
		Integer indicePadre = padre.getIndice();

		hijo.setIndice(indicePadre);
		padre.setIndice(indiceHijo);

		// Invierto los indices
		nodos.set(indiceHijo, padre);
		nodos.set(indicePadre, hijo);
	}

	protected void swapPositionAscendente(NodoWrapper hijo, NodoWrapper padre) {
		swapPosiciones(hijo, padre);

		NodoWrapper nuevoPadre = this.getPadre(hijo);

		if (!esRaiz(hijo) && nuevoPadre.getPrioridad() < hijo.getPrioridad()) {
			swapPositionAscendente(hijo, nuevoPadre);
		}

	}

	protected int getSiguienteIndice() {
		return this.nodos.size();
	}

	protected int getIndiceUltimoInsertado() {
		return this.nodos.size() - 1;
	}

	protected Integer getIndiceRaiz() {
		return 1;
	}

	protected boolean esRaiz(NodoWrapper nodo) {
		NodoWrapper padre = this.getPadre(nodo);
		return padre == null;
	}

	protected NodoWrapper getRaiz() {
		int indiceRaiz = this.getIndiceRaiz();
		return nodos.size() > 0 ? nodos.get(indiceRaiz) : null;
	}

	protected NodoWrapper getUltimoInsertado() {
		int indiceUltimoInsertado = this.getIndiceUltimoInsertado();
		return nodos.get(indiceUltimoInsertado);
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		for (int i = 1; i < nodos.size(); i++) {
			NodoWrapper n = nodos.get(i);
			b.append(n + " || ");
		}

		return b.toString();
	}
	
	protected int getCantidadNodos(){
		return nodos.size();
	}
	
	public String deepToString(){
		
		StringBuilder b = new StringBuilder();
		
		for(NodoWrapper nodo : getNodos()){
			
			NodoWrapper hijoIzquierdo = getHijoIzquierdo(nodo);
			NodoWrapper hijoDerecho = getHijoDerecho(nodo);
			
			System.out.println("===============================");
			System.out.println("Nodo " + nodo);
			System.out.println("Hijo izq" + hijoIzquierdo);
			System.out.println("Hijo der " + hijoDerecho);
			
			
			System.out.println("===============================");
		}
		
		return b.toString();
	}
	
	public List<NodoWrapper> getNodos(){
		return nodos.subList(1, nodos.size() -1 );
	}

}
