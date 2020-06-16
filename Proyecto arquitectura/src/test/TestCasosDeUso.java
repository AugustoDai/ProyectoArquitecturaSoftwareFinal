package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import cargarCdrsDeRepositorio.CargarCdrsDesdeRepositorio;
import cargarCdrsDeRepositorio.ICargarCdrsDesdeRepositorio;
import cargarUsuariosDeRepositorio.CargarUsuariosDesdeRepositorio;
import cargarUsuariosDeRepositorio.ICargarUsuariosDesdeRepositorio;
import entidades.CDR;
import entidades.Usuario;
import facturarParaUsuario.FacturarParaUsuario;
import facturarParaUsuario.IFacturarParaUsuario;
import guardarCdrsTarificados.GuardarCdrsTarificados;
import guardarCdrsTarificados.IGuardarCdrsTarificados;
import planes.PlanPostpago;
import planes.PlanWow;
import repository.IRepository;
import repository.JDBRepository;
import responseModels.FacturacionResponseModel;
import tarifaciones.TarifacionFijaPorMinuto;
import tarificar.ITarificar;
import tarificar.Tarificar;

public class TestCasosDeUso {

	//caso de uso cargar cdrs desde repositorio
	@Test
	public void DeberiaRetornar79372469ComoTelefonoDeOrigendelCDRCargadoDesdeElDB(){
		IRepository repositorio = new JDBRepository("TelcoDB.db");
		ICargarCdrsDesdeRepositorio casoDeUso = new CargarCdrsDesdeRepositorio(repositorio);
		assertEquals(79372469, casoDeUso.obtenerCDRS().get(0).getTelefonoOrigen());
	}
	//caso de uso cargar usuarios desde repositorio
	@Test
	public void DeberiaRetornarJoseAugustoComoElNombreDelUsuarioCargadoDesdeElDB(){
		IRepository repositorio = new JDBRepository("TelcoDB.db");
		ICargarUsuariosDesdeRepositorio casoDeUso = new CargarUsuariosDesdeRepositorio(repositorio);
		assertEquals("Jose Augusto", casoDeUso.obtenerUsuarios().get(0).getNombre());
	}
	//caso de uso guardar cdrs procesados desde repositorio
	@Test
	public void DeberiaRetornar2punto2ComoCosteDespuesDeProcesarElCDRGuardarloYVolverloACargar(){
		IRepository repositorio = new JDBRepository("TelcoDB.db");
		ArrayList<CDR> cdrs = new CargarCdrsDesdeRepositorio(repositorio).obtenerCDRS();
		for(CDR cdr:cdrs)
		{
			cdr.setCosto(2.2);
		}
		IGuardarCdrsTarificados casoDeUso = new GuardarCdrsTarificados(repositorio);
		casoDeUso.guardarRegistrosEnRepositorio(cdrs);
		assertEquals(2.2, new CargarCdrsDesdeRepositorio(repositorio).obtenerCDRS().get(0).getCosto());
	}
	//caso de uso facturar y response model 
	@Test
	public void DeberiaRetornar7ComoMontoTotalDelMes10(){
		ArrayList<CDR> registrosTelefonicosTarificados = new ArrayList<CDR>();
		registrosTelefonicosTarificados.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2));
		registrosTelefonicosTarificados.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",4.8));
		registrosTelefonicosTarificados.add(new CDR(70742567,70765342,"10/10/19","19:14","00:02:30",2.2));
		Usuario usuario = new Usuario("gio",877878,70743567);
		usuario.setPlan(new PlanWow());
		FacturacionResponseModel factura = new FacturacionResponseModel(usuario,7,"10/10/19"); 
		IFacturarParaUsuario facturar= new FacturarParaUsuario();
		assertEquals(factura.getNombre(),facturar.facturarParaUnUsuario(registrosTelefonicosTarificados,usuario, "10-10-19").getNombre()) ;
	}
	
	@Test
	public void DeberiaRetornar7ComoMonasdtoTotalDelMes10() throws ParseException{
		ArrayList<CDR> registrosTelefonicos = new ArrayList<CDR>();
		registrosTelefonicos.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",0));
		registrosTelefonicos.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:00",0));
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = new Usuario("gio",877878,70743567);
		usuario.setPlan(new PlanPostpago());
		usuario.setTarifacion(new TarifacionFijaPorMinuto());
		usuarios.add(usuario);
		ITarificar tarificar = new Tarificar(usuarios,registrosTelefonicos);
		assertEquals(2.9,tarificar.tarificarCDRs().get(1).getCosto());
	}
	
	
	
	
	
}
