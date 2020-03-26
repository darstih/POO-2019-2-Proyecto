package Excepciones;

public class ErrorCampoVacio extends ErrorLogico{
	public ErrorCampoVacio(String var){
		super(var +" Es un campo obligatorio");
	}
}
