package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento {

//Creamos los atributos
	private Integer codigo;
	private String nombre;
	private Date fechaCreacion;
	private Boolean publico;
	private EstadoDocumento estado;

//Constructor	
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Boolean publico, EstadoDocumento estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.publico = publico;
		this.estado = estado;
	}
		
//getter	
	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Boolean getPublico() {
		return publico;
	}

	public EstadoDocumento getEstado() {
		return estado;
	}
	
//Definimos el equals y el hashCode
	
	@Override
	public int hashCode() {
		return codigo.hashCode(); //+ nombre.hashCode() -> por si queremos añadirle el nombre también
	}

	@Override
	public boolean equals(Object obj) {
				
		if (obj instanceof Documento) {  //instanceof -> si el objeto tiene la misma instancia que el objeto de la clase Documento
			return codigo.equals(((Documento) obj).getCodigo());
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
