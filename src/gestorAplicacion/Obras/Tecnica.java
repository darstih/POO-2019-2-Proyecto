package gestorAplicacion.Obras;

import java.util.ArrayList;

//Autor Darwin Herrera
public class Tecnica {
	// Atributos
	private String nombre;
	private int cantObras = 0;// cantidad de obras con esta tecnica
	private String descripcion;
	private static ArrayList<Tecnica> tecnicas = new ArrayList<Tecnica>();
	// Metodos
	
	// Getters y setters
	public static void setTecnicas(ArrayList<Tecnica> te) {
		tecnicas = te;
	}
	public static ArrayList<Tecnica> getTecnicas(){
		return tecnicas;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
 	}

	public int getCantObras() {
		return this.cantObras;
	}
	public void setCantObras(int a) {
		this.cantObras=a;
	}
	public void aumentarObras() {
		int cont = getCantObras()+1;
		setCantObras(cont);
	}
	public void setNombre(String a) {
		this.nombre = a;
	}

	public void setDescripcion(String a) {
		this.descripcion = a;
	}

	// Constructores
	public Tecnica(String nombre, String desc) {
		this.nombre = nombre;
		descripcion = desc;
	}
	public Tecnica() {
		
	}
	public Tecnica(String n) {
		this(n, "Sin descripción");
	}

	public String toString() {
		return nombre;
	}
}
