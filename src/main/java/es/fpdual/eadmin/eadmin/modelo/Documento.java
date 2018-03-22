package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento extends ModeloBaseAdministracionElectronica {

//Creamos los atributos
	
	private Boolean publico;
	private EstadoDocumento estado;

//Constructor	
		
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado) {
		super(codigo, nombre, fechaCreacion);
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
