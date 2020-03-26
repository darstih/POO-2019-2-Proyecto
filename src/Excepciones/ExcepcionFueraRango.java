package Excepciones;
//Autor Darwin Herrera
public class ExcepcionFueraRango extends ErrorExistencia{
	public ExcepcionFueraRango() {
        super("El campo que tratas de buscar no exite");
    }
}
