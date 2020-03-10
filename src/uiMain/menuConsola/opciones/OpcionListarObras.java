package uiMain.menuConsola.opciones;

import gestorAplicacion.Obras.Obra;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionListarObras extends OpcionDeMenu {
	
	public OpcionListarObras() {
		super();
	}
	
	@Override
	public String toString() {
		return "Mostrar todas las obras";
	}
	
	@Override
	public MenuDeConsola ejecutar() {		
		MenuDeConsola.getMenuActual().setAux(Obra.getObras());
		
		System.out.println(obraSelec(MenuDeConsola.getMenuActual().getAux()));
		return null;
	}
}
