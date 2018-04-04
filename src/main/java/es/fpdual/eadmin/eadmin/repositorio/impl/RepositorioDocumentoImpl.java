package es.fpdual.eadmin.eadmin.repositorio.impl;

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
		
		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		documentos.add(documento);
				
		logger.info(documento.toString() + " creado correctamente");
				
		
		//System.out.println("Se ha dado de alta un documento");
					
	}

	@Override
	public void modificarDocumento(Documento documento) {
		
		logger.info("Entrando en modificarDocumento()");
		
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento que quiere modificar no existe");
		}
		documentos.set(documentos.indexOf(documento), documento);
		
		logger.info("Saliendo de modificarDocumento()");
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

}

