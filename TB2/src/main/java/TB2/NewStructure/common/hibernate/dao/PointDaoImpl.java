package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.Point;

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

}