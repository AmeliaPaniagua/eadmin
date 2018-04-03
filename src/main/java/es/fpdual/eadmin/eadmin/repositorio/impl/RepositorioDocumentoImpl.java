package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

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
		
		System.out.println("Se ha dado de alta un documento");
					
	}

	@Override
	public void modificarDocumento(Documento documento) {
		
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento que quiere modificar no existe");
		}
		documentos.set(documentos.indexOf(documento), documento);
		
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
		
		
		Optional<Documento> documentoEncontrado =
		documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst();
		if (documentoEncontrado.isPresent()) { // comprueba si el objeto es nulo o no
			documentos.remove(documentoEncontrado);
		}		
		
	}
	
	
	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		
		Optional<Documento> documentoEncontrado =
				documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst();
				if (documentoEncontrado.isPresent()) { 
					return documentoEncontrado.get();
				}	
		return null;
		
		//Esto es otra opción de poner lo mismo que de arriba pero en una sola línea y sería lo mismo
		//return documentos.stream().filter(d ->tieneIgualCodigo(d, codigo)).findFirst().orElseNull;		
		
	}


	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		
		return documentos;
	}
	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		
		//devuelve un booleano de si es igual o no
		return documento.getCodigo().equals(codigo);
		
	}

}
