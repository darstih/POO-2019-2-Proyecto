package gestorAplicacion.Interacciones;


//Autor Darwin Herrera
public class Reporte{
    //Atributos
    
    private String motivo;
    private ObjetoReporte objetoReporte;
    private boolean pendiente = true;//desde que se crea la instancia empieza a estar pendiente
    
    
    //Metodos
    
	
    
	
    //Getters y setters
    public String getMotivo(){
	return this.motivo;
    }
    public boolean getPendiente(){
	return this.pendiente;
    }
    public ObjetoReporte getObjetoReporte(){
	return this.objetoReporte;
    }
    public void setMotivo(String m){
	this.motivo = m;
    }
    public void setPendiente(boolean p){
	this.pendiente = p;
    }
    public void setObjetoReporte(ObjetoReporte o){
	this.objetoReporte = o;
    }
    
    //Constructores
    public Reporte(String motivo,ObjetoReporte objetoReporte) {
	this.motivo = motivo;
	this.objetoReporte = objetoReporte;
    }
}
