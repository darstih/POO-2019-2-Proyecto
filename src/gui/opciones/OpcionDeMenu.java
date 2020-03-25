package gui.opciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import Excepciones.CantBeNull;
import Excepciones.NoCoincideTamano;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gui.paneles.FieldPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;

public abstract class OpcionDeMenu{ // La clase abstracta obligatoria
	
	private FieldPanel auxiliar;
	
	
	
	public abstract void ejecutar() throws JsonGenerationException, JsonMappingException, IOException, NoCoincideTamano, CantBeNull ;
	
	
	
	public MenuItem toMenu() {
		MenuItem a = new MenuItem(this.toString());
		return a;
	}
	
	
	protected FlowPane mostrarEtiquetas(Obra o) {
		FlowPane panel = new FlowPane();
		for(Etiqueta i:o.getEtiquetas()) {
			panel.getChildren().add(i.graficar());
		}
		return panel;
	}
	
	
	protected FieldPanel formularioObra() throws NoCoincideTamano, CantBeNull {
		String[] criterios = new String[] {"Titulo","Descripcion","Altura","Ancho","Tecnica","Autor"};
		CrearObraHandlerClass listener = new CrearObraHandlerClass();
		return  new FieldPanel(this,"criterios",criterios,"valores",null,null,listener);
	}
	
	
	class CrearObraHandlerClass implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			new Obra(auxiliar.getValue("Titulo"), auxiliar.getValue("Descripcion"), Double.parseDouble(auxiliar.getValue("Altura")), Double.parseDouble(auxiliar.getValue("Ancho")), Calendar.getInstance(), new ArrayList<Etiqueta>(),new Tecnica( auxiliar.getValue("tecnica")), auxiliar.getValue("autor"),true);
		}
	}
	
}
