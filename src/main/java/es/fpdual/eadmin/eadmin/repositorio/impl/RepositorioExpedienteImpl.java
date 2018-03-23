package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

public class RepositorioExpedienteImpl implements RepositorioExpediente{

	private final List <Expediente> expedientes = new ArrayList<>();
	
	//getter
		public List<Expediente> getExpedientes() {
			return expedientes;
		}
	
	@Override
	public void altaExpediente(Expediente expediente) {

		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		expedientes.add(expediente);
	}

	@Override
	public void modificarExpediente(Expediente expediente) {

		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente que quiere modificar no existe");
		}
		expedientes.set(expedientes.indexOf(expediente), expediente);
		
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {

		Optional<Expediente> expedienteEncontrado =
				expedientes.stream().filter(d ->tieneIgualCodigo(d, codigoExpediente)).findFirst();
				if (expedienteEncontrado.isPresent()) { // comprueba si el objeto es nulo o no
					expedientes.remove(expedienteEncontrado);
				}	
	}

	protected boolean tieneIgualCodigo(Expediente expediente, Integer codigoExpediente) {
		
		return expediente.getCodigo().equals(codigoExpediente);
		
	}
	
	
}
