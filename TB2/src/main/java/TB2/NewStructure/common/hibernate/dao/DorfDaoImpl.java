package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Dorf;

@Repository("dorfDao")
public class DorfDaoImpl extends AbstractDao implements DorfDao {

	public void saveDorf(Dorf dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<Dorf> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Dorf.class);

		return criteria.add(Restrictions.like("name", name)).list();
	}

	@SuppressWarnings("unchecked")
	public Dorf findByXandY(int x, int y) {
		Criteria criteria = getSession().createCriteria(Dorf.class);

		List<Dorf> list = criteria.add(Restrictions.like("x", x)).add(Restrictions.like("y", y)).list();

		if (list.size() == 1) {
			return (Dorf) list.get(0);

		}else {
			return new Dorf(-1, -1, "nichts", -1);
		}

	}

	public void updateDorf(Dorf dorf) {
		getSession().update(dorf);

	}

	@SuppressWarnings("unchecked")
	public Dorf findById(int id) {
		// Criteria criteria = getSession().createCriteria(Dorf.class);
		// return criteria.add(Restrictions.like("id", id)).list();
		return (Dorf) getSession().get(Dorf.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Dorf> findAll() {
		Criteria criteria = getSession().createCriteria(Dorf.class);
		List<Dorf> list = criteria.list();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getClass() == Dorf.class) {

			} else {
				list.remove(i);
				i--;
			}
		}

		return list;
	}

}
