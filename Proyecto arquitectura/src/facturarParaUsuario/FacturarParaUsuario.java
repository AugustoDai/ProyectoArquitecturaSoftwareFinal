package facturarParaUsuario;

import java.util.ArrayList;
import entidades.CDR;
import entidades.Usuario;
import responseModels.FacturacionResponseModel;

public class FacturarParaUsuario implements IFacturarParaUsuario{

	@Override
	public FacturacionResponseModel facturarParaUnUsuario(ArrayList<CDR> crdsTarificados,Usuario usuario,String fecha){
		double monto=0;
		for(CDR cdr:crdsTarificados)
		{
			if(cumpleConFechaYNumero(fecha,usuario.getNumeroTelefonoMovil(),cdr))
				monto+=cdr.getCosto();
		}
		
		return new FacturacionResponseModel(usuario, monto, fecha);
	}
	private boolean cumpleConFechaYNumero(String fecha,int telefono,CDR cdr)
	{
		if(fecha.split("-")[1].equals(cdr.getMes())  &&  cdr.getTelefonoOrigen()==telefono)
			return true;
		return false;
	}	
}
