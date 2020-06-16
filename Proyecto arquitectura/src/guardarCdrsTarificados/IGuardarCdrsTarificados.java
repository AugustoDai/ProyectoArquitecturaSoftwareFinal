package guardarCdrsTarificados;

import java.util.ArrayList;

import entidades.CDR;


public interface IGuardarCdrsTarificados {

	public void guardarRegistrosEnRepositorio(ArrayList<CDR> registrosTelefonicos);
}
