package uiMain.menuConsola.opciones;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamaño;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAgregarEtiqueta extends OpcionDeMenu {
	private static String[] criterios = new String[] {"Label", "Tipo", "Descripción"};
	
	@Override
	public String toString() {
		return "Agregar Etiqueta";
	}
	
	@Override
	public void ejecutar() throws NoCoincideTamaño, CantBeNull {
		CrearEtiquetaHandler handler = new CrearEtiquetaHandler();
		FieldPanel form = new FieldPanel(this,"Criterio",criterios,"Valores",null,null,handler);
		PaneInteraccion.setPaneActual(form);
		
	}
	class CrearEtiquetaHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
			Obra obr = (Obra) FieldPanel.getAux();
			Etiqueta e=new Etiqueta(pane.getValue(criterios[0]),pane.getValue(criterios[1]),pane.getValue(criterios[2]));
			obr.crearEtiqueta(e);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			dialogo.setTitle("Etiqueta agregada");
			dialogo.setContentText("Etiqueta agregada");
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.showAndWait();
			PaneInteraccion.setPaneActual(obr.graficar(1));
		}
		
	}
}
