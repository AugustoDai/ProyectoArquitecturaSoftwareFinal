package repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import entidades.CDR;
import entidades.FabricaDePlanesYTarifas;
import entidades.Usuario;
import planes.PlanWow;



public class SerializationRepository implements IRepository {

	Path cdrsPath,usuariosPath;
	public SerializationRepository(String cdrsPath,String usuariosPath)
	{
		this.cdrsPath= Paths.get(cdrsPath);
		this.usuariosPath= Paths.get(usuariosPath);
		
	}
	@Override
	public ArrayList<CDR> obtenerCDRs() {

		ArrayList<CDR> CDRs = new ArrayList<CDR>();
        try {
            BufferedReader reader = Files.newBufferedReader(cdrsPath);
            String linea ="";
            while((linea = reader.readLine())!=null) {
                String[] registro = linea.split(",");
                CDR cdrLeido = obtenerCDRDeRegistro(registro);
                CDRs.add(cdrLeido);
            }

        }catch(Exception e){
            System.err.println("No se encontro archivo");
        }
		return CDRs;
	}

	private CDR obtenerCDRDeRegistro(String[] registro)
	{
		int telefonoOrigen = Integer.parseInt(registro[0]);
        int telefonoDestino = Integer.parseInt(registro[1]);
        String fecha = registro[2].replace("\"", "");
        String horaInicioLlamada = (LocalTime.parse(registro[3])).toString();
        String duracionLlamada = LocalTime.parse(registro[4]).toString();
        double costo = Double.parseDouble(registro[5]);
        CDR nuevoCDR = new CDR(telefonoOrigen,telefonoDestino,fecha,horaInicioLlamada,duracionLlamada,costo);
        return nuevoCDR;
	}

	@Override
	public void guardarCDRsProcesados( ArrayList<CDR> registrosTelefonicos) {

		String [] datosCDR = {"telefonoOrigen","telefonoDestino","fecha","HoraInicioLlamada","DuracionLlamada","costo"};
        
		File file = new File("CDRsTarifados.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            PrintWriter printerWriter = new PrintWriter(bufferWriter);
            for (CDR cdr: registrosTelefonicos) {
            	datosCDR[0] = Integer.toString(cdr.getTelefonoOrigen());
    			datosCDR[1] = Integer.toString(cdr.getTelefonoDestino());
    			datosCDR[2] = cdr.getFecha();
    			datosCDR[3] = cdr.getHora();
    			datosCDR[4] = cdr.getTiempoDuracion().toString();
    			datosCDR[5] = Double.toString(cdr.getCosto());
    			printerWriter.flush();
            }
            printerWriter.close();

        } catch (Exception e) {
            System.err.println("No se pudo crear archivo");
        }
		
	}

	@Override
	public ArrayList<Usuario> obtenerUsuarios(FabricaDePlanesYTarifas fabrica) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            BufferedReader reader = Files.newBufferedReader(usuariosPath);
            String linea ="";
            while((linea = reader.readLine())!=null) {
                String[] registro = linea.split(",");
                Usuario usuarioLeido = obtenerUsuarioDeRegistro(registro,fabrica);
                usuarios.add(usuarioLeido);
            }

        }catch(Exception e){
            System.err.println("No se encontro archivo");
        }
		return usuarios;

	}
	private Usuario obtenerUsuarioDeRegistro(String[] registro,FabricaDePlanesYTarifas fabrica)
	{
		String nombre = registro[0];
		int ci = Integer.parseInt(registro[1]);
        int numeroTelefonoMovil = Integer.parseInt(registro[2]);
        Usuario nuevoUsuario = new Usuario(nombre,ci,numeroTelefonoMovil);
        String tipoPlanDeUsuario = registro[3];
        String tipoTarifacionDeUsuario = registro[4];
        nuevoUsuario.setPlan(fabrica.getPlan(tipoPlanDeUsuario));
        nuevoUsuario.setTarifacion(fabrica.getTarifacion(tipoTarifacionDeUsuario));
        if(nuevoUsuario.getPlan().getTipoDePlan() == "WOW")
        {
        	String[] numerosAmigosString = registro[5].replace("[", "").replace("]", "").split("-");
        	ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
        	for (int i = 0; i < numerosAmigosString.length; i++){
        		numerosAmigos.add(Integer.parseInt(numerosAmigosString[i]));
        	}
        	((PlanWow) nuevoUsuario.getPlan()).setNumerosAmigos(numerosAmigos);
        }
        return nuevoUsuario;
	}
	

}