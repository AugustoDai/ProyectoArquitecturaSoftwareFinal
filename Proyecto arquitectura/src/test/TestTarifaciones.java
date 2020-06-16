package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import tarifaciones.ITarifacion;
import tarifaciones.TarifacionDiferenciadaPorHorario;
import tarifaciones.TarifacionFijaPorMinuto;



public class TestTarifaciones {
	@Test
	public void DeberiaRetornarDiferenciadaPorHorarioComoTipoDeTarifacion(){
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals("DIFERENCIADA POR HORARIO", tarifacionDiferenciadaPorHorario.getTipoDeTarifacion());
	}
	
	@Test
	public void DeberiaRetornar2Punto9ComoCosteALas7amPor2Minutos(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:02:00",0.0);
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals(2.9, tarifacionDiferenciadaPorHorario.calcularCoste(cdr));
	}
	
	@Test
	public void DeberiaRetornar1Punto9ComoCosteALas21HrsPor2Minutos(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","21:14","00:02:00",0.0);
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals(1.9, tarifacionDiferenciadaPorHorario.calcularCoste(cdr));
	}
	@Test
	public void DeberiaRetornar1Punto9ComoCosteALas00HrsPor1Minutos(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","00:14","00:01:00",0.0);
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals(0.95, tarifacionDiferenciadaPorHorario.calcularCoste(cdr));
	}
	
	@Test
	public void DeberiaRetornar0ComoCosteALasmenos21HrsPor1Minutos(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","-21:14","00:01:00",0.0);
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals(0, tarifacionDiferenciadaPorHorario.calcularCoste(cdr));
	}
	@Test
	public void DeberiaRetornar1Punto4ComoCosteALas2amPor2Minutos(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","02:14","00:02:00",0.0);
		ITarifacion tarifacionDiferenciadaPorHorario = new TarifacionDiferenciadaPorHorario();
		assertEquals(1.4, tarifacionDiferenciadaPorHorario.calcularCoste(cdr));
	}
	
	@Test
	public void DeberiaRetornarFijaPorMinutoComoTipoDeTarifacion(){
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		assertEquals("FIJA POR MINUTO", tarifacionFijaPorMinuto.getTipoDeTarifacion());
	}
	
	@Test
	public void DeberiaRetornar4Punto35ComoCoste(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:03:00",0.0);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		assertEquals(4.35, tarifacionFijaPorMinuto.calcularCoste(cdr));
	}
	
	@Test
	public void DeberiaRetornar4Punto9ComoCosteTrasEstablecerTarifa2punto45PorMinuto(){
		
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:02:00",0.0);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		((TarifacionFijaPorMinuto) tarifacionFijaPorMinuto).establecerMontoDeTarifaFijaPorMinuto(2.45);
		assertEquals(4.9, tarifacionFijaPorMinuto.calcularCoste(cdr));
	}
	
	
}
