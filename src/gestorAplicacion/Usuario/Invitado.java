package gestorAplicacion.Usuario;

import Excepciones.ErrorComentarioRepetido;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.ErrorObraRepetida;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
//Autor estructura clase Darwin Herrera
public class Invitado extends Usuario{
    //Atributos
    
    //Metodos
	public static void addComentario(Obra o, Comentario c)throws ErrorComentarioRepetido {
		Administrador.addComentario(o,c);
	}
    public static void agregarEtiqueta(Obra o,Etiqueta e)throws ErrorEtiquetaRepetida {
    	Administrador.agregarEtiqueta(o, e);
    }
    public static void borrarEtiqueta(Obra o,Etiqueta e) {
    	Administrador.borrarEtiqueta(o, e);
    }
    public static void postularObra(Obra o)throws ErrorObraRepetida{
    	
	  Administrador.addObraPendiente(o);
    }
    //Getters y setters
	@Override
	public String descripcion() {
		
		return "Soy un usuario invitado y puedo realizar sin autorizacion lo siguiente:"
				+ "\n -Agregar etiquetas de una obra."
				+ "\n -Agregar comentarios de una obra."
				+ "\n -Buscar una obra."
				+ "\n -Ver todas las obras."
				+ "\n -Ver las obras con la tecnica mas popular."
				+ "\n -Ver las obras con la etiqueta mas popular."
				+ "\n -Ver las obras del artista mas popular."
				+ "\n -Postular obra"
				+ "\n -Ver las obras mas populares.";
				
		
	}
		
    //Constructores
}
