package TB2.NewStructure.common.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.EigenesDorf;
import TB2.NewStructure.common.hibernate.service.BarbarendorfService;
import TB2.NewStructure.common.hibernate.service.DorfService;
import TB2.NewStructure.common.hibernate.service.EigenesDorfService;

public class TestDatabase {

	public static void main(String[] args) {

		System.out.println(getDistance(585, 562, 565, 593) * 14 - 420);

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		DorfService serviceDorf = (DorfService) context.getBean("dorfService");

		BarbarendorfService serviceBarbarendorf = (BarbarendorfService) context.getBean("barbarendorfService");

		Barbarendorf d = new Barbarendorf(412, 422, 5000);
		serviceBarbarendorf.saveDorf(d);

		Dorf test = new Dorf(412, 422, "wurst", 5000);
		serviceDorf.saveDorf(test);
		List<Barbarendorf> list = serviceBarbarendorf.findAll();

		d = (Barbarendorf) serviceBarbarendorf.findById(1);

		d.setX(413);
		serviceBarbarendorf.updateDorf(d);
		context.close();

	}

	public static boolean isodd(int value) {
		return value % 2 != 0;
	}

	public static double getDistance(int x1, int x2, int y1, int y2) {
		double z1 = isodd(y1) ? x1 + 0.5 : x1 - 0.5;
		double z2 = isodd(y2) ? x2 + 0.5 : x2 - 0.5;

		double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(y1 - y2, 2));
		double d2 = Math.sqrt(Math.pow((x1 - x2), 2) + 0.75 * Math.pow((y1 - y2), 2));
		return (d1 + d2) / 2;
	}
}
