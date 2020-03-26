package Excepciones;

public class ErrorAplicacion extends Exception {
	public ErrorAplicacion(String mensaje) {
		super("Manejo de errores de la Aplicación: "+mensaje);
	}
}
