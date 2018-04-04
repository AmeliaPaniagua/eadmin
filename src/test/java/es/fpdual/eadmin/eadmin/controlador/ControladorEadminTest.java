package es.fpdual.eadmin.eadmin.controlador;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;


public class ControladorEadminTest {

	private ControladorEadmin controlador;
	
	private final ServicioDocumento servicio = mock(ServicioDocumento.class);
	
	@Before
	public void inicializarEnCadaTest() {
		
		this.controlador = new ControladorEadmin(servicio);
		
	}
	
	@Test
	public void deberiaDevolverTodosLosDocumentos() {
		
		final List<Documento> documentos = new ArrayList<>();
		when(this.servicio.obtenerTodosLosDocumentos()).thenReturn(documentos);
		
		List<Documento> resultado =this.controlador.getTodosDocumento().getBody();
				
		assertSame(resultado, documentos);
		
		
	}
	
	@Test
	public void deberiaDevolverDocumentoPorCodigo() {
		
		
		//when(this.servicioDocumento.obtenerDocumentoPorCodigo(1)).thenReturn(DOCUMENTO);
		
		
	}
}
