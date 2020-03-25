package Excepciones;

public class NoCoincideTamano extends Exception{
	public NoCoincideTamano(){
		super("Los tamaños de las listas ingresadas no coinciden");
	}
}
