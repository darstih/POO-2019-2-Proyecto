package gui.opciones.publico;


import Excepciones.ErrorCampoVacio;
import Excepciones.ErrorFueraRango;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.paneles.FieldPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class BuscarObras extends OpcionDeMenu implements Independiente{
	
	public String toString() {
		return "Buscar Obras";
	}
	
	@Override
	public void ejecutar() throws ErrorCampoVacio {
			String[] criterios = new String[] {"Tipo","Busqueda","Listado"};
			BuscarObra handler = new BuscarObra();
			FieldPanel buscar = new FieldPanel(this,"criterios",criterios,"valores",new String[] {"Titulo","","1"},null,handler);
			PaneInteraccion.setPaneActual(buscar);
	}
	public BuscarObras() {
		this.descripcion= "Esta consulta busca obra por titulo exacto o por tecnica";
	}
	
	class BuscarObra implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			String titulo = "";
			Label respuesta = new Label();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				Pane pane = PaneInteraccion.getPaneActual();
				String busqued = ((FieldPanel) pane).getValue("Busqueda");
				if(busqued.trim().isEmpty()) {
					throw new ErrorCampoVacio("Busqueda");
				}else if(((FieldPanel) pane).getValue("Listado").trim().isEmpty()) {
					throw new ErrorCampoVacio("listado");
				}
				int listad = Integer.parseInt(((FieldPanel) pane).getValue("Listado"));
				int a=1;
				if(((FieldPanel) pane).getValue("Tipo").equals("Tecnica")) {
					a=0;
				}
				PaneInteraccion.setPaneActual(Usuario.buscarObra(busqued, a, listad));
			} catch (NumberFormatException | ErrorCampoVacio e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
			} catch (ErrorFueraRango e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
