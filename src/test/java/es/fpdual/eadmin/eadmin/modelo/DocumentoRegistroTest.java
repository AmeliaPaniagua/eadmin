package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoRegistroTest {
		
	private static final Date FECHA_CREACION = new Date();
		
		private static final Integer CODIGO_DOCUMENTO = 1;
		private static final String NOMBRE_DOCUMENTO ="nombre";
		private static final Boolean DOCUMENTO_PUBLICO = true;

		private static final String DNI_INTERESADO = "14621307T";
		private static final String CODIGO_REGISTRO = "1";
		
		private DocumentoRegistro documentoRegistro;
		
		@Before
		public void inicializarCadaTest() {
			documentoRegistro = new DocumentoRegistro(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, DOCUMENTO_PUBLICO, 
					EstadoDocumento.ACTIVO, DNI_INTERESADO, CODIGO_REGISTRO);
		}
		

		@Test
		public void deberiaComprobarGetters() {
			
			assertEquals(DNI_INTERESADO, documentoRegistro.getDniInteresado());
			assertEquals(CODIGO_REGISTRO, documentoRegistro.getCodigoRegistro());
						
		}
	
}
