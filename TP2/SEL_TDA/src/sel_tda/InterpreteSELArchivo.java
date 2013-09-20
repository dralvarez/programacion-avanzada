package sel_tda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class InterpreteSELArchivo {
	
	public SEL interpretar(String path){
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try	{   
			f = new File(path);
			fr = new FileReader(f);
			br = new BufferedReader(fr);		
			
			String linea = br.readLine();
			
			if(linea != null){
				int dimension = Integer.parseInt(linea);
				
				if(dimension < 2) {
					throw new RuntimeException("El tamaño del SEL es inferior a 2");
				}
				
				MatrizMathCuadrada mc = new MatrizMathCuadrada(dimension);
				
				int tope_mat = 0;
					
				while((linea = br.readLine()) != null && tope_mat < dimension * dimension)
				{
					String[] datos=linea.split(" ");
					mc.setValue(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Double.parseDouble(datos[2]));
					tope_mat++;
				}
							 
				VectorMath b = new VectorMath(dimension);
				VectorMath x = new VectorMath(dimension);	
				
				b.setValue(0, Double.parseDouble(linea));
				int j = 1;
				
				while((linea = br.readLine()) != null) {	
					b.setValue(j, Double.parseDouble(linea)) ;
					j++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{ 
				if(fr!= null)
					fr.close();
			}	
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		
		return null;
		
		
		
		
	}
}
