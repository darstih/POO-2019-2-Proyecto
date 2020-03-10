package uiMain.menuConsola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;

public abstract class OpcionDeMenu{ // La clase abstracta obligatoria
	
	protected Scanner in = new Scanner(System.in);
	
	
	abstract public MenuDeConsola ejecutar() throws JsonGenerationException, JsonMappingException, IOException;
	
	
	protected Obra obraSelec(ArrayList<Obra> ao) {
		System.out.println("Interfaz de vista de obras...");
		System.out.println("Seleccione una obra: ");
		if(ao == null) {
			ao = Obra.getObras();
		}
		
		if(ao.isEmpty()) {
			System.out.println("No hay obras");
			return null;
		}else {
		for(int i = 0;i<ao.size();i++) {
			System.out.println(i+". "+ao.get(i).getTitulo()+" del autor "+ao.get(i).getAutor());
		}
		int opcion=in.nextInt();
		return(ao.get(opcion));}
	}
	
	protected Etiqueta mostrarEtiquetas(Obra o) {
		for(int i =0;i<o.getEtiquetas().size();i++) {
			System.out.println(i+". "+o.getEtiquetas().get(i).getLabel());
		}
		Etiqueta e=o.getEtiquetas().get(in.nextInt());
		return e;
	}
	protected 	Obra formObra() {
		System.out.println("Agregando Obra, por favor ingresa los siguientes atributos:");
		
		System.out.println("T\u00EDtulo:");
		String titulo = in.nextLine();
		titulo += in.nextLine();
		
		System.out.println("Descripci\u00F3n:");
		String descripcion = in.nextLine();
		
		System.out.println("Altura:");
		double altura = in.nextDouble();
		
		System.out.println("Ancho:");
		double ancho = in.nextDouble();
		
		System.out.println("T\u00E9cnica:");
		String tec = in.nextLine();
		tec += in.nextLine();
		Tecnica tecnica = new Tecnica(tec);
		
		System.out.println("Autor:");
		String autor = in.nextLine();
		
		Obra nuevaObra = new Obra(titulo, descripcion, altura, ancho, Calendar.getInstance(), new ArrayList<Etiqueta>(), tecnica, autor);
		return nuevaObra;
	}
	public void asignar(MenuDeConsola atras,MenuDeConsola actual) {
		actual.setAtras(atras);
		atras.setSiguiente(actual);
	}
}
