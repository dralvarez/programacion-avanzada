package letrasextremas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.FileUtils;


public class LetrasExtremas {
	
	public void procesar(final String archivoDeEntrada, final String archivoDeSalida){
		Set<Character> letrasExtremas = new HashSet<Character>();
		List<String> palabrasConLetrasExtremas = new ArrayList<String>();
		
		List<String> contenido = FileUtils.leerArchivo(archivoDeEntrada);
		String cantidadDePalabrasAsString = contenido.get(0);
		
		int cantidadDePalabras = Integer.parseInt(cantidadDePalabrasAsString);
		
		Map<Character, Integer> recurrenciasMap = new HashMap<Character, Integer>();
		
		for(int i = 1; i <= cantidadDePalabras ; i++){
			String palabra = contenido.get(i);
			
			Character primeraLetra = palabra.charAt(0);
			Character ultimaLetra = palabra.charAt(palabra.length()-1);
			
			incrementarRecurrencia(recurrenciasMap, primeraLetra);
			incrementarRecurrencia(recurrenciasMap, ultimaLetra);
		}
		
		letrasExtremas = obtenerLetrasMasUsadas(recurrenciasMap);
		
		for(int i = 1; i <= cantidadDePalabras ; i++){
			String palabra = contenido.get(i);
			
			Character primeraLetra = palabra.charAt(0);
			Character ultimaLetra = palabra.charAt(palabra.length()-1);
			
			if(letrasExtremas.contains(primeraLetra) || letrasExtremas.contains(ultimaLetra)){
				palabrasConLetrasExtremas.add(palabra);				
			}
		}
		
		String lineaDeLetrasExtremas = "";
		for(Character letraExtrema : letrasExtremas){
			lineaDeLetrasExtremas += letraExtrema.toString() + " ";			
		}
			
		List<String> resultado = new ArrayList<String>();
		resultado.add(lineaDeLetrasExtremas.trim());
		resultado.addAll(palabrasConLetrasExtremas);
		
		FileUtils.escribirArchivo(archivoDeSalida, resultado);
	}
	
	
	private Set<Character> obtenerLetrasMasUsadas(
			Map<Character, Integer> recurrenciasMap) {
		
		Set<Character> letrasMasUsadas = new HashSet<Character>();
		
		Collection<Integer> values = recurrenciasMap.values();
		
		Integer max = Collections.max(values);
		
		for(Character letra : recurrenciasMap.keySet()){
			Integer integer = recurrenciasMap.get(letra);
			
			if(integer == max){
				letrasMasUsadas.add(letra);
			}
		}
		
		return letrasMasUsadas;
	}


	private void incrementarRecurrencia(
			Map<Character, Integer> recurrenciasMap, Character letra) {
		
		Integer recurrencias = recurrenciasMap.get(letra);
		
		if(recurrencias == null){
			recurrencias = 1;
		} else {
			recurrencias++;
		}
		
		recurrenciasMap.put(letra, recurrencias);
		
	}

	public static void main(String[] args) {
		LetrasExtremas le = new LetrasExtremas();
		le.procesar("casos_de_prueba/02_letras_extremas/input/06_NPalabrasConMasDeUnaLetraExtremaMasUsada_todasContienenLetrasExtremasMasUsadas.in",
					"casos_de_prueba/02_letras_extremas/output/06_NPalabrasConMasDeUnaLetraExtremaMasUsada_todasContienenLetrasExtremasMasUsadas.out");
	}
}
