package uiMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import baseDatos.DBObra;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gui.paneles.IndexPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main  extends Application{
	Scene main1,main2;
	
	public static void main (String [ ] args) throws JsonParseException, JsonMappingException, IOException {
		DBObra.inicializar();			
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception,JsonParseException, JsonMappingException, IOException {
		main1 = new Scene(new IndexPanel(),600,600);
		primaryStage.setScene(main1);
		primaryStage.show();
		
		//System.out.println(Obra.getCantObras());
		// Para usar acentos y caracteres especiales en Strings:
		// https://www.freeformatter.com/java-dotnet-escape.html
		// Recordar que el '\' debe estar solo, ej. '\\' no sirve
		
		/*MenuDeConsola menu= MenuDeConsola.inicializar();
		while (true) {
			menu =  menu.lanzarMenu();
		}*/
		
	}
}
