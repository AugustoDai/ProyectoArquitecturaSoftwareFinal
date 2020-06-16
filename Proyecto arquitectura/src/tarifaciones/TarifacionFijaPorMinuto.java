package tarifaciones;


import entidades.CDR;

public class TarifacionFijaPorMinuto implements ITarifacion  {

	private static final double TARIFA_FIJA_POR_MINUTO_USUARIO_REGULAR = 1.45;
	private double tarifaPorMinuto;
	
	public TarifacionFijaPorMinuto(){
		this.tarifaPorMinuto=TARIFA_FIJA_POR_MINUTO_USUARIO_REGULAR;;
	}
	
	@Override
	public double calcularCoste(CDR cdr) {
		return tarifaPorMinuto * cdr.getDuracionDeLlamadaEnMinutos();
	}
	
	public void establecerMontoDeTarifaFijaPorMinuto(double monto){
		tarifaPorMinuto = monto;
	}
	@Override
	public String getTipoDeTarifacion()
	{
		return "FIJA POR MINUTO";
	}
}
