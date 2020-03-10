package uiMain.menuConsola.opciones.invitado;

import java.util.ArrayList;
import java.util.Calendar;

import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gestorAplicacion.Usuario.Invitado;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;
public class OpcionEnviarObra extends OpcionDeMenu {

	@Override
	public String toString() {
		return "Agregar Obra";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		Obra nuevaObra = formObra();
		Invitado.postularObra(nuevaObra);
		System.out.println("\nObra:\n" + nuevaObra);
		System.out.println("\nEn proceso...");
		System.out.println("Cuando la apruebe un administrador será exitosamente agregada.");
		MenuDeConsola menuInvCopy = new MenuDeConsola("");
		return menuInvCopy;
		
	}
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola siguiente) {
		atras.setAtras(atras);
		atras.setSiguiente(atras.getAtras());
	}
	

}