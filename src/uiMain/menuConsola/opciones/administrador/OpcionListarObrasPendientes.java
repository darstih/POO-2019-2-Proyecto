package uiMain.menuConsola.opciones.administrador;

import gestorAplicacion.Usuario.Administrador;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;
public class OpcionListarObrasPendientes extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Mostrar Obras Pendientes";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		MenuDeConsola.getMenuActual().setAux(Administrador.getObrasPendientes()); 
		obraSelec(MenuDeConsola.getMenuActual().getAux());
		return null;
		
	}
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setAtras(atras);
		atras.setSiguiente(atras);
	}
}