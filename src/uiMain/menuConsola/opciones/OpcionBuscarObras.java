package uiMain.menuConsola.opciones;

import gestorAplicacion.Excepciones.ExcepcionFueraRango;
import gestorAplicacion.Usuario.Invitado;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionBuscarObras extends OpcionDeMenu {
	
	public String toString() {
		return "Buscar Obras";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		
		System.out.println("Por: 0. T\u00E9cnica.    1. T\u00EDtulo.");
		
		int i = in.nextInt();
		
		String t = (i == 0) ? "T\u00E9cnica" : "T\u00EDtulo";
		
		System.out.println("Ingresa " + t + ":");
		
		String var = in.nextLine();
		
		var += in.nextLine();
		
	    System.out.println("// listado = 1 | ordena las obras por su fecha de creacion, de mas antiguo a mas nuevo");
	    System.out.println("// listado = 2 | ordena por relevancia, siendo comentarios su criterio de relevancia mas comentarios a menos");
	    System.out.println("// listado = 3 | ordena las obras por su fecha de ingreso al sistema, de mas antiguo a mas nuevo");
	    
	    int listado = in.nextInt();
		
		try {
			MenuDeConsola.getMenuActual().setAux(Invitado.buscarObra(var, i, listado)); 
			System.out.println(obraSelec(MenuDeConsola.getMenuActual().getAux()));
		} catch (ExcepcionFueraRango e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setSiguiente(atras);
		atras.setAtras(atras.getAtras());
	}
}