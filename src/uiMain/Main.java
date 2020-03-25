package uiMain;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import baseDatos.DBObra;
import baseDatos.DBUsuario;
import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import gui.paneles.IndexPanel;
import gui.paneles.PaneInteraccion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uiMain.menuConsola.OpcionDeMenu;
import uiMain.menuConsola.opciones.administrador.AgregarObra;
import uiMain.menuConsola.opciones.administrador.ListarObrasPendientes;
import uiMain.menuConsola.opciones.publico.BuscarObras;
import uiMain.menuConsola.opciones.publico.EnviarObra;
import uiMain.menuConsola.opciones.publico.ListarObras;

public class Main  extends Application{
	static Scene  main1,main2;
	static Stage pantalla;
	
	public static void setScene(Scene a) {
		pantalla.setScene(a);
	}
	
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
		DBObra.guardar();
		DBUsuario.guardar();
	}
	public static void cambiarScene(String invitado) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new BuscarObras());
		op.add(new ListarObras());
		op.add(new EnviarObra());
		main2 = new Scene(new PaneInteraccion("Invitado",op,new Invitado()),600,600);
		pantalla.setScene(main2);
		pantalla.show();
	}
	public static void cambiarScene(String usu, String pass) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new BuscarObras());
		op.add(new ListarObras());
		op.add(new AgregarObra());
		op.add(new ListarObrasPendientes());
		main2 = new Scene(new PaneInteraccion(usu,op,new Administrador()),600,600);
		pantalla.setScene(main2);
		pantalla.setTitle(usu);
		pantalla.show();
	}
}
