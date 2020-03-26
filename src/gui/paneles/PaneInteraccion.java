package gui.paneles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Usuario.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import gui.GraficadorObjetos;
import gui.opciones.OpcionDeMenu;
import gui.opciones.publico.Salir;





public class PaneInteraccion extends VBox{
	
	//Atributo
	private static BorderPane actual; 
	private static ArrayList<OpcionDeMenu> opciones;
	private static String tipousuario;
	private static Hashtable<String,Obra> auxiliar; 
	//Metodos
	
	
	//Getter y setter
	
	
	public static Hashtable<String,Obra> getAux() {
		return auxiliar;
	}
	public static void setAux(Hashtable<String,Obra> a) {
		auxiliar = a;
	}
	
	
	
	
	
	
	
	public static Pane getPaneActual() {
		return (Pane) actual.getCenter();
	}
	public static void setPaneActual(Pane panel) {
		actual.setCenter(panel);
	}
	public static String getTipoUsuario() {
		return tipousuario;
	}
	
	
	//Constructores

    
	@SuppressWarnings("static-access")
	public PaneInteraccion(String user,ArrayList<OpcionDeMenu> proccons,Usuario usu) {
		this.setPadding(new Insets(20,20,20,20));
		this.opciones = proccons;
		tipousuario = user;
		Label header = new Label("Usuario : "+user);
		header.setPadding(new Insets(3,3,3,3));
		
		this.getChildren().add(header);
		
		
		EjecutarOpcion handler = new EjecutarOpcion();
		
		MenuBar barra = new MenuBar();
		//-------------------------------------------------
		MenuItem usuario = new MenuItem("Usuario "+user);
		DescripcionUsuarioHandler handlerdesc = new DescripcionUsuarioHandler();
		usuario.setOnAction(handlerdesc);
		tipousuario=usu.descripcion();
		SeparatorMenuItem separador = new SeparatorMenuItem();
		MenuItem salir = new Salir().toMenu();
		VolverInicioHandler handlerInicio = new VolverInicioHandler();
		salir.setOnAction(handlerInicio);
		Menu archivo = new Menu("Archivo");
		archivo.getItems().addAll(usuario,separador,salir);
		//-------------------------------------------------
		Menu procesos = new Menu("Procesos y consultas");
		MenuItem a = proccons.get(0).toMenu();
		a.setOnAction(handler);
		procesos.getItems().add(a);
		
		
		
		for(int i=1;i<proccons.size();i++) {
			MenuItem menuA =proccons.get(i).toMenu();
			menuA.setOnAction(handler);			
			procesos.getItems().addAll(new SeparatorMenuItem(),menuA);
		}
		//-------------------------------------------------
		Menu ayuda = new Menu("Ayuda");
		AcercaDeHandler handler1 = new AcercaDeHandler();
		MenuItem k = new MenuItem("Acerca de");
		k.setOnAction(handler1);
		ayuda.getItems().add(k);
		
		
		
		barra.getMenus().add(archivo);
		barra.getMenus().add(procesos);
		barra.getMenus().add(ayuda);		
		
		
		
		
		
		
		actual = new BorderPane();
		actual.setTop(barra);
		
		
		
		
		actual.setCenter(GraficadorObjetos.panelBienvenida(user));
		this.getChildren().add(actual);
		
		
	}
	class EjecutarOpcion implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			Object var = arg0.getSource();
			for(OpcionDeMenu i: opciones) {
				if((((MenuItem) var).getText()).equals(i.toString())) {
					try {
						i.ejecutar();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoCoincideTamano e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CantBeNull e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	class VolverInicioHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			new Salir().ejecutar();
		}
		
	}
	class AcercaDeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Acerca de ");
			a.setTitle("Autores");
			a.setContentText("Alejandro Bedoya Taborda \nDarwin Stiven Herrera Cartagena \nDavid Antonio Aristizabal Giraldo \nAndor Flander");
			a.initStyle(StageStyle.UTILITY);
			a.showAndWait();
		}
		
	}
	
	class DescripcionUsuarioHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Informacion sobre usuario: ");
			a.setContentText(tipousuario);
			a.initStyle(StageStyle.UTILITY);
			a.showAndWait();
		}
		
	}

}
