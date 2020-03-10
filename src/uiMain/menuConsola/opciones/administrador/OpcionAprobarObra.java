package uiMain.menuConsola.opciones.administrador;

import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Administrador;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAprobarObra extends OpcionDeMenu {
	
	@Override
	public String toString() {
		return "Aprobar Obra";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		Obra obra = obraSelec(MenuDeConsola.getMenuActual().getAux());
		Administrador.aprobarObra(obra);
		System.out.println("Obra aprobada");
		return null;
		
	}
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		actual.setAtras(atras);
		atras.setSiguiente(actual);
	}
}