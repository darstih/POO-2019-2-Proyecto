package uiMain.menuConsola.opciones.invitado;

import java.util.ArrayList;

import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Invitado;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionBorrarEtiqueta extends OpcionDeMenu {
		
	@Override
	public String toString() {
		return "Borrar Etiqueta";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		Obra obra = obraSelec(MenuDeConsola.getMenuActual().getAux());
		System.out.println("Seleccione una etiqueta: ");
		Etiqueta e=mostrarEtiquetas(obra);
		Invitado.borrarEtiqueta(obra, e);
		System.out.println("Etiqueta borrada");
		return null;
		
	}
	
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setSiguiente(atras);
		atras.setAtras(atras.getAtras());
	}


}
