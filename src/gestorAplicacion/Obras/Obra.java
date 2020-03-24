package gestorAplicacion.Obras;

import java.util.ArrayList;
import java.util.Calendar;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamaño;
import gestorAplicacion.Interacciones.ObjetoReporte;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import uiMain.menuConsola.opciones.OpcionAgregarComentario;
import uiMain.menuConsola.opciones.OpcionAgregarEtiqueta;
import uiMain.menuConsola.opciones.administrador.OpcionAgregarObra;
import gestorAplicacion.Interacciones.Comentario;

//Autor de clase y estructura Darwin Herrera


public class Obra extends ObjetoReporte{
	public Obra() {Obra.obras.add(this);}

	//Atributos
	private static ArrayList<Tecnica> listaTecnicas = new ArrayList<Tecnica>();
	private Tecnica tecnica;
	private String descripcion;
	private String imagen= "Aquí irían las imagenes, si tuviesemos base de datos";
	private Double altura;
	private Double ancho;
	private static ArrayList<Etiqueta> listaEtiquetas = new ArrayList<Etiqueta>();
	private ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	private Calendar fechaCreacion;
	private Calendar fechaIngreso;
	private String autor;
	private String titulo;
    private boolean visible = true;
	private static ArrayList<Obra> obras= new ArrayList<Obra>();//Andor el json tiene que actualizar esto
	private ArrayList<Comentario> comentarios; 
	//Metodos
	public boolean verificarEtiqueta(Etiqueta etiqueta){//Retornará true si ya está creada
		boolean retornar = false;
		if(listaEtiquetas.isEmpty()){
			for (int i=0;i<listaEtiquetas.size();i++){
				if(etiqueta.getLabel().equalsIgnoreCase(listaEtiquetas.get(i).getLabel())){
					retornar=true;
					break;
				}
			}
		}
		
		return retornar;
	}
	//Cree este método nuevo para verificar si una etiqueta existente ya está agregada en la obra
	public boolean verificarEtiquetaEnObra(Etiqueta etiqueta){//Retornará true si ya está creada
		boolean retornar = false;
		if(etiquetas.isEmpty()){
			for (int i=0;i<etiquetas.size();i++){
				if(etiqueta.getLabel().equalsIgnoreCase(etiquetas.get(i).getLabel())){
					retornar=true;
					break;
				}
			}
		}
		
		return retornar;
	}
	
	
	
	
	public BorderPane graficar() {
		AbrirObraHandler eve = new AbrirObraHandler();
		BorderPane obraGrafica = new BorderPane();
		obraGrafica.setStyle("-fx-background-color: #F94978");
		Text imagen = new Text(this.imagen);
		
		Text titulo = new Text(this.titulo);
		Text autor = new Text(this.autor);
		obraGrafica.setPadding(new Insets(10,10,10,10));
		obraGrafica.setPrefSize(176, 226);
		obraGrafica.setTop(titulo);
		obraGrafica.setCenter(imagen);
		
		
		obraGrafica.getCenter().setOnMouseClicked(eve);
		
		//_------------------------
		VBox nuevo = new VBox();
		AgregarComentarioHandler co = new AgregarComentarioHandler();
		Button addCom = new Button("+Comentario");
		addCom.setOnAction(co);
		AgregarEtiquetaHandler et = new AgregarEtiquetaHandler();
		Button addEti = new Button("+Etiqueta");
		addEti.setOnAction(et);
		HBox botones = new HBox();
		botones.getChildren().add(addCom);
		botones.getChildren().add(addEti);
		nuevo.getChildren().add(autor);
		nuevo.getChildren().add(botones);
		//--------------------------
		obraGrafica.setBottom(nuevo);
		
		return obraGrafica;
	}
	
	class AbrirObraHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			if(!(PaneInteraccion.getTipoUsuario()=="Invitado")) {
				BorderPane a = new BorderPane();
				a.setPadding(new Insets(20,20,20,20));
				BorderPane graf = (BorderPane) arg0.getSource();
				Obra obr = PaneInteraccion.getAux().get(graf);
				
				Label titulo = new Label(obr.titulo);
				a.setTop(titulo);
				
				a.setLeft(new Label(obr.imagen));
				VBox descripcion = new VBox(10);
				descripcion.getChildren().add(new Label("Descripción:"));
				descripcion.getChildren().add(new Label(Obra.this.descripcion));
				descripcion.getChildren().add(new Label("Altura:"));
				descripcion.getChildren().add(new Label(Obra.this.altura.toString()));
				descripcion.getChildren().add(new Label("Ancho:"));
				descripcion.getChildren().add(new Label(Obra.this.ancho.toString()));
				descripcion.getChildren().add(new Label("Autor:"));
				descripcion.getChildren().add(new Label(Obra.this.autor.toString()));
				descripcion.getChildren().add(new Label("Fecha creación:"));
				descripcion.getChildren().add(new Label(Obra.this.fechaCreacion.toString()));
				descripcion.getChildren().add(new Label("Tecnica:"));
				descripcion.getChildren().add(new Label(Obra.this.tecnica.getNombre()));
				a.setCenter(descripcion);
				
				HBox etiq = new HBox();
				for(Etiqueta i:etiquetas) {
					etiq.getChildren().add(i.graficar());
				}
				
				
				Button com = new OpcionAgregarComentario().graficar();
				Button eti = new OpcionAgregarComentario().graficar();
				HBox botones = new HBox(20);
				botones.getChildren().add(com);
				botones.getChildren().add(eti);
				
				
				VBox contenedor = new VBox();
				contenedor.getChildren().add(botones);
				contenedor.getChildren().add(etiq);
				a.setBottom(contenedor);
				
				PaneInteraccion.setPaneActual(a);
			}
			
		}
		
	}
	class AgregarComentarioHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(Obra.this);
			try {
				new OpcionAgregarComentario().ejecutar();
			} catch (NoCoincideTamaño | CantBeNull e) {
				e.printStackTrace();
			}
			
		}
		
	}
	class AgregarEtiquetaHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(Obra.this);
			try {
				new OpcionAgregarEtiqueta().ejecutar();
			} catch (NoCoincideTamaño | CantBeNull e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	
	
	
	public void crearEtiqueta(Etiqueta etiqueta){
		if(!verificarEtiqueta(etiqueta)){
			listaEtiquetas.add(etiqueta);
			System.out.println(etiqueta.getLabel());
			etiquetas.add(etiqueta);
			etiqueta.cantObras++;
		}
		else if (!verificarEtiquetaEnObra(etiqueta)){
			for (int i=0;i<listaEtiquetas.size();i++){
				if(etiqueta.getLabel().equalsIgnoreCase(listaEtiquetas.get(i).getLabel())){
					etiquetas.add(listaEtiquetas.get(i));
					listaEtiquetas.get(i).cantObras++;
					break;
				}
			}
		}
		else{
			System.out.println("La etiqueta "+etiqueta.getLabel()+" ya esta creada y esta en la obra.");
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
	public static void setObras(ArrayList<Obra> a) {
		Obra.obras = a;
	}
	//constructores
	public Obra(String titulo, String descripcion, Double altura, Double ancho, Calendar fecCreacion,  ArrayList<Etiqueta> etiquetas,Tecnica tecnica, String autor){
		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.altura = altura;
		this.ancho = ancho;
		this.fechaCreacion = fecCreacion;
		this.fechaIngreso = Calendar.getInstance();
		this.autor = autor;
		this.comentarios = new ArrayList<Comentario>();
		for(int i=0;i<etiquetas.size();i++){
			crearEtiqueta(etiquetas.get(i));
		}
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
}
