package entidades;

import planes.IPlan;
import planes.PlanPostpago;
import planes.PlanPrepago;
import planes.PlanWow;
import tarifaciones.ITarifacion;
import tarifaciones.TarifacionDiferenciadaPorHorario;
import tarifaciones.TarifacionFijaPorMinuto;

public class FabricaDePlanesYTarifas  {
	
	public IPlan getPlan(String tipoDePlan) {
		
		switch(tipoDePlan)
		{
		case "PREPAGO":
			return new PlanPrepago();
		case "POSTPAGO":
			return new PlanPostpago();
		case "WOW":
			return new PlanWow();
		default:
			return null;
		}
	}


	public ITarifacion getTarifacion(String tipoDeTarifa) {
		switch(tipoDeTarifa)
		{
		case "DIFERENCIADA POR HORARIO":
			return new TarifacionDiferenciadaPorHorario();
		case "FIJA POR MINUTO":
			return new TarifacionFijaPorMinuto();
	
		default:
			return null;
		}
	}
}
