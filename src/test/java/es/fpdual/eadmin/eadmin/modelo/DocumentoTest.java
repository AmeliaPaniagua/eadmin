package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.ModeloBaseAdministracionElectronicaTest.ModeloBaseAdministracionElectronicaFake;
import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class DocumentoTest extends AbstractoModeloBeanTest<Documento>{
	
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	
	private Documento documento;
		
	
	@Before
	public void inicializarCadaTest() {
		documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	}
	
	
	
	@Test
	public void deberiaComprobarEquals() {
		//https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
		
		
	}


	@Override
	public void before() {
		this.entityA1 = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
		this.entityA2 = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
		this.entityB = new Documento(CODIGO_DOCUMENTO, "docB", FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
		
	}


	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(DOCUMENTO_PUBLICO, documento.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, documento.getEstado());
		
	}
	
}






