package gui.opciones.publico;
//Autor David Aristizabal Giraldo
import gestorAplicacion.Usuario.Usuario;
import gui.paneles.PaneInteraccion;
import gui.opciones.Independiente;
import gui.opciones.OpcionDeMenu;

public class TecnicaMasUsada extends OpcionDeMenu implements Independiente{
	
	public String toString() {
		return "Obras con la tecnica mas usada";
	}
	
	@Override
	public void ejecutar(){
		////parte grafica de funcionalidad interesante, verifica cual es la tecnica mas usada en las obras del sistema
		// y retorna todas las obras en las que se implemento esta tecnica
		PaneInteraccion.setPaneActual(Usuario.listarObraGrafica(Usuario.tecnicaMasUsada(),3));
	}
}