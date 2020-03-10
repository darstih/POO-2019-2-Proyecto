package uiMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import baseDatos.DBObra;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import uiMain.menuConsola.MenuDeConsola;

public class Main {
	public static void main (String [ ] args) throws JsonParseException, JsonMappingException, IOException {
	
		DBObra.inicializar();
		System.out.println(Obra.getCantObras());
		// Para usar acentos y caracteres especiales en Strings:
		// https://www.freeformatter.com/java-dotnet-escape.html
		// Recordar que el '\' debe estar solo, ej. '\\' no sirve
		
		MenuDeConsola menu= MenuDeConsola.inicializar();
		while (true) {
			menu =  menu.lanzarMenu();
		}
		
		
	}
}
