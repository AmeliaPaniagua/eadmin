package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class ModeloBaseAdministracionElectronicaTest  extends AbstractoModeloBeanTest<ModeloBaseAdministracionElectronica>{
	
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	
	
	class ModeloBaseAdministracionElectronicaFake extends ModeloBaseAdministracionElectronica{

		public ModeloBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion) {
			super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion);
		}

	}
	
		
	private ModeloBaseAdministracionElectronicaFake documento;
		
	
	@Override
	public void before() {
		this.entityA1 = new ModeloBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
		this.entityA2 = new ModeloBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
		this.entityB = new ModeloBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, "docB", FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
		
	}
	
	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(CODIGO_DOCUMENTO, entityA1.getCodigo());
		assertEquals(NOMBRE_DOCUMENTO, entityA1.getNombre());
		assertEquals(FECHA_CREACION, entityA1.getFechaCreacion());
		assertEquals(FECHA_ULTIMA_MODIFICACION, entityA1.getFechaUltimaActualizacion());
		
	}
	//Esto ya no hace falta porque ya lo prueba el padre
	
//	@Before
//	public void inicializarCadaTest() {
//		documento = new ModeloBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
//	}
	
	
	
//	@Test
//	public void deberiaDevolverTrueSiTienenIgualCodigo() {
//		
//		final ModeloBaseAdministracionElectronicaFake documento2 = new ModeloBaseAdministracionElectronicaFake (CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
//		
//		final Boolean resultado = documento.equals(documento2);
//		
//		assertTrue(resultado);
//		
//	}
//	
//	
//	
//	@Test
//	public void deberiaDevolverFalseSiNoTienenIgualCodigo() {
//		
//		final ModeloBaseAdministracionElectronicaFake documento2 = new ModeloBaseAdministracionElectronicaFake (5, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION);
//		
//		final Boolean resultado = documento.equals(documento2);
//		
//		assertFalse(resultado);
//	}
//	
//	
//	@Test
//	public void deberiaDevolverFalseSiNoEsUnDocumento() {
//		final Boolean resultado = documento.equals(new Date());
//		assertFalse(resultado);
//	}
//	
//	@Test
//	public void deberiaDevolverHasCodeDelCodigo() {
//		final int resultado = documento.hashCode();
//		assertEquals(CODIGO_DOCUMENTO.hashCode(), resultado);
//	}


}
