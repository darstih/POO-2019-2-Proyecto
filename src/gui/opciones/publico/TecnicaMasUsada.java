package gui.opciones.publico;

import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class TecnicaMasUsada extends OpcionDeMenu implements Independiente{
	
	public String toString() {
		return "Tecnicas mas usadas";
	}
	
	@Override
	public void ejecutar(){
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.tecnicaMasUsada(),3));
	}
}