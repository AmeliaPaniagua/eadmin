package es.fpdual.eadmin.eadmin.servicio.impl;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

public class ServicioDocumentoImplTest {
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	
	private ServicioDocumento servicioDocumento;
	
	private Documento DOCUMENTO = mock(Documento.class);
	
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	
	@Before
	public void inicializarEnCadaTest() {
		
		this.servicioDocumento = new ServicioDocumentoImpl(repositorioDocumento);
		
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		
		//entrenando a mockito con mis valores de mentira		
		when(DOCUMENTO.getCodigo()).thenReturn(CODIGO_DOCUMENTO);
		when(DOCUMENTO.getNombre()).thenReturn(NOMBRE_DOCUMENTO);
		when(DOCUMENTO.getFechaCreacion()).thenReturn(new Date(1/1/2018));
		when(DOCUMENTO.getFechaUltimaActualizacion()).thenReturn(new Date(1/2/2018));
		
		final Documento resultado = this.servicioDocumento.altaDocumento(DOCUMENTO); 
		//any() -> se le pasa cualquier documento
			 
		verify(this.repositorioDocumento).altaDocumento(any());
		
		assertEquals(resultado.getCodigo(), DOCUMENTO.getCodigo());
		assertEquals(resultado.getNombre(), DOCUMENTO.getNombre());
		assertNotEquals(resultado.getFechaCreacion(), DOCUMENTO.getFechaCreacion());
		 
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		
		this.servicioDocumento.modificarDocumento(DOCUMENTO);
		verify(this.repositorioDocumento).modificarDocumento(DOCUMENTO);
		 
	}

	@Test
	public void deberiaEliminarUnDocumento() {
		
		when (DOCUMENTO.getCodigo()).thenReturn(CODIGO_DOCUMENTO);
		//entrando a mockito para que le dé el valor que quiero, ya que ese DOCUMENTO es de mentira
		//cuando se pida DOCUMENTO.getCodigo() va a devolver CODIGO_DOCUMENTO que le hemos dado el valor 1
		this.servicioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());
		
		verify(this.repositorioDocumento).eliminarDocumento(CODIGO_DOCUMENTO);
		 
	}
	
	
}
