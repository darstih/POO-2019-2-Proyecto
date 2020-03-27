package gui.opciones.publico;

import gestorAplicacion.Usuario.Usuario;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;
import gui.paneles.PaneInteraccion;
//Autor David Aristizabal Giraldo
public class ObrasArtistaPopular  extends OpcionDeMenu implements Independiente{

	@Override
	public String toString() {
		return "Trabajo del artista mas popular";
	}
	
	@Override
	public void ejecutar() {
		//Funcionalidad interesante, verifica cual es el artista con mas obras en el sistema y retorna todas sus obras
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.artistaMasPopular(),1));
	}
}
