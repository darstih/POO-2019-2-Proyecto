package gui.opciones.botones;

import Excepciones.CantBeNull;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import gui.GraficadorObjetos;
import gui.opciones.Dependiente;
import gui.opciones.OpcionDeMenu;

public class AgregarEtiqueta extends OpcionDeMenu implements Dependiente{
	private static String[] criterios = new String[] {"Label", "Tipo", "Descripción"};
	
	@Override
	public String toString() {
		return "+Etiqueta";
	}
	
	@Override
	public void ejecutar() throws NoCoincideTamano, CantBeNull {
		CrearEtiquetaHandler handler = new CrearEtiquetaHandler();
		FieldPanel form = new FieldPanel(this,"Criterio",criterios,"Valores",null,null,handler);
		PaneInteraccion.setPaneActual(form);
		
	}
	
	public Button graficar() {
		return new Button(new AgregarEtiqueta().toString());
	}
	public AgregarEtiqueta() {
		this.descripcion= "Este proceso proviene de un usuario invitado \n "
				+ "crea (si no existe) o agrega si existe\n"
				+ "verifica que no esté en la obra y luego la agrega a una \n"
				+ "lista de aprobación del administrador\n";
	}
	
	class CrearEtiquetaHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
			Obra obr = (Obra) FieldPanel.getAux();
			System.out.println(obr.getTitulo());
			Etiqueta e=new Etiqueta(pane.getValue(criterios[0]),pane.getValue(criterios[1]),pane.getValue(criterios[2]));
			try {
				obr.crearEtiqueta(e);
				Alert dialogo = new Alert(AlertType.INFORMATION);
				dialogo.setTitle("Etiqueta agregada");
				dialogo.setContentText("Etiqueta agregada");
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(GraficadorObjetos.graficar(obr,1,""+0));
			} catch (ErrorEtiquetaRepetida e1) {
				Alert dialogo = new Alert(AlertType.ERROR);
				dialogo.setTitle("Error");
				dialogo.getDialogPane().setContent(new Label(e1.getMessage()));//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(GraficadorObjetos.graficar(obr,1,""+0));
			}
			
		}
		
	}
}
