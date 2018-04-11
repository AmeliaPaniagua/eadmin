package es.fpdual.eadmin.eadmin.mapper;

import org.apache.ibatis.annotations.*;

import es.fpdual.eadmin.eadmin.modelo.*;

public interface DocumentoMapper {

	int insertarDocumento(@Param ("documento")Documento documento);
	//añadimos este árbol de carpetas a src/main/resources
	//es/fpdual/eadmin/eadmin/mapper
	
}
