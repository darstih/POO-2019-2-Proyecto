package gui.paneles;
import java.util.ArrayList;
import uiMain.menuConsola.OpcionDeMenu;
import java.util.Hashtable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamaño;
import gestorAplicacion.Obras.Obra;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class FieldPanel extends Pane {
	
	//Atributos
	private Hashtable<String,TextField> valores = new Hashtable<String,TextField>();
	private VBox root; 
	private static ArrayList<Obra> auxO ;
	private OpcionDeMenu opcion;
	private Button borrar;
	private Button aceptar;
	
	
	//Metodos
	public VBox graficar() {
		return root;
	}
	

	//getter y setter
	public Button getBAceptar() {
		return aceptar;
	}
	public static ArrayList<Obra> getAuxObra() {return auxO;}
	public static void setAuxObra(ArrayList<Obra> arrayList) {auxO = arrayList;}
	
	public String getValue(String criterio) {
		return valores.get(criterio).getAccessibleText();
	}

	
	//Constructores
	private FieldPanel(OpcionDeMenu opcion,String tituloCriterios, String[] criterios, String tituloValores, String[] valores, boolean[] habilitado) throws NoCoincideTamaño, CantBeNull{
		this.opcion = opcion;
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
				this.aceptar = new Button();
				this.borrar = new Button();
				root = new VBox();
				root.getChildren().add((new Label(this.opcion.toString())));
				GridPane panel = new GridPane();
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
				
				root.getChildren().add(panel);
				root.setPadding(new Insets(10,10,10,10));
				this.getChildren().add(root);		
				
				
				borrar.setOnAction(listenerBorrar);
				
				
			}else {
				throw new NoCoincideTamaño();
			}
		}
		
	}
	
	public FieldPanel(OpcionDeMenu titulo,String[] criterios,String[] valores,boolean[] habilitado) throws NoCoincideTamaño, CantBeNull {
		new FieldPanel(titulo,"Criterio",criterios,"valor",valores,habilitado);
	}
	public FieldPanel(OpcionDeMenu titulo,String[] criterios,String[] valores) throws NoCoincideTamaño, CantBeNull {
		new FieldPanel(titulo,criterios,valores,null);
	}
	public FieldPanel(OpcionDeMenu titulo,String[] criterios) throws NoCoincideTamaño, CantBeNull {
		new FieldPanel(titulo,criterios,null);
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
