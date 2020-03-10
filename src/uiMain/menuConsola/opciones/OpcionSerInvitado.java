package uiMain.menuConsola.opciones;

import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;
import uiMain.menuConsola.opciones.invitado.*;

public class OpcionSerInvitado extends OpcionDeMenu {

	@Override
	public String toString() {
		return "Iniciar como invitado";
	}

	@Override
	public MenuDeConsola ejecutar() {
		MenuDeConsola menuInv = new MenuDeConsola("Interfaz de invitado...");
		menuInv.añadirOpcion(new OpcionEnviarObra());
		menuInv.añadirOpcion(new OpcionBuscarObras());
		menuInv.añadirOpcion(new OpcionListarObras());
		menuInv.añadirOpcion(new OpcionSerAdministrador());
		menuInv.añadirOpcion(new OpcionAgregarEtiqueta());
		menuInv.añadirOpcion(new OpcionAgregarComentario());
		menuInv.añadirOpcion(new OpcionBorrarEtiqueta());
		menuInv.añadirOpcion(new OpcionVolver());
		menuInv.añadirOpcion(new OpcionSalir());
		return menuInv;
	}

}
