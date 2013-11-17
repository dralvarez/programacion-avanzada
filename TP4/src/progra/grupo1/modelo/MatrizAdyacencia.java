package progra.grupo1.modelo;

public interface MatrizAdyacencia {

	Adyacencia get(int i, int j);
	
	void put(int i, int j, Adyacencia object);

	int getDimension();
}
