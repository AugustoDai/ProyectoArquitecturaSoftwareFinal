package tarifaciones;

import entidades.CDR;

public class TarifacionDiferenciadaPorHorario implements ITarifacion  {

	private static final double TARIFA_POR_MINUTO_HORARIO_NORMAL = 1.45;
	private static final double TARIFA_POR_MINUTO_HORARIO_REDUCIDO = 0.95;
	private static final double TARIFA_POR_MINUTO_HORARIO_SUPER_REDUCIDO = 0.70;
	@Override
	public double calcularCoste(CDR cdr){
		int hora = Integer.parseInt(cdr.getHora().split(":")[0]);
		if(hora>=7 && hora<21)
			return TARIFA_POR_MINUTO_HORARIO_NORMAL * cdr.getDuracionDeLlamadaEnMinutos();
		if(hora>=21)
			return TARIFA_POR_MINUTO_HORARIO_REDUCIDO * cdr.getDuracionDeLlamadaEnMinutos();
		if(hora >=0 &&hora<1)
			return TARIFA_POR_MINUTO_HORARIO_REDUCIDO * cdr.getDuracionDeLlamadaEnMinutos();
		if(hora>=1 && hora< 7)
			return TARIFA_POR_MINUTO_HORARIO_SUPER_REDUCIDO * cdr.getDuracionDeLlamadaEnMinutos();
		return 0;
	}
	@Override
	public String getTipoDeTarifacion()
	{
		return "DIFERENCIADA POR HORARIO";
	}
}
