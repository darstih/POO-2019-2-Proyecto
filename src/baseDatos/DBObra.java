package baseDatos;

import gestorAplicacion.Obras.Obra;
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

public class DBObra {
	
	public static final ObjectMapper soyElMapa = new ObjectMapper(); //Soy el mapa, soy el mapa soy el mapa
	
	private static final String rutaFichero= "src/baseDatos/temp/Obra.txt";
	private static final File archivo = new File (rutaFichero);
	
	
	public static void inicializar() throws JsonParseException, JsonMappingException, IOException {
		FileReader lector = new FileReader (archivo);
		BufferedReader br = new BufferedReader(lector);
		// Lectura del fichero
		String linea;
		String coso="";
		while((linea=br.readLine())!=null)
			coso = coso + linea;
		System.out.println(coso);
		ArrayList<Obra> obrasVisible = new ArrayList<Obra>();
		ArrayList<Obra> obrasNoVisible = new ArrayList<Obra>();
		ArrayList<Obra> obras = (soyElMapa.readValue(coso, new TypeReference<ArrayList<Obra>>() {} ));
		for(int i = 0;i<obras.size();i++) {
			Obra temp = obras.get(i);
			if(temp.getVisible()) {
				obrasVisible.add(temp);
			}else {
				obrasNoVisible.add(temp);
			}
		}
		Obra.setObras(obrasVisible);
		Administrador.setObrasPendientes(obrasNoVisible);
		if( null != lector ){   
            lector.close();     
         }  
	}
	
	public static void guardar() throws JsonGenerationException, JsonMappingException, IOException {
		FileWriter escritor = new FileWriter(rutaFichero);
		PrintWriter pw = new PrintWriter(escritor);
		ArrayList<Obra> obras = Obra.getObras();
		ArrayList<Obra> obrasP = Administrador.getObrasPendientes();
		for(int i = 0; i<obrasP.size();i++) {
			obras.add(obrasP.get(i));
		}
		String theJsonText = soyElMapa.writeValueAsString(obras);
		pw.println(theJsonText);
		if (null != escritor)
            escritor.close();
		}
}