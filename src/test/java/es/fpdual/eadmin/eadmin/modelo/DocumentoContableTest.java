package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoContableTest {
	
private static final Date FECHA_CREACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;

	private static final BigDecimal IMPORTE_DOCUMENTO_CONTABLE = new BigDecimal(50.20);
	private static final String DNI_INTERESADO = "14621307T";
	
	private DocumentoContable documentoContable;
	
	@Before
	public void inicializarCadaTest() {
		documentoContable = new DocumentoContable(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO, IMPORTE_DOCUMENTO_CONTABLE, DNI_INTERESADO);
		
	}	

	@Test
	public void deberiaComprobarGetters() {
		
		assertEquals(IMPORTE_DOCUMENTO_CONTABLE, documentoContable.getImporte());
		assertEquals(DNI_INTERESADO, documentoContable.getDniInteresado());
					
	}
}
