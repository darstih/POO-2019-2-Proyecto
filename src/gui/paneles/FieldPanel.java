	package gui.paneles;
	import uiMain.menuConsola.OpcionDeMenu;

import java.util.Enumeration;
import java.util.Hashtable;
	import Excepciones.CantBeNull;
	import Excepciones.NoCoincideTamaño;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.VBox;
	
	
	public class FieldPanel extends VBox {
		
		//Atributos
		private Hashtable<String,TextField> valores = new Hashtable<String,TextField>();
		private Button borrar;
		private static Object aux;
		//Metodos
		
		public static Object getAux() {
			return aux;
		}
		public static void setAux(Object a) {
			aux = a;
		}
		
		
		//getter y setter
		public String getValue(String criterio) {
			return valores.get(criterio).getText();
		}
		

		
		//Constructores
		public FieldPanel(OpcionDeMenu opcion,String tituloCriterios, String[] criterios, String tituloValores, String[] valores, boolean[] habilitado, EventHandler<ActionEvent> hand) throws NoCoincideTamaño, CantBeNull{
			this.setPadding(new Insets(20,20,20,20));
			this.setStyle("-fx-background-color: #E0EE97");
			Label titulo = new Label(opcion.toString());
			titulo.setAlignment(Pos.CENTER);
			titulo.setPadding(new Insets(10,10,10,10));
			this.getChildren().add(titulo);
			if(criterios == null) {
				throw new CantBeNull("criterios");
			}else {
				if(habilitado == null) {
					habilitado = new boolean[criterios.length];
					for(int k=0;k<criterios.length;k++) {
						habilitado[k] = true;
					}
				}
				if(valores== null) {
					valores = new String[criterios.length];
					for(int k=0;k<criterios.length;k++) {
						valores[k] = "";
					}
				}
				if(criterios.length == valores.length) {
					Label tcrt = new Label(tituloCriterios);
					Label tvl = new Label(tituloValores);				
					Button aceptar = new Button("Aceptar");
					
					aceptar.setOnAction(hand);
					this.borrar = new Button("Borrar");
					GridPane panel = new GridPane();
					//panel.setStyle("-fx-background-color: #E0EE97");
					panel.setHgap(10);
					panel.setVgap(10);
					panel.add(tcrt, 0, 0);
					panel.add(tvl, 1, 0);
					int i;
					for(i = 0; i< criterios.length;i++) {
						
						panel.add(new Label(criterios[i]), 0,i+1);
						
						this.valores.put(criterios[i],new TextField(valores[i]));
						panel.add(this.valores.get(criterios[i]), 1, i+1);
						if(!habilitado[i]) {
							this.valores.get(criterios[i]).setEditable(false);
						}
					}
					BorrarHandlerClass listenerBorrar = new BorrarHandlerClass();
					panel.add(aceptar, 0, i+1);
					panel.add(borrar, 1, i+1);
					panel.setAlignment(Pos.CENTER);
					this.borrar.setOnAction(listenerBorrar);
					
					
					this.getChildren().add(panel);
					
					
					
					
				}else {
					throw new NoCoincideTamaño();
				}
			}
			
		}
		
		
		//Inner class
		class BorrarHandlerClass implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent e) {
				for(TextField i :valores.values()) {
					i.setAccessibleText("");
				}
			}
		}
		
	}
