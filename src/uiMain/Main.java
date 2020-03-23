package uiMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import baseDatos.DBObra;
import baseDatos.DBUsuario;
import gui.paneles.IndexPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uiMain.menuConsola.opciones.OpcionSalir;

public class Main  extends Application{
	Scene main1,main2;
	
	public static void main (String [ ] args) throws JsonParseException, JsonMappingException, IOException {
		DBObra.inicializar();
		DBUsuario.inicializar();
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception,JsonParseException, JsonMappingException, IOException {
		main1 = new Scene(new IndexPanel(),600,600);
		primaryStage.setScene(main1);
		primaryStage.show();
		
		
		
	}
	public void stop() throws JsonGenerationException, JsonMappingException, IOException {
		new OpcionSalir().ejecutar();
	}
}
