package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class RepositorioDocumentoImplTest {

	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final EstadoDocumento ESTADO_DOCUMENTO = EstadoDocumento.ACTIVO;
	
	private static final Documento documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, ESTADO_DOCUMENTO);
	
	
	private RepositorioDocumentoImpl repositorio;
	
	//Creamos un documento mapper 
	private DocumentoMapper mapper;
	
	@Before
	public void inicializarCadaTest() {
		
		//Mockeamos el DocumentoMapper
		mapper = mock(DocumentoMapper.class);
		//se lo damos a nuestro RepositorioDocumentoImpl el mapper mockeado
		this.repositorio = new RepositorioDocumentoImpl(this.mapper);
		
	}	
	
	@Test
	public void probarAltaDocumento() {
		
		//PRUEBA
		this.repositorio.altaDocumento(this.documento);
		
		//VERIFICACION  -> verifica 
		verify(this.mapper).insertarDocumento(this.documento);                            
		
	}
	
	
	@Test
	public void probarModificarDocumento() {
		
		//ENTRENAMIENTO
		when(mapper.modificarDocumento(this.documento)).thenReturn(1);
		
		//PRUEBA
		this.repositorio.modificarDocumento(this.documento);
		
		//VERIFICACION
		verify(this.mapper).modificarDocumento(this.documento);  

	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deberiaLanzarExcepcionSiIntentamosModificarUnDocumentoQueNoExiste() {
		
		//ENTRENAMIENTO
		when(this.mapper.modificarDocumento(this.documento)).thenReturn(0);
		
		//PRUEBA
		this.repositorio.modificarDocumento(this.documento);
		
	}
	
	
	
	@Test
	public void deberiaEliminarDocumento() {
		
		//PRUEBA
		this.repositorio.eliminarDocumento(this.documento.getCodigo());
		
		//VERIFICACION
		verify(this.mapper).eliminarDocumento(this.documento.getCodigo());  
	}
	
	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		//DECLARACION
		
		//ENTRENAMIENTO
		when(this.mapper.seleccionarDocumento(CODIGO_DOCUMENTO)).thenReturn(this.documento);
		
		//PRUEBA
		final Documento resultado = this.repositorio.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		//VERIFICACION
		assertThat(resultado, is(this.documento));
	}
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() {
		//DECLARACION
		final List<Documento> todosLosDocumentos = Arrays.asList(this.documento);		
		
		//ENTRENAMIENTO
		when(this.mapper.seleccionarTodosLosDocumentos()).thenReturn(todosLosDocumentos);
		
		//PRUEBA
		final List<Documento> resultado = this.repositorio.obtenerTodosLosDocumentos();
		
		//VERIFICACION
		assertThat(resultado, hasSize(1));
		assertThat(resultado, hasItem(this.documento));
	}
	

}
