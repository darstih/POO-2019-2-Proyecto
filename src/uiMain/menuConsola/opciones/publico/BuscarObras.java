package uiMain.menuConsola.opciones.publico;

import Excepciones.CantBeNull;
import Excepciones.ExcepcionFueraRango;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.paneles.FieldPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import uiMain.menuConsola.OpcionDeMenu;

public class BuscarObras extends OpcionDeMenu {
	
	public String toString() {
		return "Buscar Obras";
	}
	
	@Override
	public void ejecutar() throws NoCoincideTamano, CantBeNull {
			String[] criterios = new String[] {"Tipo","Busqueda","Listado"};
			System.out.println("Ejecución de buscar obras");
			BuscarObra handler = new BuscarObra();
			FieldPanel buscar = new FieldPanel(this,"criterios",criterios,"valores",new String[] {"Titulo","","1"},null,handler);
			PaneInteraccion.setPaneActual(buscar);
	}
	
	class BuscarObra implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {//Si este handler se activa es porque se está mostrando por lo tanto es el actual
			try {
				Pane pane = PaneInteraccion.getPaneActual();
				
				PaneInteraccion.setPaneActual(Usuario.buscarObra(((FieldPanel) pane).getValue("Busqueda"), (((FieldPanel) pane).getValue("Tipo") == "Tecnica") ?  0: 1, Integer.parseInt(((FieldPanel) pane).getValue("Listado"))));
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ExcepcionFueraRango e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
