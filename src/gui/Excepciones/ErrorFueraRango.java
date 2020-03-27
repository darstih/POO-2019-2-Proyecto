package gui.Excepciones;
//Autor Darwin Herrera
public class ErrorFueraRango extends ErrorExistencia{
	public ErrorFueraRango(String campo) {
        super(campo+" No exste en el sistema");
    }
}
