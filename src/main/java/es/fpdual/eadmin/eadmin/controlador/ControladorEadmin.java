package es.fpdual.eadmin.eadmin.controlador;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

@RestController
public class ControladorEadmin {

	private final ServicioDocumento servicioDocumento;
	
	@Autowired
	public ControladorEadmin(ServicioDocumento servicioDocumento) {
		this.servicioDocumento = servicioDocumento;
		
	}
	
	
	//para probar en el navegador http://localhost:8080/eadmin/documentos
	@GetMapping(value="/eadmin/documentos")
	public ResponseEntity<List<Documento>> getTodosDocumento(){
	
		return new ResponseEntity<List<Documento>>(servicioDocumento.obtenerTodosLosDocumentos(),HttpStatus.OK);
	}
	
	
	//para probar en el navegador http://localhost:8080/eadmin/documentos/1
	@GetMapping(value="/eadmin/documentos/{codigo}")
	public ResponseEntity <Documento> getDocumento(@PathVariable("codigo") Integer codigo) {
		
		final Documento documento = this.servicioDocumento.obtenerDocumentoPorCodigo(codigo);
		
		if (Objects.nonNull(documento)) {
			return new ResponseEntity<Documento>(documento, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	//para probar en el navegador, desde google chrome añado la extensión Postman y desde ahí
	//en GET ponemos http://localhost:8080/eadmin/documentos/eliminar/1
	@PostMapping(value="eadmin/documentos/eliminar/{codigo}")
	public ResponseEntity<?> eliminarDocumento(@PathVariable("codigo") Integer codigo){
	
		this.servicioDocumento.eliminarDocumento(codigo);
	
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
