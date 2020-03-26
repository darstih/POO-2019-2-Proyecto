package gui;

import java.util.Hashtable;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import gui.opciones.botones.ActualizarObra;
import gui.opciones.botones.AgregarComentario;
import gui.opciones.botones.AgregarEtiqueta;
import gui.opciones.publico.AgregarReporte;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GraficadorObjetos {
	private static Obra obra;
	
	public static BorderPane graficar(Obra a,int opcion) {
		obra = a;
		GraficadorObjetos graficador = new GraficadorObjetos();
		AbrirObraHandler eve = graficador.new AbrirObraHandler();
		BorderPane obraGrafica = new BorderPane();
		obraGrafica.setStyle("-fx-background-color: #F94978");
		Label imagen = new Label(obra.rutaImagen());
		obraGrafica.setOnMouseClicked(eve);
		Label titulo = new Label(obra.getTitulo());
		Label autor = new Label(obra.getAutor());
		obraGrafica.setPadding(new Insets(10,10,10,10));
		obraGrafica.setPrefSize(176, 226);
		obraGrafica.setTop(titulo);
		obraGrafica.setCenter(imagen);
		
		
		obraGrafica.getCenter().setOnMouseClicked(eve);
		
		//_------------------------
		VBox nuevo = new VBox();
		HBox botones = new HBox();
		if(opcion==1) {//listar obras
			AgregarComentarioHandler co = graficador.new AgregarComentarioHandler();
			Button addCom = new Button("+Comentario");
			addCom.setOnAction(co);
			AgregarEtiquetaHandler et = graficador.new AgregarEtiquetaHandler();
			Button addEti = new Button("+Etiqueta");
			addEti.setOnAction(et);
			botones.getChildren().add(addCom);
			botones.getChildren().add(addEti);
		}else if(opcion==2) {
			Button addObra = new Button("Aprobar");
			AprobarObraHandler ap = graficador.new AprobarObraHandler();
			addObra.setOnAction(ap);
			EliminarObraHandler elimiarObra = graficador.new EliminarObraHandler();
			Button removeObra = new Button("Eliminar");
			removeObra.setOnAction(elimiarObra);
			botones.getChildren().add(addObra);
			botones.getChildren().add(removeObra);
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
			Obra.addObra(obra);
			obra.setVisible(true);
			eliminarObra(obra);
			
		}
	
	}
	class EliminarObraHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			eliminarObra(obra);
		}
	
	}
	public static Label graficar(Comentario o) {
    	Label cont = new Label(o.getContenido());
    	cont.setPadding(new Insets(20,20,20,20));
    	cont.maxWidth(300);
    	cont.setStyle("-fx-background-color: #BF7BF3");
    	return cont;
    }
    
	public static void eliminarObra(Obra o) {
		for(int i = 0; i<Administrador.getObrasPendientes().size();i++) {
			if((o.getTitulo()+o.getAutor()).equals(Administrador.getObrasPendientes().get(i).getTitulo()+Administrador.getObrasPendientes().get(i).getAutor())) {
				Administrador.getObrasPendientes().remove(i);
				PaneInteraccion.setPaneActual(listarObraGraficaPendiente());
				break;
			}
		}
	}
	
	public static FlowPane listarObraGraficaPendiente() {
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		Hashtable<BorderPane,Obra> tabla = new Hashtable<BorderPane,Obra>(); 
		for(Obra i:Administrador.getObrasPendientes()) {
			BorderPane a = graficar(i,2);
			tabla.put(a, i);
			pane.getChildren().add(a);
			
		}
		PaneInteraccion.setAux(tabla);
		return pane;
	}
	
	
	
	class AbrirObraHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			BorderPane a = new BorderPane();
			a.setStyle("-fx-background-color: #F94978");
			BorderPane graf = (BorderPane) arg0.getSource(); 
			a.setPadding(new Insets(20,20,20,20));
			Obra obr = PaneInteraccion.getAux().get(graf);
			Label titulo = new Label(obr.getTitulo());
			a.setTop(titulo);
			Label image = new Label(obr.rutaImagen());
			image.setPrefWidth(300);
			a.setLeft(image);
			VBox descripcion = new VBox(10);
			
			descripcion.getChildren().add(new Label("Descripción:"));
			descripcion.getChildren().add(new Label(obr.getDescripcion()));
			descripcion.getChildren().add(new Label("Altura:"));
			descripcion.getChildren().add(new Label(Double.toString(obr.getAltura())));
			descripcion.getChildren().add(new Label("Ancho:"));
			descripcion.getChildren().add(new Label(Double.toString(obr.getAncho())));
			descripcion.getChildren().add(new Label("Autor:"));
			descripcion.getChildren().add(new Label(obr.getAutor().toString()));
			descripcion.getChildren().add(new Label("Fecha creación:"));
			descripcion.getChildren().add(new Label(obr.getFechaCreacion().toString()));
			descripcion.getChildren().add(new Label("Tecnica:"));
			descripcion.getChildren().add(new Label(obr.getTecnica().getNombre()));
			a.setCenter(descripcion);
			
			FlowPane etiq = new FlowPane();
			for(Etiqueta i:obr.getEtiquetas()) {
				etiq.getChildren().add(graficar(i));
			}
			
			
			Button com = new AgregarComentario().graficar();
			Button rep = new AgregarReporte().graficar();
			Button eti = new AgregarEtiqueta().graficar();
			
			FlowPane botones = new FlowPane();
			botones.setPadding(new Insets(20,20,20,20));
			AgregarReporteHandler handlerReporte = new AgregarReporteHandler();
			AgregarComentarioHandler handlerComentario = new AgregarComentarioHandler();
			AgregarEtiquetaHandler handlerEtiqueta = new AgregarEtiquetaHandler();
			
			eti.setOnAction(handlerEtiqueta);
			com.setOnAction(handlerComentario);
			rep.setOnAction(handlerReporte);
			
			botones.getChildren().add(com);
			botones.getChildren().add(rep);
			botones.getChildren().add(eti);
			if(!(PaneInteraccion.getTipoUsuario()==new Invitado().descripcion() )) {
				rep= new Button("Borrar");
				Button act = new ActualizarObra().graficar();
				ActualizarHandler handlerActualizar = new ActualizarHandler();
				BorrarObraHandler handlerBorrarObra = new BorrarObraHandler();
				rep.setOnAction(handlerBorrarObra);
				act.setOnAction(handlerActualizar);
				botones.getChildren().add(act);
			}
			
			VBox contenedor = new VBox();
			contenedor.getChildren().add(botones);
			contenedor.getChildren().add(etiq);
			for(Comentario i: obr.getComentarios()) {
				contenedor.getChildren().add(graficar(i));
			}
			a.setBottom(contenedor);
			PaneInteraccion.setPaneActual(a);
			
			
		}
		
		
		
	}
	public static Pane graficar(Etiqueta e) {
		VBox a = new VBox();
		a.setPadding(new Insets(2,2,2,2));
		a.setSpacing(2);
		a.getChildren().add(new Label(e.getLabel()));
		a.getChildren().add(new Label(e.getTipo()));
		a.getChildren().add(new Label(e.getDescripcion()));
		return a;
	}
	class AgregarReporteHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(obra);
			try {
				new AgregarReporte().ejecutar();
			} catch (NoCoincideTamano | CantBeNull e) {
				e.printStackTrace();
			}
			
		}
		
	}
	class ActualizarHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(obra);
			try {
				new ActualizarObra().ejecutar();
			} catch (NoCoincideTamano | CantBeNull e) {
				e.printStackTrace();
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
			
			FieldPanel.setAux(obra);
			try {
				new AgregarComentario().ejecutar();
			} catch (NoCoincideTamano | CantBeNull e) {
				e.printStackTrace();
			}
			
		}
		
	}
	class AgregarEtiquetaHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			
			FieldPanel.setAux(obra);
			try {
				new AgregarEtiqueta().ejecutar();
			} catch (NoCoincideTamano | CantBeNull e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}