package TB2.NewStructure.common.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;

import TB2.NewStructure.common.hibernate.model.Dorf;
import TB2.NewStructure.common.hibernate.model.Provinz;

@Repository("provinzDao")
public class ProvinzDaoImpl extends AbstractDao implements ProvinzDao {

	public void saveProvinz(Provinz dorf) {
		persist(dorf);

	}

	@SuppressWarnings("unchecked")
	public List<Provinz> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Provinz.class);

		return criteria.add(Restrictions.like("name", name)).list();
	}

	public void updateProvinz(Provinz dorf) {
		getSession().update(dorf);

	}

	public Provinz findById(int id) {
		return (Provinz) getSession().get(Provinz.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Provinz> findAll() {
		Criteria criteria = getSession().createCriteria(Provinz.class);
		List<Provinz> list = criteria.list();
		return list;
	}

	public Provinz findByXandY(int x, int y) {
		Criteria criteria = getSession().createCriteria(Provinz.class);

		@SuppressWarnings("unchecked")
		List<Provinz> list = criteria.add(Restrictions.like("x", x)).add(Restrictions.like("y", y)).list();

		if (list.size() == 1) {
			return list.get(0);

		} else {
			return new Provinz(-1, -1, "Keine Provinz", new LocalDateTime());
		}
	}

}