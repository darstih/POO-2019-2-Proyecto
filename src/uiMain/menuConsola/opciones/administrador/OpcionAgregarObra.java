package uiMain.menuConsola.opciones.administrador;

import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Administrador;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAgregarObra extends OpcionDeMenu {
	
	
	@Override
	public String toString() {
		return "Agregar Obra";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		Obra nuevaObra = formObra();
		Administrador.agregarObra(nuevaObra);
		System.out.println("\nObra agregada:\n" + nuevaObra + "\n");
		return null;
		
	}
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setSiguiente(atras);
		atras.setAtras(atras.getAtras());
	}
}
