package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class RepositorioExpedienteImplTest {
	
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final String NOMBRE_DOCUMENTO ="nombre";
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final Date FECHA_ARCHIVO = new Date();
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final EstadoExpediente ESTADO_EXPEDIENTE = EstadoExpediente.EN_TRAMITE;
	
	private static final List <Expediente> LISTA_EXPEDIENTE = new ArrayList<>();
	
	private static final Expediente EXPEDIENTE = new Expediente (CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, FECHA_ARCHIVO, DOCUMENTO_PUBLICO, ESTADO_EXPEDIENTE, LISTA_EXPEDIENTE);
	
	private RepositorioExpedienteImpl repositorio;
	
	
	public void inicializarCadaTest() {
		
		this.repositorio = new RepositorioExpedienteImpl();
		
	}
	
	

}
