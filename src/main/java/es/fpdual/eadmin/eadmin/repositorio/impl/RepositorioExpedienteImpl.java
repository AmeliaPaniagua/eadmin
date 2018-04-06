package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Repository
public class RepositorioExpedienteImpl implements RepositorioExpediente{
	
	private static final Logger logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);

	private final List <Expediente> expedientes = new ArrayList<>();
	

	//getter
		public List<Expediente> getExpedientes() {
			return expedientes;
		}
	
	@Override
	public void altaExpediente(Expediente expediente) {

		logger.info("Entrando en altaExpediente");
		
		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		expedientes.add(expediente);
		
		escribirAltaExpFichero(expediente);
		
		logger.info(expediente.toString() + " creado correctamente");
	}

	@Override
	public void modificarExpediente(Expediente expediente) {

		logger.info("Entrando en modificarExpediente");
		
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente que quiere modificar no existe");
		}
		expedientes.set(expedientes.indexOf(expediente), expediente);
		
		escribirModificacionExpFichero(expediente);
		
		logger.info("Saliendo de modificarDocumento");
		
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {

		logger.info("Entrando en eliminarExpediente");
		
		Optional<Expediente> expedienteEncontrado =
				expedientes.stream().filter(d ->tieneIgualCodigo(d, codigoExpediente)).findFirst();
				if (expedienteEncontrado.isPresent()) { // comprueba si el objeto es nulo o no
					escribirEliminarExpFichero(expedienteEncontrado);
					expedientes.remove(expedienteEncontrado);
					System.out.println(expedienteEncontrado);
				}	
				
		logger.info("Saliendo de eliminarExpediente");
	}

	protected boolean tieneIgualCodigo(Expediente expediente, Integer codigoExpediente) {
		
		return expediente.getCodigo().equals(codigoExpediente);
		
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		
		Optional<Expediente> expedienteEncontrado =
				expedientes.stream().filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();
		if (expedienteEncontrado.isPresent()) {
			expedienteEncontrado.get().getListaDocumentos().add(documento);
			return expedienteEncontrado.get();
		}
		
		return null;
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		
		Optional<Expediente> expedienteEncontrado =
				expedientes.stream().filter(e -> e.getCodigo().equals(codigoExpediente)).findFirst();
		if (expedienteEncontrado.isPresent()) {
			
			localizaYEliminaElDocumentoYDevuelveElDocumento(codigoDocumento, expedienteEncontrado.get());
			return expedienteEncontrado.get();
			
		}
		
		return null;
	}

	public void localizaYEliminaElDocumentoYDevuelveElDocumento(Integer codigoDocumento,
			Expediente expedienteEncontrado) {
		
		Optional<Documento> documentoEncontrado = expedienteEncontrado.getListaDocumentos()
				.stream().filter((Documento e) -> e.getCodigo().equals(codigoDocumento)).findFirst();
		
		if (documentoEncontrado.isPresent()) {
			
			expedienteEncontrado.getListaDocumentos().remove(documentoEncontrado.get());
			//return documentoEncontrado.get();
		}
		
	}
	
	
	public void crearFicheroExpedientes() {
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "ficheroexpediente.txt";
		
		
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			
			for (Expediente exp: expedientes) {
				
				pw.println("Expediente:" + exp.getCodigo());
				pw.println("Nombre:" + exp.getNombre());
				pw.println("Fecha Creación: " + exp.getFechaCreacion());
			}	
	
			pw.close();
			
			logger.info("Escribiendo en el fichero");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");		
		
	}
	
	
	public void escribirAltaExpFichero(Expediente exp){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "AltaExpediente.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			pw.println("Expediente:" + exp.getCodigo());
			pw.println("Nombre:" + exp.getNombre());
			pw.println("Fecha Creación: " + exp.getFechaCreacion());
		
			pw.close();
			
			logger.info("Escribiendo en el fichero el alta del expediente");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	
	public void escribirModificacionExpFichero(Expediente exp){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "ModificarExpediente.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero, true);
			pw = new PrintWriter (file);
			
			pw.println("Expediente:" + exp.getCodigo());
			pw.println("Nombre:" + exp.getNombre());
			pw.println("Fecha Creación: " + exp.getFechaCreacion());
	
			pw.close();
			
			logger.info("Escribiendo en el fichero el expediente modificado");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	
	public void escribirEliminarExpFichero(Optional<Expediente> exp){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "EliminarExpediente.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			pw.println("Expediente:" + exp.get().getCodigo());
			pw.println("Nombre:" + exp.get().getNombre());
			pw.println("Fecha Creación: " + exp.get().getFechaCreacion());
	
			pw.close();
			
			logger.info("Escribiendo en el fichero la eliminación del expediente");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	
	
	
	
}
