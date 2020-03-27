package gui.opciones.publico;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import gui.Excepciones.ErrorCampoVacio;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Usuario;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;
import gui.paneles.PaneInteraccion;

public class Novedades extends OpcionDeMenu implements Independiente{

	public String toString() {
		return "Novedades";
	}
	@Override
	public void ejecutar() throws JsonGenerationException, JsonMappingException, IOException, ErrorCampoVacio {
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.listarObra(Obra.getObras(),3,-1),2));
	}

}
