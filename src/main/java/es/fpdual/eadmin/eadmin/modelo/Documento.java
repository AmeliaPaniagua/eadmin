package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento extends ModeloBaseAdministracionElectronica {

//Creamos los atributos
	
	private final Boolean publico;
	private final EstadoDocumento estado;

//Constructor	
		
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico, EstadoDocumento estado) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion);
		this.publico = publico;
		this.estado = estado;
		
	}


	//getter	

	public Boolean getPublico() {
		return publico;
	}

	public EstadoDocumento getEstado() {
		return estado;
	}
	
	
	
	
}
