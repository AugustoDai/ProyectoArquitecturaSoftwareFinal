package test;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import entidades.FabricaDePlanesYTarifas;
import planes.IPlan;
import tarifaciones.ITarifacion;

public class TestFabricaDePlanesYTarifas {

	@Test
	public void DeberiaRetornarPlanPREPAGO(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		IPlan plan = fabrica.getPlan("PREPAGO");
		assertEquals("PREPAGO", plan.getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetornarPlanPOSTPAGO(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		IPlan plan = fabrica.getPlan("POSTPAGO");
		assertEquals("POSTPAGO", plan.getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetornarPlanWOW(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		IPlan plan = fabrica.getPlan("WOW");
		assertEquals("WOW", plan.getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetornarPlanNull(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		assertNull(fabrica.getPlan("asd"));
	}
	@Test
	public void DeberiaRetornarTarificacionDIFERENCIADAPORHORARIO(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		ITarifacion tarificacion = fabrica.getTarifacion("DIFERENCIADA POR HORARIO");
		assertEquals("DIFERENCIADA POR HORARIO", tarificacion.getTipoDeTarifacion());
	}
	
	@Test
	public void DeberiaRetornarTarificacionFIJAPORMINUTO(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		ITarifacion tarificacion = fabrica.getTarifacion("FIJA POR MINUTO");
		assertEquals("FIJA POR MINUTO",tarificacion.getTipoDeTarifacion());
	}
	
	
	@Test
	public void DeberiaRetornarTarificacionNull(){
		FabricaDePlanesYTarifas fabrica= new FabricaDePlanesYTarifas();
		assertNull(fabrica.getTarifacion("asd"));
	}
}
