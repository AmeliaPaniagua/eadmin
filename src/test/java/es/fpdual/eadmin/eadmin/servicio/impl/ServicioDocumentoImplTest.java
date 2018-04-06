package es.fpdual.eadmin.eadmin.servicio.impl;

import org.junit.*;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

public class ServicioDocumentoImplTest {

	
	private ServicioDocumentoImpl servicioDocumento;
	
	//mock objeto vacío que sino lo entrenas no hace nada, es tonto
	//spy es un objeto real pero lo entrenas para que no haga lo que ya está probado
	private Documento DOCUMENTO = mock(Documento.class);
	
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	
	@Before
	public void inicializarEnCadaTest() {
		//spy es un servicio(objeto) de verdad pero que podamos modificar su comportamiento en un momento concreto
		this.servicioDocumento = spy(new ServicioDocumentoImpl(repositorioDocumento));
		
	}
		
	@Test
	public void deberiaAlmacenarUnDocumento() {
				
		final Documento documentoModificado = mock(Documento.class);
		
		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaCorrecta(DOCUMENTO);
		
		final Documento resultado = this.servicioDocumento.altaDocumento(DOCUMENTO); 
			 
		verify(this.repositorioDocumento).altaDocumento(documentoModificado);
		
		assertSame(resultado, documentoModificado);
		 
	}
		
	@Test
	public void deberiaModificarUnDocumento() {	
		
		final Documento documentoModificado = mock(Documento.class);
		
		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaUltimaActualizacion(DOCUMENTO);
		
		final Documento resultado = this.servicioDocumento.modificarDocumento(DOCUMENTO); 
			 
		verify(this.repositorioDocumento).modificarDocumento(documentoModificado);
		
		assertSame(resultado, documentoModificado);
		
	}

	@Test
	public void deberiaEliminarUnDocumento() {
		
		when (DOCUMENTO.getCodigo()).thenReturn(1);
		//entrando a mockito para que le dé el valor que quiero, ya que ese DOCUMENTO es de mentira
		//cuando se pida DOCUMENTO.getCodigo() va a devolver CODIGO_DOCUMENTO que le hemos dado el valor 1
		this.servicioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());
		
		verify(this.repositorioDocumento).eliminarDocumento(1);
		 
	}
	
	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		
		when(repositorioDocumento.obtenerDocumentoPorCodigo(1)).thenReturn(DOCUMENTO);
		
		final Documento resultado = servicioDocumento.obtenerDocumentoPorCodigo(1);
		
		//se usa el assertSame para comparar con que es el mismo mismo en la instancia, no mira por código 
		//que es lo que haría el equals
		assertSame(resultado, DOCUMENTO);
		
	}
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() {
		
		final List<Documento> documentos = new ArrayList<>();
		
		when(repositorioDocumento.obtenerTodosLosDocumentos()).thenReturn(documentos);

		final List<Documento> resultado = this.servicioDocumento.obtenerTodosLosDocumentos();
		
		assertSame(resultado, documentos);
		
		
	}
	
	
}
