package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entidades.CDR;
import planes.IPlan;
import planes.PlanPostpago;
import planes.PlanPrepago;
import planes.PlanWow;
import tarifaciones.ITarifacion;
import tarifaciones.TarifacionFijaPorMinuto;

public class TestPlanes {
	@Test
	public void DeberiaRetornarPostpagoComoTipoDePlan(){
		IPlan planPostpago = new PlanPostpago();
		assertEquals("POSTPAGO", planPostpago.getTipoDePlan());
	}
	@Test
	public void DeberiaRetorna1punto45AlCalcularElCosteDeLLamadaConTarifacionFijaPorMinuto(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:01:00",2.2);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		IPlan planPostpago = new PlanPostpago();
		assertEquals(1.45, planPostpago.calcularCostoDeLlamada(tarifacionFijaPorMinuto, cdr));
	}
	
	@Test
	public void DeberiaRetornarPrepagoComoTipoDePlan(){
		IPlan planPrepago = new PlanPrepago();
		assertEquals("PREPAGO", planPrepago.getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetorna2punto90AlCalcularElCosteDeLLamadaConTarifacionFijaPorMinuto(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:02:00",2.2);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		IPlan planPrepago = new PlanPrepago();
		assertEquals(2.9, planPrepago.calcularCostoDeLlamada(tarifacionFijaPorMinuto, cdr));
	}
	
	@Test
	public void DeberiaRetornarWowComoTipoDePlan(){
		IPlan planWow = new PlanWow();
		assertEquals("WOW", planWow.getTipoDePlan());
	}
	
	@Test
	public void DeberiaRetorna2punto90AlCalcularElCosteDeLLamadaConTarifacionFijaPorMinutoNoNumeroAmmigo(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:02:00",0.0);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		IPlan planWow = new PlanWow();
		ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
		numerosAmigos.add(70768374);
		((PlanWow) planWow).setNumerosAmigos(numerosAmigos);
		assertEquals(2.9, planWow.calcularCostoDeLlamada(tarifacionFijaPorMinuto, cdr));
	}
	
	@Test
	public void DeberiaRetorna0AlCalcularElCosteDeLLamadaConTarifacionFijaPorMinutoNumeroAmmigo(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","07:14","00:02:00",0.0);
		ITarifacion tarifacionFijaPorMinuto = new TarifacionFijaPorMinuto();
		IPlan planWow = new PlanWow();
		ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
		numerosAmigos.add(70765342);
		((PlanWow) planWow).setNumerosAmigos(numerosAmigos);
		assertEquals(0, planWow.calcularCostoDeLlamada(tarifacionFijaPorMinuto, cdr));
	}
	
	@Test
	public void deberiaRetornarLaListaDeAmigos(){
		IPlan planWow = new PlanWow();
		ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
		numerosAmigos.add(70768374);
		((PlanWow) planWow).setNumerosAmigos(numerosAmigos);
		assertEquals("70768374", ((PlanWow) planWow).getNumeroAmigosParaString());
	}
	
	@Test
	public void deberiaRetornarSinNumerosAmigos(){
		IPlan planWow = new PlanWow();
		assertEquals("Sin numeros amigos", ((PlanWow) planWow).getNumeroAmigosParaString());
	}
	
}
