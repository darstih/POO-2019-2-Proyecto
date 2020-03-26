package gestorAplicacion.Obras;

import java.util.ArrayList;

//Autor Darwin Herrera
public class Etiqueta {
	//Atributos
	private String tipo;
	private String descripcion;
	private String label;
	private int cantObras=0;
	
	private static ArrayList<Etiqueta> listaEtiquetas = new ArrayList<Etiqueta>();
	public Etiqueta() {}
	//Metodos
	
	
	//Getters y setters
	public String getTipo(){
		return tipo;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setLabel(String label){
		this.label=label;
	}

	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}

	public String getLabel(){
		return label;
	}

	public void setTipo(String tipo){
		this.tipo=tipo;
	}
	public void setCantObras(int cantObras) {
		this.cantObras = cantObras;
	}
	public int getCantObras(){
		return cantObras;
	}
	public static void setEtiquetas(ArrayList<Etiqueta> et) {
		listaEtiquetas = et;
	}
	public static ArrayList<Etiqueta> getEtiquetas(){
		return listaEtiquetas;
	}
	public void aumentarCantObras() {
		this.cantObras++;
	}
	//Constructores
	public Etiqueta(String label, String tipo, String descripcion) {
		this.label = label;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	public Etiqueta(String labol, String tipo) {
		new Etiqueta(labol, tipo,"Sin descripción");
	}
	public static void setEtiqueta(Etiqueta etiqueta) {
		listaEtiquetas.add(etiqueta);
	}
	public static void aumentarContador(Etiqueta etiqueta) {
		for(int i = 0; i<listaEtiquetas.size();i++) {
			if(listaEtiquetas.get(i).getLabel().equalsIgnoreCase(etiqueta.getLabel())) {
				int aux = listaEtiquetas.get(i).getCantObras()+1;
				listaEtiquetas.get(i).setCantObras(aux);;
				break;
			}
		}
	}
	
	//Aca verificamos si una etiqueta ya existe
	public static boolean verificarEtiqueta(Etiqueta etiqueta){
		boolean retornar = false;
		for (int i=0;i<listaEtiquetas.size();i++){
			if(etiqueta.getLabel().equalsIgnoreCase(listaEtiquetas.get(i).getLabel())){
				retornar=true;			
				break;
			}
		}
		return retornar;
	}
}
