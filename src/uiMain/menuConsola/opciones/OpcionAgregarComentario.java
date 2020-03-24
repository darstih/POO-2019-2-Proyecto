package uiMain.menuConsola.opciones;
import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamaño;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Invitado;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAgregarComentario extends OpcionDeMenu {
	
	
	@Override
	public String toString() {
		return "+Comentario";
	}
	public Button graficar() {
		return new Button(new OpcionAgregarComentario().toString());
	}
	
	
	@Override
	public void ejecutar() throws NoCoincideTamaño, CantBeNull {
		CrearComentarioHandler handler = new CrearComentarioHandler();
		FieldPanel form = new FieldPanel(this,"Criterio",new String[] {"Comentario"},"Valores",null,null,handler);
		PaneInteraccion.setPaneActual(form);
	}
	
	class CrearComentarioHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			FieldPanel pane = (FieldPanel) PaneInteraccion.getPaneActual();
			
			Invitado.addComentario((Obra) FieldPanel.getAux(),new Comentario(pane.getValue("Comentario")));
			
		}
		
	}
}
