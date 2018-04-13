package es.fpdual.eadmin.eadmin.modelo;

import java.util.*;

public enum EstadoDocumento {

	ACTIVO(1), APROBADO(2), ELIMINADO(3);
	///esto es como si hicieramos 
	//public static final EstadoDocumento ACTIVO = new EstadoDocumento(1)
	
	private final int codigo;
	
	
	private EstadoDocumento (int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public static EstadoDocumento obtenerPorCodigo(Integer codigo) {
		
		return Arrays.asList(EstadoDocumento.values()).stream()
		.filter(codigoLista -> codigoLista.getCodigo() == codigo.intValue()).findAny().orElse(null);
		
		
//		switch (codigo) {
//		case 1:
//			this.estado =  EstadoDocumento.ACTIVO;
//			break;
//		case 2:
//			this.estado = EstadoDocumento.APROBADO;
//			break;
//		case 3:
//			this.estado = EstadoDocumento.ELIMINADO;
//			break;	
		
		
	}
	
// SE PUEDE HACER ASÍ TAMBIEN
//	public static EstadoDocumento obtenerPorCodigo(Integer codigo) {
//		
//		return Arrays.asList(EstadoDocumento.values()).stream()
//		.filter(estado -> esIgual(estado,codigo)).findAny().orElse(null);
//	}
//	
//	public static boolean esIgual (EstadoDocumento estado, Integer codigo)
//	{
//		return estado.getCodigo() == codigo.intValue();
//	}
	
	
}
