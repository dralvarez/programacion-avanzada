package progra.grupo1.proceso;

import java.util.List;

import progra.grupo1.DatosPrincesa;
import progra.grupo1.InterpreteArchivoPrincesa;
import progra.grupo1.ResultadoPrincesa;
import progra.grupo1.modelo.Grafo;
import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.helper.Interprete;

public class RescatandoPrincesa {

	public ResultadoPrincesa rescatar(String path){
		EstadosRescate estado = EstadosRescate.EXITO;
		
		DijkstraImpl dijkstra = new DijkstraImpl();
		Interprete<DatosPrincesa> interprete = new InterpreteArchivoPrincesa();
		
		DatosPrincesa datos = interprete.generar(path);
		
		Grafo grafo = datos.getGrafo();
		Nodo nodoInicial = datos.getNodoPrincipe();
		Nodo nodoFinal = datos.getNodoPrincesa();
		List<Nodo> nodosDragones = datos.getNodosDragones();
		
		List<Nodo> caminoMasCorto = dijkstra.getCaminoMasCorto(grafo, nodoInicial, nodoFinal);
		
		if(!caminoMasCorto.isEmpty()){
			
			if(tieneDragones(caminoMasCorto, nodosDragones)){
				grafo.eliminarNodos(nodosDragones);
				
				caminoMasCorto = dijkstra.getCaminoMasCorto(grafo, nodoInicial, nodoFinal);
				
				if(caminoMasCorto.isEmpty()){
					estado = EstadosRescate.INTERCEPTADO;				
				}
			}
		} else {
			estado = EstadosRescate.NO_HAY_CAMINO;
		}
			
		return new ResultadoPrincesa(estado, caminoMasCorto);
	}

	private boolean tieneDragones(List<Nodo> caminoMasCorto,
			List<Nodo> nodosDragones) {
		boolean tieneDragones = false;
		
		for(Nodo nodo : caminoMasCorto){
			for(Nodo dragon : nodosDragones){
				if(nodo.equals(dragon)){
					tieneDragones = true;
					break;
				}
			}
		}
		
		return tieneDragones;
	}
	
	public enum EstadosRescate {
		EXITO, INTERCEPTADO, NO_HAY_CAMINO;
	}
	
	public static void main(String[] args) {
		
		RescatandoPrincesa rescate = new RescatandoPrincesa();
		ResultadoPrincesa princesa = rescate.rescatar("recursos/princesa1.in");

		System.out.println(princesa);
		
	}
}
