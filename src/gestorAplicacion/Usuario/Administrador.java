package gestorAplicacion.Usuario;

import java.util.ArrayList;

import baseDatos.DBObra;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Interacciones.Reporte;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
//Autor clase y estructura Darwin Herrera
public class Administrador extends Usuario {
	//Atributos
    private static ArrayList<Obra> obrasPendientes = new ArrayList<Obra>();
	private static ArrayList<Reporte> reportesComentario = new ArrayList<Reporte>();
	private static ArrayList<Reporte> reportesObra = new ArrayList<Reporte>();

	//Metodos
	//autor Darwin Herrera
	public static void agregarObra(Obra a) {
		Obra.addObra(a);
		DBObra dbObra = new DBObra();//abriendo db
  	}
	//autor Darwin Herrera
	public static void addComentario(Obra o,Comentario c) {
		o.agregarComentario(c);
	}

	public static void addReporteComentario(Reporte r ){
		reportesComentario.add(r);
	}
	public static void addReporteObra(Reporte r){
		reportesObra.add(r);
	}

	public static void aprobarReporte(Comentario c){
		c.setVisible(false);
	}
	public static void aprobarReporte(Obra a){
		a.setVisible(false);
	}
	public static void agregarEtiqueta(Obra a, Etiqueta e) {
		a.crearEtiqueta(e);
	}
	public static void borrarEtiqueta(Obra a, Etiqueta e) {
		a.borrarEtiqueta(e);
	}
        public static void addObraPendiente(Obra o){
	    obrasPendientes.add(o);
        }
    
        public static void aprobarObra(Obra o) {
	        agregarObra(o);
	        obrasPendientes.remove(o);
	}
    //Getters y setters
		public static ArrayList<Obra> getObrasPendientes() {
			return obrasPendientes;
		}
		public static void setObrasPendientes(ArrayList<Obra> obrasPendientes) {
			Administrador.obrasPendientes = obrasPendientes;
		}
		public static ArrayList<Reporte> getReportesComentario() {
			return reportesComentario;
		}
		public static void setReportesComentario(ArrayList<Reporte> reportesComentario) {
			Administrador.reportesComentario = reportesComentario;
		}
		public static ArrayList<Reporte> getReportesObra() {
			return reportesObra;
		}
		public static void setReportesObra(ArrayList<Reporte> reportesObra) {
			Administrador.reportesObra = reportesObra;
		}
	
    //Constructores
}
