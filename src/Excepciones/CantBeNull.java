package Excepciones;

public class CantBeNull extends Exception{
	public CantBeNull(String var){
		super(var +": no puede ser Null");
	}
}
