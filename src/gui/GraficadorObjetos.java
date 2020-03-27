package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;

import Excepciones.ErrorCampoVacio;
import Excepciones.ErrorObraRepetida;
import gestorAplicacion.Interacciones.Comentario;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import gui.opciones.botones.ActualizarObra;
import gui.opciones.botones.AgregarComentario;
import gui.opciones.botones.AgregarEtiqueta;
import gui.paneles.FieldPanel;
import gui.paneles.PaneInteraccion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class GraficadorObjetos {
	private static Obra obra;
	
	public static BorderPane graficar(Obra a,int opcion,String indice) {
		obra = a;
		GraficadorObjetos graficador = new GraficadorObjetos();
		AbrirObraHandler eve = graficador.new AbrirObraHandler();
		BorderPane obraGrafica = new BorderPane();
		obraGrafica.setStyle("-fx-background-color: #F94978");
		Image imgFoto=null;
		try {
			imgFoto = new Image(new FileInputStream("../../Imagenes/404.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView imagen = new ImageView();
		imagen.setImage(imgFoto);
		imagen.setFitWidth(170);
		imagen.setFitHeight(120);
		//Label imagen = new Label(obra.rutaImagen())
		obraGrafica.setOnMouseClicked(eve);
		obraGrafica.setId(indice);
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
			addCom.setId(indice);
			AgregarEtiquetaHandler et = graficador.new AgregarEtiquetaHandler();
			Button addEti = new Button("+Etiqueta");
			addEti.setId(indice);
			addEti.setOnAction(et);
			botones.getChildren().add(addCom);
			botones.getChildren().add(addEti);
		}else if(opcion==2) {
			Button addObra = new Button("Aprobar");
			addObra.setId(indice);
			AprobarObraHandler ap = graficador.new AprobarObraHandler();
			addObra.setOnAction(ap);
			EliminarObraHandler elimiarObra = graficador.new EliminarObraHandler();
			Button removeObra = new Button("Eliminar");
			removeObra.setId(indice);
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
			String titulo = "";
			Label respuesta = new Label();
			respuesta.setWrapText(true);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			Button graf = (Button)event.getSource();
			Obra obr = PaneInteraccion.getAux().get(graf.getId());
			try {
				Administrador.agregarObra(obr);
				titulo = "Obra agregada correctamente";
				respuesta.setText("La obra se agrego correctamente");
				Administrador.eliminarObraPendiente(obr);
				obr.setVisible(true);
			} catch (ErrorObraRepetida e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
			}finally {
				dialogo.setTitle(titulo);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				PaneInteraccion.setPaneActual(listarObraGraficaPendiente());
			}
		}
	
	}
	class EliminarObraHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			String titulo = "Obra agregada correctamente";
			Label respuesta = new Label("La obra se agrego correctamente");
			respuesta.setWrapText(true);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			dialogo.setTitle(titulo);
			dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.showAndWait();
			Button graf = (Button)event.getSource();
			Obra obr = PaneInteraccion.getAux().get(graf.getId());
			Administrador.eliminarObraPendiente(obr);
			PaneInteraccion.setPaneActual(listarObraGraficaPendiente());
		}
	
	}
	public static Label graficar(Comentario o) {
    	Label cont = new Label(o.getContenido());
    	cont.setPadding(new Insets(20,20,20,20));
    	cont.maxWidth(300);
    	cont.setStyle("-fx-background-color: #BF7BF3");
    	return cont;
    }
    
	public static ScrollPane listarObraGraficaPendiente() {
		ScrollPane panef = new ScrollPane();
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		int cont = 0;
		Hashtable<String,Obra> tabla = new Hashtable<String,Obra>(); 
		for(Obra i:Administrador.getObrasPendientes()) {
			BorderPane a = graficar(i,2,""+cont);
			tabla.put(""+cont, i);
			cont++;
			pane.getChildren().add(a);
			
		}
		PaneInteraccion.setAux(tabla);
		panef.setContent(pane);
		return panef;
	}
	
	public static VBox panelBienvenida(String user) {
		Label titulo = new Label("Bienvenido "+user+" a Művezárt");
		titulo.setPadding(new Insets(40,40,40,40));
		titulo.setStyle("-fx-font-size: 15pt;");
		titulo.setAlignment(Pos.CENTER);
		Label descripcion;
		if(user=="Invitado") {
			descripcion = new Label("Esta es una simulación virtual de una galeria de arte\n"
					+ "por lo tanto podrás ver, buscar, comentar y etiquetar las obras que \n"
					+ "están dentro de nuestra base de datos\n\n"
					+ "Dentro del menú de procesos y consultas podrá encontrar las siguientes opciones" 
					+ "\n -Buscar una obra."
					+ "\n -Ver todas las obras."
					+ "\n -Ver las obras con la tecnica mas popular."
					+ "\n -Ver las obras con la etiqueta mas popular."
					+ "\n -Ver las obras del artista mas popular."
					+ "\n -Ver las obras mas populares."
					+ "\n -Postular obra");
			descripcion.setWrapText(true);
		}else {
			descripcion = new Label(user+" usted es un usuario administrador\n"
					+ "Dentro del menú de procesos y consultas podrá encontrar las siguientes opciones"
					+ "\n -Buscar una obra."
					+ "\n -Agregar una obra."
					+ "\n -Mostrar Obras pendientes."
					+ "\n -Ver todas las obras."
					+ "\n -Ver las obras con la tecnica mas popular."
					+ "\n -Ver las obras con la etiqueta mas popular."
					+ "\n -Ver las obras del artista mas popular."
					+ "\n -Ver las obras mas populares."
					+ "\n Ademas de las interacciones basicas: "
					+ "\n -Agregar etiquetas de una obra."
					+ "\n -Agregar comentarios de una obra.");
			descripcion.setWrapText(true);
		}
		descripcion.setAlignment(Pos.TOP_LEFT);
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().add(titulo);
		pane.getChildren().add(descripcion);
		return pane;
		
	}
	
	class AbrirObraHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			BorderPane a = new BorderPane();
			a.setStyle("-fx-background-color: #F94978");
			BorderPane graf = (BorderPane) arg0.getSource(); 
			a.setPadding(new Insets(20,20,20,20));
			Obra obr = PaneInteraccion.getAux().get(graf.getId());
			Label titulo = new Label(obr.getTitulo());
			a.setTop(titulo);
			Image imgFoto=null;
			try {
				imgFoto = new Image(new FileInputStream("../../Imagenes/404.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageView image = new ImageView();
			image.setImage(imgFoto);
			image.setFitWidth(280);
			image.setFitHeight(250);
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
			com.setId(graf.getId());
			Button eti = new AgregarEtiqueta().graficar();
			eti.setId(graf.getId());
			FlowPane botones = new FlowPane();
			botones.setPadding(new Insets(20,20,20,20));
			AgregarComentarioHandler handlerComentario = new AgregarComentarioHandler();
			AgregarEtiquetaHandler handlerEtiqueta = new AgregarEtiquetaHandler();
			
			eti.setOnAction(handlerEtiqueta);
			com.setOnAction(handlerComentario);
			
			botones.getChildren().add(com);
			botones.getChildren().add(eti);
			if(!(PaneInteraccion.getTipoUsuario()==new Invitado().descripcion() )) {
				Button act = new ActualizarObra().graficar();
				ActualizarHandler handlerActualizar = new ActualizarHandler();
				BorrarObraHandler handlerBorrarObra = new BorrarObraHandler();
				act.setOnAction(handlerActualizar);
				act.setId(graf.getId());
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
		return a;
	}
	class ActualizarHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			Button btn = (Button)arg0.getSource();
			String titulo = "";
			Label respuesta = new Label();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			Obra obr = PaneInteraccion.getAux().get(btn.getId());
			FieldPanel.setAux(obr);
			try {
				new ActualizarObra().ejecutar();
				//titulo = "Obra agregada correctamente";
				//respuesta.setText("Cuando la apruebe un administrador será exitosamente agregada.");
			} catch ( ErrorCampoVacio e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
				dialogo.setTitle(titulo);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
				
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
			Button graf = (Button)arg0.getSource();
			Obra obr = PaneInteraccion.getAux().get(graf.getId());
			FieldPanel.setAux(obr);
			String titulo = "";
			Label respuesta = new Label();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				new AgregarComentario().ejecutar();
				//titulo = "Obra agregada correctamente";
				//respuesta.setText("Cuando la apruebe un administrador será exitosamente agregada.");
			} catch (ErrorCampoVacio e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
			}
			
		}
		
	}
	class AgregarEtiquetaHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			Button graf = (Button)arg0.getSource();
			Obra obr = PaneInteraccion.getAux().get(graf.getId());
			FieldPanel.setAux(obr);
			String titulo = "";
			Label respuesta = new Label();
			Alert dialogo = new Alert(AlertType.INFORMATION);
			try {
				new AgregarEtiqueta().ejecutar();
				//titulo = "Obra agregada correctamente";
				//respuesta.setText("Cuando la apruebe un administrador será exitosamente agregada.");
				//dialogo.setTitle(titulo);
				//dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				//dialogo.initStyle(StageStyle.UTILITY);
				//dialogo.showAndWait();
			} catch ( ErrorCampoVacio e) {
				titulo = "ERROR";
				respuesta.setText(e.getMessage());
				dialogo.setAlertType(AlertType.ERROR);
				dialogo.setTitle(titulo);
				dialogo.getDialogPane().setContent(respuesta);//se hace asi para que muestre todo el texto
				dialogo.initStyle(StageStyle.UTILITY);
				dialogo.showAndWait();
			}
			
		}
		
	}
	
}
