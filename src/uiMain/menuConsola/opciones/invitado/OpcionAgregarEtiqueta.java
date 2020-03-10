package uiMain.menuConsola.opciones.invitado;

import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Invitado;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAgregarEtiqueta extends OpcionDeMenu {
	
	
	@Override
	public String toString() {
		return "Agregar Etiqueta";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		Obra obra = obraSelec(MenuDeConsola.getMenuActual().getAux());
		Etiqueta e=crearEtiqueta();
		Invitado.agregarEtiqueta(obra, e);
		System.out.println("Etiqueta agregada");
		return null;
		
	}
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setSiguiente(atras);
		atras.setAtras(atras.getAtras());
	}
	private Etiqueta crearEtiqueta() {
		System.out.println("Ingrese label: ");
		String arg=in.next();
		System.out.println("Ingrese tipo: ");
		String arg1=in.next();
		System.out.println("Ingrese descripcion: ");
		String arg2=in.next();
		Etiqueta e=new Etiqueta(arg,arg1,arg2);
		return e;
	}
}