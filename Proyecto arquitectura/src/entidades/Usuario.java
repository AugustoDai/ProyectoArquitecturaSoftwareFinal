package entidades;


import planes.IPlan;
import tarifaciones.ITarifacion;

public class Usuario {

	private String nombre;
	private int ci,numeroTelefonoMovil;
	IPlan plan;
	ITarifacion tarifacion;
	
	public Usuario(String nombre, int ci, int numeroTelefonoMovil) {
		this.nombre = nombre;
		this.ci = ci;
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}
	
	public void setCi(int ci) {
		this.ci = ci;
	}
	public int getCi() {
		return ci;
	}
	
	public void setNumeroTelefonoMovil(int numeroTelefonoMovil) {
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}
	public int getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}
	
	public void setPlan(IPlan plan) {
		this.plan = plan;
	}
	public IPlan getPlan() {
		return plan;
	}
	
	public void setTarifacion(ITarifacion tarifacion) {
		this.tarifacion = tarifacion;
	}
	public ITarifacion getTarifacion() {
		return tarifacion;
	}
	
}
