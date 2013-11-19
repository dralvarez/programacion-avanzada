package progra.grupo1;

import java.util.ArrayList;
import java.util.List;

import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;

public class DatosPrincesa {

	private int indicePrincesa;
	private int indicePrincipe;
	private int[] indicesDragones;

	private Grafo grafo;

	public int getIndicePrincesa() {
		return indicePrincesa;
	}

	public void setIndicePrincesa(int indicePrincesa) {
		this.indicePrincesa = indicePrincesa;
	}

	public int getIndicePrincipe() {
		return indicePrincipe;
	}

	public void setIndicePrincipe(int indicePrincipe) {
		this.indicePrincipe = indicePrincipe;
	}

	public int[] getIndicesDragones() {
		return indicesDragones;
	}

	public void setIndicesDragones(int[] indicesDragones) {
		this.indicesDragones = indicesDragones;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public Nodo getNodoPrincipe(){
		return grafo.getNodo(indicePrincipe);
	}
	
	public Nodo getNodoPrincesa(){
		return grafo.getNodo(indicePrincesa);
	}
	
	public List<Nodo> getNodosDragones(){
		List<Nodo> nodosDragones = new ArrayList<Nodo>();
		for(int indice : indicesDragones){
			nodosDragones.add(grafo.getNodo(indice));
		}
		return nodosDragones;
	}

}