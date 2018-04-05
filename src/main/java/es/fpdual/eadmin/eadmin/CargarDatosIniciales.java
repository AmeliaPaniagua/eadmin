package es.fpdual.eadmin.eadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Component
public class CargarDatosIniciales implements ApplicationRunner{

	private final RepositorioDocumento repositorioDocumento;
	private final RepositorioExpediente repositorioExpediente;
	private static final Date FECHA = new Date();
	private List<Documento> lista = new ArrayList<>();
	
	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento, RepositorioExpediente repositorioExpediente) {
		this.repositorioDocumento = repositorioDocumento;
		this.repositorioExpediente = repositorioExpediente;
		
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.repositorioDocumento.altaDocumento(new Documento(1, "doc1", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(2, "doc2", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(3, "doc3", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(4, "doc4", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(5, "doc5", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		
		this.repositorioDocumento.crearFicheroDocumentos();
		
		this.repositorioDocumento.modificarDocumento(new Documento(2, "doc2_Modificado", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.modificarDocumento(new Documento(4, "doc4_Modificado", FECHA, FECHA, true, EstadoDocumento.ACTIVO ));
		
		this.repositorioDocumento.crearFicheroDocumentos();
		
		this.repositorioDocumento.eliminarDocumento(1);
		this.repositorioDocumento.eliminarDocumento(3);
		this.repositorioDocumento.eliminarDocumento(5);
		
		this.repositorioDocumento.crearFicheroDocumentos();
		
		
		//Expedientes
		this.repositorioExpediente.altaExpediente(new Expediente(1, "exp1", FECHA, FECHA, FECHA, true, EstadoExpediente.INICIADO, lista));
		this.repositorioExpediente.altaExpediente(new Expediente(2, "exp2", FECHA, FECHA, FECHA, true, EstadoExpediente.INICIADO, lista));
		this.repositorioExpediente.altaExpediente(new Expediente(3, "exp3", FECHA, FECHA, FECHA, true, EstadoExpediente.INICIADO, lista));
		
		
	}

}
