package gui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import baseDatos.DBEtiqueta;
import baseDatos.DBObra;
import baseDatos.DBTecnica;
import baseDatos.DBUsuario;
import gui.paneles.IndexPanel;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main  extends Application{
	static Scene  main1,main2;
	static Stage pantalla;
	
	public static void setScene(Scene a) {
		pantalla.setScene(a);
	}

	
	public static void main (String [ ] args) {
		
		launch(args);
	}
	public void init() throws JsonParseException, JsonMappingException, IOException {
		DBObra.inicializar();//Todas las obras que hay en el sistema
		DBUsuario.inicializar();//Todos los administradores del sistema
		DBEtiqueta.inicializar();//Todas las etiquetas que hay en las obras (sin repeticion)
		DBTecnica.inicializar();
	}
	@Override
	public void start(Stage primaryStage) throws Exception,JsonParseException, JsonMappingException, IOException {
		pantalla = primaryStage;
		GridPane bi = new GridPane();
		ArrayList<ColumnConstraints> a = new ArrayList<ColumnConstraints>();
		a.add(new ColumnConstraints());a.get(0).setPercentWidth(100/3);
		a.add(new ColumnConstraints());a.get(1).setPercentWidth(100/3);
		a.add(new ColumnConstraints());a.get(2).setPercentWidth(100/3);
		ArrayList<RowConstraints> b = new ArrayList<RowConstraints>();
		b.add(new RowConstraints());b.get(0).setPercentHeight(100/3);
		b.add(new RowConstraints());b.get(1).setPercentHeight(100/3);
		b.add(new RowConstraints());b.get(2).setPercentHeight(100/3);
		bi.getColumnConstraints().addAll(a);
		bi.getRowConstraints().addAll(b);
		Label hola = new Label("Bienvenidos a la \ngaleria de arte");
		hola.setAlignment(Pos.CENTER);
		bi.add(hola, 1, 1);
		bi.setAlignment(Pos.CENTER);
		Scene saludo = new Scene(bi,600,600);
		setScene(saludo);
		primaryStage.show();
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
					main1 = new Scene(new IndexPanel(),600,600);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                pantalla.setScene(main1);
            }
        });
        new Thread(sleeper).start();
		
	}
	public void stop() throws JsonGenerationException, JsonMappingException, IOException {
		DBObra.guardar();
		DBUsuario.guardar();
		DBEtiqueta.guardar();
		DBTecnica.guardar();
	}
	
}
