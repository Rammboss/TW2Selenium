package TB2.NewStructure.common.hibernate;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.dao.PointDao;
import TB2.NewStructure.common.hibernate.model.Point;

public class TestDatabase {

	private static Logger logger = LoggerFactory.getLogger(TestDatabase.class);

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PointDao servicePoint = context.getBean(PointDao.class);

		for (Point p : servicePoint.findAll()) {

			logger.info(p.getCheckedAt().plusHours(24) + "is Before" + LocalDateTime.now());
			logger.info("" + p.getCheckedAt().plusHours(24).isBefore(LocalDateTime.now()));
		}

		context.close();

	}

}
