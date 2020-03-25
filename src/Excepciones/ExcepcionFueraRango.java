package Excepciones;
//Autor Darwin Herrera
public class ExcepcionFueraRango extends Exception{
	public ExcepcionFueraRango() {
        super("Error: Elegiste una opción que no está establecida");
    }
}
