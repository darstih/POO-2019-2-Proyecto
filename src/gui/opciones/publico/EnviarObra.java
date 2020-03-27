package gui.opciones.publico;
import java.util.ArrayList;
import java.util.Calendar;

import gui.Excepciones.ErrorCampoVacio;
import gui.Excepciones.ErrorDimensionNoNum;
import gui.Excepciones.ErrorDimensionReal;
import gui.Excepciones.ErrorEtiquetaRepetida;
import gui.Excepciones.ErrorObraRepetida;
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
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class EnviarObra extends OpcionDeMenu implements Independiente{

	@Override
	public String toString() {
		return "Postular Obra";
	}
	
	@Override
	public void ejecutar() throws ErrorCampoVacio {
		String[] criterios = new String[] {"Titulo","Descripcion","Altura","Ancho","Tecnica","Autor"};
		CrearObraHandler hand = new CrearObraHandler();
		FieldPanel a = new FieldPanel(this,"criterios",criterios,"valores",null,null,hand);
		PaneInteraccion.setPaneActual(a);
	}
	
	public EnviarObra() {
		this.descripcion= "Este proceso crea una sugerencia de obra\n para la administración de la galeria";
	}
	
	class CrearObraHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			String titulo = "";
			Label respuesta = new Label();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
				String camposVal[] = {pane.getValue("Titulo"),pane.getValue("Descripcion"),pane.getValue("Tecnica"),
						pane.getValue("Autor"),((FieldPanel) pane).getValue("Ancho"),((FieldPanel) pane).getValue("Altura")};
				String campos[] = {"Titulo","Descripcion","Tecnica","Autor","Ancho","Alto"};
				for(int i = 0; i<=5;i++) {
					if(camposVal[i].trim().isEmpty()) {
						throw new ErrorCampoVacio(campos[i]);
					}
				}
				double a= 0,h= 0;
				try{
					a =  Double.parseDouble(camposVal[4]);
				}catch(NumberFormatException e) {
					throw new ErrorDimensionNoNum("Ancho");
				}try{
					h =  Double.parseDouble(camposVal[5]);
				}catch(NumberFormatException e) {
					throw new ErrorDimensionNoNum("Alto");
				}
				Invitado.postularObra(new Obra(camposVal[0],camposVal[1],h, a,Calendar.getInstance(),new ArrayList<Etiqueta>(), new Tecnica(camposVal[2]),camposVal[3],false));
				if(a<=0 || h<=0) {
					throw new ErrorDimensionReal();
				}
				respuesta.setText("Cuando la apruebe un administrador será exitosamente agregada.");
				dialogo.setTitle("Obra agregada correctamente");
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
			} catch (ErrorDimensionNoNum | ErrorEtiquetaRepetida |ErrorObraRepetida | ErrorDimensionReal | ErrorCampoVacio e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
			}
		}
	}
}
