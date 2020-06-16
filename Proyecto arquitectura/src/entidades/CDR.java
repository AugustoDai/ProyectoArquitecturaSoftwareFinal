package entidades;
public class CDR {
	private int telefonoOrigen, telefonoDestino;
	private String fecha, hora, tiempoDuracion;
	private double costo;
	
	public CDR(int telefonoOrigen,int telefonoDestino,String fecha,String hora,String tiempoDuracion,double costo){
		this.telefonoOrigen = telefonoOrigen;
		this.telefonoDestino = telefonoDestino;
		this.fecha = fecha;
		this.hora = hora;
		this.tiempoDuracion = tiempoDuracion;
		this.costo=costo;
	}
	
	

	public int getTelefonoDestino() {
		return telefonoDestino;
	}

	public String getFecha() {
		return fecha;
	}
	
	public int getTelefonoOrigen() {
		return telefonoOrigen;
	}
	
	public String getMes() {
		return fecha.split("/")[1];
	}

	public String getHora() {
		return hora;
	}

	public String getTiempoDuracion() {
		return tiempoDuracion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo=costo;
	}
	
	public double getDuracionDeLlamadaEnMinutos()
	{
		String[] duracion = tiempoDuracion.split(":");
		double tiempoEnMinutos= (Double.parseDouble(duracion[0])*60)+(Double.parseDouble(duracion[1]));
		if(duracion.length==3)
		{
			tiempoEnMinutos+=(Double.parseDouble(duracion[2])/100);
		}
		return tiempoEnMinutos;
	}
}