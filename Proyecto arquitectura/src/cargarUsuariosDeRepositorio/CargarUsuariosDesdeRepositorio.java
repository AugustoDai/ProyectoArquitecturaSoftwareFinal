package cargarUsuariosDeRepositorio;

import java.util.ArrayList;

import entidades.FabricaDePlanesYTarifas;
import entidades.Usuario;
import repository.IRepository;

public class CargarUsuariosDesdeRepositorio implements ICargarUsuariosDesdeRepositorio {

	FabricaDePlanesYTarifas fabrica;
	IRepository repositorio;
	public CargarUsuariosDesdeRepositorio(IRepository repositorio){
		fabrica =new FabricaDePlanesYTarifas();
		this.repositorio=repositorio;
	}
	public ArrayList<Usuario> obtenerUsuarios()
	{
		return repositorio.obtenerUsuarios(fabrica);
	}
}
