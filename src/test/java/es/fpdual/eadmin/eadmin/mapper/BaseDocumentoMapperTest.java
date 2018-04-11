package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

//como es la clase padre la declaramos como abstract para que no se pueda instanciar por otra clase

public abstract class BaseDocumentoMapperTest {
	
	private Documento documento;
	
	@Autowired
	private DocumentoMapper mapper;
	
	@Before
	public void inicializarEnCadaTest() {
		documento = new Documento (1, "Documento 1", new Date(), new Date(), true, EstadoDocumento.ACTIVO);
	}
	
	@Test
	public void deberiaInsertarUnDocumento() throws Exception{
		//DECLARACION ( en este caso se hace un método con @Before para que se ejecute en cada test ya que vamos a necesitar ese objeto en otros test
		
		final int resultado = this.mapper.insertarDocumento(this.documento);
		
		//VERIFICACION ->Utilizamos el assertThat para que sea más fácil de leer, ya que sería así-> Verifica que el resultado es 1
		assertThat(resultado, is(1));
		
	}
	
}
