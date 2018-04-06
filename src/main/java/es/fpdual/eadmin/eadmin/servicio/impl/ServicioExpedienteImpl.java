package es.fpdual.eadmin.eadmin.servicio.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.modelo.builder.ExpedienteBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;

@Service
public class ServicioExpedienteImpl implements ServicioExpediente {

	RepositorioExpediente repositorioExpediente;
	
	@Autowired
	public ServicioExpedienteImpl (RepositorioExpediente repositorioExpediente) {
		this.repositorioExpediente = repositorioExpediente;
	}
	
	@Override
	public Expediente altaExpediente(Expediente expediente) {
		
		final Expediente expedienteModificado = this.obtenerExpedienteConFechaCorrecta(expediente);
		
		repositorioExpediente.altaExpediente(expedienteModificado);
		
		return expedienteModificado;
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {
		
		final Expediente expedienteModificado = this.obtenerExpedienteConFechaUtimaActualizacion(expediente);
		
		repositorioExpediente.modificarExpediente(expedienteModificado);
		
		return expedienteModificado;
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {
		
		repositorioExpediente.eliminarExpediente(codigoExpediente);
		
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		
		return repositorioExpediente.asociarDocumentoAlExpediente(codigoExpediente, documento);
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		
		return repositorioExpediente.desasociarDocumentoDelExpediente(codigoExpediente, codigoDocumento);
	}

	protected Expediente obtenerExpedienteConFechaCorrecta(Expediente expediente) {
		
		return new ExpedienteBuilder().clonar(expediente).
				conFechaCreacion(dameFechaActual()).construir();
	}
	
	protected Expediente obtenerExpedienteConFechaUtimaActualizacion(Expediente expediente) {
		
		return new ExpedienteBuilder().clonar(expediente).
				conFechaUltimaActualizacion(dameFechaActual()).construir();
		
	}
	
	
	protected Date dameFechaActual() {
		
		return new Date();//Devuelve la fecha actual
	}
}
