package tarificar;

import java.util.ArrayList;

import entidades.CDR;
import entidades.Usuario;
import planes.IPlan;
import tarifaciones.ITarifacion;

public class Tarificar implements ITarificar{

	private ArrayList<Usuario> registroDeUsuarios = new ArrayList<Usuario>();
	private ArrayList<CDR> registroDeCdrs = new ArrayList<CDR>();
	
	public Tarificar(ArrayList<Usuario> registroDeUsuarios,ArrayList<CDR> registroDeCdrs)
	{
		this.registroDeCdrs=registroDeCdrs;
		this.registroDeUsuarios=registroDeUsuarios;
	}
	@Override
	public ArrayList<CDR> tarificarCDRs() {
		ArrayList<CDR> cdrsTarificados=new ArrayList<CDR>();
		for(CDR cdr:registroDeCdrs)
		{
			Usuario usuario =buscarUsuario(cdr.getTelefonoOrigen());
			IPlan planDeUsuario= usuario.getPlan();
			ITarifacion TarifacionDeUsuario= usuario.getTarifacion();
			double costoCalculado=planDeUsuario.calcularCostoDeLlamada(TarifacionDeUsuario, cdr);
			cdr.setCosto(costoCalculado);
			cdrsTarificados.add(cdr);
		}
		return cdrsTarificados;
	}
	
	public Usuario buscarUsuario(int numeroTelefonico)
	{
		Usuario usuarioBuscado=null;
		for(Usuario usuario:registroDeUsuarios)
		{
			if(usuario.getNumeroTelefonoMovil()==numeroTelefonico)
			{
				usuarioBuscado = usuario;
				break;
			}
				
		}
		return usuarioBuscado;
	}

}
