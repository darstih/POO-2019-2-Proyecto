package uiMain.menuConsola.opciones.publico;
import java.util.ArrayList;
import java.util.Calendar;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gestorAplicacion.Usuario.Invitado;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import uiMain.menuConsola.Independiente;
import uiMain.menuConsola.OpcionDeMenu;

public class EnviarObra extends OpcionDeMenu implements Independiente{

	@Override
	public String toString() {
		return "Agregar Obra";
	}
	
	@Override
	public void ejecutar() throws NoCoincideTamano, CantBeNull {
		String[] criterios = new String[] {"Titulo","Descripcion","Altura","Ancho","Tecnica","Autor"};
		CrearObraHandler hand = new CrearObraHandler();
		FieldPanel a = new FieldPanel(this,"criterios",criterios,"valores",null,null,hand);
		PaneInteraccion.setPaneActual(a);
	}
	
	
	
	class CrearObraHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			try {
				FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
				Invitado.postularObra(new Obra(pane.getValue("Titulo"), pane.getValue("Descripcion") ,Double.parseDouble(pane.getValue("Altura")), Double.parseDouble( pane.getValue("Ancho")),Calendar.getInstance(),new ArrayList<Etiqueta>(), new Tecnica( pane.getValue("Tecnica")), pane.getValue("Autor"),false));
				Alert dialogo = new Alert(AlertType.INFORMATION);
				dialogo.setTitle("Obra agregada correctamente");
				dialogo.setContentText("Cuando la apruebe un administrador será exitosamente agregada.");
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Error por formato del número");
			}
		}
	}
}
