package es.fpdual.eadmin.eadmin.mapper.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;


@MappedTypes(EstadoDocumento.class) //cuando encuentre un EstadoDocumento tiene que utilizar este TypeHandler
public class EstadoDocumentoTypeHandler implements TypeHandler<EstadoDocumento> {

	@Override
	public void setParameter(PreparedStatement ps, int columnIndex, EstadoDocumento estadoDocumento, JdbcType jdbcType)
			throws SQLException {
		
		ps.setInt(columnIndex, estadoDocumento.getCodigo());
		
	}
	
	@Override
	public EstadoDocumento getResult(ResultSet rs, String columnName) throws SQLException {

		return EstadoDocumento.obtenerPorCodigo(rs.getInt(columnName));
	}

	@Override
	public EstadoDocumento getResult(ResultSet rs, int columnIndex) throws SQLException {
		
		return EstadoDocumento.obtenerPorCodigo(rs.getInt(columnIndex));
	}

	@Override
	public EstadoDocumento getResult(CallableStatement cs, int columnIndex) throws SQLException {
		
		return EstadoDocumento.obtenerPorCodigo(cs.getInt(columnIndex));
	}

	

	

}
