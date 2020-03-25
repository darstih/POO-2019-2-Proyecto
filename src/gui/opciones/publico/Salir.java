package gui.opciones.publico;

import gui.paneles.IndexPanel;
import javafx.scene.Scene;
import gui.Main;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class Salir extends OpcionDeMenu implements Independiente{
	
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
