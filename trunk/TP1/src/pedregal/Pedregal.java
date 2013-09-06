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
		Pedregal pedregal = new Pedregal();
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/00_caso_original.in", 
				"casos_de_prueba/03_pedregal/output/00_caso_original.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/01_imposibleConstruir.in", 
				"casos_de_prueba/03_pedregal/output/01_imposibleConstruir.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/02_terrenoYCasaGrandesMuchosPeñascosSI.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/03_terrenoYCasaGrandesMuchosPeñascosNO.in", 
				"casos_de_prueba/03_pedregal/output/03_terrenoYCasaGrandesMuchosPeñascosNO.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/04_terrenoYCasaChicosMuchosPeñascos.in", 
				"casos_de_prueba/03_pedregal/output/04_terreno YCasaChicosMuchosPeñascos.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/05_terrenoYCasaGrandesPocosPeñascos.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/06_terrenoYCasaChicosPocosPeñascos.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/07_terrenoGrandeCasaChicaPocosPeñascos.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/08_terrenoGrandeCasaChicaMuchosPeñascos.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
		pedregal.evaluar("casos_de_prueba/03_pedregal/input/09_CasoFatiga_NumerosMuyGrandes.in", 
				"casos_de_prueba/03_pedregal/output/01_caso.out");
	}
}
