package TB2.NewStructure.common.hibernate;

import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.model.Analyser;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.service.AnalyserService;
import TB2.NewStructure.common.hibernate.service.DorfService;


public class TestDatabase {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 
        AnalyserService service = (AnalyserService) context.getBean("analyserService");
        
        DorfService serviceDorf = (DorfService) context.getBean("dorfService");
        Dorf d = new Dorf(412, 422, "Babarendorf", new LocalDateTime());
        serviceDorf.saveDorf(d);
//        Dorf d = (Dorf) serviceDorf.findById(3);
//        d.setX(413);
//        serviceDorf.updateDorf(d);
        
        Analyser a = service.findById(1);
        a.setX(412);
        a.setY(422);
        a.setBreite(1);
        a.setRichtung(1);
        a.setRichtungscounter(0);
        service.updateAnalyser(a);
        context.close();

	}

}
