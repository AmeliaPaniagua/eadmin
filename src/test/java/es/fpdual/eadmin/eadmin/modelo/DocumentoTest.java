package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoTest {
	
	private static final Date FECHA_CREACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	
	private Documento documento;
		
	
	@Before
	public void inicializarCadaTest() {
		documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	}
	

	@Test
	public void deberiaComprobarGetters() {
					
		assertEquals(DOCUMENTO_PUBLICO, documento.getPublico());
		assertEquals(EstadoDocumento.ACTIVO, documento.getEstado());
	}
	
	
	
	
}






