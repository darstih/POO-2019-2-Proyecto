package uiMain.menuConsola.opciones.publico;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import javafx.scene.layout.FlowPane;
import uiMain.menuConsola.Independiente;
import uiMain.menuConsola.OpcionDeMenu;

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
