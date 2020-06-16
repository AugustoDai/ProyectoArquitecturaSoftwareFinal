package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entidades.CDR;

public class TestCDR {

	@Test
	public void DeberiaRetornar70743567ComoTelefonoDeOrigenCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		assertEquals(70743567, cdr.getTelefonoOrigen());
	}
	@Test
	public void DeberiaRetornar70765342ComoTelefonoDestinoCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		assertEquals(70765342, cdr.getTelefonoDestino());
	}
	@Test
	public void DeberiaRetornar101019ComoFechaCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		assertEquals("10/10/19", cdr.getFecha());
	}
	
	@Test
	public void DeberiaRetornar2024ComoHoraCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","20:24","00:02:30",2.2);
		assertEquals("20:24", cdr.getHora());
	}
	
	@Test
	public void DeberiaRetornar0235ComoTiempoDuracionCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		assertEquals("00:02:30", cdr.getTiempoDuracion());
	}
	
	@Test
	public void DeberiaRetornar0ComoCostoCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",0.0);
		assertEquals(0.0, cdr.getCosto());
	}
	@Test 
	public void DeberiaRetornar5ComoCostoCDRSiModificoElCostoA5(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		cdr.setCosto(5);
		assertEquals(5.0, cdr.getCosto());
	}
	@Test 
	public void DeberiaRetornar2punto30ComoDuracionDeLlamadaEnMinutos(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2);
		assertEquals(2.30, cdr.getDuracionDeLlamadaEnMinutos());
	}
	@Test 
	public void DeberiaRetornar2ComoDuracionDeLlamadaEnMinutos(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","00:02",2.2);
		assertEquals(2, cdr.getDuracionDeLlamadaEnMinutos());
	}
	@Test 
	public void DeberiaRetornar60ComoDuracionDeLlamadaEnMinutos(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","01:00",2.2);
		assertEquals(60, cdr.getDuracionDeLlamadaEnMinutos());
	}
	@Test 
	public void DeberiaRetornar60punto2ComoDuracionDeLlamadaEnMinutos(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","01:00:20",2.2);
		assertEquals(60.2, cdr.getDuracionDeLlamadaEnMinutos());
	}
	@Test 
	public void DeberiaRetornar10ComoElMesDelCDR(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","01:00:20",2.2);
		assertEquals("10", cdr.getMes());
	}
	@Test 
	public void DeberiaRetornar70743567ComoNumeroDeOrigen(){
		CDR cdr = new CDR(70743567,70765342,"10/10/19","19:14","01:00:20",2.2);
		assertEquals(70743567, cdr.getTelefonoOrigen());
	}
}
