package cargarCdrsDeRepositorio;

import java.util.ArrayList;

import entidades.CDR;
import repository.IRepository;

public class CargarCdrsDesdeRepositorio implements ICargarCdrsDesdeRepositorio{
	IRepository repositorio;

	public CargarCdrsDesdeRepositorio(IRepository repositorio)
	{
		this.repositorio=repositorio;
	}
	@Override
	public ArrayList<CDR> obtenerCDRS() {
		
		return repositorio.obtenerCDRs();
	}

	
}
