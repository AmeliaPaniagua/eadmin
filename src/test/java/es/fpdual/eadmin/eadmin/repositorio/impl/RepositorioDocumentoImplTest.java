package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.Date;

import org.junit.Before;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class RepositorioDocumentoImplTest {

private static final Date FECHA_CREACION = new Date();
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	
	private Documento documento;
	private RepositorioDocumentoImpl repositorio;
	
	@Before
	public void inicializarCadaTest() {
		
		documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
		
		repositorio = new RepositorioDocumentoImpl();
		
		
	}	
	
	public void probarAltaDocumento() {
		
		repositorio.altaDocumento(documento);
		
		
	}
	
	
}
