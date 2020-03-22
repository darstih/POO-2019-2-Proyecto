package gui.paneles;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class IndexPanel extends Application{
		
	private static String[] pathImagenes = {"","","","",""};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane p0 = new GridPane();
		
		GridPane p1 = new GridPane();
		GridPane p2 = new GridPane();
		BorderPane p3 = new BorderPane();
		GridPane p4 = new GridPane();
		MenuBar barraMenu = new MenuBar();
		Menu inicio = new Menu("Inicio");
		MenuItem msalir = new MenuItem("Salir");
		MenuItem mdescripcion = new MenuItem("Descripcion");
		
		inicio.getItems().add(mdescripcion);		
		inicio.getItems().add(msalir);

		
		barraMenu.getMenus().add(inicio);
		
		p1.setMinSize(300, 200);
		p1.setMaxSize(300, 200);
		p2.setMinSize(300, 400);;
		p2.setMaxSize(300, 400);
		p3.setMinSize(300, 200);
		p3.setMaxSize(300, 200);
		p4.setMinSize(300, 400);
		p4.setMaxSize(300, 400);
		
		
		
		
	//	p1.setStyle("-fx-background-color: #2196f3;");
	//	p2.setStyle("-fx-background-color: #ff9800;");
	//	p3.setStyle("-fx-background-color: #9e9e9e;");
//		p4.setStyle("-fx-background-color: #795548;");
		
		VBox P5 = new VBox(p1,p2);
		
		VBox P6 = new VBox(p3,p4);
		
		p0.add(P5,0,0);
		p0.add(P6,1,0);
		
		Label lblLogin = new Label("Iniciar seccion ");
		Label lblUsu = new Label("Usuario: ");
		Label lbli = new Label("¿No tienes cuenta?");
		Label lblPass = new Label("Contraseña: ");
		Label lblsaludo = new Label("Bienvenido a Művezárt");
		TextField txtUsu = new TextField();
		PasswordField pssfUsu = new PasswordField();
		Button btnAceptar = new Button("Aceptar");
		Button btnInvitado = new Button("Acceder como invitado");
		Image imagen = new Image(getClass().getResourceAsStream("/../monalisa.jpg"));
		ImageView imga = new ImageView(imagen);

		btnAceptar.setMaxWidth(Double.MAX_VALUE);
		btnInvitado.setMaxWidth(Double.MAX_VALUE);
		lblLogin.setMaxWidth(Double.MAX_VALUE);
		lbli.setMaxWidth(Double.MAX_VALUE);
		lbli.setAlignment(Pos.CENTER);
		lblLogin.setAlignment(Pos.CENTER);
		lblsaludo.setFont(new Font(18));
		
		p1.add(imga, 0, 0);
		p3.setRight(barraMenu);
		p3.setCenter(lblsaludo);
		p4.add(lblLogin,0,0,2,1);	
		p4.add(lblUsu,0,1);	
		p4.add(lblPass,0,2);
		p4.add(txtUsu,1,1);
		p4.add(pssfUsu,1,2);
		p4.add(btnAceptar,0,3,2,1);
		p4.add(lbli,0,4,2,1);	
		p4.add(btnInvitado,0,5,2,1);
		p4.setVgap(30);
		p4.setPadding(new Insets(10,10,10,10));
		
		Scene a = new Scene(p0,600,600);
		primaryStage.setScene(a);
		primaryStage.show();
	}
    public static void main(String[] args) {
    	launch(args);
    	
    }
}
