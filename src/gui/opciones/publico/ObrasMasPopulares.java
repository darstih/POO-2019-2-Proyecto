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
//Autor David Aristizabal Giraldo
public class ObrasMasPopulares extends OpcionDeMenu implements Independiente{
	public String toString() {
		return "Obras mas populares";
	}
	@Override
	public void ejecutar() throws JsonGenerationException, JsonMappingException, IOException, ErrorCampoVacio {
		//parte grafica de funcionalidad interesante, verifica cuales son las obras con mas interaccion en el sistema
		// y esto se mide por la cantidad de los comentarios
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(),2));
	
	}
}
