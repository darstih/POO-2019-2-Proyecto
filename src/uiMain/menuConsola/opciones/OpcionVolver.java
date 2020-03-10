package uiMain.menuConsola.opciones;

import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionVolver extends OpcionDeMenu {
	
	public OpcionVolver() {
		super();
	}
	
	@Override
	public String toString() {
		return "Volver atras";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		return MenuDeConsola.getMenuActual().getAtras();
	}
	
}