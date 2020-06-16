package tarifaciones;

import entidades.CDR;

public interface ITarifacion {

	public double calcularCoste(CDR cdr);
	public String getTipoDeTarifacion();
}
