package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.EadminApplication;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private static final Logger logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);
	
	private final List <Documento> documentos = new ArrayList<>();
	

	//getter
	public List<Documento> getDocumentos() {
		return documentos;
	}
	

	
	@Override
	public void altaDocumento(Documento documento) {
		
		logger.info("Entrando en altaDocumento");
		
		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		documentos.add(documento);
				
		escribirAltaDocFichero(documento);
		
		logger.info(documento.toString() + " creado correctamente");
					
	}
	
			

	@Override
	public void modificarDocumento(Documento documento) {
		
		logger.info("Entrando en modificarDocumento");
		
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento que quiere modificar no existe");
		}
		documentos.set(documentos.indexOf(documento), documento);
		
		escribirModificacionDocFichero(documento);
		
		logger.info("Saliendo de modificarDocumento");
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		
		//Documento documentoEncontrado = null;
		
//		for (int i = 0; i < documentos.size(); i++) {
//			if (documentos.get(i).getCodigo().equals(codigo)) {
//				documentoEncontrado = documentos.get(i);
//				break;
//			}
//		} 
		
		//Comentamos el for porque según Java 8 es mejor buscar en un array utilizando stream
		
		logger.info("Entrando en eliminarDocumento()");
		
		Optional<Documento> documentoEncontrado =
		documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst();
		if (documentoEncontrado.isPresent()) { // comprueba si el objeto es nulo o no
			logger.info("Saliendo de eliminarDocumento()");
			escribirEliminarDocFichero(documentoEncontrado);
			documentos.remove(documentoEncontrado.get());
		}
		
		logger.info("Saliendo de eliminarDocumento()");
		
	}
	
	
	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		
		logger.info("Entrando en obtenerDocumentoPorCodigo");
		
		Optional<Documento> documentoEncontrado =
				documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst();
		if (documentoEncontrado.isPresent()) { 
			logger.info("Saliendo en obtenerDocumentoPorCodigo");
			return documentoEncontrado.get();
		}	
		logger.info("Saliendo en obtenerDocumentoPorCodigo");
		return null;
		
		//Esto es otra opción de poner lo mismo que de arriba pero en una sola línea y sería lo mismo
		//return documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst().orElseNull;		
		
	}


	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		
		logger.info("Entrando en obtenerTodosLosDocumentos");
		
		for (Documento doc: documentos) {
			logger.info("**************");
			logger.info("Documento:" + doc.getCodigo());
			logger.info("Nombre:" + doc.getNombre());
			logger.info("Fecha Creación: " + doc.getFechaCreacion());
			logger.info("**************");
		}		
		logger.info("Saliendo de obtenerTodosLosDocumentos");
		
		return this.documentos;
	}
	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		
		//devuelve un booleano de si es igual o no
		return documento.getCodigo().equals(codigo);
		
	}
	
	public void crearFicheroDocumentos() {
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "ficherodocumentos.txt";
		
		
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			
			for (Documento doc: documentos) {
				
				pw.println("Documento:" + doc.getCodigo());
				pw.println("Nombre:" + doc.getNombre());
				pw.println("Fecha Creación: " + doc.getFechaCreacion());
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
	
	
	public void escribirAltaDocFichero(Documento doc){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "Alta.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			pw.println("Documento:" + doc.getCodigo());
			pw.println("Nombre:" + doc.getNombre());
			pw.println("Fecha Creación: " + doc.getFechaCreacion());
		
			pw.close();
			
			logger.info("Escribiendo en el fichero el alta");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	
	public void escribirModificacionDocFichero(Documento doc){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "Modificar.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero, true);
			pw = new PrintWriter (file);
			
			pw.println("Documento:" + doc.getCodigo());
			pw.println("Nombre:" + doc.getNombre());
			pw.println("Fecha Creación: " + doc.getFechaCreacion());
	
			pw.close();
			
			logger.info("Escribiendo en el fichero el documento modificado");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	
	public void escribirEliminarDocFichero(Optional<Documento> doc){
		
		FileWriter file = null;
		PrintWriter pw = null;
		
		
		logger.info("Iniciando...");
		String nombreFichero = "Eliminar.txt";
			
		try {			
			
			file = new FileWriter (nombreFichero,true);
			pw = new PrintWriter (file);
			
			
			//pw.println(documentos);
			pw.println("Documento:" + doc.get().getCodigo());
			pw.println("Nombre:" + doc.get().getNombre());
			pw.println("Fecha Creación: " + doc.get().getFechaCreacion());
	
			pw.close();
			
			logger.info("Escribiendo en el fichero la eliminación del documento");
			
		} catch (IOException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			pw.close();
		}
		
		
		logger.info("Escritura terminada");	
		
	}
	

}

