package es.fpdual.eadmin.eadmin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import es.fpdual.eadmin.eadmin.modelo.*;

public interface DocumentoMapper {

	int insertarDocumento(@Param ("documento")Documento documento);
	//añadimos este árbol de carpetas a src/main/resources
	//es/fpdual/eadmin/eadmin/mapper
	
	int eliminarDocumento(@Param ("codigo")int codigo);
	
	int modificarDocumento(@Param("documento")Documento documento);
	
	Documento seleccionarDocumento(@Param("codigo")int codigo);
	
	List<Documento> seleccionarTodosLosDocumentos();
	
	int seleccionarDocumentoCodigoMaximo();


	
	
}
