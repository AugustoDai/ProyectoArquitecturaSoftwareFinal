package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import entidades.CDR;
import entidades.FabricaDePlanesYTarifas;
import entidades.Usuario;
import repository.IRepository;
import repository.JDBRepository;
import repository.SerializationRepository;


public class TestRepository {

	@Test
	public void DeberiaRetorna5ComoTamañoDelArraySiCargamos5CDRs(){
		IRepository serializationRepository =new SerializationRepository("cdrs.csv","usuarios.csv");
		ArrayList<CDR> CDRs = serializationRepository.obtenerCDRs();
		assertEquals(5,CDRs.size() );
	}
	
	@Test
	public void DeberiaRetorna0ComoTamañoDelArraySiNoSeEncontroElArchivoDeCarga(){
		IRepository serializationRepository =new SerializationRepository("jtestdata\\cdrs.csv","usuarios.csv");
		ArrayList<CDR> CDRs = serializationRepository.obtenerCDRs();
		assertEquals(0,CDRs.size() );
	}
	@Test
	public void DeberiaGuardarLosCDRsProcesados(){
		IRepository serializationRepository =new SerializationRepository("cdrs.csv","usuarios.csv");
		ArrayList<CDR> CDRs = serializationRepository.obtenerCDRs();
		serializationRepository.guardarCDRsProcesados(CDRs);
	}
	
	@Test
	public void DeberiaRetornar5ComoTamañoDeLaListaDeUsuariosQueCargo(){
		IRepository serializationRepository =new SerializationRepository("cdrs.csv","usuarios.csv");
		FabricaDePlanesYTarifas fabrica = new FabricaDePlanesYTarifas();
		ArrayList<Usuario> Usuarios= serializationRepository.obtenerUsuarios(fabrica);
		assertEquals(5,Usuarios.size() );
	}
	
	@Test
	public void DeberiaRetorna5ComoTamañoDelArraySiCargamos5CDRsDesdeJDB(){
		IRepository JDBRepositorio =new JDBRepository("TelcoDB.db");
		assertEquals(5,JDBRepositorio.obtenerCDRs().size() );
	}
	
	@Test
	public void DeberiaGuardarElArchivoEnLaBaseDeDatos(){
		IRepository JDBRepositorio =new JDBRepository("TelcoDB.db");
		JDBRepositorio.guardarCDRsProcesados(JDBRepositorio.obtenerCDRs());
	}

	@Test
	public void DeberiaCargar5UsuariosDesdeLaBaseDeDatos(){
		IRepository JDBRepositorio =new JDBRepository("TelcoDB.db");
		FabricaDePlanesYTarifas fabrica = new FabricaDePlanesYTarifas();
		assertEquals(4,JDBRepositorio.obtenerUsuarios(fabrica).size());
	}
	
	@Test
	public void DeberiaSalirUnMensajeDeErrorAlCargarUsuariosDesdeLaBaseDeDatosErronea(){
		IRepository JDBRepositorio =new JDBRepository("TelcdoDB.db");
		FabricaDePlanesYTarifas fabrica = new FabricaDePlanesYTarifas();
		assertEquals(0,JDBRepositorio.obtenerUsuarios(fabrica).size());
	}
	
	@Test
	public void DeberiaSalirUnMensajeDeErrorAlCargarCDRSDesdeLaBaseDeDatosErronea(){
		IRepository JDBRepositorio =new JDBRepository("TelcdoDB.db");
		assertEquals(0,JDBRepositorio.obtenerCDRs().size());
	}
	
	@Test
	public void DeberiaSalirUnMensajeDeErrorAlGuardarCDRSEnLaBaseDeDatosErronea(){
		IRepository JDBRepositorio =new JDBRepository("TelcdoDB.db");
		ArrayList<CDR> registrosTelefonicosTarificados = new ArrayList<CDR>();
		registrosTelefonicosTarificados.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",2.2));
		registrosTelefonicosTarificados.add(new CDR(70743567,70765342,"10/10/19","19:14","00:02:30",4.8));
		registrosTelefonicosTarificados.add(new CDR(70742567,70765342,"10/10/19","19:14","00:02:30",2.2));
		JDBRepositorio.guardarCDRsProcesados(registrosTelefonicosTarificados);
	}
}
