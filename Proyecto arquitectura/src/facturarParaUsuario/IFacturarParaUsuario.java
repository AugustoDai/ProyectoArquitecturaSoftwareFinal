package facturarParaUsuario;

import java.util.ArrayList;
import entidades.CDR;
import entidades.Usuario;
import responseModels.FacturacionResponseModel;

public interface IFacturarParaUsuario {

	public FacturacionResponseModel facturarParaUnUsuario(ArrayList<CDR> crdsTarificados,Usuario usuario,String fecha);
}
