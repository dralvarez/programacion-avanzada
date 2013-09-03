package pedregal;
import java.util.List;
import java.util.Set;

import utils.FileUtils;


public class Pedregal {

	public boolean evaluar(String pathArchivoDeEntrada, String pathArchivoDeSalida){
		boolean funcionaCorrectamente = true;
		
		List<String> archivoDeEntrada = FileUtils.leerArchivo(pathArchivoDeEntrada);
		List<String> archivoDeSalida = FileUtils.leerArchivo(pathArchivoDeSalida);
		
		DatosDeEntrada entrada = new DatosDeEntrada(archivoDeEntrada);
		DatosDeSalida salida = new DatosDeSalida(archivoDeSalida);
		
		boolean sePuedeConstruir = salida.sePuedeConstruir();
		
		if(sePuedeConstruir){
			funcionaCorrectamente = confirmarQueSePuedeConstruir(entrada, salida);			
		} else {
			funcionaCorrectamente = confirmarQueNoSePuedeConstruir(entrada);
		}
		
		System.out.println(funcionaCorrectamente);
		
		return funcionaCorrectamente;
	}

	private boolean confirmarQueNoSePuedeConstruir(DatosDeEntrada entrada) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean confirmarQueSePuedeConstruir(DatosDeEntrada entrada, DatosDeSalida salida) {
		boolean funcionaCorrectamente = false;
		
		Coordenada posicionCasa = salida.getPosicionCasa();
		Coordenada tamanoCasa = entrada.getTamanoCasa();
		Set<Coordenada> penascos = entrada.getPenascos();
		String orientacion = salida.getOrientacion();
		
		if("N".equals(orientacion) || "S".equals(orientacion)){
			funcionaCorrectamente = verificarHorizonalmente(posicionCasa, tamanoCasa, penascos);
		} else if("O".equals(orientacion) || "E".equals(orientacion)){
			funcionaCorrectamente = verificarVerticalmente(posicionCasa, tamanoCasa, penascos);
		}

		return funcionaCorrectamente;
	}
	
	private boolean verificarHorizonalmente(Coordenada posicionCasa,
			Coordenada tamanoCasa, Set<Coordenada> penascos) {
		boolean funcionaCorrectamente = true;
		
		for(int x = posicionCasa.getX() ; x < posicionCasa.getX() + tamanoCasa.getX(); x++){
			for(int y = posicionCasa.getY() ; y < posicionCasa.getY() + tamanoCasa.getY() ; y++ ){
				Coordenada posicionActual = new Coordenada(x, y);
				boolean posicionTienePenascos = penascos.contains(posicionActual);
				
				if(posicionTienePenascos){
					funcionaCorrectamente = false;
					break;
				}
			}
		}
		
		return funcionaCorrectamente;
	}

	private boolean verificarVerticalmente(Coordenada posicionCasa,
			Coordenada tamanoCasa, Set<Coordenada> penascos) {
		boolean funcionaCorrectamente = true;
		
		for(int x = posicionCasa.getX() ; x < posicionCasa.getX() + tamanoCasa.getY(); x++){
			for(int y = posicionCasa.getY() ; y < posicionCasa.getY() + tamanoCasa.getX() ; y++ ){
				Coordenada posicionActual = new Coordenada(x, y);
				boolean posicionTienePenascos = penascos.contains(posicionActual);
				
				if(posicionTienePenascos){
					funcionaCorrectamente = false;
					break;
				}
			}
		}
		
		return funcionaCorrectamente;
	}

	public static void main(String[] args) {
		new Pedregal().evaluar("casos_de_prueba/03_peñasques/input/01_caso.in", 
				"casos_de_prueba/03_peñasques/output/01_caso.out");
	}
}
