package Excepciones;

public class ErrorDimensionReal extends ErrorLogico{
	public ErrorDimensionReal() {
		super("El ancho y/o alto debe ser un valor posible en el mundo real");
	}
}
