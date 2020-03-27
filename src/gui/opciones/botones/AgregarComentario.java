package gui.opciones.botones;

import Excepciones.ErrorCampoVacio;
import Excepciones.ErrorComentarioRepetido;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import gui.GraficadorObjetos;
import gui.opciones.Dependiente;
import gui.opciones.OpcionDeMenu;

public class AgregarComentario extends OpcionDeMenu implements Dependiente{
	
	
	@Override
	public String toString() {
		return "+Comentario";
	}
	public Button graficar() {
		return new Button(new AgregarComentario().toString());
	}
	public AgregarComentario() {
		this.descripcion= "Este proceso crea un nuevo comentario que luego\n"
				+ "será aprobado o rechazado por un administrador";
	}
	
	@Override
	public void ejecutar() throws ErrorCampoVacio {
		CrearComentarioHandler handler = new CrearComentarioHandler();
		FieldPanel form = new FieldPanel(this,"Criterio",new String[] {"Comentario"},"Valores",null,null,handler);
		PaneInteraccion.setPaneActual(form);
	}
	
	class CrearComentarioHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			Obra obr = (Obra) FieldPanel.getAux();
			String titulo = "";
			Label respuesta = new Label();
			if(!(PaneInteraccion.getTipoUsuario()=="Invitado")) {
				try {
					if(pane.getValue("Comentario").trim().isEmpty()) {
						throw new ErrorCampoVacio("Comentario");
					}
					Administrador.addComentario(obr,new Comentario( pane.getValue("Comentario")));
					titulo = "Comentario agregado";
					respuesta.setText("Comentario agregado");
					dialogo.setTitle(titulo);
					dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.showAndWait();
					PaneInteraccion.setPaneActual(GraficadorObjetos.graficar(obr,1,"0"));
				} catch (ErrorComentarioRepetido | ErrorCampoVacio e) {
					titulo = "ERROR";
					dialogo.setAlertType(AlertType.ERROR);
					respuesta.setText(e.getMessage());
					dialogo.setTitle(titulo);
					dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.showAndWait();
				}
			}else {
				try {
					if(pane.getValue("Comentario").trim().isEmpty()) {
						throw new ErrorCampoVacio("Comentario");
					}
					Invitado.addComentario(obr,new Comentario(pane.getValue("Comentario")));
					titulo = "Comentario agregado";
					respuesta.setText("Comentario agregado");
					dialogo.setTitle(titulo);
					dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.showAndWait();
					PaneInteraccion.setPaneActual(GraficadorObjetos.graficar(obr,1,"0"));
				} catch (ErrorComentarioRepetido | ErrorCampoVacio e) {
					titulo = "ERROR";
					dialogo.setAlertType(AlertType.ERROR);
					respuesta.setText(e.getMessage());
					dialogo.setTitle(titulo);
					dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.showAndWait();
				}
			}
			
		}
		
	}
}
