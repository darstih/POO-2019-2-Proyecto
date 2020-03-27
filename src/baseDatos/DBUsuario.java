package baseDatos;

import gestorAplicacion.Usuario.Administrador;

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

public class DBUsuario {
	
	public static final ObjectMapper soyElMapa = new ObjectMapper(); //Soy el mapa, soy el mapa soy el mapa
	
	private static final String rutaFichero= "src/baseDatos/temp/Usuario.txt";
	private static final File archivo = new File (rutaFichero);
	
	
	
	public static void inicializar() throws JsonParseException, JsonMappingException, IOException {
		FileReader lector = new FileReader (archivo);
		BufferedReader br = new BufferedReader(lector);
		// Lectura del fichero
		String linea;
		String coso="";
		while((linea=br.readLine())!=null)
			coso = coso + linea;
		Administrador.setAdministradores(soyElMapa.readValue(coso, new TypeReference<ArrayList<Administrador>>() {} ));
		if( null != lector ){   
            lector.close();     
         }  
	}
	
	public static void guardar() throws JsonGenerationException, JsonMappingException, IOException {
		FileWriter escritor = new FileWriter(rutaFichero);
		PrintWriter pw = new PrintWriter(escritor);
		String theJsonText = soyElMapa.writeValueAsString(Administrador.getAdministradores());
		pw.println(theJsonText);
		if (null != escritor)
            escritor.close();
		}
}