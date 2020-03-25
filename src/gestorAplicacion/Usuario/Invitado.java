package gestorAplicacion.Usuario;

import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Interacciones.Reporte;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
//Autor estructura clase Darwin Herrera
public class Invitado extends Usuario{
    //Atributos
    
    //Metodos
	public static void addComentario(Obra o, Comentario c) {
		Administrador.addComentario(o,c);
	}
    public static void reporte(Reporte r){
	if(r.getObjetoReporte() instanceof Comentario){
	    Administrador.addReporteComentario(r);
	}else{
	    Administrador.addReporteObra(r);
	}
    }
    public static void agregarEtiqueta(Obra o,Etiqueta e) {
    	Administrador.agregarEtiqueta(o, e);
    }
    public static void borrarEtiqueta(Obra o,Etiqueta e) {
    	Administrador.borrarEtiqueta(o, e);
    }
    public static void postularObra(Obra o){
	Administrador.addObraPendiente(o);
    }
    //Getters y setters
	@Override
	public String descripcion() {
		
		return "Soy un usuario invitado y puedo realizar sin autorizacion lo siguiente:"
				+ "\n -Agregar y borrar etiquetas de una obra."
				+ "\n Y puedo hacer los siguiente sólo si un admistrador lo autoriza:"
				+ "\n -Agregar y reportar obras."
				+ "\n -Agregar y reportar comentario en una obra.";
		
	}
		
    //Constructores
}
