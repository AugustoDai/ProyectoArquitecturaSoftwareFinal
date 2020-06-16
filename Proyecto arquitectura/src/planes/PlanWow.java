package planes;

import java.util.ArrayList;

import entidades.CDR;
import tarifaciones.ITarifacion;

public class PlanWow implements IPlan   {

	private ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
	@Override
	public double calcularCostoDeLlamada(ITarifacion tarifacion,CDR cdr) {
		if(numerosAmigos.contains(cdr.getTelefonoDestino()))
			return 0.0;
		else
			return tarifacion.calcularCoste(cdr);
	}
	public void setNumerosAmigos(ArrayList<Integer> numerosAmigos)
	{
		this.numerosAmigos=numerosAmigos;
	} 
	
	@Override
	public String getTipoDePlan()
	{	
		return "WOW";
	}
	
	public String getNumeroAmigosParaString()
    {
         String resultNumerosAmigos = "";
         if(numerosAmigos.size()>0)
         {
             for(Integer numero:numerosAmigos)
             {
                 resultNumerosAmigos+= (numero).toString()+ "-";
             }
             return resultNumerosAmigos.substring(0, resultNumerosAmigos.length()-1);
         }else
             return resultNumerosAmigos+="Sin numeros amigos";
     }
}
