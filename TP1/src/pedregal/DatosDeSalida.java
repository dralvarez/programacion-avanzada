package pedregal;

import java.util.List;

public class DatosDeSalida {
	
	private boolean sePuedeConstruir;
	private Coordenada posicionCasa;
	private String orientacion;
	
	public DatosDeSalida() {
	}

	public DatosDeSalida(boolean sePuedeConstruir, Coordenada posicionCasa,
			String orientacion) {
		this.sePuedeConstruir = sePuedeConstruir;
		this.posicionCasa = posicionCasa;
		this.orientacion = orientacion;
	}

	public DatosDeSalida(List<String> archivoDeSalida) {
		String sePuedeConstruirString = archivoDeSalida.get(0);
		String coordenadaCasaString = archivoDeSalida.get(1);
		String orientacion = archivoDeSalida.get(2);
		
		this.sePuedeConstruir = "SI".equals(sePuedeConstruirString);
		this.posicionCasa = new Coordenada(coordenadaCasaString);
		this.orientacion = orientacion;
	}

	public boolean sePuedeConstruir() {
		return sePuedeConstruir;
	}

	public void setSePuedeConstruir(boolean sePuedeConstruir) {
		this.sePuedeConstruir = sePuedeConstruir;
	}

	public Coordenada getPosicionCasa() {
		return posicionCasa;
	}

	public void setPosicionCasa(Coordenada posicionCasa) {
		this.posicionCasa = posicionCasa;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	
}
