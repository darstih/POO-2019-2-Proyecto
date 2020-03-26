package gestorAplicacion.Obras;
//Autor Darwin Herrera
public class Tecnica {
	// Atributos
	private String nombre;
	static int cantObras = 0;// cantidad de obras con esta tecnica
	private String descripcion;
	// Metodos
	
	// Getters y setters
	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
 	}

	public int getCantObras() {
		return this.cantObras;
	}
	void setCantObras(int a) {
		this.cantObras=a;
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
