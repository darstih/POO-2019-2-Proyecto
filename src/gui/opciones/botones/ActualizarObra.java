package gui.opciones.botones;

import Excepciones.ErrorCampoVacio;
import Excepciones.ErrorDimensionReal;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import gui.opciones.Dependiente;
import gui.opciones.OpcionDeMenu;

public class ActualizarObra extends OpcionDeMenu implements Dependiente{


	@Override
	public String toString() {
		return "Actualizar Obra";
	}
	public  ActualizarObra() {
		this.descripcion= "Este proceso actualiza una obra directamente y \n";
	}
	@Override
	public void ejecutar() throws ErrorCampoVacio {
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
			String titulo = "";
			Label respuesta = new Label();
			respuesta.setWrapText(true);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				Pane pane = PaneInteraccion.getPaneActual();
				Obra obra = (Obra) FieldPanel.getAux();
				String campos[] = {"Titulo","Descripcion","Altura","Ancho","Tecnica","Autor"};
				for(int i = 0; i<=5;i++) {
					if(((FieldPanel) pane).getValue(campos[i]).trim().isEmpty()) {
						throw new ErrorCampoVacio(campos[i]);
					}
				}
				double h = Double.parseDouble(((FieldPanel) pane).getValue(campos[2]));
				double a = Double.parseDouble(((FieldPanel) pane).getValue(campos[3]));
				if(h<=0 || a<=0) {
					throw new ErrorDimensionReal();
				}
				obra.setTitulo(((FieldPanel) pane).getValue(campos[0]));
				obra.setDescripcion(((FieldPanel) pane).getValue(campos[1]) );
				obra.setAltura(h);
				obra.setAncho(a);
				obra.setTecnica(new Tecnica(((FieldPanel) pane).getValue(campos[4])));
				obra.setAutor(((FieldPanel) pane).getValue(campos[5]));
				titulo = "Obra agregada correctamente";
				respuesta.setText("La obra se agrego correctamente");
				PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Obra.getObras(), 1));
			} catch (NumberFormatException | ErrorCampoVacio | ErrorDimensionReal e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
			}finally {
				dialogo.setTitle(titulo);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
			}
		}
	}
}



