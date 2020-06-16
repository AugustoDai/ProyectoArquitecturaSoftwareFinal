package planes;

import entidades.CDR;
import tarifaciones.ITarifacion;

public class PlanPrepago implements IPlan    {

	@Override
	public double calcularCostoDeLlamada(ITarifacion tarifacion,CDR cdr) {
		return tarifacion.calcularCoste(cdr);

	}

	@Override
	public String getTipoDePlan()
	{	
		return "PREPAGO";
	}
}
