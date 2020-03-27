package gestorAplicacion.Interacciones;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
//Autor Darwin Herrera
public class Comentario{
    public Comentario() {}
    //Atributos
    private String autor;
    private String contenido;
  
    //Getters y setters

    public String getAutor(){
    	return autor;
    }
    public String getContenido(){
    	return contenido;
    }

    private Comentario(String o, String contenido) {
		autor=o;
		this.contenido = contenido;
	}
	public Comentario( String contenido) {
		this("Desconocido",contenido);
	}
	public String IdUnico() {
		return autor+" "+contenido;
	}

}
