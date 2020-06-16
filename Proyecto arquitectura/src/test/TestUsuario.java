package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import entidades.Usuario;
import planes.IPlan;
import planes.PlanWow;
import tarifaciones.ITarifacion;
import tarifaciones.TarifacionFijaPorMinuto;
public class TestUsuario {
	
	@Test
	public void DeberiaRetornarJuanJudas() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		assertEquals( "Juan Judas",usuario.getNombre());
	}
	@Test
	public void DeberiaRetornarMarcelPuente() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		usuario.setNombre("Marcelo Puente");
		assertEquals("Marcelo Puente", usuario.getNombre());
	}
	@Test
	public void DeberiaRetornar83925956DeNumeroDeTelefono() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		assertEquals(83925956, usuario.getNumeroTelefonoMovil());
	}
	@Test
	public void DeberiaRetornar79372469DeNumeroDeTelefono() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		usuario.setNumeroTelefonoMovil(79372469);
		assertEquals(79372469, usuario.getNumeroTelefonoMovil());
	}
	@Test
	public void DeberiaRetornar1324561203ComoCi() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		assertEquals(1324561203, usuario.getCi());
	}
	@Test
	public void DeberiaRetornar123456789ComoCi() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		usuario.setCi(123456789);
		assertEquals(123456789, usuario.getCi());
	}
	
	@Test
	public void DeberiaRetornarPlanWOWSiSeteoEsePlan() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		IPlan plan = new PlanWow();
		usuario.setPlan(plan);
		assertEquals("WOW", usuario.getPlan().getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetornarLaTarifacionFijaPorMinuto() {
		Usuario usuario= new Usuario("Juan Judas",1324561203,83925956);
		ITarifacion tarifacion = new TarifacionFijaPorMinuto();
		usuario.setTarifacion(tarifacion);
		assertEquals("FIJA POR MINUTO", usuario.getTarifacion().getTipoDeTarifacion());
	}
	
}
