package gestorAplicacion.Obras;

import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import Excepciones.CantBeNull;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.ErrorObraRepetida;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Interacciones.ObjetoReporte;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import gui.opciones.botones.ActualizarObra;
import gui.opciones.botones.AgregarComentario;
import gui.opciones.botones.AgregarEtiqueta;
import gui.opciones.publico.AgregarReporte;
import gestorAplicacion.Interacciones.Comentario;


//Autor de clase y estructura Darwin Herrera

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obra extends ObjetoReporte{
	public Obra() {Obra.obras.add(this);}

	//Atributos
	private static ArrayList<Tecnica> listaTecnicas = new ArrayList<Tecnica>();
	private Tecnica tecnica;
	private String descripcion;
	private String imagen= "Ruta de imagen";
	private Double altura;
	private Double ancho;
	
	private ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	private Calendar fechaCreacion;
	private Calendar fechaIngreso;
	private String autor;
	private String titulo;
    private boolean visible = true;
	private static ArrayList<Obra> obras= new ArrayList<Obra>();
	private ArrayList<Comentario> comentarios; 
	//Metodos
	//Cree este método nuevo para verificar si una etiqueta existente ya está agregada en la obra
	public boolean verificarEtiquetaEnObra(Etiqueta etiqueta){//Retornará true si ya está creada
		boolean retornar = false;
		if(!etiquetas.isEmpty()){
			for (int i=0;i<etiquetas.size();i++){
				if(etiqueta.getLabel().equalsIgnoreCase(etiquetas.get(i).getLabel())){
					retornar=true;
					break;
				}
			}
		}
		return retornar;
	}
	
	
	
	public void crearEtiqueta(Etiqueta etiqueta)throws ErrorEtiquetaRepetida{
		if(!Etiqueta.verificarEtiqueta(etiqueta)){
			Etiqueta.setEtiqueta(etiqueta);
			etiqueta.aumentarCantObras();
			etiquetas.add(etiqueta);
		}else if (!verificarEtiquetaEnObra(etiqueta)){
			Etiqueta.aumentarContador(etiqueta);
			etiquetas.add(etiqueta);
		}else{
			throw new ErrorEtiquetaRepetida();
		}
		
	}
	
	public void borrarEtiqueta(Etiqueta etiqueta) {
		if(verificarEtiquetaEnObra(etiqueta)) {
			for (int i=0;i<etiquetas.size();i++){
				if(etiqueta.getLabel().equalsIgnoreCase(etiquetas.get(i).getLabel())){
					etiquetas.remove(i);
					break;
				}
			}
		}
	}

	public boolean verificarTecnica(Tecnica tecnica){//Retornará true si ya está creada
		boolean retornar = false;
		if(listaTecnicas.isEmpty()){
			for (int i=0;i<listaTecnicas.size();i++){
				if(tecnica.getNombre().equalsIgnoreCase(listaTecnicas.get(i).getNombre())){
					retornar=true;
					break;
				}
			}
		}
		return retornar;
	}

	@SuppressWarnings("static-access")
	public void crearTecnica(Tecnica tecnica){
		if(!verificarTecnica(tecnica)){
			listaTecnicas.add(tecnica);
			this.tecnica=listaTecnicas.get(listaTecnicas.size()-1);
			listaTecnicas.get(listaTecnicas.size()-1).cantObras++;
		}
		else{
			for (int i=0;i<listaTecnicas.size();i++){
				if(tecnica.getNombre().equalsIgnoreCase(listaTecnicas.get(i).getNombre())){
					this.tecnica=listaTecnicas.get(i);
					listaTecnicas.get(i).cantObras++;
					break;
				}
			}
		}
	}
	
	
	//Getters y setters
	
	public static ArrayList<Tecnica> getListaTecnicas(){
		return listaTecnicas;
	}
	
	public static ArrayList<Etiqueta> getListaEtiquetas(){
		return listaEtiquetas;
	}
	
	public void agregarComentario(Comentario c) {
	    this.comentarios.add(c);
	}
	public static void addObra(Obra o) {
	    Obra.obras.add(o);
	    
	}

	public static int getCantObras() {
	    return Obra.obras.size();
	}
        public boolean getVisible(){
	    return this.visible;
        }
	public String getAutor() {
	    return this.autor;
	}

	public ArrayList<Etiqueta> getEtiquetas() {
	    return etiquetas;
	}

        public ArrayList<Comentario> getComentarios() {
	    return comentarios;
	}
	public Calendar getFechaCreacion() {
	    return fechaCreacion;
	}
	public String getDescripcion() {
	    return descripcion;
	}

	public Calendar getFechaIngreso() {
	    return fechaIngreso;
	}

	public Double getAltura() {
	    return altura;
	}

	public Double getAncho() {
	    return ancho;
	}

	public int devolverCantComentarios() {
	    return (comentarios != null) ? this.comentarios.size() : 0;
	}

	public String getTitulo() {
	    return titulo;
	}

	public void setTitulo(String titulo) {
	    this.titulo = titulo;
	}
       
	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setAutor(String autor) {
	    this.autor = autor;
	}
        public void setVisible(boolean v){
	    this.visible = v;
        }
	public Tecnica getTecnica() {
	    return tecnica;
	}
	public String rutaImagen() {
		return imagen;
	}
	public static void setObras(ArrayList<Obra> a) {
		Obra.obras = a;
	}
	//constructores
	public Obra(String titulo, String descripcion, Double altura, Double ancho, Calendar fecCreacion,  ArrayList<Etiqueta> etiquetas,Tecnica tecnica, String autor,boolean visible)throws ErrorEtiquetaRepetida{
		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.altura = altura;
		this.ancho = ancho;
		this.visible = visible;
		this.fechaCreacion = fecCreacion;
		this.fechaIngreso = Calendar.getInstance();
		this.autor = autor;
		this.comentarios = new ArrayList<Comentario>();
		this.etiquetas = etiquetas;
		crearTecnica(tecnica);
	}
	public static ArrayList<Obra> getObras() {
		return obras;
	}
	public void setTecnica(Tecnica tecnica) {
		this.tecnica = tecnica;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}
	public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}
	public void setFechaCreacion(Calendar fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String IdUnico() {
		// TODO Auto-generated method stub
		return autor+" "+titulo;
	}
}
