package uiMain.menuConsola.opciones;

import gestorAplicacion.Usuario.Administrador;
import uiMain.menuConsola.MenuDeConsola;

import uiMain.menuConsola.opciones.administrador.*;
import uiMain.menuConsola.opciones.invitado.OpcionAgregarComentario;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionSerAdministrador extends OpcionDeMenu {
	@Override
	public String toString() {
		return "Iniciar como Administrador";
	}

	
	
	@Override
	public MenuDeConsola ejecutar() {
		MenuDeConsola menuAdm = new MenuDeConsola("Interfaz de Administrador...");
		System.out.println(menuAdm.getNombre());
		
		menuAdm.añadirOpcion(new OpcionAgregarObra());
		menuAdm.añadirOpcion(new OpcionListarObrasPendientes());
		menuAdm.añadirOpcion(new OpcionSerInvitado());
		
		menuAdm.añadirOpcion(new OpcionAgregarComentario());
		menuAdm.añadirOpcion(new OpcionAprobarObra());
		menuAdm.añadirOpcion(new OpcionVolverInicio());
		menuAdm.añadirOpcion(new OpcionSalir());
		return menuAdm;
	}
	
}
