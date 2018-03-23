package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.List;

public class Expediente extends ModeloBaseAdministracionElectronica {

	private final Date fechaArchivo;
	private final Boolean publico;
	private final EstadoExpediente estado;
	
	private List <Documento> listaDocumentos;

	public Expediente(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Date fechaArchivo, Boolean publico,
			EstadoExpediente estado, List documentos) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion);

		this.fechaArchivo = fechaArchivo;
		this.publico = publico;
		this.estado = estado;
		this.listaDocumentos = documentos;
	}
	

	public Date getFechaArchivo() {
		return fechaArchivo;
	}

	public Boolean getPublico() {
		return publico;
	}

	public EstadoExpediente getEstado() {
		return estado;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}
	
	
	public void setDocumentos(List<Documento> documentos) {
		this.listaDocumentos = listaDocumentos;
	}


	@Override
	public int hashCode() {
		return codigo.hashCode()
				+ nombre.hashCode()
				+ fechaCreacion.hashCode()
				+ fechaUltimaActualizacion.hashCode()
				+ fechaArchivo.hashCode()
				+ publico.hashCode()
				+ estado.hashCode(); 
	}

	@Override
	public boolean equals(Object obj) {
				
		if (obj instanceof Expediente) {  
			return codigo.equals(((Expediente) obj).getCodigo())
				&& nombre.equals(((Expediente) obj).getNombre())
				&& fechaCreacion.equals(((Expediente) obj).getFechaCreacion())
				&& fechaUltimaActualizacion.equals(((Expediente) obj).getFechaUltimaActualizacion())
				&& fechaArchivo.equals(((Expediente) obj).getFechaArchivo())
				&& publico.equals(((Expediente) obj).getPublico())
				&& estado.equals(((Expediente) obj).getEstado());
						
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		return "Expediente con código " + codigo;
	}
	
	
	
	
	
	
}
