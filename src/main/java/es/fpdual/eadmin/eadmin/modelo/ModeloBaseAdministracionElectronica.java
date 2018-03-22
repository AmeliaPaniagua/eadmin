package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public abstract class ModeloBaseAdministracionElectronica {

	protected Integer codigo;
	protected String nombre;
	protected Date fechaCreacion;
	protected Date fechaUltimaActualizacion;
	
	
	public ModeloBaseAdministracionElectronica(Integer codigo, String nombre, Date fechaCreacion, Date fechUltimaActualizacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	
	//Definimos el equals y el hashCode
	

		@Override
		public int hashCode() {
			return codigo.hashCode(); //+ nombre.hashCode() -> por si queremos añadirle el nombre también
		}

		@Override
		public boolean equals(Object obj) {
					
			if (obj instanceof ModeloBaseAdministracionElectronica) {  //instanceof -> si el objeto tiene la misma instancia que el objeto de la clase Documento
				return codigo.equals(((ModeloBaseAdministracionElectronica) obj).getCodigo());
				//&& nombre.equals(((Documento) obj).getNombre())-> por si queremos comparar tambien por el nombre, si el codigo y el nombre son iguales
				//pues el objeto sería el mismo
			}
			
			return false;
			
		}
		
		@Override
		public String toString() {
			return "Documento con código " + codigo;
		}
	
}
