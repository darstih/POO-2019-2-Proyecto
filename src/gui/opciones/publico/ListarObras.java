package gui.opciones.publico;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class ListarObras extends OpcionDeMenu implements Independiente{
	
	@Override
	public String toString() {
		return "Mostrar todas las obras";
	}
	
	@Override
	public void ejecutar() {
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(),1));
	}
}
