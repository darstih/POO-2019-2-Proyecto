package gui.opciones.administrador;

import java.util.ArrayList;
import java.util.Calendar;

import Excepciones.CantBeNull;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.ErrorObraRepetida;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.paneles.FieldPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;



	
public class AgregarObra extends OpcionDeMenu implements Independiente{
	
	
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
		public void handle(ActionEvent arg0){//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			String titulo = "";
			Label respuesta = new Label();
			respuesta.setWrapText(true);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				Pane pane = PaneInteraccion.getPaneActual();
				Administrador.agregarObra(new Obra(((FieldPanel) pane).getValue("Titulo"), ((FieldPanel) pane).getValue("Descripcion") ,Double.parseDouble(((FieldPanel) pane).getValue("Altura")), Double.parseDouble(((FieldPanel) pane).getValue("Ancho")),Calendar.getInstance(),new ArrayList<Etiqueta>(), new Tecnica(((FieldPanel) pane).getValue("Tecnica")), ((FieldPanel) pane).getValue("Autor"),true));
				titulo = "Obra agregada correctamente";
				respuesta.setText("La obra se agrego correctamente");
			} catch (NumberFormatException | ErrorEtiquetaRepetida | ErrorObraRepetida e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
			}finally {
				dialogo.setTitle(titulo);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
			}
		}
	}
	
	
}
