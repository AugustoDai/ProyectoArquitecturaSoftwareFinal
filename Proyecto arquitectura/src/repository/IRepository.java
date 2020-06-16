package repository;
import java.util.ArrayList;

import entidades.CDR;
import entidades.FabricaDePlanesYTarifas;
import entidades.Usuario;

public interface IRepository {

	public ArrayList<CDR> obtenerCDRs();
	public void guardarCDRsProcesados(ArrayList<CDR> cdrsProcesados);
	public ArrayList<Usuario> obtenerUsuarios(FabricaDePlanesYTarifas fabrica);
	//public void guardarUsuarios();
}
