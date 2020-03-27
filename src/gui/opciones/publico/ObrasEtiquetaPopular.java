package gui.opciones.publico;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import gui.Excepciones.ErrorCampoVacio;
import gestorAplicacion.Usuario.Usuario;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;
import gui.paneles.PaneInteraccion;
//Autor David Aristizabal Giraldo
public class ObrasEtiquetaPopular extends OpcionDeMenu implements Independiente{
	@Override
	public String toString() {
		return "Obras con la etiqueta mas popular";
	}
	@Override
	public void ejecutar() throws JsonGenerationException, JsonMappingException, IOException, ErrorCampoVacio {
		//Funcionalidad interesante, verifica cual es la Etiqueta con mas obras en el sistema y 
		//retorna todas las obras que la contienen
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.etiquetaMasPopular(),1));
	}
}

