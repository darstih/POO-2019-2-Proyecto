package gestorAplicacion.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import Excepciones.ErrorFueraRango;
import gestorAplicacion.Obras.Etiqueta;
import gestorAplicacion.Obras.Obra;
import gestorAplicacion.Obras.Tecnica;
import gui.GraficadorObjetos;
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
	
	public static ArrayList<Obra> obraConMasReportes(){
		ArrayList<Obra> obras=new ArrayList<>();
		String artistamasusado = null;
		String artista;
		String titulomasusado = null;
		String titulo;
		int numerodeuso=0;
		for(int i=0;i<Administrador.getReportesObra().size();i++) {
			String[] id=Administrador.getReportesObra().get(i).getObjetoReporte().IdUnico().split(" ");
			artista=id[0];
			titulo=id[1];
			int uso=0;
			for(int l=0;l<Obra.getCantObras();l++) {
				if(artista.equalsIgnoreCase(Obra.getObras().get(l).getAutor()) && titulo.equalsIgnoreCase(Obra.getObras().get(l).getTitulo())) {
					uso++;
				}
			
			}
			if(uso>numerodeuso) {
				artistamasusado=artista;
				titulomasusado=titulo;
			}
		}
		for(int l=0;l<Obra.getCantObras();l++) {
			if(artistamasusado.equalsIgnoreCase(Obra.getObras().get(l).getAutor()) && titulomasusado.equalsIgnoreCase(Obra.getObras().get(l).getTitulo())) {
				obras.add(Obra.getObras().get(l));
			}
		
		}
		return obras;
	}
	public static FlowPane listarObraGrafica(ArrayList<Obra> obras,int listado) {
		FlowPane pane = new FlowPane();
		pane.setVgap(10);
		pane.setHgap(10);
		obras= listarObra(obras,listado);
		Hashtable<String,Obra> tabla = new Hashtable<String,Obra>();
		int cont = 0;
		for(Obra i:obras) {
			BorderPane a = GraficadorObjetos.graficar(i,1,""+cont);
			tabla.put(""+cont, i);
			cont++;
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
			if(a.get(i).getTecnica().getNombre().equalsIgnoreCase(var)) {
				res.add(a.get(i));
			}
		}
		return listarObraGrafica(res,listado);
	}
	private static FlowPane buscarObraPorTitulo(String var,int listado){//eficiencia O(n)
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
	public static FlowPane buscarObra(String var,int i,int listado) throws ErrorFueraRango{
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
