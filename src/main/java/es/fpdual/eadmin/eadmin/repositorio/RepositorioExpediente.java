package es.fpdual.eadmin.eadmin.repositorio;

import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {

	void altaExpediente(Expediente expediente);
	
	void modificarExpediente(Expediente expediente);
	
	void eliminarExpediente(Integer codigoExpediente);
	
}
