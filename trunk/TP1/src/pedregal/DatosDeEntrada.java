package pedregal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatosDeEntrada {
	
	private Set<Coordenada> penascos = new HashSet<Coordenada>();
	private int cantidadDePenascos;
	private Coordenada tamanoCasa;
	private Coordenada tamanoTerreno;
	

	public DatosDeEntrada(Set<Coordenada> penascos, int cantidadDePenascos,
			Coordenada tamanoCasa, Coordenada tamanoTerreno) {
		this.penascos = penascos;
		this.cantidadDePenascos = cantidadDePenascos;
		this.tamanoCasa = tamanoCasa;
		this.tamanoTerreno = tamanoTerreno;
	}

	public DatosDeEntrada() {
	
	}
	
	public DatosDeEntrada(List<String> contenidoArchivoDeEntrada){
		
		String tamanoTerrenoString = contenidoArchivoDeEntrada.get(0);
		String tamanoCasaString = contenidoArchivoDeEntrada.get(1);
		String cantidadPenascosString = contenidoArchivoDeEntrada.get(2);
		
		this.tamanoCasa = new Coordenada(tamanoCasaString);
		this.tamanoTerreno = new Coordenada(tamanoTerrenoString);
		this.cantidadDePenascos = Integer.parseInt(cantidadPenascosString);
		
		for(int i=3 ; i < contenidoArchivoDeEntrada.size() ; i++){
			String penascoString = contenidoArchivoDeEntrada.get(i);
			penascos.add(new Coordenada(penascoString));
		}
	}

	public Set<Coordenada> getPenascos() {
		return penascos;
	}

	public void setPenascos(Set<Coordenada> penascos) {
		this.penascos = penascos;
	}

	public Coordenada getTamanoCasa() {
		return tamanoCasa;
	}

	public void setTamanoCasa(Coordenada tamanoCasa) {
		this.tamanoCasa = tamanoCasa;
	}

	public Coordenada getTamanoTerreno() {
		return tamanoTerreno;
	}

	public void setTamanoTerreno(Coordenada tamanoTerreno) {
		this.tamanoTerreno = tamanoTerreno;
	}

	public int getCantidadDePenascos() {
		return cantidadDePenascos;
	}

	public void setCantidadDePenascos(int cantidadDePenascos) {
		this.cantidadDePenascos = cantidadDePenascos;
	}

	

}
