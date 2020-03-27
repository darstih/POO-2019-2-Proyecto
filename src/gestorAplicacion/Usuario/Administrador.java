package gestorAplicacion.Usuario;

import java.util.ArrayList;
import java.util.Hashtable;

import Excepciones.ErrorComentarioRepetido;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.ErrorObraRepetida;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gui.paneles.PaneInteraccion;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
//Autor clase y estructura Darwin Herrera
public class Administrador extends Usuario {
	//Atributos
    private static ArrayList<Obra> obrasPendientes = new ArrayList<Obra>();
	private static ArrayList<Comentario> reportesComentario = new ArrayList<Comentario>();
	private static ArrayList<Obra> reportesObra = new ArrayList<Obra>();
	private static ArrayList<Administrador> usuarios= new ArrayList<Administrador>();
	private String usuario;
	private String password;
	
	//Metodos
	//autor Darwin Herrera
	
	
	
	public static void agregarObra(Obra a)throws ErrorObraRepetida{
		if(!Obra.existeObra(a)) {
			Obra.addObra(a);
		}
		
  	}
	//autor Darwin Herrera
	public static void addComentario(Obra o,Comentario c)throws ErrorComentarioRepetido {
		o.agregarComentario(c);
	}

	public static void addReporteComentario(Comentario r ){
		reportesComentario.add(r);
	}
	public static void addReporteObra(Obra r){
		reportesObra.add(r);
		r.setContenidoReporte("");
	}
	public static boolean verificarAdmin(String usuario,String password) {
		boolean answer = false;
		for(int i = 0; i<usuarios.size();i++) {
			if(usuarios.get(i).getUsuario().equals(usuario) && usuarios.get(i).getPassword().equals(password)) {
				answer = true;
				break;
			}
		}
		return answer;
		
	}
	
	public static void aprobarReporte(Comentario c){
		c.setVisible(false);
	}
	public static void aprobarReporte(Obra a){
		a.setVisible(false);
	}
	public static void agregarEtiqueta(Obra a, Etiqueta e)throws ErrorEtiquetaRepetida {
		a.crearEtiqueta(e);
	}
	public static void borrarEtiqueta(Obra a, Etiqueta e) {
		a.borrarEtiqueta(e);
	}
    public static void addObraPendiente(Obra o)throws ErrorObraRepetida{
    	if(!Obra.existeObra(o)) {
    		obrasPendientes.add(o);
    	}
    
	}
    public static void eliminarObraPendiente(Obra o) {
		for(int i = 0; i<obrasPendientes.size();i++) {
			if((o.IdUnico()).equals(obrasPendientes.get(i).IdUnico())) {
				obrasPendientes.remove(i);
				break;
			}
		}
	}
    public static void aprobarObra(Obra o)throws ErrorObraRepetida {
    	agregarObra(o);
    	o.setVisible(true);
    	obrasPendientes.remove(o);
	}	
    //Getters y setters
		public static ArrayList<Obra> getObrasPendientes() {
			return obrasPendientes;
		}
		public String getUsuario() {
			return usuario;
		}
		public String getPassword() {
			return password;
		}
		public static ArrayList<Administrador> getAdministradores(){
			return usuarios;
		}
		public static void setObrasPendientes(ArrayList<Obra> obrasPendientes) {
			Administrador.obrasPendientes = obrasPendientes;
		}
		public static ArrayList<Comentario> getReportesComentario() {
			return reportesComentario;
		}
		public static void setReportesComentario(ArrayList<Comentario> reportesComentario) {
			Administrador.reportesComentario = reportesComentario;
		}
		public static ArrayList<Obra> getReportesObra() {
			return reportesObra;
		}
		public static void setReportesObra(ArrayList<Obra> reportesObra) {
			Administrador.reportesObra = reportesObra;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public static void setAdministradores(ArrayList<Administrador> la) {
			Administrador.usuarios = la;
		}
		
    //Constructores
		@Override
		public String descripcion() {
			// TODO Auto-generated method stub
			return "Soy un administrador y puedo hacer lo siguiente:"
					+ "\n -Agregar y aprobar obras."
					+ "\n -Agregar y aprobar comentarios."
					+ "\n -Agregar y aprobar reportes de obras y comentarios."
					+ "\n -Agregar y borrar etiquetas.";
		}
}
