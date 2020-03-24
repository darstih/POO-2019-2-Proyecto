package uiMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamaño;
import baseDatos.DBObra;
import baseDatos.DBUsuario;
import gestorAplicacion.Usuario.Administrador;
import gui.paneles.IndexPanel;
import gui.paneles.PaneInteraccion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uiMain.menuConsola.OpcionDeMenu;
import uiMain.menuConsola.opciones.OpcionAgregarComentario;
import uiMain.menuConsola.opciones.OpcionSalir;
import uiMain.menuConsola.opciones.administrador.OpcionAgregarObra;
import uiMain.menuConsola.opciones.administrador.OpcionListarObrasPendientes;
import uiMain.menuConsola.opciones.invitado.OpcionBuscarObras;
import uiMain.menuConsola.opciones.invitado.OpcionEnviarObra;
import uiMain.menuConsola.opciones.invitado.OpcionListarObras;

public class Main  extends Application{
	static Scene  main1,main2;
	static Stage pantalla;
	
	public static void main (String [ ] args) throws JsonParseException, JsonMappingException, IOException {
		DBObra.inicializar();
		DBUsuario.inicializar();
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception,JsonParseException, JsonMappingException, IOException {
		pantalla = primaryStage;
		main1 = new Scene(new IndexPanel(),600,600);
		pantalla.setScene(main1);
		pantalla.show();
	}
	public void stop() throws JsonGenerationException, JsonMappingException, IOException {
		new OpcionSalir().ejecutar();
	}
	public static void cambiarScene(String invitado) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new OpcionBuscarObras());
		op.add(new OpcionListarObras());
		op.add(new OpcionEnviarObra());
		
		
		main2 = new Scene(new PaneInteraccion("Invitado",op),600,600);
		pantalla.setScene(main2);
		pantalla.show();
	}
	public static void cambiarScene(String usu, String pass) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new OpcionBuscarObras());
		op.add(new OpcionListarObras());
		op.add(new OpcionAgregarObra());
		op.add(new OpcionListarObrasPendientes());
		main2 = new Scene(new PaneInteraccion(usu,op),600,600);
		pantalla.setScene(main2);
		pantalla.show();
	}
}
