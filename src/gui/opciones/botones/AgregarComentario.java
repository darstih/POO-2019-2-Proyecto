package gui.opciones.botones;
import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
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
	
	
	@Override
	public void ejecutar() throws NoCoincideTamano, CantBeNull {
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
			if(!(PaneInteraccion.getTipoUsuario()=="Invitado")) {
				Administrador.addComentario(obr,new Comentario( pane.getValue("Comentario")));
				dialogo.setTitle("Comentario agregado");
				dialogo.setContentText("Comentario agregado");
			}else {
				Invitado.addComentario(obr,new Comentario(pane.getValue("Comentario")));
				dialogo.setTitle("Comentario agregado");
				dialogo.setContentText("Cuando la apruebe un administrador será exitosamente agregado.");
			}
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.showAndWait();
			PaneInteraccion.setPaneActual(obr.graficar(1));
		}
		
	}
}
