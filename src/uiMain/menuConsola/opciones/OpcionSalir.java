package uiMain.menuConsola.opciones;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import baseDatos.DBObra;
import baseDatos.DBUsuario;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionSalir extends OpcionDeMenu {

	@Override
	public String toString() {
		return "Salir";
	}
	
	@Override
	public void ejecutar() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("\u00A1Chao! \u00A1Gracias por usar M\u0171vez\u00E1rt!");
		DBObra.guardar();
		DBUsuario.guardar();
		System.exit(0);
	}

}
