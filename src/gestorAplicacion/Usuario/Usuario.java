package gestorAplicacion.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import gestorAplicacion.Excepciones.ExcepcionFueraRango;
import gestorAplicacion.Obras.Obra;
import gui.paneles.PaneInteraccion;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;


//Autor clase y estructura Darwin Herrera
public abstract class Usuario{

	//Atributos


	// listado = 1 | ordena las obras por su fecha de creacion, de mas antiguo a mas nuevo
	// listado = 2 | ordena por relevancia, siendo comentarios su criterio de relevancia mas comentarios a menos
	// listado = 3 | ordena las obras por su fecha de ingreso al sistema, de mas antiguo a mas nuevo
	private static ArrayList<Obra> listarObra(ArrayList<Obra> listaO, int listado){
		if(listado ==1){
			Collections.sort(listaO,(o1,o2) -> {
				return o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
			});
			return listaO;
		}else if(listado ==2){
			Collections.sort(listaO,(o1,o2) -> {
				return Integer.compare(o1.devolverCantComentarios()*-1,o2.devolverCantComentarios()*-1);
			});
			return listaO;
		}else if(listado == 3){
			Collections.sort(listaO,(o1,o2) -> {
				return o1.getFechaIngreso().compareTo(o2.getFechaIngreso());
			});
			return listaO;
		}
		return listaO;
	}

	public abstract String descripcion(); 



	public static FlowPane listarObraGrafica(ArrayList<Obra> obras,int listado) {
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		obras= listarObra(obras,listado);
		Hashtable<BorderPane,Obra> tabla = new Hashtable<BorderPane,Obra>(); 
		for(Obra i:obras) {
			BorderPane a = i.graficar(1);
			tabla.put(a, i);
			pane.getChildren().add(a);
			
		}
		PaneInteraccion.setAux(tabla);
		return pane;
	}
	//Autor Darwin Herrera
	private static FlowPane buscarObraPorTecnica(String var,int listado){//eficiencia O(n)
		ArrayList<Obra> a = Obra.getObras();
		ArrayList<Obra> res=new ArrayList<Obra>();
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getTecnica().getNombre()==var) {
				res.add(a.get(i));
			}
		}
		return listarObraGrafica(res,listado);
	}
	private static FlowPane buscarObraPorTitulo(String var,int listado){//eficiencia O(n)
		ArrayList<Obra> a = Obra.getObras();
		ArrayList<Obra> res=new ArrayList<Obra>();
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getTitulo()==var) {
				res.add(a.get(i));
			}
		}
		return listarObraGrafica(res,listado);
	}








	// listado = 1 | ordena las obras por su fecha de creacion, de mas antiguo a mas nuevo
	// listado = 2 | ordena por relevancia, siendo comentarios su criterio de relevancia mas comentarios a menos
	// listado = 3 | ordena las obras por su fecha de ingreso al sistema, de mas antiguo a mas nuevo

	//Autor Darwin Herrera
	public static FlowPane buscarObra(String var,int i,int listado) throws ExcepcionFueraRango{
		try {
			if(i==0) {
				return buscarObraPorTecnica(var,listado);
			}else if(i==1) {
				return buscarObraPorTitulo(var,listado);
			}else {
				throw new ExcepcionFueraRango();
			}
		}catch(ExcepcionFueraRango e) {
			return null;
		}
	}
	//Metodos

	//Getters y setters

	//Constructores
	public Usuario() {

	}

}
