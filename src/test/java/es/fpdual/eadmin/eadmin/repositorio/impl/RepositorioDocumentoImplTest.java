package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

public class RepositorioDocumentoImplTest {

private static final Date FECHA_CREACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	
	private static final Documento DOC = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	

	
	private RepositorioDocumentoImpl repositorio;
	
	@Before
	public void inicializarCadaTest() {
				
		this.repositorio = new RepositorioDocumentoImpl();
		
	}	
	
	@Test
	public void probarAltaDocumento() {
		
		repositorio.altaDocumento(DOC);
		assertSame(DOC, repositorio.getDocumentos().get(0));                              
		
		
	}
	
	@Test
	public void probarDocumentoExiste() {
		
		repositorio.altaDocumento(DOC);		
		final Boolean resultado = repositorio.getDocumentos().contains(DOC);
		
		assertTrue(resultado);		
		
	}
	
	
	
	@Test
	public void probarModificarDocumento() {
		
		Documento doc2 = new Documento(CODIGO_DOCUMENTO, "nombre2", FECHA_CREACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
		
		repositorio.modificarDocumento(doc2);
		
		
		
	}
	
	@Test
	public void deberiaEliminarDocumento() {
		
		this.repositorio.getDocumentos().add(DOC);
		this.repositorio.eliminarDocumento(DOC.getCodigo());
		assertTrue(this.repositorio.getDocumentos().isEmpty());
		
	}
	
	@Test
	public void deberiaEliminarDocumentoQueNoEstaEnLista() {
		
		this.repositorio.eliminarDocumento(DOC.getCodigo());
		assertTrue(this.repositorio.getDocumentos().isEmpty());
		
	}
	
}
