package es.fpdual.eadmin.eadmin.servicio.impl;

import org.junit.*;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

public class ServicioExpedienteImplTest {

	
	private ServicioExpedienteImpl servicioExpediente;
	
	private Expediente expediente = mock(Expediente.class);
	
	private final RepositorioExpediente repositorioExpediente = mock(RepositorioExpediente.class);
	
	@Before
	public void inicializarEnCadaTest() {
		
		this.servicioExpediente = spy(new ServicioExpedienteImpl(repositorioExpediente));
		
	}
	
	@Test
	public void deberiaDarAltaUnExpediente() {
		
		final Expediente expedienteModificado = mock(Expediente.class);
		
		doReturn(expedienteModificado).when(this.servicioExpediente).obtenerExpedienteConFechaCorrecta(expediente);
		
		final Expediente resultado = this.servicioExpediente.altaExpediente(expediente);
		
		verify(this.repositorioExpediente).altaExpediente(expedienteModificado);
		
		assertSame(resultado, expedienteModificado);
		
	}
	
	@Test
	public void deberiaModificarUnExpediente() {
		
		final Expediente expedienteModificado = mock(Expediente.class);
		
		doReturn(expedienteModificado).when(this.servicioExpediente).obtenerExpedienteConFechaUtimaActualizacion(expediente);
		
		final Expediente resultado = this.servicioExpediente.modificarExpediente(expediente);
		
		verify(this.repositorioExpediente).modificarExpediente(expedienteModificado);
		
		assertSame(resultado, expedienteModificado);
		
	}
	
	@Test
	public void deberiaEliminarUnExpediente() {
		
		when (expediente.getCodigo()).thenReturn(1);
				
		this.servicioExpediente.eliminarExpediente(expediente.getCodigo());
		
		verify(this.repositorioExpediente).eliminarExpediente(1);
		
	}
	
	@Test
	public void deberiaAsociarDocumentoAlExpediente() {
		
		
	}
	
	@Test
	public void deberiaDesasociarDocumentoDelExpediente() {
		
		
	}
	
	
}
