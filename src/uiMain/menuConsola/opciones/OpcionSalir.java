package uiMain.menuConsola.opciones;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import baseDatos.DBObra;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionSalir extends OpcionDeMenu {

	@Override
	public String toString() {
		return "Salir";
	}

	@Override
	public MenuDeConsola ejecutar() throws JsonGenerationException, JsonMappingException, IOException {
		// Para usar acentos y caracteres especiales en Strings:
		// https://www.freeformatter.com/java-dotnet-escape.html
		// Recordar que el '\' debe estar solo, ej. '\\' no sirve
		System.out.println("\u00A1Chao! \u00A1Gracias por usar M\u0171vez\u00E1rt!");
		DBObra.guardar();
		System.exit(0);
		return null;
	}

}
