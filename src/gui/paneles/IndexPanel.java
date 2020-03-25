package gui.paneles;

import java.util.ArrayList;

import gestorAplicacion.Usuario.Administrador;
import gestorAplicacion.Usuario.Invitado;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import gui.Main;
import gui.opciones.OpcionDeMenu;
import gui.opciones.administrador.AgregarObra;
import gui.opciones.administrador.ListarObrasPendientes;
import gui.opciones.publico.BuscarObras;
import gui.opciones.publico.EnviarObra;
import gui.opciones.publico.ListarObras;

public class IndexPanel extends GridPane{
		
	private static String[] pathImagenes = {"../../Imagenes/imagen1.jpg","../../Imagenes/imagen2.jpg",
			"../../Imagenes/imagen3.jpg","../../Imagenes/imagen4.jpg","../../Imagenes/imagen5.jpg"};
	private static String[] nombres = {"Darwin Stiven Herrera Cartagena",
			"Alejandro Bedoya Taborda    ","David Antonio Aristizabal Giraldo"}; 
	private static String[] edades = {"20","20","19"};
	private static String[] correos = {"dsherrerac@unal.edu.co","albedoyat@unal.edu.co","daaristizabalg@unal.edu.co"};
	private static String[] fechas = {"15/08/1999","24/09/1999","24/01/2001"};
	private static String[] pathFotos = {"../../Imagenes/fotoDarwin.jpeg",
			"../../Imagenes/fotoAlejandro.jpeg","../../Imagenes/fotoDavid.jpeg"};
	private static Label ltxtNombre,ltxtEdad,ltxtCorreo,ltxtFecha;
	private Image imgFoto,imagen; 
	private ImageView imgF,imga;
	private TextField txtUsu;
	private Label lblsaludo;
	private PasswordField pssfUsu;
	private static int cntI = 0;
	private static int cntD = 0;
	
	public IndexPanel()  {
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
		
		RowConstraints a = new RowConstraints();
		a.setPercentHeight(100/3);
		RowConstraints b = new RowConstraints();
		b.setPercentHeight(100*2/3);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(50);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(50);
		this.getRowConstraints().addAll(a,b);
		this.getColumnConstraints().addAll(c1,c2);
		
		this.add(p1,0,0);this.add(p3,1,0);
		this.add(p2,0,1);this.add(p4,1,1);
		
		//ELEMENTOS VALIDACION
		Label lblLogin = new Label("Iniciar sesión ");
		Label lblUsu = new Label("Usuario: ");
		Label lbli = new Label("¿No tienes cuenta?");
		Label lblPass = new Label("Contraseña: ");
		lblsaludo = new Label("Bienvenido a Művezárt");
		txtUsu = new TextField();
		pssfUsu = new PasswordField();
		Button btnAceptar = new Button("Aceptar");
		Button btnInvitado = new Button("Acceder como invitado");
		lblLogin.setFont(new Font(15));
		
		//ELEMENTOS HOJA DE VIDA
		imgFoto = new Image(getClass().getResourceAsStream(pathFotos[cntD%3]));
		imgF = new ImageView(imgFoto);
		Label lblCambiar = new Label("");
		Label lblNombre = new Label("Nombre");
		Label lblEdad = new Label("Edad");
		Label lblCorreo = new Label("Correo");
		Label lblFecha = new Label("Fecha de Nacimiento");
		ltxtNombre = new Label(nombres[cntD%3]);
		ltxtEdad = new Label(edades[cntD%3]);
		ltxtCorreo = new Label(correos[cntD%3]);
		ltxtFecha = new Label(fechas[cntD%3]);
		imgF.setFitWidth(100);
		imgF.setFitHeight(120);
		lblCambiar.setMaxHeight(Double.MAX_VALUE);
		lblCambiar.setMaxWidth(Double.MAX_VALUE);
		imgF.setStyle("-fx-background-color: #795548;");
		//lblCambiar.setStyle("-fx-background-color: #795548;");
		
		//p1.setStyle("-fx-background-color: #795548;");
		
		//ELEMENTOS IMAGENES ASOCIADAS
		imagen = new Image(getClass().getResourceAsStream(pathImagenes[cntI%5]));
		imga = new ImageView(imagen);
		imga.setFitWidth(250);
		imga.setFitHeight(200);
		

		btnAceptar.setMaxWidth(Double.MAX_VALUE);
		btnInvitado.setMaxWidth(Double.MAX_VALUE);
		lblLogin.setMaxWidth(Double.MAX_VALUE);
		lbli.setMaxWidth(Double.MAX_VALUE);
		lbli.setAlignment(Pos.CENTER);
		lblLogin.setAlignment(Pos.CENTER);
		lblsaludo.setFont(new Font(18));
		lblsaludo.setWrapText(true);
		
		
		p1.add(imga, 0, 0);
		
		//PANEL HOJA DE VIDA
		p2.add(imgF, 1, 0,2,1);
		p2.add(lblNombre, 0, 1,2,1);
		p2.add(ltxtNombre, 0, 2,2,1);
		p2.add(lblEdad, 0, 3,2,1);
		p2.add(ltxtEdad, 0, 4,2,1);
		p2.add(lblCorreo, 0, 5,2,1);
		p2.add(ltxtCorreo, 0, 6,2,1);
		p2.add(lblFecha, 0, 7,2,1);
		p2.add(ltxtFecha, 0, 8,2,1);
		p2.setVgap(15);
		p2.add(lblCambiar,0,0,7,10);
		p2.setPadding(new Insets(10,10,10,10));
		
		p3.setRight(barraMenu);
		p3.setCenter(lblsaludo);
		
		//PANEL LOGIN
		p4.add(lblLogin,0,0,2,1);	
		p4.add(lblUsu,0,1);	
		p4.add(lblPass,0,2);
		p4.add(txtUsu,1,1);
		p4.add(pssfUsu,1,2);
		p4.add(btnAceptar,0,3,2,1);
		p4.add(lbli,0,4,2,1);	
		p4.add(btnInvitado,0,5,2,1);
	
		p4.setPadding(new Insets(10,10,10,10));
		p4.setVgap(30);
		p4.setAlignment(Pos.CENTER);
		
		//ESCUCHADORES
		LblHandlerClassH lblHandlerClass = new LblHandlerClassH();
		btnHandlerClasssalir salir = new btnHandlerClasssalir();
		btnHandlerClassdesc desc = new btnHandlerClassdesc();
		ImgHandlerClassImg handlerFotos = new ImgHandlerClassImg();
		validarHandler validar = new validarHandler();
		invitadoHandler invitado = new invitadoHandler();
		lblCambiar.setOnMouseClicked(lblHandlerClass);
		mdescripcion.setOnAction(desc);
		imga.setOnMouseEntered(handlerFotos);
		msalir.setOnAction(salir);
		btnAceptar.setOnMouseClicked(validar);
		btnInvitado.setOnMouseClicked(invitado);
		
	}
	
	private class LblHandlerClassH implements EventHandler<Event>{
    	@Override
		public void handle(Event event) {
			cntD++;
			imgFoto = new Image(getClass().getResourceAsStream(pathFotos[cntD%3]));
			imgF.setImage(imgFoto);
			ltxtNombre.setText(nombres[cntD%3]);
			ltxtEdad.setText(edades[cntD%3]);
			ltxtFecha.setText(fechas[cntD%3]);
			ltxtCorreo.setText(correos[cntD%3]);
		}
    }
    
    private class ImgHandlerClassImg implements EventHandler<Event>{
    	@Override
		public void handle(Event event) {
    		cntI++;
    		imagen = new Image(getClass().getResourceAsStream(pathImagenes[cntI%5]));
    		imga.setImage(imagen);
    	}
    }
    
    private class btnHandlerClasssalir implements  EventHandler<ActionEvent>{
    	@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
    }
    private class btnHandlerClassdesc implements  EventHandler<ActionEvent>{
    	@Override
		public void handle(ActionEvent event) {
    		lblsaludo.setFont(new Font(14));
    		lblsaludo.setMaxHeight(200);
    		
    		lblsaludo.setText("Művezárt es un software que simula el funcionamiento de una galeria de arte,"
    				+ "los usuarios de Művezárt podran visualizar diferentes obras de arte con criticas de demas"
    				+ " usuarios del sistema");
		}
    }
    private class validarHandler implements  EventHandler<Event>{
		@Override
		public void handle(Event event) {
			if(Administrador.verificarAdmin(txtUsu.getText(),pssfUsu.getText())) {
				cambiarScene(txtUsu.getText(),pssfUsu.getText());
			}else {
				txtUsu.setStyle("-fx-border-color: #b71c1c;");
			}
		}
    }
    private class invitadoHandler implements  EventHandler<Event>{
		@Override
		public void handle(Event event) {
			cambiarScene("Invitado");
		}
    }
    public static void cambiarScene(String invitado) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new BuscarObras());
		op.add(new ListarObras());
		op.add(new EnviarObra());
		Scene main2 = new Scene(new PaneInteraccion("Invitado",op,new Invitado()),600,600);
		Main.setScene(main2);
	}
	public static void cambiarScene(String usu, String pass) {
		ArrayList<OpcionDeMenu> op = new ArrayList<OpcionDeMenu>();
		op.add(new BuscarObras());
		op.add(new ListarObras());
		op.add(new AgregarObra());
		op.add(new ListarObrasPendientes());
		Scene main2 = new Scene(new PaneInteraccion(usu,op,new Administrador()),600,600);
		Main.setScene(main2);
		
	}
}
