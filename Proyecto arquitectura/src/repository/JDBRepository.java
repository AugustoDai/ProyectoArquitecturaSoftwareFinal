package repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.CDR;
import entidades.FabricaDePlanesYTarifas;
import entidades.Usuario;
import planes.PlanWow;

public class JDBRepository implements IRepository {

	ResultSet result = null;
	Connection connect;
	PreparedStatement statement;
	String DBPath;
	public JDBRepository(String DBPath)
	{
		this.DBPath=DBPath;
	}
	public void connect() {
		try {
			connect = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
			if(connect != null) {
				System.out.println("Conectado");
			}
		} catch(SQLException ex){
			System.err.println("No se ha podido conectar a la Base de Datos\n" + ex.getMessage());
		}
	}
	
	public void close() {
		try {
			connect.close();
		}catch(SQLException ex) {
			Logger.getLogger(JDBRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public ArrayList<CDR> obtenerCDRs() {
		connect();
		ArrayList<CDR> CDRs = new ArrayList<CDR>();
		int telefonoOrigen,telefonoDestino;
		String fecha,horaInicioLlamada, duracionLlamada;
		double costo;
		try {
			PreparedStatement st = connect.prepareStatement("select * from CDR");
			result = st.executeQuery();
			while(result.next()) {
				telefonoOrigen = result.getInt("telefonoOrigen");
				telefonoDestino = result.getInt("telefonoDestino");
				fecha = result.getString("fecha");
				horaInicioLlamada = result.getString("horaInicioLlamada");
				duracionLlamada = result.getString("duracionLlamada");
				costo = result.getDouble("costo");
				CDR cdr = new CDR(telefonoOrigen, telefonoDestino, fecha, horaInicioLlamada, duracionLlamada,costo);
				CDRs.add(cdr);
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		close();
		return CDRs;
	}

	@Override
	public void guardarCDRsProcesados( ArrayList<CDR> registrosTelefonicos) {
		connect();
		int indice = 1;
			
		try {
			
			for(CDR cdr: registrosTelefonicos) {
				
				String sql = "UPDATE CDR SET costo='" + Double.toString(cdr.getCosto()) + "' WHERE id="+indice;
				statement = connect.prepareStatement(sql);
				statement.execute();
				indice +=1;
				
			}		
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		close();
		
	}

	@Override
	public ArrayList<Usuario> obtenerUsuarios(FabricaDePlanesYTarifas fabrica) {
		connect();
		Usuario usuario = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			statement = connect.prepareStatement("select * from Usuario");
			result = statement.executeQuery();
			while(result.next()) {
				usuario = obtenerUsuarioDeRegistroDB(result,fabrica);
				usuarios.add(usuario);
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		close();
		return usuarios;
	}
	
	private Usuario obtenerUsuarioDeRegistroDB(ResultSet result,FabricaDePlanesYTarifas fabrica)
	{
		Usuario nuevoUsuario=null;
		try {
			String nombre = result.getString("nombre");
			int ci = result.getInt("ci");
			int numeroTelefonoMovil = result.getInt("numeroTelefono");		
			nuevoUsuario = new Usuario(nombre,ci,numeroTelefonoMovil);
			String tipoPlanDeUsuario = result.getString("plan");
			String tipoTarifacionDeUsuario = result.getString("tarifa");
			nuevoUsuario.setPlan(fabrica.getPlan(tipoPlanDeUsuario));
		    nuevoUsuario.setTarifacion(fabrica.getTarifacion(tipoTarifacionDeUsuario));
	       
	        if(nuevoUsuario.getPlan().getTipoDePlan() == "WOW")
	        {
	        	String[] numerosAmigosString = result.getString("numerosAmigos").replace("[", "").replace("]", "").split("-");
	        	ArrayList<Integer> numerosAmigos = new ArrayList<Integer>();
	        	for (int i = 0; i < numerosAmigosString.length; i++){
	        		numerosAmigos.add(Integer.parseInt(numerosAmigosString[i]));
	        	}
	        	((PlanWow) nuevoUsuario.getPlan()).setNumerosAmigos(numerosAmigos);
	        }
		} catch (SQLException e) {
			
			 System.err.println("Error al leer los datos del usuario de la base de datos");
		}
		
        return nuevoUsuario;
	}

}
