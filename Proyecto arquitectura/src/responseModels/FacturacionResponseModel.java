package responseModels;

import entidades.Usuario;

public class FacturacionResponseModel {

	String nombre,plan,fecha;
	int numeroTelefonico,ci;
	double montoAPagar;
	public FacturacionResponseModel(Usuario usuario,double monto,String fecha)
	{
		this.nombre = usuario.getNombre();
		this.ci= usuario.getCi();
		this.numeroTelefonico=usuario.getNumeroTelefonoMovil();
		this.plan=usuario.getPlan().getTipoDePlan();
		this.montoAPagar = monto;
		this.fecha = fecha;
	}
	public String getNombre()
	{
		return nombre;
	}
}
