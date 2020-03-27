package gui.opciones.publico;

import gestorAplicacion.Usuario.Usuario;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;
import gui.paneles.PaneInteraccion;

public class ObrasArtistaPopular  extends OpcionDeMenu implements Independiente{

	@Override
	public String toString() {
		return "Trabajo del artista mas popular";
	}
	
	@Override
	public void ejecutar() {
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.artistaMasPopular(),1));
	}
}
