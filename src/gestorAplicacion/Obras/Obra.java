package gestorAplicacion.Obras;

import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import Excepciones.ErrorComentarioRepetido;
import Excepciones.ErrorEtiquetaRepetida;
import Excepciones.ErrorObraRepetida;
import gestorAplicacion.Interacciones.ObjetoReporte;
import gestorAplicacion.Interacciones.Comentario;


//Autor de clase y estructura Darwin Herrera

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obra extends ObjetoReporte{
	public Obra() {Obra.obras.add(this);}

	//Atributos
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
	
	public boolean comentarioRepetido(Comentario comentario)throws ErrorComentarioRepetido {
		boolean answer = false;
		for(int i = 0; i<comentarios.size();i++) {
			if(comentarios.get(i).getContenido().equalsIgnoreCase(comentario.getContenido())) {
				answer = true;
				throw new ErrorComentarioRepetido();
			}
		}
		return answer;
	}
	
	
	public static boolean existeObra(Obra obra)throws ErrorObraRepetida {
		boolean answer = false;
		for(int i = 0; i<obras.size();i++) {
			if(obras.get(i).IdUnico().equals(obra.IdUnico())) {
				answer = false;
				throw new ErrorObraRepetida();
			}
		}
		return answer;
	}
	
	
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
		if(!Tecnica.getTecnicas().isEmpty()){
			for (int i=0;i<Tecnica.getTecnicas().size();i++){
				if(tecnica.getNombre().equalsIgnoreCase(Tecnica.getTecnicas().get(i).getNombre())){
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
			Tecnica.getTecnicas().add(tecnica);
			this.tecnica=Tecnica.getTecnicas().get(Tecnica.getTecnicas().size()-1);
			Tecnica.getTecnicas().get(Tecnica.getTecnicas().size()-1).aumentarObras();
		}
		else{
			for (int i=0;i<Tecnica.getTecnicas().size();i++){
				if(tecnica.getNombre().equalsIgnoreCase(Tecnica.getTecnicas().get(i).getNombre())){
					this.tecnica=Tecnica.getTecnicas().get(i);
					Tecnica.getTecnicas().get(i).aumentarObras();
					break;
				}
			}
		}
	}
	
	
	//Getters y setters
	
	public void agregarComentario(Comentario c)throws ErrorComentarioRepetido {
		if(!comentarioRepetido(c)) {
			this.comentarios.add(c);
		}
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
