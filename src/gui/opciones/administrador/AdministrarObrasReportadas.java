package gui.opciones.administrador;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import Excepciones.ErrorCampoVacio;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Usuario;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;
import gui.paneles.PaneInteraccion;

public class AdministrarObrasReportadas extends OpcionDeMenu implements Independiente{

	public String toString() {
		return "Administrar obras con reporte";
	}

	@Override
	public void ejecutar() throws JsonGenerationException, JsonMappingException, IOException, ErrorCampoVacio {
		PaneInteraccion.setPaneActual(Usuario.listarObraGraficaReportes(Administrador.getReportesObra()));
		
	}

}
