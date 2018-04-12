package es.fpdual.eadmin.eadmin.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilidades {
	
	private Utilidades() {
		//Clase de utilidad no instanciable
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
	}
	
}
