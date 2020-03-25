package gui.opciones.botones;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.paneles.FieldPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import gui.opciones.Dependiente;
import gui.opciones.OpcionDeMenu;

public class ActualizarObra extends OpcionDeMenu implements Dependiente{


	@Override
	public String toString() {
		return "Actualizar Obra";
	}

	@Override
	public void ejecutar() throws NoCoincideTamano, CantBeNull {
		String[] criterios = new String[] {"Titulo","Descripcion","Altura","Ancho","Tecnica","Autor"};
		ActualizarObraHandler hand = new ActualizarObraHandler();
		Obra obra = (Obra) FieldPanel.getAux();
		String[] values = new String[] {obra.getTitulo(),obra.getDescripcion(),obra.getAltura().toString(),obra.getAncho().toString(),obra.getTecnica().getNombre(),obra.getAutor()};
		boolean[] habl = new boolean[] {false,true,true,true,true,true};
		FieldPanel a = new FieldPanel(this,"criterios",criterios,"valores",values,habl,hand);
		PaneInteraccion.setPaneActual(a);
	}
	public Button graficar() {
		return new Button(this.toString());
		
	}



	class ActualizarObraHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			try {
				Pane pane = PaneInteraccion.getPaneActual();
				Obra obra = (Obra) FieldPanel.getAux();
				
				obra.setTitulo(((FieldPanel) pane).getValue("Titulo"));
				obra.setDescripcion(((FieldPanel) pane).getValue("Descripcion") );
				obra.setAltura(Double.parseDouble(((FieldPanel) pane).getValue("Altura")));
				obra.setAncho(Double.parseDouble(((FieldPanel) pane).getValue("Ancho")));
				obra.setTecnica(new Tecnica(((FieldPanel) pane).getValue("Tecnica")));
				obra.setAutor(((FieldPanel) pane).getValue("Autor"));
				Alert dialogo = new Alert(AlertType.INFORMATION);
				dialogo.setTitle("Obra actualizada correctamente");
				dialogo.setContentText("La obra se actualizó correctamente");
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}



