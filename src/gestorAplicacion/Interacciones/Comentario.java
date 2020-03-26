package gestorAplicacion.Interacciones;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
@JsonIgnoreProperties(ignoreUnknown = true)
//Autor Darwin Herrera
public class Comentario extends ObjetoReporte{
    public Comentario() {}
    //Atributos
    private String autor;
    private String contenido;
    private boolean visible = true; //false cuando el comentario es reportado y un admin lo acepta
    
    
    //Metodos
    
    
    //Getters y setters

    public String getAutor(){
    	return autor;
    }
    public boolean getVisible(){
    	return this.visible;
    }
    public void setVisible(boolean v){
    	this.visible = v;//se oculta comentario por reporte
    }   
    
    public String getContenido(){
    	return contenido;
    }
////    public boolean getVisible(){
////	return this.visible;
////    }
////    public void setVisible(boolean v){
////	this.visible = v;//se oculta comentario por reporte
////    }   
//    
    
    
	
        //Constructores
	//Comentario debe tener autor, obra, contenido
	
    private Comentario(String o, String contenido) {
		autor=o;
		this.contenido = contenido;
	}
	public Comentario( String contenido) {
		this("Desconocido",contenido);
	}
	@Override
	
	public String IdUnico() {
		// TODO Auto-generated method stub
		return autor+" "+contenido;
	}

}
