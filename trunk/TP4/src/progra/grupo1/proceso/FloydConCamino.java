package progra.grupo1.proceso;

import progra.grupo1.InterpreteArchivoGrafo;
import progra.grupo1.modelo.Adyacencia;
import progra.grupo1.modelo.GrafoDirigido;
import progra.grupo1.modelo.Nodo;

public class FloydConCamino {

	public ResultadoFloyd ejecutar(GrafoDirigido grafo) {
		int cantidadNodos = grafo.getCantidadNodos();

		int[][] A = new int[cantidadNodos][cantidadNodos];
		Adyacencia[][] C = grafo.getMatrizAdyacencias();
		Nodo[][] P = new Nodo[cantidadNodos][cantidadNodos];

		for (int i = 0; i < cantidadNodos; i++) {
			for (int j = 0; j < cantidadNodos; j++) {
				boolean sonAdyacentes = grafo.sonAdyacentes(i, j);
				Adyacencia adyacencia = C[i][j];
				int distancia = sonAdyacentes ? adyacencia.getDistancia()
						: -1;
				A[i][j] = distancia;
				P[i][j] = null;
			}
		}

		for (int k = 0; k < cantidadNodos; k++) {
			for (int i = 0; i < cantidadNodos; i++) {
				for (int j = 0; j < cantidadNodos; j++) {
					int distanciaIK = A[i][k];
					int distanciaKJ = A[k][j];
					int distanciaIJ = A[i][j];
					boolean sonAdyacentes = grafo.sonAdyacentes(i, j);
					if (sonAdyacentes && (distanciaIK + distanciaKJ < distanciaIJ)) {
						A[i][j] = distanciaIK + distanciaKJ;
						P[i][j] = grafo.getNodo(k);
					}
				}
			}
		}

		return new ResultadoFloyd(A, P);
	}

	public static void main(String[] args) {
		FloydConCamino floyd = new FloydConCamino();
		InterpreteArchivoGrafo interprete = new InterpreteArchivoGrafo();
		GrafoDirigido grafo = (GrafoDirigido) interprete
				.generarGrafo("recursos/grafo.in");

		ResultadoFloyd ejecutar = floyd.ejecutar(grafo);

		int[][] A = ejecutar.getA();
		Nodo[][] P = ejecutar.getP();

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("===================");

		for (int i = 0; i < P.length; i++) {
			for (int j = 0; j < P.length; j++) {
				System.out.print(P[i][j] + " ");
			}
			System.out.println();
		}

	}

	public class ResultadoFloyd {

		private int[][] A;
		private Nodo[][] P;

		public ResultadoFloyd(int[][] a, Nodo[][] p) {
			A = a;
			P = p;
		}

		public int[][] getA() {
			return A;
		}

		public Nodo[][] getP() {
			return P;
		}

	}
}
