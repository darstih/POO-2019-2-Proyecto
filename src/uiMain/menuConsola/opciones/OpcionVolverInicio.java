package uiMain.menuConsola.opciones;

//import gui.escena.EscenaInicial;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionVolverInicio extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Salir";
	}
	
	@Override
	public void ejecutar() {
		//EscenaInicial.init();
	}
	
	public Pane toGrafic() {
		Pane a = new Pane();
		Button b = new Button(this.toString());
		a.getChildren().add(b);
		return a;
		
	}
	
}
