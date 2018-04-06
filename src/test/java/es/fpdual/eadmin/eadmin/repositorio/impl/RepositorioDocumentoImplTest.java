package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class RepositorioDocumentoImplTest {

	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final EstadoDocumento ESTADO_DOCUMENTO = EstadoDocumento.ACTIVO;
	
	private static final Documento DOCUMENTO = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
	
	
	private RepositorioDocumentoImpl repositorio;
	
	@Before
	public void inicializarCadaTest() {
				
		this.repositorio = new RepositorioDocumentoImpl();
		
	}	
	
	@Test
	public void probarAltaDocumento() {
		
		this.repositorio.altaDocumento(DOCUMENTO);
		assertFalse(repositorio.getDocumentos().isEmpty());                              
		
	}
	
	@Test
	public void probarDocumentoNoExiste() {
		
		this.repositorio.altaDocumento(DOCUMENTO);
		Documento doc2 = new Documento(2, "nombre2", FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
		
		repositorio.modificarDocumento(doc2);
		assertNotEquals(DOCUMENTO, doc2);
	}
	
	@Test
	public void probarDocumentoExiste() {
		
		this.repositorio.altaDocumento(DOCUMENTO);
		Documento doc2 = new Documento(CODIGO_DOCUMENTO, "nombre2", FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
		
		this.repositorio.altaDocumento(doc2);
		assertEquals(DOCUMENTO, doc2);
		
	}
	
	@Test
	public void probarModificarDocumento() {
		
		//this.repositorio.getDocumentos().add(DOCUMENTO);
		this.repositorio.altaDocumento(DOCUMENTO);
		Documento doc2 = new Documento(CODIGO_DOCUMENTO, "nombre2", FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
		
		repositorio.modificarDocumento(doc2);
		assertEquals(DOCUMENTO, doc2);
		
	}
	
	@Test
	public void deberiaEliminarDocumento() {
		
		this.repositorio.getDocumentos().add(DOCUMENTO);
		this.repositorio.eliminarDocumento(DOCUMENTO.getCodigo());
		assertTrue(this.repositorio.getDocumentos().isEmpty());
		
	}
	
	@Test
	public void deberiaEliminarDocumentoQueNoEstaEnLista() {
		
		this.repositorio.eliminarDocumento(DOCUMENTO.getCodigo());
		assertTrue(this.repositorio.getDocumentos().isEmpty());
		
	}
	
	@Test
	public void probarQueObtieneElDocumento() {
		
		this.repositorio.altaDocumento(DOCUMENTO);
		
		Documento resultado = repositorio.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		assertEquals(resultado, DOCUMENTO);
		
	}
		
	@Test
	public void probarQueNoObtieneElDocumento() {
		
		Documento resultado = repositorio.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		assertNotEquals(resultado,DOCUMENTO);
		
		
	}
	
	@Test
	public void probarQueObtieneTodosLosDocumentos() {
		
		List <Documento> lista = this.repositorio.getDocumentos() ;
		List <Documento> resultado = this.repositorio.obtenerTodosLosDocumentos();
		assertEquals(resultado,lista);
		
		
		
	}
	
	
}
