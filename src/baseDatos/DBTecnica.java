package baseDatos;

import gestorAplicacion.Obras.Tecnica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class DBTecnica {
	
	public static final ObjectMapper soyElMapa = new ObjectMapper();
	
	private static final String rutaFichero= "src/baseDatos/temp/Tecnica.txt";
	private static final File archivo = new File (rutaFichero);
	
	
	
	public static void inicializar() throws JsonParseException, JsonMappingException, IOException {
		DBTecnica dbTecnica = new DBTecnica();
		FileReader lector = new FileReader (archivo);
		BufferedReader br = new BufferedReader(lector);
		// Lectura del fichero
		String linea;
		String coso="";
		while((linea=br.readLine())!=null)
			coso = coso + linea;
		System.out.println(coso);
		Tecnica.setTecnicas(soyElMapa.readValue(coso, new TypeReference<ArrayList<Tecnica>>() {} ));
		if( null != lector ){   
            lector.close();     
         }  
	}
	
	public static void guardar() throws JsonGenerationException, JsonMappingException, IOException {
		FileWriter escritor = new FileWriter(rutaFichero);
		PrintWriter pw = new PrintWriter(escritor);
		ArrayList<Tecnica> tecnicas = Tecnica.getTecnicas();
		String theJsonText = soyElMapa.writeValueAsString(tecnicas);
		pw.println(theJsonText);
		System.out.println(theJsonText);
		if (null != escritor)
            escritor.close();
		}
}