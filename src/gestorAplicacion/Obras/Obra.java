package gestorAplicacion.Obras;

import java.util.ArrayList;
import java.util.Calendar;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Interacciones.ObjetoReporte;
import gestorAplicacion.Usuario.Administrador;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
	private String imagen= "Ruta de imagen";
	private Double altura;
	private Double ancho;
	private static ArrayList<Etiqueta> listaEtiquetas = new ArrayList<Etiqueta>();
	private ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
	private Calendar fechaCreacion;
	private Calendar fechaIngreso;
	private String autor;
	private String titulo;
    private boolean visible = true;
	private static ArrayList<Obra> obras= new ArrayList<Obra>();
	private ArrayList<Comentario> comentarios; 
	//Metodos
	public boolean verificarEtiqueta(Etiqueta etiqueta){
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
	
	
	
	
	public BorderPane graficar(int opcion) {
		AbrirObraHandler eve = new AbrirObraHandler();
		BorderPane obraGrafica = new BorderPane();
		obraGrafica.setStyle("-fx-background-color: #F94978");
		Label imagen = new Label(this.imagen);
		obraGrafica.setOnMouseClicked(eve);
		Label titulo = new Label(this.titulo);
		Label autor = new Label(this.autor);
		obraGrafica.setPadding(new Insets(10,10,10,10));
		obraGrafica.setPrefSize(176, 226);
		obraGrafica.setTop(titulo);
		obraGrafica.setCenter(imagen);
		
		
		obraGrafica.getCenter().setOnMouseClicked(eve);
		
		//_------------------------
		VBox nuevo = new VBox();
		HBox botones = new HBox();
		if(opcion==1) {//listar obras
			AgregarComentarioHandler co = new AgregarComentarioHandler();
			Button addCom = new Button("+Comentario");
			addCom.setOnAction(co);
			AgregarEtiquetaHandler et = new AgregarEtiquetaHandler();
			Button addEti = new Button("+Etiqueta");
			addEti.setOnAction(et);
			botones.getChildren().add(addCom);
			botones.getChildren().add(addEti);
		}else if(opcion==2) {
			Button addObra = new Button("Aprobar");
			AprobarObraHandler ap = new AprobarObraHandler();
			addObra.setOnAction(ap);
			AgregarEtiquetaHandler et = new AgregarEtiquetaHandler();
			Button addEti = new Button("Eliminar");
			addEti.setOnAction(et);
			botones.getChildren().add(addObra);
			botones.getChildren().add(addEti);
		}
		nuevo.getChildren().add(autor);
		nuevo.getChildren().add(botones);
		//--------------------------
		obraGrafica.setBottom(nuevo);
		
		return obraGrafica;
	}
	class AprobarObraHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Obra.addObra(Obra.this);
			Obra.this.setVisible(true);
		}
	
	}
	
	class AbrirObraHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			if(!(PaneInteraccion.getTipoUsuario()=="Invitado")) {
				BorderPane a = new BorderPane();
				a.setStyle("-fx-background-color: #F94978");
				BorderPane graf = (BorderPane) arg0.getSource(); 
				a.setPadding(new Insets(20,20,20,20));
				Obra obr = PaneInteraccion.getAux().get(graf);
				Label titulo = new Label(obr.titulo);
				a.setTop(titulo);
				Label image = new Label(obr.imagen);
				image.setPrefWidth(300);
				a.setLeft(image);
				VBox descripcion = new VBox(10);
				
				descripcion.getChildren().add(new Label("Descripción:"));
				descripcion.getChildren().add(new Label(obr.descripcion));
				descripcion.getChildren().add(new Label("Altura:"));
				descripcion.getChildren().add(new Label(Double.toString(obr.altura)));
				descripcion.getChildren().add(new Label("Ancho:"));
				descripcion.getChildren().add(new Label(Double.toString(obr.ancho)));
				descripcion.getChildren().add(new Label("Autor:"));
				descripcion.getChildren().add(new Label(obr.autor.toString()));
				descripcion.getChildren().add(new Label("Fecha creación:"));
				descripcion.getChildren().add(new Label(obr.fechaCreacion.toString()));
				descripcion.getChildren().add(new Label("Tecnica:"));
				descripcion.getChildren().add(new Label(obr.tecnica.getNombre()));
				a.setCenter(descripcion);
				
				HBox etiq = new HBox();
				for(Etiqueta i:etiquetas) {
					etiq.getChildren().add(i.graficar());
				}
				
				
				Button com = new OpcionAgregarComentario().graficar();
				Button br= new Button("Borrar");
				Button eti = new OpcionAgregarEtiqueta().graficar();
				HBox botones = new HBox(20);
				BorrarObraHandler handlerBorrarObra = new BorrarObraHandler();
				AgregarComentarioHandler handlerComentario = new AgregarComentarioHandler();
				AgregarEtiquetaHandler handlerEtiqueta = new AgregarEtiquetaHandler();
				eti.setOnAction(handlerEtiqueta);
				com.setOnAction(handlerComentario);
				br.setOnAction(handlerBorrarObra);
				botones.getChildren().add(com);
				
				botones.getChildren().add(eti);
				
				
				VBox contenedor = new VBox();
				contenedor.getChildren().add(botones);
				contenedor.getChildren().add(etiq);
				for(Comentario i: obr.comentarios) {
					contenedor.getChildren().add(i.graficar());
				}
				
				
				
				a.setBottom(contenedor);
				
				PaneInteraccion.setPaneActual(a);
			}else {
				BorderPane a = new BorderPane();
				a.setStyle("-fx-background-color: #F94978");
				BorderPane graf = (BorderPane) arg0.getSource(); 
				a.setPadding(new Insets(20,20,20,20));
				Obra obr = PaneInteraccion.getAux().get(graf);
				Label titulo = new Label(obr.titulo);
				a.setTop(titulo);
				Label image = new Label(obr.imagen);
				image.setPrefWidth(300);
				a.setLeft(image);
				VBox descripcion = new VBox(10);
				
				descripcion.getChildren().add(new Label("Descripción:"));
				descripcion.getChildren().add(new Label(obr.descripcion));
				descripcion.getChildren().add(new Label("Altura:"));
				descripcion.getChildren().add(new Label(Double.toString(obr.altura)));
				descripcion.getChildren().add(new Label("Ancho:"));
				descripcion.getChildren().add(new Label(Double.toString(obr.ancho)));
				descripcion.getChildren().add(new Label("Autor:"));
				descripcion.getChildren().add(new Label(obr.autor.toString()));
				descripcion.getChildren().add(new Label("Fecha creación:"));
				descripcion.getChildren().add(new Label(obr.fechaCreacion.toString()));
				descripcion.getChildren().add(new Label("Tecnica:"));
				descripcion.getChildren().add(new Label(obr.tecnica.getNombre()));
				a.setCenter(descripcion);
				
				HBox etiq = new HBox();
				for(Etiqueta i:etiquetas) {
					etiq.getChildren().add(i.graficar());
				}
				
				
				Button com = new OpcionAgregarComentario().graficar();
				
				Button eti = new OpcionAgregarComentario().graficar();
				HBox botones = new HBox(20);
				botones.setPadding(new Insets(20,20,20,20));
				
				AgregarComentarioHandler handlerComentario = new AgregarComentarioHandler();
				AgregarEtiquetaHandler handlerEtiqueta = new AgregarEtiquetaHandler();
				Button borrar = new Button("Borrar");
				
				eti.setOnAction(handlerEtiqueta);
				com.setOnAction(handlerComentario);
				botones.getChildren().add(com);
				
				botones.getChildren().add(eti);
				
				
				VBox contenedor = new VBox();
				contenedor.getChildren().add(botones);
				contenedor.getChildren().add(etiq);
				for(Comentario i: obr.comentarios) {
					contenedor.getChildren().add(i.graficar());
				}
				
				
				
				a.setBottom(contenedor);
				
				PaneInteraccion.setPaneActual(a);
			}
			
		}
		
	}
	class BorrarObraHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			BorderPane graf = (BorderPane) arg0.getSource();
			Obra obr = PaneInteraccion.getAux().get(graf);
			Obra.getObras().remove(obr);
		}
		
	}
	class AgregarComentarioHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(Obra.this);
			try {
				new OpcionAgregarComentario().ejecutar();
			} catch (NoCoincideTamano | CantBeNull e) {
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
			} catch (NoCoincideTamano | CantBeNull e) {
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
	public Obra(String titulo, String descripcion, Double altura, Double ancho, Calendar fecCreacion,  ArrayList<Etiqueta> etiquetas,Tecnica tecnica, String autor,boolean visible){
		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.altura = altura;
		this.ancho = ancho;
		this.visible = visible;
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
