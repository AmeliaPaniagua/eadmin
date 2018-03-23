package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ModeloBaseAdministracionElectronicaTest {
	
	class ModeloBaseAdministracionElectronicaFake extends ModeloBaseAdministracionElectronica{

		public ModeloBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion) {
			super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion);
		}

	}
	

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	
	private ModeloBaseAdministracionElectronicaFake documento;
	
	@Before
	public void inicializarCadaTest() {
		documento = new ModeloBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
	}
	
	@Test
	public void deberiaComprobarGetters() {
					
		assertEquals(CODIGO_DOCUMENTO, documento.getCodigo());
		assertEquals(NOMBRE_DOCUMENTO, documento.getNombre());
		assertEquals(FECHA_CREACION, documento.getFechaCreacion());
		assertEquals(FECHA_ULTIMA_MODIFICACION, documento.getFechaUltimaActualizacion());
		

	}
	
	@Test
	public void deberiaDevolverTrueSiTienenIgualCodigo() {
		
		final ModeloBaseAdministracionElectronicaFake documento2 = new ModeloBaseAdministracionElectronicaFake (CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
		
		final Boolean resultado = documento.equals(documento2);
		
		assertTrue(resultado);
		
	}
	
	
	
	@Test
	public void deberiaDevolverFalseSiNoTienenIgualCodigo() {
		
		final ModeloBaseAdministracionElectronicaFake documento2 = new ModeloBaseAdministracionElectronicaFake (5, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
		
		final Boolean resultado = documento.equals(documento2);
		
		assertFalse(resultado);
	}
	
	
	@Test
	public void deberiaDevolverFalseSiNoEsUnDocumento() {
		final Boolean resultado = documento.equals(new Date());
		assertFalse(resultado);
	}
	
	@Test
	public void deberiaDevolverHasCodeDelCodigo() {
		final int resultado = documento.hashCode();
		assertEquals(CODIGO_DOCUMENTO.hashCode(), resultado);
	}
	
}
