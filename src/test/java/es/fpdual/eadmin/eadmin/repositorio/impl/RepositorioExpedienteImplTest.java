package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class RepositorioExpedienteImplTest {
	
	private static final Integer CODIGO_EXPEDIENTE = 2;
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE ="nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final Date FECHA_ARCHIVO = new Date();
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final EstadoDocumento ESTADO_DOCUMENTO = EstadoDocumento.ACTIVO;
	private static final EstadoExpediente ESTADO_EXPEDIENTE = EstadoExpediente.EN_TRAMITE;
	
	private static final List <Documento> LISTA_DOCUMENTOS = new ArrayList<>();
	
	private static final Expediente EXPEDIENTE = new Expediente (CODIGO_DOCUMENTO, NOMBRE, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, FECHA_ARCHIVO, DOCUMENTO_PUBLICO, ESTADO_EXPEDIENTE, LISTA_DOCUMENTOS);
	private static final Documento DOCUMENTO = new Documento(CODIGO_EXPEDIENTE, NOMBRE, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
	
	private RepositorioExpedienteImpl repositorio;
	
	
	public void inicializarCadaTest() {
		
		this.repositorio = new RepositorioExpedienteImpl();
		
	}
	
	@Test
	public void testAltaExpediente() {
		repositorio.altaExpediente(EXPEDIENTE);
		assertFalse(repositorio.getExpedientes().isEmpty());
	}
 
	@Test
	public void testModificarExpediente() {
		repositorio.getExpedientes().add(EXPEDIENTE);
		Expediente expediente2 = new Expediente(CODIGO_EXPEDIENTE, NOMBRE, FECHA_CREACION, new Date(30/03/2017), FECHA_ARCHIVO, DOCUMENTO_PUBLICO, ESTADO_EXPEDIENTE, LISTA_DOCUMENTOS);
		repositorio.modificarExpediente(expediente2);
		assertEquals(EXPEDIENTE, expediente2);
		
	}

	@Test
	public void testEliminarExpediente() {
		this.repositorio.getExpedientes().add(EXPEDIENTE);
		this.repositorio.eliminarExpediente(EXPEDIENTE.getCodigo());
		assertTrue(repositorio.getExpedientes().isEmpty());
	}

	@Test
	public void testEliminarExpedienteSiNoExiste() {
		this.repositorio.eliminarExpediente(EXPEDIENTE.getCodigo());
		assertTrue(repositorio.getExpedientes().isEmpty());
	}
	
	@Test
	public void deberiaDesasociarExpediente() {
		EXPEDIENTE.getListaDocumentos().add(DOCUMENTO);
		repositorio.getExpedientes().add(EXPEDIENTE);
		
		repositorio.desasociarDocumentoDelExpediente(EXPEDIENTE.getCodigo(), DOCUMENTO.getCodigo());
		assertTrue(EXPEDIENTE.getListaDocumentos().isEmpty());
	}
	
	@Test
	public void deberiaAsociarExpediente() {		
		repositorio.getExpedientes().add(EXPEDIENTE);	
		this.EXPEDIENTE.getListaDocumentos().add(DOCUMENTO);
		
		repositorio.asociarDocumentoAlExpediente(CODIGO_EXPEDIENTE, DOCUMENTO);
		assertFalse(EXPEDIENTE.getListaDocumentos().isEmpty());
}
	
	

}
