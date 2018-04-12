package es.fpdual.eadmin.eadmin.mapper;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-configuracion/eadmin-persistencia.xml", 
		"classpath:spring-configuracion/eadmin-sqlserver-persistencia.xml" })

//esta anotación es para que no se haga el commit automático, sino que retrocede solo lo prueba 
//y no lo ejecuta, así podremos probarlo todas las veces que queramos
@Rollback 
public class SQLServerDocumentoMapperTest_IT extends BaseDocumentoMapperTest {

	
	
}
