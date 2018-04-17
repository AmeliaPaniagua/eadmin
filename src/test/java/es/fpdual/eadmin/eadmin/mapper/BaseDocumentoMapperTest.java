package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.util.Utilidades;

//como es la clase padre la declaramos como abstract para que no se pueda instanciar por otra clase

 //@Transactional -> todos los métodos que tenga esta clase tienen que ejecutarse en una transacción
				//si quisiera que fuera un sólo método lo pondría encima del método concreto
				//se le pone en concreto la transacción que queremos abrir, que la tenemos en eadmin-persistencia.xml
@Transactional("eadminTransactionManager")
public abstract class BaseDocumentoMapperTest {
	
	private Documento documento;
	
	@Autowired
	private DocumentoMapper mapper;
	
	@Before
	public void inicializarEnCadaTest() {
		documento = new Documento (1, "Documento 1", Utilidades.asDate(LocalDate.of(2015, 2, 1)), Utilidades.asDate(LocalDate.of(2015, 2, 2)), true, EstadoDocumento.ACTIVO);
	}
	
	
	@Test
	public void deberiaInsertarUnDocumento() throws Exception{
		//DECLARACION ( en este caso se hace un método con @Before para que se ejecute en cada test ya que vamos a necesitar ese objeto en otros test
		
		//ENTRENAMIENTO
		
		final int resultado = this.mapper.insertarDocumento(this.documento);
		
		//VERIFICACION ->Utilizamos el assertThat para que sea más fácil de leer, ya que sería así-> Verifica que el resultado es 1
		assertThat(resultado, is(1));
		
	}
	
	@Test
	public void deberiaEliminarUnDocumento() {
		//DECLARACION
		
		//ENTRENAMIENTO
		this.mapper.insertarDocumento(this.documento);
		
		//PRUEBA
		final int resultado = this.mapper.eliminarDocumento(1);
		
		//VERIFICACIÓN
		assertThat(resultado, is(1));		
		
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		//DECLARACION
		final Documento documentoActualizado = new Documento (1, "Documento_MODIFICADO",  Utilidades.asDate(LocalDate.of(2015, 3, 1)), Utilidades.asDate(LocalDate.of(2015, 5, 1)), true, EstadoDocumento.ACTIVO);
		
		//ENTRENAMIENTO
		this.mapper.insertarDocumento(documento);
		
		//PRUEBA
		final int resultado = this.mapper.modificarDocumento(documentoActualizado);
		
		//VERIFICACIÓN
		
		assertThat(resultado, is(1));
		
		final Documento documentoModificado = this.mapper.seleccionarDocumento(1);
		assertThat(documentoModificado, is(documentoActualizado));
		
		
	}
	
	@Test
	public void deberiaRecuperarUnDocumentoPorCodigo() {
		//DECLARACION
		
		//ENTRENAMIENTO
		this.mapper.insertarDocumento(this.documento);
		
		final Documento resultado = this.mapper.seleccionarDocumento(1);
		
		assertThat(resultado,is(documento));
		
	}
	
	public void deberiaRecuperarTodosLosDocumento()throws Exception{
		
		//DECLARACION
		final Documento documento2 = new Documento(2, "Documento2",
				Utilidades.asDate(LocalDate.of(2015, 3, 1)), Utilidades.asDate(LocalDate.of(2015, 5, 1)), false,
				EstadoDocumento.ACTIVO);

		// ENTRENAMIENTO
		this.mapper.insertarDocumento(this.documento);
		this.mapper.insertarDocumento(documento2);

		// PRUEBA
	
		
		final List<Documento> resultado = this.mapper.seleccionarTodosLosDocumentos();
		
		// VERIFICACIÓN
		assertThat(resultado.size(), is(2));
		 //esto es lo mismo que lo de arriba
		assertThat(resultado, hasSize(2)); //comprobar que la longitud es 2
		
		//Verifica que dentro de la lista están los dos documentos
		assertThat(resultado, hasItems(this.documento, documento2));
		
	}
	
	@Test
	public void deberiaObtener1CuandoNoHayElementosAlCalcularElMaximoCodigo() {
		
		final int resultado = this.mapper.seleccionarDocumentoCodigoMaximo();
		
		assertThat(resultado, is(1));
		
	}
	
	@Test
	public void deberiaDevolverElSiguienteIdentificador() {
		
		this.mapper.insertarDocumento(this.documento);
		
		final int resultado = this.mapper.seleccionarDocumentoCodigoMaximo();
		
		assertThat(resultado, is(2));
		
	}
	
	
	
//	@Test
//	public void deberiaSeleccionarUnDocumento() {
//		
//		this.mapper.insertarDocumento(documento);
//		
//		final Documento resultado = this.mapper.seleccionarDocumento(documento);
//		
//		assertThat(resultado,is(documento));
//	}
	
	
//	@Test
//	public void deberiaSeleccionarUnDocumento() {
//		
//		this.mapper.insertarDocumento(documento);
//		
//		final int resultado = this.mapper.seleccionarDocumento(1);
//		
//		assertThat(resultado,is(1));
//	}
//	
	
}
