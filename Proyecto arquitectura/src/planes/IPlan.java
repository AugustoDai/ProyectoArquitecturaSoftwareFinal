package planes;

import entidades.CDR;
import tarifaciones.ITarifacion;

public interface IPlan {

	public double calcularCostoDeLlamada(ITarifacion tarifacion,CDR cdr);
	public String getTipoDePlan();
}
