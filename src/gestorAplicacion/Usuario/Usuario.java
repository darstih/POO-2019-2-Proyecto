package gestorAplicacion.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import gui.Excepciones.ErrorFueraRango;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gui.GraficadorObjetos;
import gui.paneles.PaneInteraccion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;


//Autor clase y estructura Darwin Herrera
public abstract class Usuario{

	//Atributos
	//Autor David Aristizabal Giraldo
	// listado = 1 | ordena las obras por su fecha de creacion, de mas antiguo a mas nuevo
	// listado = 2 | ordena por relevancia, siendo comentarios su criterio de relevancia mas comentarios a menos
	// listado = 3 | ordena las obras por su fecha de ingreso al sistema, de mas antiguo a mas nuevo
	public static ArrayList<Obra> listarObra(ArrayList<Obra> listaO, int listado, int nove){
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
			ArrayList<Obra> obraf = new ArrayList<Obra>();
			int top = listaO.size();
			//Aca es para retornar las obras mas nuevas, no todas
			if(nove==-1) {
				top = 3;
			}
			
			for(int i = listaO.size()-1;i>=0;i--) {
				obraf.add(listaO.get(i));
				if(nove==-1 && obraf.size()==3) {
					break;
				}
			}
			return obraf;
		}
		return listaO;
	}

	public abstract String descripcion(); 
	//Funciones interesantes
	//Autor: Alejandro Bedoya
	public static ArrayList<Obra> tecnicaMasUsada() {
		ArrayList<Obra> obras=new ArrayList<>();
		int numerodeuso=0;
		Tecnica tecnicamasusada = null;
		Tecnica tecnica;
		ArrayList<Tecnica> listatecnicas= Tecnica.getTecnicas();
		for(int i=0;i<listatecnicas.size();i++) {
			tecnica=listatecnicas.get(i);
			if(tecnica.getCantObras()>numerodeuso) {
				tecnicamasusada=tecnica;
				numerodeuso=tecnica.getCantObras();
			}
			
		}
		for(int i=0;i<Obra.getCantObras();i++) {
			Obra obra=Obra.getObras().get(i);
			if(tecnicamasusada.getNombre().equalsIgnoreCase(obra.getTecnica().getNombre())) {
				obras.add(obra);
			}
		}
		return obras;
	}

	//Funciones interesantes
	//Autor: Alejandro Bedoya
	public static ArrayList<Obra> etiquetaMasPopular(){
		ArrayList<Obra> obras=new ArrayList<>();
		Etiqueta etiquetamasusada = null;
		Etiqueta etiqueta;
		int numerodeuso=0;
		for(int i=0;i<Etiqueta.getEtiquetas().size();i++) {
			etiqueta=Etiqueta.getEtiquetas().get(i);
			if(etiqueta.getCantObras()>numerodeuso) {
				etiquetamasusada=etiqueta;
				numerodeuso=etiqueta.getCantObras();
			}
		}
		for(int i=0;i<Obra.getCantObras();i++) {
			Obra obra=Obra.getObras().get(i);
			for(int l=0; l<obra.getEtiquetas().size();l++) {
				if(etiquetamasusada.getLabel().equalsIgnoreCase(obra.getEtiquetas().get(l).getLabel())) {
					obras.add(obra);
				}
			}
			
		}
		return obras;
	}
	//Funciones interesantes
	//Autor: Alejandro Bedoya
	public static ArrayList<Obra> artistaMasPopular(){
		ArrayList<Obra> obras=new ArrayList<>();
		String artistamasusado = "";
		String artista;
		int numerodeuso=0;
		for(int i=0;i<Obra.getCantObras();i++) {
			int uso=0;
			artista=Obra.getObras().get(i).getAutor();
			for(int l=0;l<Obra.getCantObras();l++) {
				if(artista.equalsIgnoreCase(Obra.getObras().get(l).getAutor())) {
					uso++;
				}
			}
			if(uso>numerodeuso) {
				artistamasusado=artista;
				numerodeuso = uso;
			}
		}
		for(int i=0;i<Obra.getCantObras();i++) {
			if(artistamasusado.equalsIgnoreCase(Obra.getObras().get(i).getAutor())) {
				obras.add(Obra.getObras().get(i));
			}
		}
		return obras;
	}
	
	public static ScrollPane listarObraGrafica(ArrayList<Obra> obras,int listado) {
		ScrollPane panef = new ScrollPane();
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		obras= listarObra(obras,listado,1);
		Hashtable<String,Obra> tabla = new Hashtable<String,Obra>();
		int cont = 0;
		for(Obra i:obras) {
			BorderPane a = GraficadorObjetos.graficar(i,1,""+cont);
			tabla.put(""+cont, i);
			cont++;
			pane.getChildren().add(a);
			
		}
		PaneInteraccion.setAux(tabla);
		panef.setContent(pane);
		return panef;
	}
	public static FlowPane listarObraGraficaReportes(ArrayList<Obra> obras) {
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		Hashtable<String,Obra> tabla = new Hashtable<String,Obra>();
		int cont = 0;
		for(Obra i:obras) {
			BorderPane a = GraficadorObjetos.graficar(i,2,""+cont);
			tabla.put(""+cont, i);
			cont++;
			pane.getChildren().add(a);
		}
		PaneInteraccion.setAux(tabla);
		return pane;
	}
	
	//Autor Darwin Herrera
	private static ScrollPane buscarObraPorTecnica(String var,int listado){//eficiencia O(n)
		ArrayList<Obra> a = Obra.getObras();
		ArrayList<Obra> res=new ArrayList<Obra>();
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getTecnica().getNombre().equalsIgnoreCase(var)) {
				res.add(a.get(i));
			}
		}
		return listarObraGrafica(res,listado);
	}
	private static ScrollPane buscarObraPorTitulo(String var,int listado){//eficiencia O(n)
		ArrayList<Obra> a = Obra.getObras();
		ArrayList<Obra> res=new ArrayList<Obra>();
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getTitulo().equalsIgnoreCase(var)) {
				res.add(a.get(i));
			}
		}
		return listarObraGrafica(res,listado);
	}

	// listado = 1 | ordena las obras por su fecha de creacion, de mas antiguo a mas nuevo
	// listado = 2 | ordena por relevancia, siendo comentarios su criterio de relevancia mas comentarios a menos
	// listado = 3 | ordena las obras por su fecha de ingreso al sistema, de mas antiguo a mas nuevo

	//Autor Darwin Herrera
	public static ScrollPane buscarObra(String var,int i,int listado) throws ErrorFueraRango{
		try {
			if(i==0) {
				return buscarObraPorTecnica(var,listado);
			}else if(i==1) {
				return buscarObraPorTitulo(var,listado);
			}else {
				throw new ErrorFueraRango("Parametro");
			}
		}catch(ErrorFueraRango e) {
			return null;
		}
	}
	//Metodos

	//Getters y setters

	//Constructores
	public Usuario() {

	}

}
