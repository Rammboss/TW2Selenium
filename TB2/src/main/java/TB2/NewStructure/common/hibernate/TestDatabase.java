package TB2.NewStructure.common.hibernate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.configuration.HibernateConfiguration;
import TB2.NewStructure.common.hibernate.model.Point;
import TB2.NewStructure.common.hibernate.service.PointService;

public class TestDatabase {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		
		HibernateConfiguration h = new HibernateConfiguration();
		Session session =  (Session) h.sessionFactory().getConfiguration().configure().buildSessionFactory() ;
		Transaction tx = session.beginTransaction();
		   
		for ( int i=0; i<100000; i++ ) {
		    Point p = new Point(1, 1, false);
		    session.save(p);
		    if ( i % 1000 == 0 ) { //20, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
		    }
		}
		   
		tx.commit();
		session.close();
		


		PointService servicePoint = (PointService) context.getBean("pointService");

		List<Point> pointlist = new ArrayList<Point>();

		for (int x = 0; x <= 1000; x++) {

			for (int y = 0; y <= 1000; y++) {
				pointlist.add(new Point(x, y, false));
			}

		}

		pointlist.sort(new Comparator<Point>() {

			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return getDistance((int) o1.getX(), 585, (int) o1.getY(), 565)
						- getDistance((int) o2.getX(), 585, (int) o2.getY(), 565);
			}
		});
		int i = 0;
		for (Point point : pointlist) {
			servicePoint.savePoint(point);
			i++;
			if ((i % 100000) == 0) {
				context = new AnnotationConfigApplicationContext(AppConfig.class);

				servicePoint = (PointService) context.getBean("pointService");
			}

		}

		context.close();

	}

	public static boolean isodd(int value) {
		return value % 2 != 0;
	}

	public static int getDistance(int x1, int x2, int y1, int y2) {
		double z1 = isodd(y1) ? x1 + 0.5 : x1 - 0.5;
		double z2 = isodd(y2) ? x2 + 0.5 : x2 - 0.5;

		double d1 = Math.sqrt(Math.pow((z1 - z2), 2) + 0.75 * Math.pow(y1 - y2, 2));
		double d2 = Math.sqrt(Math.pow((x1 - x2), 2) + 0.75 * Math.pow((y1 - y2), 2));
		int erg = (int) ((d1 + d2) / 2 * 10000);
		return erg;
	}

}
