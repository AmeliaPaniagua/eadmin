package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.List;

public class Expediente {

	private Integer codigo;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaArchivo;
	private Boolean publico;
	private EstadoExpediente estado;
	
	private List <Documento>documentos;

	public Expediente(Integer codigo, String nombre, Date fechaCreacion, Date fechaArchivo, Boolean publico,
			EstadoExpediente estado, List documentos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaArchivo = fechaArchivo;
		this.publico = publico;
		this.estado = estado;
		this.documentos = documentos;
	}

	
	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
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

	public List getDocumentos() {
		return documentos;
	}
	
	public int hashCode() {
		return codigo.hashCode()
				+ nombre.hashCode()
				+ fechaCreacion.hashCode()
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