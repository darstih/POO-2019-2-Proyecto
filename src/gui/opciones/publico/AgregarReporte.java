package gui.opciones.publico;

import Excepciones.ErrorCampoVacio;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Interacciones.ObjetoReporte;
import gestorAplicacion.Interacciones.Reporte;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Invitado;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class AgregarReporte extends OpcionDeMenu implements Independiente{
	//Atributos
	String[] criterios = new String[] {"Motivo"};
	
	
	//Metodos
	@Override
	public String toString() {
		return "Reportar";
	}
	public Button graficar() {
		return new Button(new AgregarReporte().toString());
	}
	public AgregarReporte() {
		this.descripcion= "Este proceso crea un reporte acerca de una obra o un\n"
						+ "comentario que afecte de algún modo al usuario, \n"
						+ "claramente con un motivo";
	}
	
	@Override
	public void ejecutar() throws NoCoincideTamano, ErrorCampoVacio {
		CrearReporteHandler handler = new CrearReporteHandler();
		FieldPanel form = new FieldPanel(this,"Criterio",criterios,"Valores",null,null,handler);
		PaneInteraccion.setPaneActual(form);
	}
	
	
	
	
	//Inner class
	class CrearReporteHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			ObjetoReporte obj = (ObjetoReporte) FieldPanel.getAux();

			Invitado.reporte(new Reporte(pane.getValue(criterios[0]),obj));
			dialogo.setTitle("Reporte agregado");
			dialogo.setContentText("Cuando la apruebe un administrador será ejecutado.");

			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.showAndWait();
			PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
		}
	}
}