package gui.Excepciones;
//Autor David Aristizabal Giraldo
public class ErrorCampoVacio extends ErrorLogico{
	public ErrorCampoVacio(String var){
		super(var +" Es un campo obligatorio");
	}
}
