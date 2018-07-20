package TB2.NewStructure.common.hibernate.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import TB2.NewStructure.common.hibernate.configuration.AppConfig;
import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.Point;
import TB2.NewStructure.common.hibernate.service.PointService;

@Repository("pointDao")
public class PointDaoImpl extends AbstractDao implements PointDao {

	public void savePoint(Point dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<Point> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Point.class);

		return criteria.add(Restrictions.like("name", name)).list();
	}

	public void updatePoint(Point dorf) {
		getSession().update(dorf);

	}

	@SuppressWarnings("unchecked")
	public Point findById(int id) {
		return (Point) getSession().get(Point.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Point> findAll() {
		Criteria criteria = getSession().createCriteria(Point.class);
		List<Point> list = criteria.list();

		return list;
	}
	@Transactional
	public void initPoints() {
		Transaction tx = getSession().getTransaction();
		
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
			savePoint(point);
			i++;
			if (i % 500 == 0) {
				System.out.println(i);

			}
			if( i % 20000 == 0 ) { // Same as the JDBC batch size
			      //flush a batch of inserts and release memory:
				getSession().flush();
				getSession().clear();
				tx.commit();


			   }
//			if ((i % 100000) == 0) {
//				context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//				servicePoint = (PointService) context.getBean("pointService");
//			}

		}
		tx.commit();
		getSession().close();
		
	
		
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