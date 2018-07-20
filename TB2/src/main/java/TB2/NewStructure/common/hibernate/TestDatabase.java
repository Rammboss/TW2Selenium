package TB2.NewStructure.common.hibernate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.configuration.HibernateConfiguration;
import TB2.NewStructure.common.hibernate.model.Point;
import TB2.NewStructure.common.hibernate.service.PointService;

public class TestDatabase {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PointService servicePoint = (PointService) context.getBean("pointService");
		
		
		
		for (Point p : servicePoint.findAll()) {
			
			System.out.println(p.getCheckedAt().plusHours(24) + "is Before" + new LocalDateTime());
			System.out.println(p.getCheckedAt().plusHours(24).isBefore(new LocalDateTime()));
		}
		

		

		context.close();

	}

	

}
