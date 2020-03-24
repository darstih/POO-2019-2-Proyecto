package uiMain.menuConsola.opciones;

import gui.paneles.IndexPanel;
import javafx.scene.Scene;
//import gui.escena.EscenaInicial;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import uiMain.Main;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionVolverInicio extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Salir";
	}
	
	@Override
	public void ejecutar() {
		try {
			Main.setScene(new Scene(new IndexPanel(),600,600));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
