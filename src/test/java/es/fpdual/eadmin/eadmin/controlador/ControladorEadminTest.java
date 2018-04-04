package es.fpdual.eadmin.eadmin.controlador;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
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
	private final Documento DOCUMENTO = new Documento(1, "nombre", new Date(), new Date(), true,
EstadoDocumento.ACTIVO);
	
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
				
		when(this.servicio.obtenerDocumentoPorCodigo(1)).thenReturn(DOCUMENTO);
		
		Documento resultado = controlador.getDocumento(1).getBody();
		
		assertSame(resultado,DOCUMENTO);
				
	}
	
	@Test
	public void deberiaEliminarDocumento() {
		
		ResponseEntity<?> resultado = controlador.eliminarDocumento(1);
		assertEquals(resultado.getStatusCode(), HttpStatus.OK);
		verify(this.servicio).eliminarDocumento(1);
		
	}
}
