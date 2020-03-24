package uiMain.menuConsola.opciones.invitado;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import javafx.scene.layout.FlowPane;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionListarObras extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Mostrar todas las obras";
	}
	
	@Override
	public void ejecutar() {
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(),1));
	}
}
