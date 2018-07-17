package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Barbarendorf;
import TB2.NewStructure.common.hibernate.model.Dorf;

@Repository("babarendorfDao")
public class BarbarendorfDaoImpl extends AbstractDao implements BarbarendorfDao {

	public void saveDorf(Barbarendorf dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<Barbarendorf> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Barbarendorf.class);

		return criteria.add(Restrictions.like("name", name)).list();
	}

	public void updateDorf(Barbarendorf dorf) {
		getSession().update(dorf);

	}

	@SuppressWarnings("unchecked")
	public Barbarendorf findById(int id) {
		// Criteria criteria = getSession().createCriteria(Dorf.class);
		// return criteria.add(Restrictions.like("id", id)).list();
		return (Barbarendorf) getSession().get(Barbarendorf.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Barbarendorf> findAll() {
		Criteria criteria = getSession().createCriteria(Barbarendorf.class);
		List<Barbarendorf> list = criteria.list();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getClass() == Barbarendorf.class) {

			} else {
				list.remove(i);
				i--;
			}
		}

		return list;
	}

	public Barbarendorf findByXandY(int x, int y) {
		Criteria criteria = getSession().createCriteria(Barbarendorf.class);

		List<Barbarendorf> list = criteria.add(Restrictions.like("x", x)).add(Restrictions.like("y", y)).list();

		if (list.size() == 1) {
			return (Barbarendorf) list.get(0);

		}else {
			return new Barbarendorf(-1, -1, "Kein Dorfname", -1);
		}
	}

}