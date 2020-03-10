package uiMain.menuConsola.opciones;

import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionVolverInicio extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Volver al inicio";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		return MenuDeConsola.inicializar();
	}
	
}