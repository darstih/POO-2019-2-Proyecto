package uiMain.menuConsola.opciones.invitado;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Invitado;
import uiMain.menuConsola.MenuDeConsola;
import uiMain.menuConsola.OpcionDeMenu;

public class OpcionAgregarComentario extends OpcionDeMenu {
	
	public OpcionAgregarComentario() {
		super();
	}
	
	@Override
	public String toString() {
		return "Agregar Comentario";
	}
	
	@Override
	public MenuDeConsola ejecutar() {
		Obra o=obraSelec(MenuDeConsola.getMenuActual().getAux());
		Comentario c=crearComentario();
		Invitado.addComentario(o,c);
		System.out.println("Comentario agregado");
		return null;
		
	}
	public Comentario crearComentario() {
		System.out.println("Ingrese comentario: ");
		String arg=in.nextLine();
		arg += in.nextLine();
		Comentario e=new Comentario(arg);
		return e;

	}
	@Override
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		atras.setSiguiente(atras);
		atras.setAtras(atras.getAtras());
	}
	
	
}
