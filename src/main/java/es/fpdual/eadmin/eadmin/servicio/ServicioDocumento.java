package es.fpdual.eadmin.eadmin.servicio;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface ServicioDocumento {

	Documento altaDocumento(Documento documento);

	void modificarDocumento(Documento documento);

	void eliminarDocumento(Integer codigo);

	

}
