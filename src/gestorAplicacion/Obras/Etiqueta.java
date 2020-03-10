package gestorAplicacion.Obras;
//Autor Darwin Herrera
public class Etiqueta {
	//Atributos
	private String tipo;
	private String descripcion;
	private String label;
	int cantObras=0;

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

	public int getCantObras(){
		return cantObras;
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
	
}
