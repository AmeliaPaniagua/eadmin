package es.fpdual.eadmin.eadmin.modelo;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

//	public Documento(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico, Integer estado) {
//		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion);
//		this.publico = publico;
//		
//		//Esta parte se podría hacer con un switch o un if como lo tenemos abajo
//		//si me vine el codigo, se obtiene sino devuelve nulo
//		//EstadoDocumento::obtenerPorCodigo   -_esto es una landa
//		this.estado = Optional.ofNullable(estado).map(EstadoDocumento::obtenerPorCodigo).orElse(null);
//		
//	
//		
////		switch (estado) {
////			case 1:
////				this.estado =  EstadoDocumento.ACTIVO;
////				break;
////			case 2:
////				this.estado = EstadoDocumento.APROBADO;
////				break;
////			case 3:
////				this.estado = EstadoDocumento.ELIMINADO;
////				break;		
////		}		
//		
//	}
	

	//getter	

	public Boolean getPublico() {
		return publico;
	}

	public EstadoDocumento getEstado() {
		return estado;
	}
	
	@Override
	public boolean equals(Object obj) {
				
		if (obj instanceof Documento) {  //instanceof -> si el objeto tiene la misma instancia que el objeto de la clase Documento
			
			final Documento  param  = (Documento)obj;
			final EqualsBuilder equalsBuilder = new EqualsBuilder();
			
			equalsBuilder.appendSuper(super.equals(param));
	
			equalsBuilder.append(this.estado, param.estado);
			
			return equalsBuilder.isEquals();
			
			//-> por si queremos comparar tambien por el nombre, si el codigo y el nombre son iguales
			//pues el objeto sería el mismo
		}
		
		return false;
		
	}
	
	
	@Override
	public int hashCode() {
		
		final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		
		hashCodeBuilder.appendSuper(super.hashCode());
		
		hashCodeBuilder.append(this.estado);
		
		return hashCodeBuilder.toHashCode();
				
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
