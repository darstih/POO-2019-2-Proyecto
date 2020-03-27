package gui.opciones.administrador;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import Excepciones.ErrorCampoVacio;
import gui.paneles.PaneInteraccion;
import gui.GraficadorObjetos;
import gui.opciones.Independiente;
//import uiMain.menuConsola.MenuDeConsola;
import gui.opciones.OpcionDeMenu;
public class ListarObrasPendientes extends OpcionDeMenu implements Independiente{
	
	@Override
	public String toString() {
		return "Mostrar Obras Pendientes";
	}

	@Override
	public void ejecutar()
			throws JsonGenerationException, JsonMappingException, IOException, ErrorCampoVacio {
		PaneInteraccion.setPaneActual(GraficadorObjetos.listarObraGraficaPendiente());
		
	}
}