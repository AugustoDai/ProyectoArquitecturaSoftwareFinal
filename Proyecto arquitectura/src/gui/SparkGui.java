package gui;

import static spark.Spark.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import cargarCdrsDeRepositorio.CargarCdrsDesdeRepositorio;
import cargarUsuariosDeRepositorio.CargarUsuariosDesdeRepositorio;
import entidades.CDR;
import entidades.Usuario;
import facturarParaUsuario.FacturarParaUsuario;
import guardarCdrsTarificados.GuardarCdrsTarificados;
import repository.IRepository;
import repository.JDBRepository;
import repository.SerializationRepository;
import responseModels.FacturacionResponseModel;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import tarificar.Tarificar;

public class SparkGui implements IGUI{
	
	
	IRepository repositorio =null;
	ArrayList<Usuario> registroDeUsuarios = null;
	ArrayList<CDR> registrosTelefonicos = null;
	ArrayList<CDR> registrosTelefonicosTarificados = null;
	ArrayList<FechaYCdrs> registroFechasYCdrsTarificados = new ArrayList<FechaYCdrs>();
	
	String tipoPersistencia=null; 

	
	
	@Override
	public void iniciarInterfaz() {
	
		get("/", (req, res) -> 
        {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/home/inicio.vm"));
        });
		
		get("/configuracion", (req, res) -> 
        {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/home/configuracion.vm"));
        });
		
		get("/ruta", (request, response) -> 
        {
        	
            Map<String, Object> model = new HashMap<>();
            tipoPersistencia = request.queryParams("Persistencia");
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/home/tipoDePersistenciaEscogido.vm"));
        });
		
		
		get("/cargarArchivoDeCDRs", (request, response) -> 
        {
        	
            Map<String, Object> model = new HashMap<>();
            if(tipoPersistencia==null)
            	tipoPersistencia = "Serializacion";
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/home/rutaPara"+tipoPersistencia+".vm"));
        });
		
		
		get("/cargarArchivo", (request, response) -> 
        {
        	
            Map<String, Object> model = new HashMap<>();
            repositorio = tipoDeRepositorio(request);
            registrosTelefonicos = new CargarCdrsDesdeRepositorio(repositorio).obtenerCDRS();
            registroDeUsuarios = new CargarUsuariosDesdeRepositorio(repositorio).obtenerUsuarios();
            
            
            model.put("CDRs", registrosTelefonicos);
            model.put("Usuarios",registroDeUsuarios);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/cdrs/cdrsCargadosConteo.vm"));
        });

		
		get("/usuarios", (request, response) -> 
        {
            Map<String, Object> model = new HashMap<>();
            model.put("usuarios",registroDeUsuarios);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/usuarios/interfazUsuarios.vm"));
        });

		
		get("/CDRs", (request, response) -> 
        {
            Map<String, Object> model = new HashMap<>();
            model.put("CDRs", registrosTelefonicos);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/cdrs/interfazCdrs.vm"));
        });
		
		get("/tarifar", (request, response) -> 
        {
            Map<String, Object> model = new HashMap<>();
            registrosTelefonicosTarificados = new Tarificar(registroDeUsuarios, registrosTelefonicos).tarificarCDRs();
            model.put("CDRs", registrosTelefonicosTarificados);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/cdrs/interfazCdrs.vm"));
        });
		
		get("/guardarTarifa", (request, response) -> 
        {
        	new GuardarCdrsTarificados(repositorio).guardarRegistrosEnRepositorio(registrosTelefonicos);
        	guardarDatos(registrosTelefonicosTarificados);
            Map<String, Object> model = new HashMap<>();
            model.put("CDRs", registrosTelefonicos);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/cdrs/interfazCdrs.vm"));
        });
		
		get("/recuperarTarificacion", (request, response) -> 
        {
            Map<String, Object> model = new HashMap<>();
            model.put("tarificacionesGuardadasConFecha", this.registroFechasYCdrsTarificados);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/cdrs/allTarificados.vm"));
        });
		
		
		post("/recibirJson", (request, response)->
		{	
			response.type("application/json");
			Object obj = new JSONParser().parse(request.body());
			JSONObject numeroYFecha =  (JSONObject)obj;
			String fecha = numeroYFecha.get("fecha").toString();
			int numeroTelefonico = Integer.parseInt(numeroYFecha.get("numero telefonico").toString());
			Usuario usuario= buscarUsuario(numeroTelefonico);
			FacturacionResponseModel factura = null;
			if(usuario!=null)
			{
				factura= new FacturarParaUsuario().facturarParaUnUsuario(registrosTelefonicosTarificados,usuario,fecha);
			}
			
			return new Gson().toJson(
				      new Gson().toJsonTree(factura));

		});
	}

	private void guardarDatos(ArrayList<CDR> cdrsTarificados) {
		Date fechaActual = new Date();
		FechaYCdrs fechasYCdrsTarificadoss = new FechaYCdrs(fechaActual, cdrsTarificados);
		this.registroFechasYCdrsTarificados.add(fechasYCdrsTarificadoss);
	}
	
	private IRepository tipoDeRepositorio(Request request) {
		IRepository repositorioResult = null;
		String tipoPersistencia = request.queryParams("Persistencia");
        if(tipoPersistencia.equals("JDB"))
        	repositorioResult = new JDBRepository(request.queryParams("JDBroute"));
        if(tipoPersistencia.equals("Serializacion"))
        	repositorioResult = new SerializationRepository(request.queryParams("RutaArchivoCDRs"),request.queryParams("RutaArchivoUsuarios"));
		return repositorioResult;
	}
	
	private Usuario buscarUsuario(int numeroTelefonico)
	{
		for(Usuario usuario:registroDeUsuarios)
		{
			if(numeroTelefonico == usuario.getNumeroTelefonoMovil())
				return usuario;
		}
		return null;
	}



}

