package progra.grupo1;

import java.util.List;

import progra.grupo1.modelo.Nodo;
import progra.grupo1.proceso.RescatandoPrincesa.EstadosRescate;

public class ResultadoPrincesa {

	private EstadosRescate estado;
	
	private List<Nodo> camino;
	
	public ResultadoPrincesa(EstadosRescate estado, List<Nodo> camino) {
		this.estado = estado;
		this.camino = camino;
	}

	public EstadosRescate getEstado() {
		return estado;
	}

	public void setEstado(EstadosRescate estado) {
		this.estado = estado;
	}

	public List<Nodo> getCamino() {
		return camino;
	}

	public void setCamino(List<Nodo> camino) {
		this.camino = camino;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Estado: " + estado);
		b.append("====>");
		if(EstadosRescate.EXITO.equals(estado)){
			b.append("Camino: " + camino);
		}
		
		return b.toString();
	}
	
	
}
