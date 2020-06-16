package guardarCdrsTarificados;

import java.util.ArrayList;

import entidades.CDR;
import repository.IRepository;

public class GuardarCdrsTarificados implements IGuardarCdrsTarificados{
	
	IRepository repositorio;
	public GuardarCdrsTarificados(IRepository repositorio){
	this.repositorio=repositorio;	
	}
	public void guardarRegistrosEnRepositorio(ArrayList<CDR> registrosTelefonicos) {
		repositorio.guardarCDRsProcesados(registrosTelefonicos);
	}

}
